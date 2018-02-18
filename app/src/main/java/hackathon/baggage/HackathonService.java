package hackathon.baggage;

import hackathon.baggage.response.cities.Cities;
import hackathon.baggage.response.packs.Packs;
import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.http.GET;
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

    @GET("cities")
    Call<Cities> getAllCities();
}
