package me.akshanshjain.garnish.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class StepsItem implements Parcelable{

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

    protected StepsItem(Parcel in) {
        id = in.readInt();
        shortDesc = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDesc);
        dest.writeString(description);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StepsItem> CREATOR = new Creator<StepsItem>() {
        @Override
        public StepsItem createFromParcel(Parcel in) {
            return new StepsItem(in);
        }

        @Override
        public StepsItem[] newArray(int size) {
            return new StepsItem[size];
        }
    };
}
