package hackathon.baggage.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String USER_ID = "5a88b2145e758d6f855909b6";
    public abstract void clickBottomSearchItem(View view);
    public abstract void clickBottomPackageItem(View view);
    public abstract void clickBottomTravelerItem(View view);
    public abstract void clickBottomRequestItem(View view);
    public abstract void clickBottomDealItem(View view);
}
