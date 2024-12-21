package com.example.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);
        getSupportActionBar().setTitle("Contacts");

        addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MyContactListPreferences", Context. MODE_PRIVATE ).getString("sortfield" , "contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context. MODE_PRIVATE ).getString("sortorder" , "ASC");

        ContactDataSource ds = new ContactDataSource(this);
        ds.open();
        final ArrayList<Contact> contacts = ds.getContacts(sortBy, sortOrder);
        ds.close();
        listView = findViewById(R.id.listView);
        listView.setAdapter( new ContactAdapter( this , contacts));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settingsOption){
            Intent intent = new Intent(ContactListActivity.this, ContactSettingsActivity.class);
            startActivity(intent);
        }else if(id == R.id.exitAppOption){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}