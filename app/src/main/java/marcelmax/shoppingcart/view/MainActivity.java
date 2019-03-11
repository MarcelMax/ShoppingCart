package marcelmax.shoppingcart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import marcelmax.shoppingcart.R;

public class MainActivity extends AppCompatActivity {

    /**
     * Shopping cart is a mock app to simulate shopping.
     * You can select a variety of products and add it to your cart
     * To fetch the product data I used Retrofit. The data is provided by mockaroo
     * which lets you create a Json File and fill it with "random values"
     *
     * for navigation i used the new navigationcomponent.
     *
     * The architecture used is the mvvm pattern.
     *
     * there are a few things that still are not working as intended, but
     * i'm aware of it and wrote todos for  e.g. quantity handling
     *
     */

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupNavigation();

    }

    private void setupNavigation() {
        setSupportActionBar(toolbar);

        //setup Back button
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this,R.id.my_nav_host_fragment).navigateUp();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.cartFragment) {
            return NavigationUI.onNavDestinationSelected(item,
                    Navigation.findNavController(this, R.id.my_nav_host_fragment));
        }

        return super.onOptionsItemSelected(item);
    }

}
