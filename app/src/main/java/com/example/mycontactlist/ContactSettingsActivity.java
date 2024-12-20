package com.example.mycontactlist;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactSettingsActivity extends AppCompatActivity {
    RadioButton rbName;
    RadioButton rbCity;
    RadioButton rbBirthDay;
    RadioButton rbAscending;
    RadioButton rbDescending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_settings);
        getSupportActionBar().setTitle("Contact Settings");

        rbName = (RadioButton) findViewById(R.id.radioName);
        rbCity = (RadioButton) findViewById(R.id.radioCity);
        rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
        rbAscending = (RadioButton) findViewById(R.id.radioAscending);
        rbDescending = (RadioButton) findViewById(R.id.radioDescending );

        this.initSettings();
        this.initSortByClick();
        this.initSortOrderClick();
    }
    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString( "sortfield" , "contactname" );
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString( "sortorder" , "ASC" );

        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked( true );
        }else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked( true );
        }else {
            rbBirthDay.setChecked( true );
        }

        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked( true );
        }else {
            rbDescending.setChecked( true );
        }
    }
    private void initSortByClick() {
        RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroup1);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", MODE_PRIVATE).edit().putString( "sortfield" , "contactname" ).commit();
                }else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", MODE_PRIVATE).edit().putString( "sortfield" , "city" ).commit();
                }else {
                    getSharedPreferences("MyContactListPreferences", MODE_PRIVATE).edit().putString( "sortfield" , "birthday" ).commit();
                }
            }
        });
    }
    private void initSortOrderClick() {
        RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup2);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", MODE_PRIVATE).edit().putString( "sortorder" , "ASC" ).commit();
                }else {
                    getSharedPreferences("MyContactListPreferences", MODE_PRIVATE).edit().putString( "sortorder" , "DESC" ).commit();
                }
            }
        });
    }
}