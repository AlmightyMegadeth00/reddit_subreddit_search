
package com.example.reddit.davekessler_androidcodechallenge.models;

import java.util.HashMap;
import java.util.Map;

public class Child {

    private String kind;
    private SearchResultPost data;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SearchResultPost getData() {
        return data;
    }

    public void setData(SearchResultPost data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
