package com.citi.impactsphere.ai.service;

import com.citi.impactsphere.ai.model.ImpactGraph;
import com.citi.impactsphere.ai.model.TestPlanEntry;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TestPlannerService {
    Mono<List<TestPlanEntry>> createTestPlan(ImpactGraph impactGraph);
}
