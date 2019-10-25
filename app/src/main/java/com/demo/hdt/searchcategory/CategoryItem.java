package com.demo.hdt.searchcategory;

public class CategoryItem {
    private String categoryId;
    private String categoryName;
    private int categoryGroup;
    private String rankParentId;
    private String rankParentName;

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

    public int getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(int categoryGroup) {
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
