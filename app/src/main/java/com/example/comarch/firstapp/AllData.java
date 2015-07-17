package com.example.comarch.firstapp;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;


public class AllData extends Fragment {

    private ImageButton backButton;
    private TableLayout tableLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_data, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        backButton = (ImageButton) view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MainActivity.PlaceholderFragment())
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Uri data = Uri.parse(ExampleProvider.DATA_URL);
        Cursor c = getActivity().getContentResolver().query(data,null,null,null,null);

        while(c.moveToNext()){
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            StringBuilder sb = new StringBuilder("id: ");
            sb.append(c.getInt(0));
            sb.append(", name: ");
            sb.append(c.getString(1));
            sb.append(", surname: ");
            sb.append(c.getString(2));
            textView.setText(sb.toString());
            tableLayout.addView(textView);
        }

    }
}
