
package com.jithin.test.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "default",
    "medium",
    "high",
    "standard",
    "maxres"
})
public class Thumbnails {

    @JsonProperty("default")
    private Thumbnail _default;
    @JsonProperty("medium")
    private Thumbnail medium;
    @JsonProperty("high")
    private Thumbnail high;
    @JsonProperty("standard")
    private Thumbnail standard;
    @JsonProperty("maxres")
    private Thumbnail maxres;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("default")
    public Thumbnail getDefault() {
        return _default;
    }

    @JsonProperty("default")
    public void setDefault(Thumbnail _default) {
        this._default = _default;
    }

    @JsonProperty("medium")
    public Thumbnail getMedium() {
        return medium;
    }

    @JsonProperty("medium")
    public void setMedium(Thumbnail medium) {
        this.medium = medium;
    }

    @JsonProperty("high")
    public Thumbnail getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Thumbnail high) {
        this.high = high;
    }

    @JsonProperty("standard")
    public Thumbnail getStandard() {
        return standard;
    }

    @JsonProperty("standard")
    public void setStandard(Thumbnail standard) {
        this.standard = standard;
    }

    @JsonProperty("maxres")
    public Thumbnail getMaxres() {
        return maxres;
    }

    @JsonProperty("maxres")
    public void setMaxres(Thumbnail maxres) {
        this.maxres = maxres;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
