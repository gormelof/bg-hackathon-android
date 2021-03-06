package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import hackathon.baggage.networking.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.networking.ServiceGenerator;
import hackathon.baggage.response.cities.Cities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    private Button mSearchPack;
    private Button mSearchTravel;

    private AutoCompleteTextView mFrom;
    private AutoCompleteTextView mTo;
    private EditText mWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_main);

        mSearchPack = (Button) findViewById(R.id.btn_activity_main_search_pack);
        mSearchTravel = (Button) findViewById(R.id.btn_activity_main_search_travel);
        mFrom = (AutoCompleteTextView) findViewById(R.id.et_activity_main_from);
        mTo = (AutoCompleteTextView) findViewById(R.id.et_activity_main_to);
        mWeight = (EditText) findViewById(R.id.et_activity_main_weight);

        HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

        Call<Cities> call = hackathonService.getAllCities();

        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.isSuccessful()) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_dropdown_item_1line, response.body().getData());
                    mFrom.setAdapter(adapter);
                    mTo.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        mSearchPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PackActivity.class);
                intent.putExtra("FROM", mFrom.getText().toString());
                intent.putExtra("TO", mTo.getText().toString());
                intent.putExtra("WEIGHT", mWeight.getText().toString());
                startActivity(intent);
            }
        });

        mSearchTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
                intent.putExtra("FROM", mFrom.getText().toString());
                intent.putExtra("TO", mTo.getText().toString());
                intent.putExtra("WEIGHT", mWeight.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void clickBottomSearchItem(View view) {
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
