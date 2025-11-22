# ImpactSphere AI – Completion & Fixes Prompt

## Context
You are extending an existing Spring Boot 3 / Java 17 project (`impact-sphere-ai`) designed for a GenAI-powered pre-impact and post-impact analysis tool.  The repository currently has skeletons for:
- A Spring Boot launcher (`ImpactSphereAiApplication`).
- A REST controller (`AnalysisController`) that fetches requirement text from Jira/Confluence/BRD/raw text.
- Placeholder service interfaces and classes.

However, the critical services and domain models are incomplete or missing, and the application does not yet build or run end-to-end.  Your job is to finish these implementations, fix compilation issues, and add simple unit tests so that the pre-impact analysis endpoint returns meaningful data.

## Completion Tasks
Implement or update the following components under `backend/src/main/java/com/citi/impactsphere/ai` unless otherwise specified:

1. **Domain Models**  
   Create the following model classes in the `model` package (if they don’t already exist):  
   - `ImpactNode` with fields: `moduleName` (String), `riskScore` (double), and `cascadeReason` (String).  
   - `ImpactGraph` with one field: `List<ImpactNode> nodes`.  
   - `TestPlanEntry` with fields: `testCaseId`, `testName`, `priority`, `riskLevel`, `reason`.  
   - `AnalysisResult` with fields: `ImpactGraph impactGraph` and `List<TestPlanEntry> testPlan`.  
   All classes should have public getters, setters, and at least one no-args and one all-args constructor.

2. **BRD Processor**  
   In `service` package, create the interface `BrdProcessorService` with a method:  
   ```java
   Mono<List<String>> extractImpactedModules(String requirementText);
   ```  
   In `service/impl`, implement `BrdProcessorServiceImpl` that uses **simple keyword matching** on the requirement text to detect mentions of “Module A”, “Module B”, and “Module C” (case‑insensitive) and returns a list of normalized module names (`ModuleA`, `ModuleB`, etc.).  This stub demonstrates the flow while avoiding external dependencies; it should never return null.

3. **Repository Indexer**  
   Create `RepoIndexerService` interface with:  
   ```java
   Mono<List<String>> listModules(String repoUrl);
   ```  
   Implement `RepoIndexerServiceImpl` to return a fixed list of modules `["ModuleA", "ModuleB", "ModuleC"]` for now.  In a future version this class would parse the codebase (via JavaParser) but for the MVP stub simply illustrate how the pipeline works.

4. **Impact Reasoner**  
   Create `ImpactReasonerService` interface with:  
   ```java
   Mono<ImpactGraph> analyseImpact(List<String> impactedModules, List<String> allModules);
   ```  
   Implement `ImpactReasonerServiceImpl` in `service/impl`: assign a `riskScore` of `0.9` to each module listed in `impactedModules` and `0.1` to any other module in `allModules`; set `cascadeReason` to “Directly impacted by requirement” or “No direct impact” respectively.

5. **Test Planner**  
   Create `TestPlannerService` interface with:  
   ```java
   Mono<List<TestPlanEntry>> createTestPlan(ImpactGraph impactGraph);
   ```  
   Implement `TestPlannerServiceImpl`: iterate over `impactGraph.getNodes()`, and for each node with `riskScore > 0.5` create a `TestPlanEntry` whose ID is `TC_001`, `TC_002`, etc., name is `"Verify " + node.getModuleName() + " behaviour"`, priority is `P1`, riskLevel is `High`, and reason comes from `cascadeReason`.  Return all generated entries.

6. **Analysis Controller Updates**  
   - Inject the new services (`BrdProcessorService`, `RepoIndexerService`, `ImpactReasonerService`, `TestPlannerService`) via constructor injection.
   - Update `/api/analyze/pre` to:
     1. Fetch the requirement text from Jira, Confluence, BRD file (stubbed as raw text), or rawText.
     2. Call `BrdProcessorService.extractImpactedModules(text)`.
     3. Call `RepoIndexerService.listModules(request.getRepoUrl())`.
     4. Call `ImpactReasonerService.analyseImpact(impactedModules, allModules)`.
     5. Call `TestPlannerService.createTestPlan(impactGraph)`.
     6. Wrap `ImpactGraph` and test plan into `AnalysisResult`, and return it as the response body.
   - Ensure the method signature is:
     ```java
     @PostMapping("/analyze/pre")
     public Mono<ResponseEntity<AnalysisResult>> analyzePre(@RequestBody AnalyzePreRequest request)
     ```
     so that Spring can serialize the response to JSON.

7. **Unit Tests**  
   Under `src/test/java/com/citi/impactsphere/ai`:
   - Write `BrdProcessorServiceImplTest` with a test `extractImpactedModulesDetectsModules()` that calls the service with input `"Changes affect Module A and module c and MODULE B"` and asserts that `ModuleA`, `ModuleB`, and `ModuleC` appear in the result.
   - Write `AnalysisControllerTest` using `@SpringBootTest` and `@AutoConfigureWebTestClient`:
     1. Create an `AnalyzePreRequest` with `inputType = RequirementInputType.RAW_TEXT`, `rawText = "Changes in Module A and Module C"`, and any non-null `repoUrl`.
     2. Send POST to `/api/analyze/pre` using `WebTestClient`.
     3. Assert HTTP 200 and that `$.impactGraph.nodes.length()` is 3 and `$.testPlan.length()` > 0.

8. **Dependencies**  
   - Ensure the POM has `spring-boot-starter-webflux` (for WebClient and reactive controllers) and `spring-boot-starter-test` (for JUnit5 and WebTestClient).  These are already present, but confirm or add them if missing.
   - No other external libraries are required for the MVP.

## Expected End State
After you implement the above:
- `mvn clean test` should succeed without compilation errors.
- The endpoint `/api/analyze/pre` returns a JSON body with an `impactGraph` showing risk scores and a `testPlan` with at least one high-priority test case whenever the requirement text references any of ModuleA/B/C.
- Simple unit tests pass, demonstrating the services and pipeline work correctly.

Use this prompt as guidance to generate or complete all the necessary code.  Follow the existing package structure (`com.citi.impactsphere.ai`) and naming conventions.  Do not introduce unused dependencies or break existing functionality.
