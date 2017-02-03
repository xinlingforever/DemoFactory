package com.xx.demoproject.demofactory.dagger2.car;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xx.demoproject.demofactory.App;
import com.xx.demoproject.demofactory.R;

import javax.inject.Inject;

public class CarActivity extends AppCompatActivity {

    @Inject
    CarPresenter mCarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        CarFragment carFragment = (CarFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (carFragment == null){
            carFragment = CarFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout, carFragment);
            fragmentTransaction.commit();
        }

        DaggerCarComponent
                .builder()
                .dataModelComponent(((App) getApplication()).getDataModelComponent())
                .carPresenterModule(new CarPresenterModule(carFragment))
                .build()
                .inject(this);

    }
}
