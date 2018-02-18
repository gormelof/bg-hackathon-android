package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.adapters.RequestAdapter;
import hackathon.baggage.adapters.TravelAdapter;
import hackathon.baggage.response.requests.Requests;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RequestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RequestAdapter mRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_request);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_travel_list);

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Requests> call = hackathonService.getSentFromRequests(BaseActivity.USER_ID);

        call.enqueue(new Callback<Requests>() {
            @Override
            public void onResponse(@NonNull Call<Requests> call, @NonNull Response<Requests> response) {
                if (response.isSuccessful()) {
                    mRequestAdapter = new RequestAdapter(getApplicationContext(), response.body().getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(mRequestAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Requests> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void clickBottomSearchItem(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomPackageItem(View view) {
        Intent intent = new Intent(this, PackCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomTravelerItem(View view) {
        Intent intent = new Intent(this, TravelCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomRequestItem(View view) {
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomDealItem(View view) {
        Intent intent = new Intent(this, DealActivity.class);
        startActivity(intent);
    }
}
