package hackathon.baggage.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hackathon.baggage.networking.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.networking.ServiceGenerator;
import hackathon.baggage.adapters.PackageTravelSelectAdapter;
import hackathon.baggage.listeners.RecyclerItemClickListener;
import hackathon.baggage.response.travels.Datum;
import hackathon.baggage.response.travels.Travels;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PackageTravelSelectActivity extends BaseActivity {
    private static final String TAG = PackageTravelSelectActivity.class.getSimpleName().toUpperCase();

    private RecyclerView mRecyclerView;
    private PackageTravelSelectAdapter mPackageTravelSelectAdapter;

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

        Call<Travels> call = hackathonService.getUserTravels(USER_ID, packageWeight, packageFrom, packageTo);
        call.enqueue(new Callback<Travels>() {
            @Override
            public void onResponse(Call<Travels> call, final Response<Travels> response) {
                if (response.isSuccessful()) {
                    mPackageTravelSelectAdapter = new PackageTravelSelectAdapter(getApplicationContext(), response.body().getData());
                    mRecyclerView.setAdapter(mPackageTravelSelectAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);

                    mRecyclerView.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), mRecyclerView,
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Datum clickedItem = response.body().getData().get(position);

                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    PackageTravelSelectActivity.this);
                                            builder.setTitle("Request");
                                            builder.setMessage("Would you like to make a recommendation for the transportation of your package?");
                                            builder.setNegativeButton("Cancel",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // close
                                                        }
                                                    });
                                            builder.setPositiveButton("Confirm",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    });
                                            builder.show();
                                        }

                                        @Override
                                        public void onLongItemClick(View view, int position) {
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
