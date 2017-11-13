package main.java;

import com.booking.security.hackertest.detector.HackerDetector;
import com.booking.security.hackertest.detector.SimpleHackerDetector;
import com.booking.security.hackertest.exception.IncorrectEpochException;
import com.booking.security.hackertest.model.LogAttemptTracker;

public class Tryouts {

    public static void main(String args[]){

        Long currentEpoch = System.currentTimeMillis();
        HackerDetector hd = new SimpleHackerDetector();
        try {

            System.out.println("1 " + hd.parseLogLine((currentEpoch - 5 * 60 * 1000 + 6000) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("2 " + hd.parseLogLine((currentEpoch - 1 * 60 * 1000 + 717) +",187.218.83.136,John.Smith,FAILURE"));
            System.out.println("3 " + hd.parseLogLine((currentEpoch - 2 * 60 * 1000) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("4 " + hd.parseLogLine((currentEpoch - 3 * 60 * 1000 + 901) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("5 " + hd.parseLogLine((currentEpoch - 2 * 60 * 1000 - 551) +",187.218.83.136,John.Smith,FAILURE"));
            System.out.println("6 " + hd.parseLogLine((currentEpoch - 2 * 60 * 1000 + 891) +",188.218.83.136,John.Smith,SUCCESS"));
            System.out.println("7 " + hd.parseLogLine((currentEpoch - 3 * 60 * 1000 +  500) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("8 " + hd.parseLogLine((currentEpoch - 4 * 60 * 1000 - 661) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("9 " + hd.parseLogLine((currentEpoch - 7 * 60 * 1000 - 661) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("10 " + hd.parseLogLine((currentEpoch - 2 * 60 * 1000 - 661) +",188.218.83.136,John.Smith,FAILURE"));
            System.out.println("11 " + hd.parseLogLine((currentEpoch - 3 * 60 * 1000 + 1098) +",187.218.83.136,John.Smith,FAILURE"));
            System.out.println("12 " + hd.parseLogLine((currentEpoch - 3 * 60 * 1000 + 1098) +",187.218.83.136,John.Smith,FAILURE"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
