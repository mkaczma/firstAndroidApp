package com.example.comarch.firstapp;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class DataFragment extends Fragment {

    private EditText nameText;
    private EditText surnameText;
    private ImageButton backButton;
    private Button saveButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        nameText = (EditText) view.findViewById(R.id.nameTextEdit);
        surnameText = (EditText) view.findViewById(R.id.surnameTextEdit);
        backButton = (ImageButton) view.findViewById(R.id.backButton);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MainActivity.PlaceholderFragment())
                        .commit();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String surname = surnameText.getText().toString();
                if (name.length() == 0 || surname.length() == 0) {
                    Toast.makeText(DataFragment.this.getActivity(), "You must fill both field!", Toast.LENGTH_LONG).show();
//                } else {
//                    DatabaseExample database = new DatabaseExample(getActivity());
//                    Data data = new Data(name,surname);
//                    boolean ok = database.addData(data);
//                    if(ok){
//                        Toast.makeText(DataFragment.this.getActivity(), "Save successful!", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(DataFragment.this.getActivity(), "Something go wrong.", Toast.LENGTH_LONG).show();
//                    }
//                }
                }else {
                    ContentValues values = new ContentValues();
                    values.put(ExampleProvider.NAME, name);
                    values.put(ExampleProvider.SURNAME, surname);
                    Uri uri = getActivity().getContentResolver().insert(ExampleProvider.CONTENT_URI, values);
                    Toast.makeText(DataFragment.this.getActivity(), "Save successful!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
