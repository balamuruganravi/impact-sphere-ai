package com.citi.impactsphere.ai.controller;

import com.citi.impactsphere.ai.confluence.ConfluencePageFetcher;
import com.citi.impactsphere.ai.jira.JiraRequirementFetcher;
import com.citi.impactsphere.ai.model.AnalysisResult;
import com.citi.impactsphere.ai.model.RequirementInputType;
import com.citi.impactsphere.ai.service.BrdProcessorService;
import com.citi.impactsphere.ai.service.ImpactReasonerService;
import com.citi.impactsphere.ai.service.RepoIndexerService;
import com.citi.impactsphere.ai.service.TestPlannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AnalysisController {

    private final JiraRequirementFetcher jiraFetcher;
    private final ConfluencePageFetcher confluenceFetcher;
    private final BrdProcessorService brdProcessor;
    private final RepoIndexerService repoIndexer;
    private final ImpactReasonerService impactReasoner;
    private final TestPlannerService testPlanner;

    public AnalysisController(JiraRequirementFetcher jiraFetcher,
                              ConfluencePageFetcher confluenceFetcher,
                              BrdProcessorService brdProcessor,
                              RepoIndexerService repoIndexer,
                              ImpactReasonerService impactReasoner,
                              TestPlannerService testPlanner) {
        this.jiraFetcher = jiraFetcher;
        this.confluenceFetcher = confluenceFetcher;
        this.brdProcessor = brdProcessor;
        this.repoIndexer = repoIndexer;
        this.impactReasoner = impactReasoner;
        this.testPlanner = testPlanner;
    }

    @PostMapping("/analyze/pre")
    public Mono<ResponseEntity<AnalysisResult>> analyzePre(@RequestBody AnalyzePreRequest request) {
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
                requirementSource = Mono.just(request.getBrdFileBase64());
                break;
            case RAW_TEXT:
            default:
                requirementSource = Mono.just(request.getRawText());
                break;
        }

        return requirementSource
                .flatMap(text -> brdProcessor.extractImpactedModules(text)
                        .flatMap(impacted -> repoIndexer.listModules(request.getRepoUrl())
                                .flatMap(allModules -> impactReasoner.analyseImpact(impacted, allModules)
                                        .flatMap(graph -> testPlanner.createTestPlan(graph)
                                                .map(testPlan -> new AnalysisResult(graph, testPlan)))))))
                .map(ResponseEntity::ok);
    }
}
