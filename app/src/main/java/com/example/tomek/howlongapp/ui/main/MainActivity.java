package com.example.tomek.howlongapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.AppDataManager;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    MainContract.Presenter presenter;
    TextView textView;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btn_add = findViewById(R.id.btn_add);
        presenter = new MainPresenter(this, AppDataManager.getInstance());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked();
            }
        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showText(String text) {
        textView.setText(text);
    }



}
