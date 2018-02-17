package hackathon.baggage.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hackathon.baggage.R;
import hackathon.baggage.models.Travel;
import hackathon.baggage.adapters.TravelAdapter;

public class TravelActivity extends AppCompatActivity {
    private static final String TAG = TravelActivity.class.getSimpleName().toUpperCase();

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_travel_list);

        TravelAdapter travelAdapter = new TravelAdapter(this, Travel.getDemoData());
        mRecyclerView.setAdapter(travelAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
