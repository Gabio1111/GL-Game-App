package com.example.gl.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gl.R;
import com.example.gl.adapter.AdapterGameModel;
import com.example.gl.mainActivity.eExtantionType;
import com.example.gl.model.GameModel;
import com.example.gl.response.IMyCallback;
import com.example.gl.response.ResponseFromAPI;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultBySearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultBySearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HashMap<eExtantionType,String> m_HashMap;
    private String m_Search;
    private ResponseFromAPI m_Respond;

    public ResultBySearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ResultBySearch.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultBySearch newInstance() {
        ResultBySearch fragment = new ResultBySearch();

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
        View root = inflater.inflate(R.layout.fragment_result_by_search, container, false);

        savedInstanceState = this.getArguments();

        if (savedInstanceState != null) {

            m_Search = savedInstanceState.getString("SearchQuery");

            if (m_Search == null) {

                m_Search = null;
                m_HashMap = new HashMap<>();
                m_HashMap = (HashMap<eExtantionType, String>) savedInstanceState.getSerializable("hashmap");

            } else {

                m_HashMap = null;
            }


        }

        m_Respond = ResponseFromAPI.getInstance();
        m_Respond.GetFilteredGHames(new IMyCallback() {

            @Override
            public <T> void onSuccess(@NonNull List<T> games) {

                featchDataToRecycleViewOfGames(R.id.recycle_view_filtered, (List<GameModel>)games, root, container);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable)
            {

                Toast.makeText(root.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT);

            }

        }, m_HashMap, m_Search);

        return root;

    }

    private void featchDataToRecycleViewOfGames(int i_TheRecycleView, List<GameModel> i_ListGame, View i_Root, ViewGroup i_ViewGroup) {

        RecyclerView recyclerView = i_Root.findViewById(i_TheRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(i_Root.getContext(), LinearLayoutManager.VERTICAL, false));
        AdapterGameModel adapterGameModel = new AdapterGameModel(i_ListGame, i_ViewGroup.getContext());
        recyclerView.setAdapter(adapterGameModel);

    }
}