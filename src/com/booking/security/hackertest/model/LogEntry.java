package com.booking.security.hackertest.model;


import com.booking.security.hackertest.exception.IncorrectLogLineException;

/*
 Model that represents all the entries in a single log line
 */
public class LogEntry {

    private final static String SEPARATOR = ",";

    private Long date;
    private String ip;
    private String username;
    private LoginStatus action;

    private LogEntry(Long date, String ip, String username, LoginStatus action) {
        this.date = date;
        this.ip = ip;
        this.username = username;
        this.action = action;
    }

    /**
     * Parses a single line of log with entries delimited by SEPARATOR
     * Throws exception if any of the entry is missing/empty or incomplete
     *
     * @param logLine
     * @return
     * @throws IncorrectLogLineException
     */
    public static LogEntry processLogEntry(String logLine) throws IncorrectLogLineException{
        String[] logEntries = logLine.split(SEPARATOR);
        if(logEntries.length < 4){
            throw new IncorrectLogLineException("Incomplete Login information");
        }
        Long logEpoch;
        try {
            logEpoch = Long.valueOf(logEntries[0]);
        }catch(Exception e){
            throw new IncorrectLogLineException("Incorrect Login epoch");
        }
        String logIp = logEntries[1];
        String statusString = logEntries[3];
        if(statusString == null ) {
            throw new IncorrectLogLineException("Missing Login status");
        }
        LoginStatus logStatus;
        if("SUCCESS".equals(statusString)) {
            logStatus = LoginStatus.SUCCESS;
        }else if("FAILURE".equals(statusString)) {
            logStatus = LoginStatus.FAILURE;
        }else{
            throw new IncorrectLogLineException("Invalid Login status");
        }
        LogEntry logEntry = new LogEntry(logEpoch, logIp, logEntries[2], logStatus);
        return logEntry;
    }

    public Long getDate() {
        return date;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public LoginStatus getAction() {
        return action;
    }
}
