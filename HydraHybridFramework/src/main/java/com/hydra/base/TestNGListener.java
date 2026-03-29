package com.hydra.base;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TestNG Listener - Handles test execution events
 */
public class TestNGListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestNGListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("====================================");
        logger.info("Test Suite Started: " + context.getSuite().getName());
        logger.info("Total test count: " + context.getAllTestMethods().length);
        logger.info("====================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("====================================");
        logger.info("Test Suite Finished: " + context.getSuite().getName());
        logger.info("Passed tests: " + context.getPassedTests().size());
        logger.info("Failed tests: " + context.getFailedTests().size());
        logger.info("Skipped tests: " + context.getSkippedTests().size());
        logger.info("====================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("================================");
        logger.info("Test Started: " + result.getMethod().getMethodName());
        logger.info("Description: " + result.getMethod().getDescription());
        logger.info("================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("");
        logger.info("✓ TEST PASSED: " + result.getMethod().getMethodName());
        logger.info("Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
        logger.info("");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("");
        logger.error("✗ TEST FAILED: " + result.getMethod().getMethodName());
        logger.error("Error Message: " + result.getThrowable().getMessage());
        logger.error("Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
        logger.error("");

        // Print stack trace
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("");
        logger.warn("⊘ TEST SKIPPED: " + result.getMethod().getMethodName());
        logger.warn("Skip Message: " + result.getThrowable().getMessage());
        logger.warn("");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("Test failed within success percentage: " + result.getMethod().getMethodName());
    }
}
