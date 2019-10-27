package com.demo.hdt.searchcategory;

public class ItemCategory {
    private String categoryId;
    private String categoryName;
    private Integer categoryGroup;
    private String rankParentId;
    private String rankParentName;

    public ItemCategory() {
    }

    public ItemCategory(String categoryId, String categoryName, Integer categoryGroup, String rankParentId, String rankParentName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryGroup = categoryGroup;
        this.rankParentId = rankParentId;
        this.rankParentName = rankParentName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(Integer categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    public String getRankParentId() {
        return rankParentId;
    }

    public void setRankParentId(String rankParentId) {
        this.rankParentId = rankParentId;
    }

    public String getRankParentName() {
        return rankParentName;
    }

    public void setRankParentName(String rankParentName) {
        this.rankParentName = rankParentName;
    }
}
