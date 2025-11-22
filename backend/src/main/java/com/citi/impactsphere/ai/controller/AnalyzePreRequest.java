package com.citi.impactsphere.ai.controller;

import com.citi.impactsphere.ai.model.RequirementInputType;

/**
 * AnalyzePreRequest represents the request body for the pre-impact analysis endpoint.
 * It provides the input type and any parameters required for fetching requirement text.
 */
public class AnalyzePreRequest {

    private RequirementInputType inputType;
    private String jiraKey;
    private String confluencePageId;
    private String brdFileBase64;
    private String rawText;
    private String repoUrl;

    public RequirementInputType getInputType() {
        return inputType;
    }

    public void setInputType(RequirementInputType inputType) {
        this.inputType = inputType;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
    }

    public String getConfluencePageId() {
        return confluencePageId;
    }

    public void setConfluencePageId(String confluencePageId) {
        this.confluencePageId = confluencePageId;
    }

    public String getBrdFileBase64() {
        return brdFileBase64;
    }

    public void setBrdFileBase64(String brdFileBase64) {
        this.brdFileBase64 = brdFileBase64;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }
}