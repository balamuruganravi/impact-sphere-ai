package com.citi.impactsphere.ai.model;

import java.util.ArrayList;
import java.util.List;

public class ImpactGraph {
    private List<ImpactNode> nodes;

    public ImpactGraph() {
        this.nodes = new ArrayList<>();
    }

    public ImpactGraph(List<ImpactNode> nodes) {
        this.nodes = nodes != null ? nodes : new ArrayList<>();
    }

    public List<ImpactNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ImpactNode> nodes) {
        this.nodes = nodes != null ? nodes : new ArrayList<>();
    }
}
