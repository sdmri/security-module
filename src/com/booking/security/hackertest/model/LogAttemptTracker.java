package com.booking.security.hackertest.model;

import com.booking.security.hackertest.exception.IncorrectEpochException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Keeps track of failed attempts
 */
public class LogAttemptTracker {

    // Minimum number of failed attempts to alert suspicious activity
    private final static int FIXED_SIZE = 5;

    // Sorted list of latest failed attempt epochs
    // This will grow to a max of FIXED_SIZE
    private List<Long> trackList = new ArrayList<>();

    /**
     * Adds a new epoch to the tracker.
     * If the number of epochs have grown to FIXED_SIZE,
     * return the oldest one
     *
     * @param attemptEpoch
     * @return
     * @throws IncorrectEpochException
     */
    public synchronized Long addNewAndReturnFirstFailedAttempt(Long attemptEpoch) throws IncorrectEpochException {
        if(attemptEpoch == 0 || attemptEpoch == null){
            throw new IncorrectEpochException("Invalid epoch passed to add to tracker");
        }
        // First entry in list
        if(trackList.size() == 0){
            trackList.add(attemptEpoch);
            return null;
        }

        trackList.add(attemptEpoch);

        // If we have more epochs than the limit,
        // return the oldest
        if(trackList.size() == FIXED_SIZE) {
            // Sort to get the oldest as the first
            Collections.sort(trackList);
            return trackList.remove(0);
        }
        return null;
    }

}
