package com.minileet.model;

public class Problem {

    private Long id;
    private String title;
    private String slug;
    private String description;
    private String difficulty; // EASY / MEDIUM / HARD

    public Problem() {
    }

    public Problem(Long id, String title, String slug, String description, String difficulty) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
