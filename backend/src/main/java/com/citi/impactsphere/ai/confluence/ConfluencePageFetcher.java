package com.citi.impactsphere.ai.confluence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ConfluencePageFetcher retrieves requirement text from Confluence pages.
 * It leverages the Confluence REST API to fetch page content in storage format,
 * then extracts and cleans the HTML to plain text. The service reads the base URL
 * and access token from application.yml.
 */
@Service
public class ConfluencePageFetcher {

    private final WebClient webClient;

    public ConfluencePageFetcher(
            @Value("${confluence.url}") String baseUrl,
            @Value("${confluence.token}") String token) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    /**
     * Fetches the plain text of a Confluence page given its page ID.
     *
     * @param pageId The Confluence page ID.
     * @return A Mono emitting plain text representing the page content.
     */
    public Mono<String> fetchPage(String pageId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("wiki", "rest", "api", "content", pageId)
                        .queryParam("expand", "body.storage")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    // TODO: parse JSON response, extract body.storage.value (HTML) and strip tags.
                    // For now return the raw response as a placeholder.
                    return response;
                });
    }
}