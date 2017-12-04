package dailynews.penlymeng.com.dailylearning.service;

import dailynews.penlymeng.com.dailylearning.model.SourceNew;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by l.pen on 12/2/2017.
 */

public interface CoreService {

    @GET("sources")
    Call<SourceNew> listSourceNews(@Query("apiKey") String apiKey);




}
