package hackathon.baggage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hackathon.baggage.R;

public class DealActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
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
    public void clickBottomDealItem(View view) {}
}
