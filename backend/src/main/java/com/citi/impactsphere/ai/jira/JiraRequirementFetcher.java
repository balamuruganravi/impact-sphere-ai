package com.citi.impactsphere.ai.jira;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * JiraRequirementFetcher is responsible for retrieving requirement text from
 * Jira stories or epics. It uses the Jira REST API to fetch issue details
 * and extracts relevant fields such as summary, description and acceptance criteria.
 * <p>
 * This service expects a personal access token configured via application.yml.
 * For the hackathon MVP it returns a plain string of concatenated requirement content.
 */
@Service
public class JiraRequirementFetcher {

    private final WebClient webClient;
    private final String jiraUrl;

    public JiraRequirementFetcher(
            @Value("${jira.url}") String jiraUrl,
            @Value("${jira.token}") String jiraToken) {
        this.jiraUrl = jiraUrl;
        this.webClient = WebClient.builder()
                .baseUrl(jiraUrl)
                .defaultHeader("Authorization", "Bearer " + jiraToken)
                .build();
    }

    /**
     * Fetches the requirement text for a given Jira issue key.
     *
     * @param issueKey The Jira issue key (e.g., CM-1234).
     * @return A Mono emitting plain text representing the requirement.
     */
    public Mono<String> fetchRequirement(String issueKey) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("rest", "api", "3", "issue", issueKey)
                        .queryParam("expand", "renderedFields")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    // TODO: parse JSON response to extract summary, description, acceptance criteria.
                    // For now return the raw response as a placeholder.
                    return response;
                });
    }
}