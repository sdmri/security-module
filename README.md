# security-module

Identifies potential hacks by detecting frequent failed login attempts.

## SimpleHackerDetector.java 

Implements the functionality to detect frequent failed attempts by consuming each log line passed to it and returning an IP that it detects as that of a hacker. It also takes care of logs that may come out of order.

## Tryouts.Java

A simple main method to send out of order logs and print results of the API for each log consumed.

