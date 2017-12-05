package dailynews.penlymeng.com.dailylearning.service;

import dailynews.penlymeng.com.dailylearning.model.SourceNews;
import dailynews.penlymeng.com.dailylearning.model.TopNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by l.pen on 12/2/2017.
 */

public interface CoreService {

    @GET("sources")
    Call<SourceNews> listSourceNews(@Query("apiKey") String apiKey);

    @GET("top-headlines?sources=bbc-news")
    Call<TopNews> listTopNews(@Query("apiKey") String apiKey);




}
