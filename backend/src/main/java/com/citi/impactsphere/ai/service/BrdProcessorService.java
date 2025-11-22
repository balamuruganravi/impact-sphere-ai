package com.citi.impactsphere.ai.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface BrdProcessorService {
    Mono<List<String>> extractImpactedModules(String requirementText);
}
