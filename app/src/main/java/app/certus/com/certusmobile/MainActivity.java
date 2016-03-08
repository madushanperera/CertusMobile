package app.certus.com.certusmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import app.certus.com.adapters.CustomSpinnerAdapter;

import app.certus.com.adapters.Product_ScrollerAdapter;
import app.certus.com.model.Products;
import app.certus.com.network.VolleySingleton;
import layout.AboutUsFragment;
import layout.CartFragment;
import layout.HomeFragment;
import layout.RecentPurchasesFragment;
import layout.WishListFragment;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Spinner subCategories_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customizeToolBar(0);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        TextView headerText = (TextView) headerView.findViewById(R.id.login_name);
        headerText.setText(MyApplication.getAndroidSession().getAttribute("user_name").toString());
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        //First fragment
        setFragment(0);

    }


    public void addItemsToSpinner(final Collection<String> collection, final int cat_id) {

        ArrayList<String> list = new ArrayList<>();
        list.addAll(collection);

        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(getApplicationContext(), list);
        subCategories_spinner.setAdapter(spinAdapter);
        subCategories_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {
                // On selecting a spinner item
                String subCategory = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
                String url = "http://10.0.2.2:8080/ECommerceApp/mobileFilterAllProductsAction?sub_name="
                        + subCategory.toLowerCase().replace(" ", "%20") + "&cat=" + cat_id;

                RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Type type = new TypeToken<List<Products>>() {
                                }.getType();
                                List<Products> proList = new Gson().fromJson(response.toString(), type);
                                Toast t = Toast.makeText(MainActivity.this, "Returned - Category : "
                                        + cat_id + " Product : " + proList.get(0).getName(), Toast.LENGTH_SHORT);
                                t.show();
                                HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                                        .findFragmentById(R.id.fragment);
                                if (homeFragment != null) {
                                    homeFragment.setProduct_scrollerAdapter(proList);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 50000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 50000;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });
                requestQueue.add(jsonArrayRequest);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home:
                                menuItem.setChecked(true);
                                customizeToolBar(0);
                                setFragment(0);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_cart:
                                menuItem.setChecked(true);
                                customizeToolBar(1);
                                setFragment(1);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_wish_list:
                                menuItem.setChecked(true);
                                customizeToolBar(2);
                                setFragment(2);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_recent_purchases:
                                menuItem.setChecked(true);
                                customizeToolBar(3);
                                setFragment(3);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_about_us:
                                menuItem.setChecked(true);
                                customizeToolBar(4);
                                setFragment(4);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_logout:
                                // menuItem.setChecked(true);
                                //Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                        }
                        return true;
                    }
                });

    }


    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragment, homeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                CartFragment cartFragment = new CartFragment();
                fragmentTransaction.replace(R.id.fragment, cartFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                WishListFragment wishFragment = new WishListFragment();
                fragmentTransaction.replace(R.id.fragment, wishFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                RecentPurchasesFragment recentPurchasesFragment = new RecentPurchasesFragment();
                fragmentTransaction.replace(R.id.fragment, recentPurchasesFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                fragmentTransaction.replace(R.id.fragment, aboutUsFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    public void customizeToolBar(int position) {
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
        switch (position) {
            case 0:
                toolbar = (Toolbar) findViewById(R.id.toolbar_home);
                toolbar.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar);
                subCategories_spinner = (Spinner) findViewById(R.id.spinner_subCategories);
                actionBar = getSupportActionBar();
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                break;
            case 1:
                toolbar = (Toolbar) findViewById(R.id.toolbar_cart);
                toolbar.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
                actionBar.setTitle("Check Out");
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            case 2:
                toolbar = (Toolbar) findViewById(R.id.toolbar_wish_list);
                toolbar.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
                actionBar.setTitle("Wish List");
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            case 3:
                toolbar = (Toolbar) findViewById(R.id.toolbar_recent_purchases);
                toolbar.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
                actionBar.setTitle("My Recent Purchases");
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            case 4:
                toolbar = (Toolbar) findViewById(R.id.toolbar_about_us);
                toolbar.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
                actionBar.setTitle("About Us");
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;

        }
    }


}
