package hackathon.baggage;

import hackathon.baggage.models.Pack;
import hackathon.baggage.models.Travel;
import hackathon.baggage.response.cities.Cities;
import hackathon.baggage.response.packs.Packs;
import hackathon.baggage.response.requests.Requests;
import hackathon.baggage.response.travels.Travels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HackathonService {

    @GET("travels")
    Call<Travels> getAllTravels();

    @GET("travels")
    Call<Travels> getUserTravels(
            @Query("u") String userId,
            @Query("w") String weight,
            @Query("f") String from,
            @Query("t") String to
    );

    @GET("travels")
    Call<Travels> getSearchTravels(
            @Query("w") String weight,
            @Query("f") String from,
            @Query("t") String to
    );

    @POST("travels")
    Call<ResponseBody> createTravel(@Body Travel travel);

    @GET("packs")
    Call<Packs> getAllPacks();

    @GET("packs")
    Call<Packs> getUserPacks(
            @Query("u") String userId,
            @Query("w") String weight,
            @Query("f") String from,
            @Query("t") String to
    );

    @GET("packs")
    Call<Packs> getSearchPacks(
            @Query("w") String weight,
            @Query("f") String from,
            @Query("t") String to
    );

    @POST("packs")
    Call<ResponseBody> createPack(@Body Pack pack);

    @GET("cities")
    Call<Cities> getAllCities();

    @GET("requests")
    Call<Requests> getSentFromRequests(
            @Query("sf") String userId
    );
}
