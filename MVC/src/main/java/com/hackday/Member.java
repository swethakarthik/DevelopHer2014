package com.hackday;

/**
 * Created by ywei on 11/14/14.
 */
public class Member {
    String     id;
    String     firstName;
    String     lastName;
    String     industry;
    ProfileUrl siteStandardProfileRequest;
    Skills     skills;
    //Education  educations;
    Position   positions;
    String     imageUrl;
    String      profileUrl;
    String     email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public ProfileUrl getSiteStandardProfileRequest() {
        return siteStandardProfileRequest;
    }

    public void setSiteStandardProfileRequest(ProfileUrl siteStandardProfileRequest) {
        this.siteStandardProfileRequest = siteStandardProfileRequest;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Position getPositions() {
        return positions;
    }

    public void setPositions(Position positions) {
        this.positions = positions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
