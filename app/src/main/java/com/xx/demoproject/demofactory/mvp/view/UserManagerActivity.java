package com.xx.demoproject.demofactory.mvp.view;

import android.os.BatteryStats;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xx.demoproject.demofactory.R;
import com.xx.demoproject.demofactory.mvp.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserManagerActivity extends AppCompatActivity implements IUserView{


    @BindView(R.id.inputFN)
    EditText inputFN;
    @BindView(R.id.inputLN)
    EditText inputLN;
    @BindView(R.id.saveBtn)
    Button saveBtn;
    @BindView(R.id.resultID)
    TextView resultID;
    @BindView(R.id.searchID)
    EditText searchID;
    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.searchResult)
    TextView searchResult;
    @BindView(R.id.activity_user_manager)
    RelativeLayout activityUserManager;

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);

        mUserPresenter = new UserPresenter(this);
    }

    @OnClick({R.id.saveBtn, R.id.searchBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                if (mUserPresenter != null && !TextUtils.isEmpty(getUserFirstName()) && !TextUtils.isEmpty(getUserLastName())){
                    mUserPresenter.saveUser(getUserFirstName(), getUserLastName());
                }
                break;
            case R.id.searchBtn:
                if (mUserPresenter != null){
                    mUserPresenter.loadUser(getUid());
                }
                break;
        }
    }

    @Override
    public long getUid() {
        try {
            return Long.valueOf(searchID.getText().toString()).longValue();
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public String getUserFirstName() {
        return inputFN.getText().toString();
    }

    @Override
    public String getUserLastName() {
        return inputLN.getText().toString();
    }

    @Override
    public void setResultUid(long uid) {
        resultID.setText(""+uid);
    }

    @Override
    public void setResultStr(String result) {
        searchResult.setText(result);
    }
}
