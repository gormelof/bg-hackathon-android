package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.adapters.TravelAdapter;
import hackathon.baggage.listeners.RecyclerItemClickListener;
import hackathon.baggage.response.travels.Datum;
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

        Intent intent = getIntent();
        String from = intent.getStringExtra("FROM");
        String to = intent.getStringExtra("TO");
        String weight = intent.getStringExtra("WEIGHT");

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Travels> call = hackathonService.getSearchTravels(
                weight,
                from,
                to
        );

        call.enqueue(new Callback<Travels>() {
            @Override
            public void onResponse(Call<Travels> call, final Response<Travels> response) {
                if (response.isSuccessful()) {
                    mTravelAdapter = new TravelAdapter(getApplicationContext(), response.body().getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(mTravelAdapter);

                    mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), mRecyclerView ,
                            new RecyclerItemClickListener.OnItemClickListener() {

                                @Override public void onItemClick(View view, int position) {
                                    Datum clickedItem = response.body().getData().get(position);

                                    String userId = clickedItem.getUser().getId();
                                    String packageId = clickedItem.getId();
                                    String weight = Integer.toString(clickedItem.getWeight());
                                    String from = clickedItem.getFrom();
                                    String to = clickedItem.getTo();

                                    Intent intent = new Intent(getApplicationContext(), TravelPackSelectActivity.class);

                                    intent.putExtra("PACKAGE_USER_ID", userId);
                                    intent.putExtra("PACKAGE_ID", packageId);
                                    intent.putExtra("WEIGHT", weight);
                                    intent.putExtra("FROM", from);
                                    intent.putExtra("TO", to);

                                    startActivity(intent);
                                }

                                @Override public void onLongItemClick(View view, int position) {
                                    // do whatever
                                }

                            })
                    );
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
