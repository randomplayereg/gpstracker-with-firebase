package com.randomplayer.edmtdev_tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

public class MainActivity extends AppCompatActivity {

    private final static int LOGIN_PERMISSION=1000;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
    }


    public void loginEvent(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAllowNewEmailAccounts(true).build(), LOGIN_PERMISSION
        );
    }

//  @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                loginEvent();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_PERMISSION){
            startNewActivity(resultCode, data);
        }
    }

    private void startNewActivity(int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            Intent intent = new Intent(MainActivity.this, ListOnline.class);
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }
}
