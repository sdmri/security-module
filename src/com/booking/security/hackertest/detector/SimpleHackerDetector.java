package com.booking.security.hackertest.detector;

import com.booking.security.hackertest.exception.IncorrectEpochException;
import com.booking.security.hackertest.exception.IncorrectLogLineException;
import com.booking.security.hackertest.model.LogAttemptTracker;
import com.booking.security.hackertest.model.LogEntry;
import com.booking.security.hackertest.model.LoginStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple implementation of HackerDetector that takes into account
 * that logs might arrive out of order (timestamps) but will
 * have SLAs for delays. Any log event arriving beyond the current
 * 5 minute window will therefore not cause an alert
 */
public class SimpleHackerDetector implements HackerDetector{

    private static final Long ALERT_INTERVAL = 5 * 60 * 1000l;

    // Stores a mapping of IPs vs their latest 5 FailedAttempt Epochs
    // In a production environment, there should be a scheduled task
    // that should clean this map off old ips that have not had an incoming
    // log entry for a threshold duration
    private Map<String, LogAttemptTracker> ipTrackerMap = new ConcurrentHashMap<>();

    @Override
    /**
     * Processes each log and only takes action on it if the status is 'FAILURE'
     * Throws exception for incorrect log entries
     */
    public String parseLogLine(String line) throws IncorrectLogLineException, IncorrectEpochException {
        LogEntry logEntry = LogEntry.processLogEntry(line);
        // Do nothing for a successful login
        if (LoginStatus.SUCCESS == logEntry.getAction()) return "";
        String ip = logEntry.getIp();
        LogAttemptTracker logTracker = ipTrackerMap.get(ip);
        if(logTracker == null){
            logTracker = new LogAttemptTracker();
            // Another thread might have added an entry into the map
            // We do not want to overwrite and lose its log updates
            LogAttemptTracker existingLogTracker = ipTrackerMap.putIfAbsent(ip,logTracker);
            if(existingLogTracker != null){
                logTracker = existingLogTracker;
            }
        }
        Long firstSeenEpoch = logTracker.addNewAndReturnFirstFailedAttempt(logEntry.getDate());
        // The first seen epoch should be recent enough to alert
        if(firstSeenEpoch != null && isEpochRecent(firstSeenEpoch)){
            return ip;
        }
        return "";
    }

    /**
     * Internal method to validate that an epoch lies
     * within a pre-defined alert interval
     * @param epoch
     * @return
     */
    private boolean isEpochRecent(Long epoch){
        return System.currentTimeMillis() - epoch <= ALERT_INTERVAL;
    }
}
