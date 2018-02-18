package hackathon.baggage.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void clickBottomHomeItem(View view);
    public abstract void clickBottomPackageItem(View view);
    public abstract void clickBottomTravelerItem(View view);
    public abstract void clickBottomNotificationItem(View view);
}
