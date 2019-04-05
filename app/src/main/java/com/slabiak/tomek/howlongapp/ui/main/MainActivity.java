package com.slabiak.tomek.howlongapp.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.slabiak.tomek.howlongapp.BuildConfig;
import com.slabiak.tomek.howlongapp.R;
import com.slabiak.tomek.howlongapp.data.model.Restaurant;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportActivity;
import com.slabiak.tomek.howlongapp.ui.base.BaseActivity;
import com.slabiak.tomek.howlongapp.ui.main.adapter.RestaurantsAdapter;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailActivity;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    private final static int PLACE_PICKER_REQUEST = 999;
    private ProgressDialog mMessageDialog;

    @Inject MainContract.Presenter mPresenter;
    @Inject RestaurantsAdapter mRestaurantsAdapter;

    @BindView(R.id.lv_restautants) ListView lvRestaurantsList;
    @BindView(R.id.btn_addPlace) Button btnAddPlaceButton;
    @BindView(R.id.toolbar_main) Toolbar toolbar;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        Places.initialize(getApplicationContext(), BuildConfig.GoogleSecAPIKEY);
        mPresenter.attachView(this);
        mPresenter.start();

        toolbar.setTitle("Lista restauracji");
        setSupportActionBar(toolbar);

        lvRestaurantsList.setAdapter(mRestaurantsAdapter);
        lvRestaurantsList.setTextFilterEnabled(true);
        lvRestaurantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant listItem = (Restaurant) lvRestaurantsList.getItemAtPosition(position);
                startRestaurantDetailActivity(listItem.getId());
            }
        });


        btnAddPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAddPlaceButtonClicked();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.refreshRestaurantsList();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place selectedplace = Autocomplete.getPlaceFromIntent(data);
                mPresenter.onPlacePickerFinished(selectedplace);
            } else {
                Toast.makeText(this, "Nie dodano restauracji", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRestaurantsAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mRestaurantsAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startRestaurantDetailActivity(int restaurantId) {
        Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
        intent.putExtra("id", restaurantId);
        startActivity(intent);
    }

    @Override
    public void showLoadingProgress(boolean active) {
        if (active) {
            mMessageDialog = ProgressDialog.show(this, "Proszę czekać...", "Pobieram dane...", true);
        } else {
            mMessageDialog.dismiss();
        }
    }


    @Override
    public void showRestaurants(List<Restaurant> restaurants) {
        mRestaurantsAdapter.clear();
        mRestaurantsAdapter.setmRestaurants(restaurants);
    }

    @Override
    public void startPlacePicker() {
        List<Place.Field> fields = Arrays.asList(Place.Field.TYPES,Place.Field.ID);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, PLACE_PICKER_REQUEST);
    }
}