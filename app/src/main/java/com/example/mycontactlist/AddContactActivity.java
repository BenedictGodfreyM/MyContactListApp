package com.example.mycontactlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.format.Time;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    EditText contactNameInput;
    EditText streetAddressInput;
    EditText cityInput;
    EditText stateInput;
    EditText zipCodeInput;
    EditText phoneNumberInput;
    EditText cellPhoneNumberInput;
    EditText emailAddressInput;
    EditText birthdayInput;
    Button addContactButton;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().setTitle("Add Contact");

        contactNameInput = findViewById(R.id.enterContactName);
        streetAddressInput = findViewById(R.id.enterStreetAddress);
        cityInput = findViewById(R.id.enterCity);
        stateInput = findViewById(R.id.enterState);
        zipCodeInput = findViewById(R.id.enterZipCode);
        phoneNumberInput = findViewById(R.id.enterPhoneNumber);
        cellPhoneNumberInput = findViewById(R.id.enterCellPhoneNumber);
        emailAddressInput = findViewById(R.id.enterEmailAddress);
        birthdayInput = findViewById(R.id.enterBirthday);
        addContactButton = findViewById(R.id.addContactButton);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentContact = new Contact();
                currentContact.setContactName(contactNameInput.getText().toString());
                currentContact.setStreetAddress(streetAddressInput.getText().toString());
                currentContact.setCity(cityInput.getText().toString());
                currentContact.setState(stateInput.getText().toString());
                currentContact.setZipCode(zipCodeInput.getText().toString());
                currentContact.setPhoneNumber(phoneNumberInput.getText().toString());
                currentContact.setCellNumber(cellPhoneNumberInput.getText().toString());
                currentContact.setEMail(emailAddressInput.getText().toString());

                ContactDataSource ds = new ContactDataSource(AddContactActivity.this );
                ds.open();
                int newId = ds.getLastContactId();
                currentContact .setContactID(newId);
                boolean wasSuccessful = false ;
                wasSuccessful = ds.insertContact( currentContact );
                ds.close();
                if (wasSuccessful) {
                    contactNameInput.setText("");
                    streetAddressInput.setText("");
                    cityInput.setText("");
                    stateInput.setText("");
                    zipCodeInput.setText("");
                    phoneNumberInput.setText("");
                    cellPhoneNumberInput.setText("");
                    emailAddressInput.setText("");
                    birthdayInput.setText("");
                    Toast.makeText(AddContactActivity.this,"Contact added!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddContactActivity.this,"Unable to add contact!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}