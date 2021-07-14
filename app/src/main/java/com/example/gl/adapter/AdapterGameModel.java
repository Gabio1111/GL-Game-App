package com.example.gl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gl.R;
import com.example.gl.model.GameModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGameModel  extends RecyclerView.Adapter<AdapterGameModel.ViewHolder>
{

    private List<GameModel> m_GameModelList;
    private Context m_NewContext;

    public AdapterGameModel(List<GameModel> i_GameModelList, Context i_NewContext) {
        this.m_GameModelList = i_GameModelList;
        this.m_NewContext = i_NewContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GameModel games = m_GameModelList.get(position);

        holder.m_titleNameGame.setText(games.getName());
        String imgaeURL = games.getBackground_image();
        Picasso.with(m_NewContext).load(imgaeURL).into(holder.m_ImageView);

    }

    @Override
    public int getItemCount() {
        return m_GameModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView m_titleNameGame;
        ImageView m_ImageView;
        CardView m_CardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            m_titleNameGame = itemView.findViewById(R.id.text_view_name_game);
            m_ImageView = itemView.findViewById(R.id.image_view_profile_game);
            m_CardView = itemView.findViewById(R.id.card_view);

        }
    }
}
