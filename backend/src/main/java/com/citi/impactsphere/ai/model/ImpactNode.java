package com.citi.impactsphere.ai.model;

public class ImpactNode {
    private String moduleName;
    private double riskScore;
    private String cascadeReason;

    public ImpactNode() {
    }

    public ImpactNode(String moduleName, double riskScore, String cascadeReason) {
        this.moduleName = moduleName;
        this.riskScore = riskScore;
        this.cascadeReason = cascadeReason;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getCascadeReason() {
        return cascadeReason;
    }

    public void setCascadeReason(String cascadeReason) {
        this.cascadeReason = cascadeReason;
    }
}
