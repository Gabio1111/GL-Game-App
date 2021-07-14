package com.example.gl.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gl.R;
import com.example.gl.adapter.AdapterGameModel;
import com.example.gl.mainActivity.ManagerApp;
import com.example.gl.model.GameModel;
import com.example.gl.response.IMyCallback;
import com.example.gl.response.ResponseFromAPI;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopGamesFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopGamesFragments extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "respond_from_api";


    // TODO: Rename and change types of parameters

    public TopGamesFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment TopGamesFragments.

     */
    // TODO: Rename and change types and number of parameters
    public static TopGamesFragments newInstance()
    {

        TopGamesFragments fragment = new TopGamesFragments();

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
        View root = inflater.inflate(R.layout.fragment_top_games_fragments, container, false);

        ResponseFromAPI.getInstance().GetTopGames(new IMyCallback() {


            @Override
            public <T> void onSuccess(@NonNull List<T> games) {
                featchDataToRecycleViewOfGames(R.id.recycle_view_top_game, (List<GameModel>)games, root, container);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }
        });

        return root;

    }

    private void featchDataToRecycleViewOfGames(int i_TheRecycleView, List<GameModel> i_ListGame, View i_Root, ViewGroup i_ViewGroup) {

        RecyclerView recyclerView = i_Root.findViewById(i_TheRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(i_Root.getContext(), LinearLayoutManager.VERTICAL, false));
        AdapterGameModel adapterGameModel = new AdapterGameModel(i_ListGame, i_ViewGroup.getContext());
        recyclerView.setAdapter(adapterGameModel);

    }

}