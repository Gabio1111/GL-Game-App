package com.example.gl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gl.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUsFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUsFragments extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    public AboutUsFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment AboutUsFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUsFragments newInstance() {
        AboutUsFragments fragment = new AboutUsFragments();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about_us_fragments, container, false);


        return root;

    }
}