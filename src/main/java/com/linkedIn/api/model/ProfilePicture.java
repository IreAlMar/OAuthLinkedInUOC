package com.linkedIn.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfilePicture {

    @JsonProperty(value = "displayImage")
    public String displayImage;

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    @Override
    public String toString() {
        return "ProfilePicture{" +
                "displayImage='" + displayImage + '\'' +
                '}';
    }
}
