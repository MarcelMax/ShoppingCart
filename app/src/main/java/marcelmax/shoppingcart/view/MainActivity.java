package marcelmax.shoppingcart.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marcelmax.shoppingcart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onSupportNavigateUp() {

        return super.onSupportNavigateUp();
    }


    //todo https://www.journaldev.com/22299/android-jetpack-navigation-architecture
}
