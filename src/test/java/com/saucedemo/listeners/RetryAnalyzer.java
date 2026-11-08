package com.saucedemo.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retriesUsed = 0;
    private static final int maxRetries = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retriesUsed < maxRetries) {
            retriesUsed++;
            System.out.println("Retry attempt " + (retriesUsed) + " for " + result.getName());
            return true;
        }
        return false;
    }
}
