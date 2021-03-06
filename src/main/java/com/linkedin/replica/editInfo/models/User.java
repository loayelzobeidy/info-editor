package com.linkedin.replica.editInfo.models;

import java.util.ArrayList;

public  class User {
   public String userId;
   public String firstName;
   public String lastName;
   public String headline;
   public String industry;
   public ArrayList<String> positions;
   public ArrayList<String> educations;
   public String imageUrl;
   public String cvUrl;
   public ArrayList<String> skills;
   public ArrayList<String> friendsList;
   public ArrayList<String> bookmarkedPosts;



    public User(String userId, String firstName, String lastName, String headline, String industry) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headline = headline;
        this.industry = industry;
        this.positions = positions;
        this.educations = educations;
        this.imageUrl = imageUrl;
        this.cvUrl = cvUrl;
        this.skills = skills;
        this.friendsList = friendsList;
        this.bookmarkedPosts = bookmarkedPosts;
    }

    public User() {};



    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getIndustry() {
        return industry;
    }


    public ArrayList<String> getPositions() {
        return positions;
    }

    public ArrayList<String> getEducations() {
        return educations;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public ArrayList<String> getBookmarkedPosts() {
        return bookmarkedPosts;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }





    public void setPositions(ArrayList<String> positions) {
        this.positions = positions;
    }

    public void setEducations(ArrayList<String> educations) {
        this.educations = educations;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public void setBookmarkedPosts(ArrayList<String> bookmarkedPosts) {
        this.bookmarkedPosts = bookmarkedPosts;
    }
}
