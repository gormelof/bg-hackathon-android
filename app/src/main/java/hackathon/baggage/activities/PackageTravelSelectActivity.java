package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.adapters.PackageTravelSelectAdapter;
import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PackageTravelSelectActivity extends AppCompatActivity {
    private static final String TAG = PackageTravelSelectActivity.class.getSimpleName().toUpperCase();

    private RecyclerView mRecyclerView;
    private PackageTravelSelectAdapter mPackageTravelSelectAdapter;

    String userId = "5a88b2145e758d6f85590997";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_pack_request);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_pack_request_travel_list);

        Intent intent = getIntent();
        String packageUserId = intent.getStringExtra("USER_ID");
        String packageId = intent.getStringExtra("PACKAGE_ID");
        String packageWeight = intent.getStringExtra("WEIGHT");
        String packageFrom = intent.getStringExtra("FROM");
        String packageTo = intent.getStringExtra("TO");

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Travels> call = hackathonService.getUserTravels(userId, packageWeight, packageFrom, packageTo);
        call.enqueue(new Callback<Travels>() {
            @Override
            public void onResponse(Call<Travels> call, Response<Travels> response) {
                if (response.isSuccessful()) {
                    mPackageTravelSelectAdapter = new PackageTravelSelectAdapter(getApplicationContext(), response.body().getData());
                    mRecyclerView.setAdapter(mPackageTravelSelectAdapter);
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
