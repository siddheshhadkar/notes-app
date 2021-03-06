package com.example.architectureexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.architectureexample.EXTRA_PRIORITY";

    private EditText addTitle;
    private EditText addDescription;
    private NumberPicker priorityNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addTitle = findViewById(R.id.add_title);
        addDescription = findViewById(R.id.add_description);
        priorityNumberPicker = findViewById(R.id.priority_number_picker);

        priorityNumberPicker.setMinValue(1);
        priorityNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent i = getIntent();
        if (i.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            addTitle.setText(i.getStringExtra(EXTRA_TITLE));
            addDescription.setText(i.getStringExtra(EXTRA_DESCRIPTION));
            priorityNumberPicker.setValue(i.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = addTitle.getText().toString();
        String description = addDescription.getText().toString();
        int priority = priorityNumberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Empty Title or Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_TITLE, title);
        i.putExtra(EXTRA_DESCRIPTION, description);
        i.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            i.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
