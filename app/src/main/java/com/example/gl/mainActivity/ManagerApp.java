package com.example.gl.mainActivity;

import com.example.gl.fragment.AboutUsFragments;
import com.example.gl.fragment.AllGamesFragments;
import com.example.gl.fragment.CoomingSoonFragments;
import com.example.gl.fragment.MainMenuFragments;
import com.example.gl.fragment.SearchFragments;
import com.example.gl.fragment.TopGamesFragments;
import com.example.gl.fragment.TrailerFragments;

import java.io.Serializable;

public class ManagerApp {





    public MainMenuFragments GetMainMenu(){


        return MainMenuFragments.newInstance();

    }

    public AllGamesFragments GetGames(){

        return AllGamesFragments.newInstance();

    }

    public SearchFragments GetSearch(){

        return SearchFragments.newInstance();

    }

    public TopGamesFragments GetTopGames(){

        return TopGamesFragments.newInstance();

    }

    public CoomingSoonFragments GetCoomingSoon(){

        return CoomingSoonFragments.newInstance();

    }

    public TrailerFragments GetTrailer(){

        return TrailerFragments.newInstance();

    }

    public AboutUsFragments GetAboutUs(){

        return  AboutUsFragments.newInstance();

    }

}
