package com.citi.impactsphere.ai.service.impl;

import com.citi.impactsphere.ai.model.ImpactGraph;
import com.citi.impactsphere.ai.model.ImpactNode;
import com.citi.impactsphere.ai.service.ImpactReasonerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpactReasonerServiceImpl implements ImpactReasonerService {
    @Override
    public Mono<ImpactGraph> analyseImpact(List<String> impactedModules, List<String> allModules) {
        List<String> impacted = impactedModules != null ? impactedModules : List.of();
        List<ImpactNode> nodes = new ArrayList<>();
        if (allModules != null) {
            for (String module : allModules) {
                boolean isImpacted = impacted.stream().anyMatch(m -> m.equalsIgnoreCase(module));
                double riskScore = isImpacted ? 0.9 : 0.1;
                String reason = isImpacted ? "Directly impacted by requirement" : "No direct impact";
                nodes.add(new ImpactNode(module, riskScore, reason));
            }
        }
        return Mono.just(new ImpactGraph(nodes));
    }
}
