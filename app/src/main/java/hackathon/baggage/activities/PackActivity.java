package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.adapters.PackageAdapter;
import hackathon.baggage.listeners.RecyclerItemClickListener;
import hackathon.baggage.response.packs.Packs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PackActivity extends AppCompatActivity {
    private static final String TAG = PackActivity.class.getSimpleName().toUpperCase();

    private RecyclerView mRecyclerView;
    private PackageAdapter mPackageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_package);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_package_list);

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Packs> call = hackathonService.getAllPacks();
        call.enqueue(new Callback<Packs>() {
            @Override
            public void onResponse(Call<Packs> call, final Response<Packs> response) {
                if (response.isSuccessful()) {
                    mPackageAdapter = new PackageAdapter(getApplicationContext(), response.body().getData());
                    mRecyclerView.setAdapter(mPackageAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);

                    mRecyclerView.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {
                                    String userId = response.body().getData().get(position).getUser().getId();
                                    Intent intent = new Intent(getApplicationContext(), PackageTravelSelectActivity.class);
                                    intent.putExtra("USER_ID", userId);
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
            public void onFailure(Call<Packs> call, Throwable t) {

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
