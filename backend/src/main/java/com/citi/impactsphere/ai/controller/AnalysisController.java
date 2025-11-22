package com.citi.impactsphere.ai.controller;

import com.citi.impactsphere.ai.jira.JiraRequirementFetcher;
import com.citi.impactsphere.ai.confluence.ConfluencePageFetcher;
import com.citi.impactsphere.ai.model.RequirementInputType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * AnalysisController exposes REST endpoints for performing pre-impact analysis.
 * It orchestrates fetching requirement text from various sources (Jira, Confluence,
 * file upload, or raw text) and delegates to downstream services (not yet implemented).
 */
@RestController
@RequestMapping("/api")
public class AnalysisController {

    private final JiraRequirementFetcher jiraFetcher;
    private final ConfluencePageFetcher confluenceFetcher;
    // TODO: Inject BrdProcessorService, RepoIndexerService, ImpactReasonerService

    public AnalysisController(JiraRequirementFetcher jiraFetcher,
                              ConfluencePageFetcher confluenceFetcher) {
        this.jiraFetcher = jiraFetcher;
        this.confluenceFetcher = confluenceFetcher;
    }

    /**
     * Performs pre-impact analysis by interpreting the requirement from the provided input
     * and delegating analysis to downstream services.
     *
     * @param request the pre-analysis request containing the input type and associated fields
     * @return a Mono emitting the analysis result (currently placeholder)
     */
    @PostMapping("/analyze/pre")
    public Mono<ResponseEntity<String>> analyzePre(@RequestBody AnalyzePreRequest request) {
        RequirementInputType type = request.getInputType();
        Mono<String> requirementSource;
        switch (type) {
            case JIRA:
                requirementSource = jiraFetcher.fetchRequirement(request.getJiraKey());
                break;
            case CONFLUENCE:
                requirementSource = confluenceFetcher.fetchPage(request.getConfluencePageId());
                break;
            case BRD_FILE:
                // TODO: decode base64 and extract text using Apache Tika
                requirementSource = Mono.just(request.getBrdFileBase64());
                break;
            case RAW_TEXT:
            default:
                requirementSource = Mono.just(request.getRawText());
                break;
        }

        // TODO: Pass the requirementSource to BRD processor, repo indexer, impact reasoner and build impact graph.
        // For now we return the requirement text as a simple echo response.
        return requirementSource.map(text -> ResponseEntity.ok("Received requirement text: " + text));
    }
}