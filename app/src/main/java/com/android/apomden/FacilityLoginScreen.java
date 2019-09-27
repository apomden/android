package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.apomden.Models.Facility;
import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

public class FacilityLoginScreen extends AppCompatActivity {
    Button enterMainScreen;
    EditText passwordText;
    TextView textTop, textBeforeBox;
    ProgressDialog pdialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref   = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();


        enterMainScreen = findViewById(R.id.btnLoginToFacility);
        passwordText    = findViewById(R.id.facilityPassword);
        textTop         = findViewById(R.id.textTop);
        textBeforeBox   = findViewById(R.id.textBeforeBox);
        pdialog=new ProgressDialog(this);

        textTop.setText(Globall.selectedFacility.getFacilityName());
        textBeforeBox.setText(Globall.selectedFacility.getFacilityAddress());


        enterMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isNotEmpty(passwordText) ){
                    //start dialog
                    pdialog.setTitle("Loading.....");
                    pdialog.setIndeterminate(true);
                    pdialog.show();

                    Globall.selectedFacility.setPassword(passwordText.getText().toString().trim());

                    Globall.logUserIn(Globall.selectedFacility, "user/login/", new Responser() {
                        @Override
                        public void onSuccess(String string) {
                            Log.e("===Passed===", string);
                            editor.putString("facName",  Globall.selectedFacility.getDomain());
                            editor.putString("lollipop", Globall.selectedFacility.getPassword());
                            editor.putString("facId",    Globall.selectedFacility.getFacilityId());
                            editor.commit();

                            pdialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Success",
                                    Toast.LENGTH_LONG).show();

                            Globall.currentFacilityUrl = "https://www.apomden.com/facility/" + Globall.selectedFacility.getDomain();

                            Globall.getFacilityDetails(Globall.selectedFacility.getFacilityId());


                            Toast.makeText(
                                    getApplicationContext(),
                                    "Success",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainDashboardScreen.class));
                        }

                        @Override
                        public void onFailed(String string) {
                            Log.e("===Failed===", string);
                            pdialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    string,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please Enter Password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });




    }



    public boolean isNotEmpty (EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }


}
