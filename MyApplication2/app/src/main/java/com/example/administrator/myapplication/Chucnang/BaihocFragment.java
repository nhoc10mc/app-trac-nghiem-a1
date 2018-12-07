package com.example.administrator.myapplication.Chucnang;


import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.PlayVideoActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.VideoYouTube;
import com.example.administrator.myapplication.VideoYoutubeAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaihocFragment extends Fragment {
    public static String API_KEY = "AIzaSyDRmLvyWPzzwAoaasADzvTIQGZt427pGyA";
    String ID_PHAYLIST = "PLbhOMRbJKNlGynFokAJlS9RY-7cYsaZnu";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + ID_PHAYLIST + "&key=" + API_KEY + "&maxResults=50";
    ListView lvVideo;
    ArrayList<VideoYouTube> arrayVieo;
    VideoYoutubeAdapter adapter;



    public BaihocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //((MainActivity) getActivity()).getActionBar().setTitle("Bai hoc");
        return inflater.inflate(R.layout.fragment_baihoc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvVideo = (ListView) getActivity().findViewById(R.id.listviewVideo);
        arrayVieo = new ArrayList<>();

        adapter = new VideoYoutubeAdapter(getActivity(),R.layout.row_video_youtube,arrayVieo);
        lvVideo.setAdapter(adapter);


        GetJsonYouTube(urlGetJson);


        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("idVideoYouTube", arrayVieo.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void GetJsonYouTube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("items");
                    String title = "";
                    String url = "";
                    String idVideo = "";
                    for (int i = 0; i < jsonItems.length(); i++) {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceID.getString("videoId");
                        //Toast.makeText(getActivity(), idVideo, Toast.LENGTH_SHORT).show();
                        arrayVieo.add(new VideoYouTube(title,url,idVideo));
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Loi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
