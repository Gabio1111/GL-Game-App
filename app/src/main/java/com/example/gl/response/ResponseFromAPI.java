package com.example.gl.response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gl.R;
import com.example.gl.apiClient.ApiClient;
import com.example.gl.model.GameModel;
import com.example.gl.model.GameSingleModel;
import com.example.gl.model.GenresModel;
import com.example.gl.model.PlatformModel;
import com.example.gl.model.PublisherModel;
import com.example.gl.mainActivity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseFromAPI
{

    private final String API_KEY = "API KEY";

    private List<GameModel> m_ListGameModel;
    private List<GameModel> m_ListTopGameModel;
    private List<GameModel> m_ListCoomingSoonGameModel;
    private List<PlatformModel> m_ListAdapterPlatformModel;
    private List<PublisherModel> m_ListAdapterPublishersModel;

    private static ResponseFromAPI s_instance = null;
    private List<GameModel> m_GameList;
    private List<GenresModel> m_GenresList;


    private String m_Genres;
    private Integer m_Page;
    private Integer m_PageSize;
    private String m_Platforms;
    private String m_Developers;
    private String m_Publishers;
    private String m_Dates;
    private String m_Ordering;
    private String m_Search;

    private static ReentrantLock s_Lock = new ReentrantLock();

    private ResponseFromAPI()
    {

        m_ListGameModel = new ArrayList<>();
        m_ListTopGameModel = new ArrayList<>();
        m_ListCoomingSoonGameModel = new ArrayList<>();
        m_ListAdapterPlatformModel = new ArrayList<>();
        m_ListAdapterPublishersModel = new ArrayList<>();
        m_GameList = new ArrayList<>();
        m_GenresList = new ArrayList<>();


        m_Genres = null;
        m_Page = 1;
        m_PageSize = 20;
        m_Platforms = null;
        m_Developers = null;
        m_Publishers = null;
        m_Dates = null;
        m_Ordering = null;
        m_Search = null;

    }

    //Get instance
    public static ResponseFromAPI getInstance(){

        s_Lock.lock();

        try
        {

            if (s_instance == null)
            {

                s_instance = new ResponseFromAPI();

            }

            return s_instance;
        }

        finally
        {

            s_Lock.unlock();

        }

    }

    //Search the game by id or slug
    public void GetGameById(String i_GameIdOrSulg,IMyCallback callback){

//        if (!i_GameIdOrSulg.matches("[0-9]+"))
//        {
//
//            i_GameIdOrSulg = i_GameIdOrSulg.toLowerCase();
//
//        }

        Call<GameSingleModel> call = ApiClient.getInstance().getApi().getGameById( i_GameIdOrSulg,API_KEY);
        call.enqueue(new Callback<GameSingleModel>() {
            @Override
            public void onResponse(Call<GameSingleModel> call, Response<GameSingleModel> response) {
                GameSingleModel gameSingleModel = response.body();
                Log.d("TAG", "onResponse: " + gameSingleModel.getName());
                if(callback != null){
                    callback.onSuccess(gameSingleModel);
                }
            }

            @Override
            public void onFailure(Call<GameSingleModel> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    //Get list of name genres
    public void GetGenres(@Nullable IMyCallback i_MyCallbackSpinner){


        Call<ResponseGenresModel> call = ApiClient.getInstance().getApi().getGenres(API_KEY);

        call.enqueue(new Callback<ResponseGenresModel>()
        {

            @Override
            public void onResponse(Call<ResponseGenresModel> call, Response<ResponseGenresModel> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {

                    try{

                        m_GenresList.clear();
                        m_GenresList = response.body().getResults();

                        if (i_MyCallbackSpinner != null)
                        {

                            i_MyCallbackSpinner.onSuccess(m_GenresList);

                        }

                    }catch (Exception e){

                        i_MyCallbackSpinner.onError(e);

                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseGenresModel> call, Throwable t)
            {

               i_MyCallbackSpinner.onError(t);

            }

        });

    }

    //Get list of name platforms
    public void GetListPlatformsName(@NonNull IMyCallback i_MyCallbackSpinner) {

        Call<ResponsePlatformModel> call = ApiClient.getInstance().getApi().getListPlatforms(m_Page, API_KEY);

        call.enqueue(new Callback<ResponsePlatformModel>() {
            @Override
            public void onResponse(Call<ResponsePlatformModel> call, Response<ResponsePlatformModel> response) {

                m_ListAdapterPlatformModel.clear();
                m_ListAdapterPlatformModel = response.body().getResults();

                if (i_MyCallbackSpinner != null)
                {
                    i_MyCallbackSpinner.onSuccess(m_ListAdapterPlatformModel);

                    Log.d("Platform Size", String.valueOf(m_ListAdapterPlatformModel.size()));

                }

            }

            @Override
            public void onFailure(Call<ResponsePlatformModel> call, Throwable t) {

                i_MyCallbackSpinner.onError(t);

            }

        });

    }

    //Get list of name publishers
    public void GetListPublishersName(@NonNull IMyCallback i_MyCallbackSpinner){

        Call<ResponsePublisherModel> call = ApiClient.getInstance().getApi().getListPublishers(m_Page, API_KEY);

        call.enqueue(new Callback<ResponsePublisherModel>() {
            @Override
            public void onResponse(Call<ResponsePublisherModel> call, Response<ResponsePublisherModel> response) {


                try{

                    m_ListAdapterPublishersModel.clear();
                    m_ListAdapterPublishersModel = response.body().getResults();

                    if (i_MyCallbackSpinner != null)
                    {

                        i_MyCallbackSpinner.onSuccess(m_ListAdapterPublishersModel);

                    }

                    Log.d("Publisher Size", String.valueOf(m_ListAdapterPlatformModel.size()));

                }catch (Exception e){

                    i_MyCallbackSpinner.onError(e);

                }

            }

            @Override
            public void onFailure(Call<ResponsePublisherModel> call, Throwable t) {

                i_MyCallbackSpinner.onError(t);

            }
        });

    }

    //Get all games
    public void GetAllGames(@Nullable IMyCallback i_MyCallback) {

        // call from rawg api
        Call<ResponseGameModel> call = ApiClient.getInstance().getApi().getGames(API_KEY, String.valueOf(m_Page),
                String.valueOf(m_PageSize), m_Genres, m_Platforms, m_Developers, m_Publishers, m_Dates, m_Ordering, m_Search);


        call.enqueue(new Callback<ResponseGameModel>() {
            @Override
            public void onResponse(Call<ResponseGameModel> call, Response<ResponseGameModel> response) {


                m_ListGameModel.clear();
                m_ListGameModel = response.body().getResults();

                if (i_MyCallback != null) {//send the list top game for fragment

                    i_MyCallback.onSuccess(m_ListGameModel);
                }

            }

            @Override
            public void onFailure(Call<ResponseGameModel> call, Throwable t) {

                i_MyCallback.onError(t);

            }

        });

    }

    //Get top games
    public void GetTopGames(@Nullable IMyCallback i_MyCallback)
    {

        // call from rawg api
        Call<ResponseGameModel> call = ApiClient.getInstance().getApi().getGames(API_KEY, String.valueOf(m_Page),
                String.valueOf(m_PageSize), m_Genres, m_Platforms, m_Developers, m_Publishers, "2021-06-01,2021-06-30", "-added", m_Search);


        call.enqueue(new Callback<ResponseGameModel>()
        {

            @Override
            public void onResponse(Call<ResponseGameModel> call, Response<ResponseGameModel> response) {

                m_ListTopGameModel.clear();
                m_ListTopGameModel = response.body().getResults();

                if (i_MyCallback != null)
                {//send the list top game for fragment

                    i_MyCallback.onSuccess(m_ListTopGameModel);

                }

            }

            @Override
            public void onFailure(Call<ResponseGameModel> call, Throwable t)
            {

                i_MyCallback.onError(t);

            }

        });

    }

    //Get cooming soon games
    public void GetCoomingSoonGames(@Nullable IMyCallback i_MyCallback) {

        // call from rawg api
        Call<ResponseGameModel> call = ApiClient.getInstance().getApi().getGames(API_KEY, String.valueOf(m_Page),
                String.valueOf(m_PageSize), m_Genres, m_Platforms, m_Developers, m_Publishers, "2021-06-08,2021-08-31", "-added",  m_Search);


        call.enqueue(new Callback<ResponseGameModel>() {
            @Override
            public void onResponse(Call<ResponseGameModel> call, Response<ResponseGameModel> response) {


                m_ListCoomingSoonGameModel.clear();
                m_ListCoomingSoonGameModel = response.body().getResults();

                if (i_MyCallback != null) {//send the list top game for fragment

                    i_MyCallback.onSuccess(m_ListCoomingSoonGameModel);
                }

            }

            @Override
            public void onFailure(Call<ResponseGameModel> call, Throwable t) {

                i_MyCallback.onError(t);

            }

        });

    }

    //Get game filter
    public void GetFilteredGHames(@Nullable IMyCallback i_MyCallback, @Nullable HashMap<eExtantionType, String> i_HashMap, @Nullable String i_Search) {

        if (i_HashMap != null)
        {

            m_Search = null;
            for (Map.Entry<eExtantionType, String> Type : i_HashMap.entrySet()) {

                String value = Type.getValue().replace(' ', '-').toLowerCase();
                Type.setValue(value);

                switch (Type.getKey()) {

                    case Genre:
                        m_Genres = Type.getValue();
                        break;

                    case Platform:
                        m_Platforms = Type.getValue();
                        break;

                    case Developers:
                        m_Developers = Type.getValue();
                        break;

                    case Publisher:
                        m_Publishers = Type.getValue();
                        break;

                    case ReleaseDate:
                        m_Dates = Type.getValue();
                        break;

                    default:
                        break;

                }

            }

        }else if (i_Search != null)
        {

             i_Search = i_Search.replace(' ', '-').toLowerCase();
             m_Search = i_Search;

        }

            Call<ResponseGameModel> call = ApiClient.getInstance().getApi().getGames(API_KEY, String.valueOf(m_Page),
                    String.valueOf(m_PageSize), m_Genres, m_Platforms, m_Developers, m_Publishers, m_Ordering,  m_Dates, m_Search);

            m_Search = null;
            call.enqueue(new Callback<ResponseGameModel>()
            {

                @Override
                public void onResponse(Call<ResponseGameModel> call, Response<ResponseGameModel> response)
                {

                    try
                    {

                        m_GameList.clear();// clear the list game
                        m_GameList = response.body().getResults();//result of games

                        if (i_MyCallback != null)
                        {

                            i_MyCallback.onSuccess(m_GameList);

                        }

                    }
                    catch (Exception e)
                    {

                         i_MyCallback.onError(e);

                    }

                }

                @Override
                public void onFailure(Call<ResponseGameModel> call, Throwable t)
                {

                  i_MyCallback.onError(t);

                }

            });

        }

}
