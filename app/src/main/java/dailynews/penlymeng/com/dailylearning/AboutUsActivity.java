package dailynews.penlymeng.com.dailylearning;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class AboutUsActivity extends AppCompatActivity {


    ImageView imgAppLogo;
    public static final String APP_NME = "Daily Learning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        imgAppLogo = findViewById(R.id.img_app_logo);



        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(4) /* thickness in px */

                .bold()
                .textColor(Color.WHITE)
                .endConfig()
                .buildRoundRect(APP_NME , Color.RED,10);
        imgAppLogo.setImageDrawable(drawable);
    }
}
