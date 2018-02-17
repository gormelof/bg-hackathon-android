package hackathon.baggage.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.models.Travel;
import hackathon.baggage.adapters.TravelAdapter;
import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TravelActivity extends AppCompatActivity {
    private static final String TAG = TravelActivity.class.getSimpleName().toUpperCase();

    private RecyclerView mRecyclerView;
    private TravelAdapter mTravelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_travel);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_travel_list);

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Travels> call = hackathonService.getAllTravels();
        call.enqueue(new Callback<Travels>() {
            @Override
            public void onResponse(Call<Travels> call, Response<Travels> response) {
                if (response.isSuccessful()) {
                    mTravelAdapter = new TravelAdapter(getApplicationContext(), response.body().getData());
                    mRecyclerView.setAdapter(mTravelAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<Travels> call, Throwable t) {

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
