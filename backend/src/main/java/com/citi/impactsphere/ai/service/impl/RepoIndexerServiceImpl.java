package com.citi.impactsphere.ai.service.impl;

import com.citi.impactsphere.ai.service.RepoIndexerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RepoIndexerServiceImpl implements RepoIndexerService {
    @Override
    public Mono<List<String>> listModules(String repoUrl) {
        return Mono.just(List.of("ModuleA", "ModuleB", "ModuleC"));
    }
}
