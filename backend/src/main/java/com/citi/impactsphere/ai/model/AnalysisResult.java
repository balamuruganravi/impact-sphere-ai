package com.citi.impactsphere.ai.model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResult {
    private ImpactGraph impactGraph;
    private List<TestPlanEntry> testPlan;

    public AnalysisResult() {
        this.impactGraph = new ImpactGraph();
        this.testPlan = new ArrayList<>();
    }

    public AnalysisResult(ImpactGraph impactGraph, List<TestPlanEntry> testPlan) {
        this.impactGraph = impactGraph;
        this.testPlan = testPlan != null ? testPlan : new ArrayList<>();
    }

    public ImpactGraph getImpactGraph() {
        return impactGraph;
    }

    public void setImpactGraph(ImpactGraph impactGraph) {
        this.impactGraph = impactGraph;
    }

    public List<TestPlanEntry> getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(List<TestPlanEntry> testPlan) {
        this.testPlan = testPlan;
    }
}
