package com.example.pr.API.model;

public class Papers {
    private String name;
    private String recipe;

    public Papers(String name, String recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
