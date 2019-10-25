package com.demo.hdt.searchcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryJson {
    @SerializedName("id")
    @Expose
    private String categoryId;
    @SerializedName("parent")
    @Expose
    private SubCategoryJson categoryRankParent;
    @SerializedName("note")
    @Expose
    private String categoryNote;
    @SerializedName("logo")
    @Expose
    private String categoryLogo;
    @SerializedName("position")
    @Expose
    private Integer categoryPosition;
    @SerializedName("group")
    @Expose
    private Integer categoryGroup;
    @SerializedName("isActive")
    @Expose
    private Boolean categoryIsActive;
    @SerializedName("name")
    @Expose
    private String categoryName;
    @SerializedName("createdBy")
    @Expose
    private SubCategoryJson categoryAccountCreated;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public SubCategoryJson getCategoryRankParent() {
        return categoryRankParent;
    }

    public void setCategoryRankParent(SubCategoryJson categoryRankParent) {
        this.categoryRankParent = categoryRankParent;
    }

    public String getCategoryNote() {
        return categoryNote;
    }

    public void setCategoryNote(String categoryNote) {
        this.categoryNote = categoryNote;
    }

    public String getCategoryLogo() {
        return categoryLogo;
    }

    public void setCategoryLogo(String categoryLogo) {
        this.categoryLogo = categoryLogo;
    }

    public Integer getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(Integer categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public Integer getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(Integer categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    public Boolean getCategoryIsActive() {
        return categoryIsActive;
    }

    public void setCategoryIsActive(Boolean categoryIsActive) {
        this.categoryIsActive = categoryIsActive;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public SubCategoryJson getCategoryAccountCreated() {
        return categoryAccountCreated;
    }

    public void setCategoryAccountCreated(SubCategoryJson categoryAccountCreated) {
        this.categoryAccountCreated = categoryAccountCreated;
    }

    public class SubCategoryJson {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
