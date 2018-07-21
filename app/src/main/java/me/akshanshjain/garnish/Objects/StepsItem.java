package me.akshanshjain.garnish.Objects;

public class StepsItem {

    private int id;
    private String shortDesc;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

    private StepsItem() {
    }

    public StepsItem(int id, String shortDesc, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
