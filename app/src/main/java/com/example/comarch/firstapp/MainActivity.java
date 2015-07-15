package com.example.comarch.firstapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private Button buttonForDataFragment;
        private Button buttonForPreviewFragment;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            buttonForDataFragment = (Button) rootView.findViewById(R.id.dataButton);
            buttonForPreviewFragment = (Button) rootView.findViewById(R.id.previewButton);
            buttonForDataFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataFragment dataFragment = new DataFragment();
                    FragmentManager fm = PlaceholderFragment.this.getActivity().getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, dataFragment).commit();
                }
            });

            buttonForPreviewFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreviewDataFragment previewDataFragment = new PreviewDataFragment();
                    FragmentManager fm = PlaceholderFragment.this.getActivity().getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, previewDataFragment).commit();
                }
            });

            return rootView;
        }
    }
}
