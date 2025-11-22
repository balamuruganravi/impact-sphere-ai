package com.citi.impactsphere.ai.model;

/**
 * Enumeration describing supported input types for requirements.
 */
public enum RequirementInputType {
    /** Requirements provided as a Jira issue key. */
    JIRA,
    /** Requirements fetched from a Confluence page. */
    CONFLUENCE,
    /** Requirements uploaded as a BRD file (PDF/DOCX). */
    BRD_FILE,
    /** Requirements provided as plain text in the request body. */
    RAW_TEXT
}