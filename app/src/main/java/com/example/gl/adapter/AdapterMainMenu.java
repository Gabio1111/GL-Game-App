package com.example.gl.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gl.R;
import com.example.gl.fragment.AllGamesFragments;
import com.example.gl.fragment.GameDetailFragments;
import com.example.gl.fragment.MainMenuFragments;
import com.example.gl.model.GameModel;
import com.example.gl.model.GameSingleModel;
import com.example.gl.response.IMyCallback;
import com.example.gl.response.ResponseFromAPI;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.ViewHolder>
{

    private List<GameModel> m_GameModelList;
    private Context m_NewContext;
    private ResponseFromAPI m_Response;
    private GameSingleModel m_gameSingle;

    public AdapterMainMenu(List<GameModel> m_GameModelList, Context m_NewContext) {
        this.m_GameModelList = m_GameModelList;
        this.m_NewContext = m_NewContext;
        m_Response = ResponseFromAPI.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_main_menu, parent, false);

        return new AdapterMainMenu.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        GameModel games = m_GameModelList.get(position);

        //set name of game
        holder.m_titleNameGame.setText(games.getName());

        //set image url on imageView
        String imageURL = games.getBackground_image();
        Picasso.with(m_NewContext).load(imageURL).into(holder.m_ImageView);

        CardView cardView = holder.m_CardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Toast.makeText(m_NewContext, games.getName(), Toast.LENGTH_LONG).show();
                String gameSlug = games.getSlug();
                m_Response.GetGameById(gameSlug, new IMyCallback() {
                    @Override
                    public <T> void onSuccess(@NonNull List<T> games) {

                    }

                    @Override
                    public <T> void onSuccess(@NonNull T obj) {

                        m_gameSingle = (GameSingleModel) obj;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("GameById", m_gameSingle);
                        GameDetailFragments gameDetailFragments = new GameDetailFragments();
                        gameDetailFragments.setArguments(bundle);
                        Log.d("TAG", "onSuccess:IN AFDAPTER  " + m_gameSingle.getName() + " " + m_gameSingle.getWebsiteURL());

                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  gameDetailFragments).addToBackStack(null).commit();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }
                });


            }
        });



    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView m_titleNameGame;
        ImageView m_ImageView;
        CardView m_CardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            m_titleNameGame = itemView.findViewById(R.id.text_view_name_game);
            m_ImageView = itemView.findViewById(R.id.image_view_profile_game);
            m_CardView = itemView.findViewById(R.id.card_view_main_menu);

        }
    }
}
