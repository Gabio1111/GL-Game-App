package com.example.gl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gl.R;
import com.example.gl.model.GameSingleModel;
import com.example.gl.model.PlatformModel;
import com.example.gl.response.ResponseForSingleGameModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameDetailFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameDetailFragments extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private View m_Root;
    private GameSingleModel m_GameSingle;
    private TextView m_NameGame;
    private ImageView m_ImageGame;
    private TextView m_RatingGame;
    private TextView m_AboutGame;
    private TextView m_ReleasedGame;
    private TextView m_WebGame;
    private TextView m_PlaytimeGame;
    private TextView m_PlatformGame;
    private TextView m_SeriesCountGame;

    public GameDetailFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameDetailFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static GameDetailFragments newInstance(String param1, String param2) {
        GameDetailFragments fragment = new GameDetailFragments();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        m_Root = inflater.inflate(R.layout.fragment_game_detail_fragments, container, false);
        m_NameGame = (TextView) m_Root.findViewById(R.id.name_game_details);
        m_ImageGame = (ImageView) m_Root.findViewById(R.id.image_profile_details);
        m_RatingGame = (TextView) m_Root.findViewById(R.id.rating_game_details);
        m_AboutGame = (TextView) m_Root.findViewById(R.id.about_game_details);
        m_PlatformGame  = (TextView)m_Root.findViewById(R.id.platform_list_game_details);
        m_ReleasedGame = (TextView) m_Root.findViewById(R.id.released_game_details);
        m_WebGame = (TextView) m_Root.findViewById(R.id.web_game_details);
        m_PlaytimeGame = (TextView) m_Root.findViewById(R.id.playtime_game_details);
        m_PlatformGame = (TextView) m_Root.findViewById(R.id.platform_list_game_details);
        m_SeriesCountGame =(TextView) m_Root.findViewById(R.id.game_series_count_details);

        savedInstanceState = this.getArguments();

        if(savedInstanceState != null){
            m_GameSingle = (GameSingleModel) savedInstanceState.getSerializable("GameById");
            Log.d("TAG", "onCreateView: GAMESINGLEFRAG " + m_GameSingle.getName());

            String imgaeURL = m_GameSingle.getBackground_image();
            Picasso.with(container.getContext()).load(imgaeURL).into(m_ImageGame);

            m_NameGame.setText(m_GameSingle.getName());

            m_RatingGame.setText(m_GameSingle.getRating().toString());

            StringBuilder platformList = new StringBuilder();

            for (ResponseForSingleGameModel platformModel: m_GameSingle.getPlatforms()){

                if (platformModel.getPlatform().getName() != null){

                    platformList.append(platformModel.getPlatform().getName() + ",");

                }

            }

            platformList.setLength(platformList.length() - 1);

            Log.d("List_Platform", platformList.toString());

            m_PlatformGame.setText(platformList);

            m_ReleasedGame.setText(m_GameSingle.getReleased());

            m_WebGame.setText(m_GameSingle.getWebsiteURL());

            m_PlaytimeGame.setText(m_GameSingle.getPlaytime().toString());

            m_SeriesCountGame.setText(m_GameSingle.getGame_series_count().toString());

            m_AboutGame.setText(m_GameSingle.getDescription());


        }



        return m_Root;
    }
}