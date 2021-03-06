
package com.example.reddit.davekessler_androidcodechallenge.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Preview {

    private List<Image> images = null;
    private Boolean enabled;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
