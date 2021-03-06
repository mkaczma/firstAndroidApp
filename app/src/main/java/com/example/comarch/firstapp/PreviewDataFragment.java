package com.example.comarch.firstapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class PreviewDataFragment extends Fragment {

    private ImageButton backButton;
    private TextView name;
    private TextView surname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preview_data, container, false);
        name = (TextView) view.findViewById(R.id.name);
        surname = (TextView) view.findViewById(R.id.surname);
        backButton = (ImageButton) view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MainActivity.PlaceholderFragment())
                        .commit();
            }
        });

//        DatabaseExample database = new DatabaseExample(getActivity());
//        Data data = null;
//        if((data = database.getLastData()) == null){
//            Toast.makeText(getActivity(), "Sorry but here there is no data.", Toast.LENGTH_SHORT).show();
//        } else {
//            name.setText(data.getName());
//            surname.setText(data.getSurname());
//        }

        Uri data = Uri.parse(ExampleProvider.DATA_URL);
        Cursor c = getActivity().getContentResolver().query(data,null,null,null,null);

        if(c.moveToLast()){
            name.setText(c.getString(1));
            surname.setText(c.getString(2));
        }

        return view;
    }


}
