# Codex Context – ImpactSphere AI (Java Edition)

## Goal
Create an intelligent, AI-driven tool that performs **pre-coding impact analysis** using Business Requirement Documents (BRD), Jira stories, Confluence pages and repository context.

## Key Features
1. Parse BRD text using Apache Tika and Vertex AI LLM.
2. Fetch requirements from Jira using `JiraRequirementFetcher`.
3. Fetch requirements from Confluence using `ConfluencePageFetcher`.
4. Build dependency and dataflow graph using JavaParser and Neo4j.
5. Apply Drools rules to predict cascading impacts (A→B→C).
6. Generate Smart Test Plan with regression prioritization.
7. Provide REST APIs via Spring Boot.

## Code Guidelines
- Package: `com.citi.impactsphere.ai`
- Framework: Spring Boot 3.x using WebFlux
- Rule Engine: Drools (see `src/main/resources/rules`)
- Config via `application.yml` (no hardcoded secrets)
- Use services and controllers appropriately.
- All model classes are placed under `com.citi.impactsphere.ai.model`.
- Use Reactor `Mono` and `Flux` for asynchronous flows.
- Include Swagger (`springdoc-openapi`) annotations.
- Unit tests with JUnit5.
- Follow Sonar and OWASP clean code guidelines.

## Primary APIs
- `POST /api/analyze/pre` → run BRD + repo analysis, return impacted graph.
- `POST /api/analyze/recommend` → additional change suggestions (to be implemented).
- `POST /api/tests/plan` → smart regression test plan (to be implemented).
- `POST /api/analyze/post` → verify post-coding impacts (optional).

## Desired Output
- JSON Impact Graph
- Markdown Test Plan
- CSV/PDF export ready data

## Tools/Integrations
- Neo4j (graph DB)
- Vertex AI Java SDK
- Apache Tika for BRD ingestion
- Drools for cascade logic
- Log4j2 structured logging
- Jira and Confluence REST clients

## Style
- Clear modular services
- Descriptive method names
- No static utility abuse
- Use Reactor types (`Mono`/`Flux`) consistently