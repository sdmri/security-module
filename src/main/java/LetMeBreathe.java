package main.java;

import com.booking.security.hackertest.exception.IncorrectEpochException;
import com.booking.security.hackertest.model.LogAttemptTracker;

public class LetMeBreathe {

    public static void main(String args[]){

        LogAttemptTracker tr = new LogAttemptTracker();

        try {
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(1l));
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(2l));
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(3l));
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(4l));
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(5l));
            System.out.println(tr.addNewAndReturnFirstFailedAttempt(6l));
        } catch (IncorrectEpochException e) {
            e.printStackTrace();
        }

    }
}
