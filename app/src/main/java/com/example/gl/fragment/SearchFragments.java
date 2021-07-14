package com.example.gl.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gl.R;
import com.example.gl.mainActivity.eExtantionType;
import com.example.gl.model.GenresModel;
import com.example.gl.model.PlatformModel;
import com.example.gl.model.PublisherModel;
import com.example.gl.response.IMyCallback;
import com.example.gl.response.ResponseFromAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragments extends Fragment implements View.OnClickListener
{


    public Spinner m_SpinnerGenres;
    private Spinner m_SpinnerPlatforms;
    private Spinner m_SpinnerPublisher;
    private Spinner m_SpinnerReleaseFrom;
    private Spinner m_SpinnerReleaseTo;

    private EditText m_TextSearch;
    private ViewGroup m_Cointener;
    private HashMap<Integer, String> m_IdPlatformAndNames = new HashMap<>();
    private View m_RootView;
    private LayoutInflater m_LayoutInflater;
    private View m_CategorisedGamesView;
    private List<String> m_GenresModel;
    private eExtantionType m_DataType;
    private Button m_ButtonSearch;
    private Bundle m_Bundle;
    private Button m_ButtonClear;
    private ResponseFromAPI m_ResponseFromApi = ResponseFromAPI.getInstance();
    private boolean m_FlagDate = true;


    private HashMap<eExtantionType, String > m_HashMap = new HashMap<>();



    public SearchFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment SearchFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragments newInstance() {
        SearchFragments fragment = new SearchFragments();

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
        m_RootView =  inflater.inflate(R.layout.fragment_search_fragments, container, false);
        m_Cointener = container;
        m_LayoutInflater = inflater;
        m_Bundle = savedInstanceState;
        boolean flagDate = true;

        m_SpinnerPlatforms = (Spinner)m_RootView.findViewById(R.id.spinner_platform);
        m_SpinnerPublisher = (Spinner)m_RootView.findViewById(R.id.spinner_publisher);
        m_SpinnerGenres = (Spinner)m_RootView.findViewById(R.id.spinner_gener);
        m_SpinnerReleaseFrom = (Spinner)m_RootView.findViewById(R.id.spinner_year_of_release_from);
        m_SpinnerReleaseTo = (Spinner)m_RootView.findViewById(R.id.spinner_year_of_release_to);

        m_ButtonSearch = (Button) m_RootView.findViewById(R.id.button_search);
        m_ButtonClear =  (Button) m_RootView.findViewById(R.id.button_clear);

        m_TextSearch =  m_RootView.findViewById(R.id.search_input_text);


        m_ResponseFromApi.GetListPlatformsName(new IMyCallback() {
            @Override
            public <T> void onSuccess(@NonNull List<T> spinner) {


                for (PlatformModel platformModel: (List<PlatformModel>)spinner){

                    m_IdPlatformAndNames.put(platformModel.getId(), platformModel.getName());
                    Log.d("TAG Hash","ID: " + platformModel.getId() + " Name: " + platformModel.getName());

                }

                spinnerPlatform(m_LayoutInflater, m_Cointener, m_Bundle, (List<PlatformModel>)spinner);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

        });

        m_ResponseFromApi.GetListPublishersName(new IMyCallback() {
            @Override
            public <T> void onSuccess(@NonNull List<T> spinner) {

                spinnerPublisher(m_LayoutInflater, m_Cointener, m_Bundle, (List<PublisherModel>)spinner);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }
        });

        m_ResponseFromApi.GetGenres(new IMyCallback() {
            @Override
            public <T> void onSuccess(@NonNull List<T> spinner) {

                spinnerGenres(m_LayoutInflater, m_Cointener, m_Bundle, (List<GenresModel>)spinner);

            }

            @Override
            public <T> void onSuccess(@NonNull T obj) {


            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }
        });

        spinnerYearsOfRelease();

        m_ButtonSearch.setOnClickListener(this);
        m_ButtonClear.setOnClickListener(this);

        m_SpinnerPlatforms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(parent.getItemAtPosition(position).equals("Choose Platform"))
                {

                    //do nothing
                    Log.d("", "onItemSelected: DO NOTHING2");

                }
                else
                    {

                    String taglia = m_SpinnerPlatforms.getSelectedItem().toString();
                    m_HashMap.put(eExtantionType.Platform, taglia);
                    Log.d("TAG", "onItemSelected: " + taglia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        m_SpinnerReleaseFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Year")){
                    //do nothing
                    Log.d("", "onItemSelected: DO NOTHING4");
                }
                else{
                    String taglia = m_SpinnerReleaseFrom.getSelectedItem().toString();
                    String date = taglia + "-01-01";
                    m_HashMap.put(eExtantionType.ReleaseDate, date);
                    Log.d("TAG", "onItemSelected: " + taglia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        m_SpinnerReleaseTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Year")){
                    //do nothing
                    Log.d("", "onItemSelected: DO NOTHING4");
                }
                else if (m_HashMap.containsKey(eExtantionType.ReleaseDate)){

                    String taglia = m_SpinnerReleaseTo.getSelectedItem().toString();
                    String date = taglia + "-01-01";
                    if(Integer.parseInt(date.substring(0,4)) < Integer.parseInt(m_HashMap.get(eExtantionType.ReleaseDate).substring(0,4))){
                        Log.d("TAG", "onItemSelected: NULLLLLLLLLLLLLLLLLLLLLLL");
                        m_FlagDate = false;

                    }

                    m_HashMap.merge(eExtantionType.ReleaseDate,date , (oldValue,newValue)-> oldValue + "," + newValue);
                    m_HashMap.put(eExtantionType.ReleaseDate, date);
                    Log.d("TAG", "onItemSelected: " + taglia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        m_SpinnerGenres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Genre")){
                    //do nothing
                    Log.d("", "onItemSelected: DO NOTHING1");
                }
                else{
                    String taglia = m_SpinnerGenres.getSelectedItem().toString();
                    m_HashMap.put(eExtantionType.Genre,taglia);
                    Log.d("TAG", "onItemSelected: " + taglia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        m_SpinnerPublisher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Publisher")){
                    //do nothing
                    Log.d("", "onItemSelected: DO NOTHING3");
                }
                else{
                    String taglia = m_SpinnerPublisher.getSelectedItem().toString();
                    m_HashMap.put(eExtantionType.Publisher,taglia);
                    Log.d("TAG", "onItemSelected: " + taglia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return m_RootView;

    }

    //Platform
    private void spinnerPlatform(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, List<PlatformModel> i_List)
    {


        List<String> platformNamesList = new ArrayList<>();
        platformNamesList.add(0,"Choose Platform");

                for(PlatformModel platform: i_List)
                {

                    platformNamesList.add(platform.getName());

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_spinner_item, platformNamesList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 m_SpinnerPlatforms.setAdapter(adapter);

    }

    //Year OF Release
    private void spinnerYearsOfRelease()
    {
        List<String> ReleaseYears = new ArrayList<>();
        ReleaseYears.add(0,"Choose Year");
        ReleaseYears.add("2000");
        ReleaseYears.add("2001");
        ReleaseYears.add("2002");
        ReleaseYears.add("2003");
        ReleaseYears.add("2004");
        ReleaseYears.add("2005");
        ReleaseYears.add("2006");
        ReleaseYears.add("2007");
        ReleaseYears.add("2008");
        ReleaseYears.add("2009");
        ReleaseYears.add("2010");
        ReleaseYears.add("2011");
        ReleaseYears.add("2012");
        ReleaseYears.add("2013");
        ReleaseYears.add("2014");
        ReleaseYears.add("2015");
        ReleaseYears.add("2016");
        ReleaseYears.add("2017");
        ReleaseYears.add("2018");
        ReleaseYears.add("2019");
        ReleaseYears.add("2020");
        ReleaseYears.add("2021");

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(m_LayoutInflater.getContext(), android.R.layout.simple_spinner_item, ReleaseYears);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        m_SpinnerReleaseFrom.setAdapter(adapter);
        m_SpinnerReleaseTo.setAdapter(adapter);



    }

    //Publisher
    private void spinnerPublisher(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, List<PublisherModel> i_List)
    {


        List<String> publisherNamesList = new ArrayList<>();
        publisherNamesList.add(0, "Choose Publisher");

        for(PublisherModel publisherModel: i_List)
        {

            publisherNamesList.add(publisherModel.getName());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_spinner_item, publisherNamesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_SpinnerPublisher.setAdapter(adapter);

    }

    //Genres
    private  void spinnerGenres(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, List<GenresModel> i_List) {


        List<String> genresModelNamesList = new ArrayList<>();
        genresModelNamesList.add(0, "Choose Genre");

        for(GenresModel genresModel: i_List)
        {

            genresModelNamesList.add(genresModel.getName());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_spinner_item, genresModelNamesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_SpinnerGenres.setAdapter(adapter);



    }

    @Override
    public void onClick(View v) {

        ResultBySearch filteredGamesFragment = null;
        switch (v.getId()) {

            case R.id.button_search:
                if (m_TextSearch.getText().toString().equals("What is on your mind?") && m_TextSearch.getText().toString().equals(""))
                {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("hashmap", m_HashMap);
                    Log.d("TAG", "Clcik Search");
                    filteredGamesFragment  = new ResultBySearch();
                    filteredGamesFragment.setArguments(bundle);

                }else
                    {

                    if (m_FlagDate == false)
                    {

                        Toast.makeText(m_RootView.getContext(),"Wrong Release Date Input, Please Fix it",Toast.LENGTH_LONG).show();

                    }else
                        {

                        Bundle bundle = new Bundle();
                        String input = m_TextSearch.getText().toString();
                        Log.d("TAG Input", input);
                        input = input.replace(' ', '-').toLowerCase();
                        bundle.putString("SearchQuery", input);
                        filteredGamesFragment  = new ResultBySearch();
                        filteredGamesFragment.setArguments(bundle);
                       // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, filteredGamesFragment).addToBackStack(null).commit();

                    }

                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, filteredGamesFragment).addToBackStack(null).commit();
                break;


            case R.id.button_clear:
                Log.d("TAG", "Clcik Clear");
                  m_SpinnerGenres.setSelection(0);
                  m_SpinnerPlatforms.setSelection(0);
                  m_SpinnerPublisher.setSelection(0);
                  m_SpinnerReleaseFrom.setSelection(0);
                  m_SpinnerReleaseTo.setSelection(0);
                  m_TextSearch.setText("What is on your mind?");
                  m_FlagDate = true;
                  m_HashMap.clear();
                break;

        }

    }

}