package com.example.gl.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gl.R;
import com.example.gl.adapter.AdapterGameModel;
import com.example.gl.adapter.AdapterMainMenu;
import com.example.gl.mainActivity.ManagerApp;
import com.example.gl.model.GameModel;
import com.example.gl.response.IMyCallback;
import com.example.gl.response.ResponseFromAPI;

import java.util.List;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragments extends Fragment implements View.OnClickListener
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView m_RecycleViewGames;
    private AdapterGameModel m_AdapterGameModel;
    private View m_Root;
    private ViewGroup m_ViewGroup;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainMenuFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragments newInstance() {

        MainMenuFragments fragment = new MainMenuFragments();


        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        m_Root = inflater.inflate(R.layout.fragment_main_menu_fragments, container, false);
        m_ViewGroup = container;

        Button buttonSeeAllGame = (Button) m_Root.findViewById(R.id.click_see_all_game);
        Button buttonSeeAllTopGame = (Button)m_Root.findViewById(R.id.click_see_top_game);
        Button buttonSeeAllCoomingSoonGame = (Button)m_Root.findViewById(R.id.click_see_cooming_soon_game);


        ResponseFromAPI.getInstance().GetAllGames(new IMyCallback()
        {

            @Override
            public <T> void onSuccess(@NonNull List<T> games)
            {

                featchDataToRecycleViewOfGamesMainMenu(R.id.recycle_view_all_games, (List<GameModel>)games);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable)
            {

                Toast.makeText(m_Root.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT);

            }

        });

        ResponseFromAPI.getInstance().GetCoomingSoonGames(new IMyCallback()
        {

            @Override
            public <T> void onSuccess(@NonNull List<T> games)
            {

                featchDataToRecycleViewOfGamesMainMenu(R.id.recycler_view_cooming_soon_game, (List<GameModel>)games);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

                Toast.makeText(m_Root.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT);

            }

        });

        ResponseFromAPI.getInstance().GetTopGames(new IMyCallback()
        {

            @Override
            public <T> void onSuccess(@NonNull List<T> games)
            {

                featchDataToRecycleViewOfGamesMainMenu(R.id.recycle_view_top_game, (List<GameModel>)games);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable)
            {

                Toast.makeText(m_Root.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT);


            }

        });

        buttonSeeAllGame.setOnClickListener(this);
        buttonSeeAllTopGame.setOnClickListener(this);
        buttonSeeAllCoomingSoonGame.setOnClickListener(this);

        return m_Root;

    }

    @Override
    public void onClick(View v)
    {

                switch (v.getId()){

                    case R.id.click_see_all_game:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new AllGamesFragments()).addToBackStack(null).commit();
                        break;

                    case R.id.click_see_top_game:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new TopGamesFragments()).addToBackStack(null).commit();
                        break;

                    case R.id.click_see_cooming_soon_game:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new CoomingSoonFragments()).addToBackStack(null).commit();
                        break;

                    default:
                        break;
                }

    }


    private void featchDataToRecycleViewOfGamesMainMenu(int i_TheRecycleView, List<GameModel> i_ListGame) {

        m_RecycleViewGames = m_Root.findViewById(i_TheRecycleView);
        m_RecycleViewGames.setLayoutManager(new LinearLayoutManager(m_Root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        AdapterMainMenu adapterGameModel = new AdapterMainMenu(i_ListGame, m_ViewGroup.getContext());
        m_RecycleViewGames.setAdapter(adapterGameModel);

    }

//    private void featchDataToRecycleViewOfGames(int i_TheRecycleView, List<GameModel> i_ListGame, View i_Root, ViewGroup i_ViewGroup) {
//
//        m_RecycleViewGames = i_Root.findViewById(i_TheRecycleView);
//        m_RecycleViewGames.setLayoutManager(new LinearLayoutManager(i_Root.getContext(), LinearLayoutManager.VERTICAL, false));
//        AdapterGameModel adapterGameModel = new AdapterGameModel(i_ListGame, i_ViewGroup.getContext());
//        m_RecycleViewGames.setAdapter(adapterGameModel);
//
//    }

}