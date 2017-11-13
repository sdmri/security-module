package com.booking.security.hackertest.detector;

import com.booking.security.hackertest.exception.IncorrectEpochException;
import com.booking.security.hackertest.exception.IncorrectLogLineException;

public interface HackerDetector {
    String parseLogLine(String line) throws IncorrectLogLineException, IncorrectEpochException;
}
