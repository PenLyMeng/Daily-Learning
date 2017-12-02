package dailynews.penlymeng.com.dailylearning.model;

import java.util.List;

/**
 * Created by l.pen on 12/2/2017.
 */

public class SourceNew {


    @com.google.gson.annotations.SerializedName("status")
    public String status;
    @com.google.gson.annotations.SerializedName("sources")
    public List<Sources> sources;

    public static class Sources {
        @com.google.gson.annotations.SerializedName("id")
        public String id;
        @com.google.gson.annotations.SerializedName("name")
        public String name;
        @com.google.gson.annotations.SerializedName("description")
        public String description;
        @com.google.gson.annotations.SerializedName("url")
        public String url;
        @com.google.gson.annotations.SerializedName("category")
        public String category;
        @com.google.gson.annotations.SerializedName("language")
        public String language;
        @com.google.gson.annotations.SerializedName("country")
        public String country;
    }
}
