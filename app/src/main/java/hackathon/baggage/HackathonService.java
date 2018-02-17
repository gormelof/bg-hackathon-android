package hackathon.baggage;

import hackathon.baggage.response.packs.Packs;
import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HackathonService {

    @GET("travels")
    Call<Travels> getAllTravels();

    @GET("travels")
    Call<Travels> getUserTravels( @Query("u") String userId);

    @GET("packs")
    Call<Packs> getAllPacks();

}
