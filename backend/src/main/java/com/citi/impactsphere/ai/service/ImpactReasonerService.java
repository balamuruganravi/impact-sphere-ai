package com.citi.impactsphere.ai.service;

import com.citi.impactsphere.ai.model.ImpactGraph;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ImpactReasonerService {
    Mono<ImpactGraph> analyseImpact(List<String> impactedModules, List<String> allModules);
}
