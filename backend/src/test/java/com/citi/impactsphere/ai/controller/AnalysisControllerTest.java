package com.citi.impactsphere.ai.controller;

import com.citi.impactsphere.ai.model.RequirementInputType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class AnalysisControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void analyzePreReturnsAnalysisResult() {
        AnalyzePreRequest request = new AnalyzePreRequest();
        request.setInputType(RequirementInputType.RAW_TEXT);
        request.setRawText("Changes in Module A and Module C");
        request.setRepoUrl("dummy-repo");

        webTestClient.post()
                .uri("/api/analyze/pre")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.impactGraph.nodes.length()").isEqualTo(3)
                .jsonPath("$.testPlan.length()").isGreaterThan(0);
    }
}
