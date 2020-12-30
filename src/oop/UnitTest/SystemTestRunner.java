package oop.UnitTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SystemTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SystemTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("TEST MAILSYSTEM OK? " + result.wasSuccessful());
    }

}
