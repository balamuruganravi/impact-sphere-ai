package com.citi.impactsphere.ai.service.impl;

import com.citi.impactsphere.ai.model.ImpactGraph;
import com.citi.impactsphere.ai.model.TestPlanEntry;
import com.citi.impactsphere.ai.service.TestPlannerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestPlannerServiceImpl implements TestPlannerService {
    @Override
    public Mono<List<TestPlanEntry>> createTestPlan(ImpactGraph impactGraph) {
        List<TestPlanEntry> entries = new ArrayList<>();
        if (impactGraph != null && impactGraph.getNodes() != null) {
            AtomicInteger counter = new AtomicInteger(1);
            impactGraph.getNodes().forEach(node -> {
                if (node.getRiskScore() > 0.5) {
                    String id = String.format("TC_%03d", counter.getAndIncrement());
                    entries.add(new TestPlanEntry(
                            id,
                            "Verify " + node.getModuleName() + " behaviour",
                            "P1",
                            "High",
                            node.getCascadeReason()
                    ));
                }
            });
        }
        return Mono.just(entries);
    }
}
