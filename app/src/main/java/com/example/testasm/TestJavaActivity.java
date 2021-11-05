package com.example.testasm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author: hmei
 * @date: 2021/11/5
 * @email: huangmei@haohaozhu.com
 */
public class TestJavaActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.butter_knife)
    Button bkBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        findViewById(R.id.lambda_btn).setOnClickListener(v -> {

        });
        findViewById(R.id.current_this).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.butter_knife)
    public void onViewClick(){
//        Toast.makeText(this, "Bu", Toast.LENGTH_SHORT).show();
    }
}
