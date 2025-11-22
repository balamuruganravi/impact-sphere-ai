package com.citi.impactsphere.ai.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface RepoIndexerService {
    Mono<List<String>> listModules(String repoUrl);
}
