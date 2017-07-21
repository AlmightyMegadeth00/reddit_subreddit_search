
package com.example.reddit.davekessler_androidcodechallenge.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private String modhash;
    private List<Child> children = null;
    private String after;
    private Object before;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
