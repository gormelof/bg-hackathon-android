package hackathon.baggage;

import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HackathonService {
    @GET("travels")
    Call<Travels> getAllTravels();
}
