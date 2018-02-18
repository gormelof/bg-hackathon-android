package hackathon.baggage.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import hackathon.baggage.networking.HackathonService;
import hackathon.baggage.R;
import hackathon.baggage.networking.ServiceGenerator;
import hackathon.baggage.models.Travel;
import hackathon.baggage.response.cities.Cities;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TravelCreateActivity extends BaseActivity {
    private static final String TAG = TravelCreateActivity.class.getSimpleName().toUpperCase();

    private AutoCompleteTextView mFrom;
    private AutoCompleteTextView mTo;
    private EditText mDate;
    private EditText mWeight;

    private Button mCreate;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_travel_create);

        mFrom = (AutoCompleteTextView) findViewById(R.id.act_activity_travel_create_from);
        mTo = (AutoCompleteTextView) findViewById(R.id.act_activity_travel_create_to);
        mWeight = (EditText) findViewById(R.id.et_activity_travel_create_weight);
        mDate = (EditText) findViewById(R.id.et_activity_travel_create_date);

        mCreate = (Button) findViewById(R.id.btn_activity_travel_create_create);

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

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TravelCreateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = Integer.toString(year) + "-" + Integer.toString(month + 1) + "-" + Integer.toString(day);
                mDate.setText(date);
            }
        };

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = mFrom.getText().toString();
                String to = mTo.getText().toString();
                String weight = mWeight.getText().toString();
                String date = mDate.getText().toString();

                Travel travel = new Travel(from, to, weight, date, USER_ID);

                Call<ResponseBody> call = hackathonService.createTravel(travel);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Context context = getApplicationContext();
                            CharSequence text = "Success! Travel created.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Context context = getApplicationContext();
                        CharSequence text = "Failed! Travel not created.";
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
