package com.citi.impactsphere.ai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BrdProcessorServiceImplTest {

    @Autowired
    private BrdProcessorService brdProcessor;

    @Test
    void extractImpactedModulesDetectsModules() {
        String text = "Changes affect Module A and module c and MODULE B";
        List<String> modules = brdProcessor.extractImpactedModules(text).block();
        assertNotNull(modules);
        assertTrue(modules.contains("ModuleA"), "Should detect ModuleA");
        assertTrue(modules.contains("ModuleB"), "Should detect ModuleB");
        assertTrue(modules.contains("ModuleC"), "Should detect ModuleC");
    }
}
