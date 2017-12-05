package dailynews.penlymeng.com.dailylearning.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l.pen on 12/5/2017.
 */

public class TopNews {

    @SerializedName("status")
    public String status;
    @SerializedName("articles")
    public List<Articles> articles = new ArrayList<>();

    public static class Source {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
    }

    public static class Articles {
        @SerializedName("source")
        public Source source;
        @SerializedName("author")
        public String author;
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("url")
        public String url;
        @SerializedName("urlToImage")
        public String urlToImage;
        @SerializedName("publishedAt")
        public String publishedAt;
    }
}
