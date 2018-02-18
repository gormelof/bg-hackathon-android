package hackathon.baggage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hackathon.baggage.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.ServiceGenerator;
import hackathon.baggage.models.Pack;
import hackathon.baggage.response.cities.Cities;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PackCreateActivity extends BaseActivity {
    private static final String TAG = PackCreateActivity.class.getSimpleName().toUpperCase();

    private AutoCompleteTextView mFrom;
    private AutoCompleteTextView mTo;
    private EditText mWeight;

    private Button mCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_pack_create);


        mFrom = (AutoCompleteTextView) findViewById(R.id.act_activity_pack_create_from);
        mTo = (AutoCompleteTextView) findViewById(R.id.act_activity_pack_create_to);
        mWeight = (EditText) findViewById(R.id.et_activity_pack_create_weight);

        mCreate = (Button) findViewById(R.id.btn_activity_pack_create_create);

        final HackathonService hackathonService = ServiceGenerator.createService(HackathonService.class);

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

            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = mFrom.getText().toString();
                String to = mTo.getText().toString();
                String weight = mWeight.getText().toString();

                Pack pack = new Pack(from, to, weight);

                Call<ResponseBody> call = hackathonService.createPack(pack);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Context context = getApplicationContext();
                            CharSequence text = "Success! Package created.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Context context = getApplicationContext();
                        CharSequence text = "Failed! Package not created.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void clickBottomHomeItem(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomPackageItem(View view) {
    }

    @Override
    public void clickBottomTravelerItem(View view) {
        Intent intent = new Intent(this, TravelCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickBottomNotificationItem(View view) {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
}
