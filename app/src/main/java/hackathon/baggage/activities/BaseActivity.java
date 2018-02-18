package hackathon.baggage.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Demo USER_ID tutar.
 * Custom Bottom Bar aksiyonları için abstract aksiyon metodları barındırır.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String USER_ID = "5a8932a9f1b80cc4727c2b52";

    public abstract void clickBottomSearchItem(View view);

    public abstract void clickBottomPackageItem(View view);

    public abstract void clickBottomTravelerItem(View view);

    public abstract void clickBottomRequestItem(View view);

    public abstract void clickBottomDealItem(View view);
}
