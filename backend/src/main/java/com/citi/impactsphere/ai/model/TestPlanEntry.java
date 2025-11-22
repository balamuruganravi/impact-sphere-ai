package com.citi.impactsphere.ai.model;

public class TestPlanEntry {
    private String testCaseId;
    private String testName;
    private String priority;
    private String riskLevel;
    private String reason;

    public TestPlanEntry() {
    }

    public TestPlanEntry(String testCaseId, String testName, String priority, String riskLevel, String reason) {
        this.testCaseId = testCaseId;
        this.testName = testName;
        this.priority = priority;
        this.riskLevel = riskLevel;
        this.reason = reason;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
