package com.minileet.model;

public class Problem {
    private String id;
    private String title;
    private String description;
    private String difficulty;
    private String[] tags;

    public Problem() {}
    public Problem(String id, String title, String description, String difficulty, String[] tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.tags = tags;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String[] getTags() { return tags; }
    public void setTags(String[] tags) { this.tags = tags; }

}
