package com.linkedIn.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedInLiteProfilePojo {
    @JsonProperty(value = "id")
    public String id;

    @JsonProperty(value = "localizedFirstName")
    public String localizedFirstName;

    @JsonProperty(value = "localizedLastName")
    public String firstName;

    @JsonProperty(value = "profilePicture")
    public ProfilePicture profilePicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalizedFirstName() {
        return localizedFirstName;
    }

    public void setLocalizedFirstName(String localizedFirstName) {
        this.localizedFirstName = localizedFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "LinkedInLiteProfilePojo{" +
                "id='" + id + '\'' +
                ", localizedFirstName='" + localizedFirstName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
