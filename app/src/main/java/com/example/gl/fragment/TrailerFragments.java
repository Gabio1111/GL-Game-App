package com.example.gl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gl.R;
import com.example.gl.adapter.AdapterGameModel;
import com.example.gl.model.GameModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrailerFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrailerFragments extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    public TrailerFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment TrailerFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static TrailerFragments newInstance() {
        TrailerFragments fragment = new TrailerFragments();

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
        return inflater.inflate(R.layout.fragment_trailer_fragments, container, false);
    }

    private void featchDataToRecycleViewOfGames(int i_TheRecycleView, List<GameModel> i_ListGame, View i_Root, ViewGroup i_ViewGroup) {

        RecyclerView recyclerView = i_Root.findViewById(i_TheRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(i_Root.getContext(), LinearLayoutManager.VERTICAL, false));
        AdapterGameModel adapterGameModel = new AdapterGameModel(i_ListGame, i_ViewGroup.getContext());
        recyclerView.setAdapter(adapterGameModel);

    }
}