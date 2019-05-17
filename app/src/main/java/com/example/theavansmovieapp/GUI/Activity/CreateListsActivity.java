package com.example.theavansmovieapp.GUI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.NewList;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetNewListCallback;
import com.example.theavansmovieapp.R;

/** gemaakt door luc */

public class CreateListsActivity extends AppCompatActivity {

    /** klas attributen */
    private EditText nameEt;
    private EditText descriptionEt;
    private Button createBtn;
    private FilmsRepository repository;
    private static final String LANGUAGE = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lists2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** add back arrow to toolbar */
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        /** attributen koppelen aan de views */
        nameEt = findViewById(R.id.create_list_name_et);
        descriptionEt = findViewById(R.id.create_list_description_et);
        createBtn = findViewById(R.id.create_list_btn);

        repository = repository.getInstance();

        /** listener om lijst op te slaan */
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** nieuw object aanmaken */
                NewList newList = new NewList(nameEt.getText().toString(), descriptionEt.getText().toString(), LANGUAGE);

                repository.createList(newList, new OnGetNewListCallback() {
                    @Override
                    public void onSuccess(NewList newList) {
                        Toast.makeText(CreateListsActivity.this, "List " + nameEt.getText().toString() + " added", Toast.LENGTH_SHORT).show();
                        nameEt.setText("");
                        descriptionEt.setText("");
                        finish();
                    }

                    /** bij een error wordt er een foutmelding weergegeven */
                    @Override
                    public void onError() {
                        Toast.makeText(CreateListsActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }}
        );
    }

    public void clear() {
        nameEt.setText("");
        descriptionEt.setText("");
    }

    /** een methode die een boolean als waarde teruggeeft die overschreven wordt. dit is in
     * verband met het 'back' (pijltje) knop om terug te gaan naar het vorige scherm */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
