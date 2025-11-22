package com.citi.impactsphere.ai.service.impl;

import com.citi.impactsphere.ai.service.BrdProcessorService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BrdProcessorServiceImpl implements BrdProcessorService {

    private static final Pattern MODULE_PATTERN = Pattern.compile("module\\s*(a|b|c)", Pattern.CASE_INSENSITIVE);

    @Override
    public Mono<List<String>> extractImpactedModules(String requirementText) {
        String text = requirementText == null ? "" : requirementText;
        Matcher matcher = MODULE_PATTERN.matcher(text);
        Set<String> modules = new LinkedHashSet<>();
        while (matcher.find()) {
            String moduleSuffix = matcher.group(1).toUpperCase();
            modules.add("Module" + moduleSuffix);
        }
        return Mono.just(new ArrayList<>(modules));
    }
}
