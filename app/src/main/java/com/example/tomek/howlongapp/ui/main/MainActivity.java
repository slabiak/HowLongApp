package com.example.tomek.howlongapp.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BaseActivity;
import com.example.tomek.howlongapp.ui.main.adapter.RestaurantsAdapter;
import com.example.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View {

    private final static int PLACE_PICKER_REQUEST = 999;

    @Inject MainContract.Presenter mPresenter;
    @Inject RestaurantsAdapter mRestaurantsAdapter;

    @BindView(R.id.restaurantsList) ListView mRestaurantsList;
    @BindView(R.id.btn_add) Button mAddPlaceButton;
    @BindView(R.id.searchView) SearchView mSearchView;

    ProgressDialog mMessageDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        mPresenter.start();


        mRestaurantsList.setAdapter(mRestaurantsAdapter);
        mRestaurantsList.setTextFilterEnabled(true);
        mRestaurantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant listItem = (Restaurant) mRestaurantsList.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
                intent.putExtra("id", listItem.getId().toString());
                startActivity(intent);
            }
    });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        mAddPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAddPlaceButtonClicked();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                    Place selectedplace = PlacePicker.getPlace(this, data);
                    mPresenter.onPlacePickerFinished(selectedplace);
                } else {
                    Toast.makeText(this, "Coś poszło nie tak!", Toast.LENGTH_LONG).show();
                }
            }
        }

    @Override
    public void showMessage(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingProgress(boolean active) {
        if(active) {
            mMessageDialog = ProgressDialog.show(this, "Proszę czekać...", "Pobieram dane...", true);
        }else {
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
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}