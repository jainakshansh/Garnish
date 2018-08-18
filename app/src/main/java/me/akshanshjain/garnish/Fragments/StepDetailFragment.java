package me.akshanshjain.garnish.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepDetailFragment extends Fragment {

    private Typeface QLight;

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private ArrayList<StepsItem> stepsItemArrayList;

    private TextView description;
    private ImageView thumbnail;
    private static final String STEPS_KEY = "STEPSINFO";
    private static final String CLICKED_POSITION = "CLICKEDPOSITION";
    private int clickedPosition;

    private static final String PLAYER_STATE = "PLAYER_STATE";
    private static final String PLAYER_POS = "PLAYER_POS";

    //Mandatory constructor for instantiating the fragment.
    public StepDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
        Inflates the fragment layout and carries out all the operations related to it.
        */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        /*
        Custom Typeface for all views with texts.
        */
        QLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Quicksand-Light.ttf");

        simpleExoPlayerView = rootView.findViewById(R.id.simple_exo_player_step_detail);
        stepsItemArrayList = new ArrayList<>();

        /*
        Getting the data from the host activity.
        */
        if (getArguments() != null) {
            stepsItemArrayList = getArguments().getParcelableArrayList(STEPS_KEY);
            clickedPosition = getArguments().getInt(CLICKED_POSITION);
        }

        //Setting the description text.
        description = rootView.findViewById(R.id.step_description_detail);
        description.setTypeface(QLight);
        description.setText(stepsItemArrayList.get(clickedPosition).getDescription());

        //Getting the thumbnail image view reference from the XML.
        thumbnail = rootView.findViewById(R.id.thumbnail_iv_step_det);

        //Creating an instance of the ExoPlayer.
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        simpleExoPlayerView.setPlayer(simpleExoPlayer);

        String userAgent = Util.getUserAgent(getActivity(), "Garnish");
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), userAgent);

        Uri mediaUri = Uri.parse(stepsItemArrayList.get(clickedPosition).getVideoUrl());
        if (mediaUri == null) {
            Toast.makeText(getContext(), "Video not available!", Toast.LENGTH_SHORT).show();
            thumbnail.setVisibility(View.VISIBLE);
            if (stepsItemArrayList.get(clickedPosition).getThumbnailUrl() != null) {
                Picasso.get()
                        .load(stepsItemArrayList.get(clickedPosition).getThumbnailUrl())
                        .into(thumbnail);
            } else {
                thumbnail.setImageResource(R.drawable.no_video);
            }
        }
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, dataSourceFactory, new DefaultExtractorsFactory(), null, null);

        /*
        Initializing the Exo player.
        Passing in the media uri sample to be played.
        */
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);

        /*
        Retaining the player position and state saved before the  lifecycle change.
        */
        if (savedInstanceState != null) {
            simpleExoPlayer.seekTo(savedInstanceState.getLong(PLAYER_POS));
            simpleExoPlayer.setPlayWhenReady(savedInstanceState.getBoolean(PLAYER_STATE));
        }

        //Returning the root view.
        return rootView;
    }

    /*
    Saving the current playing position and player state which can be retained after rotation or lifecycle changes.
    */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_POS, simpleExoPlayer.getCurrentPosition());
        outState.putBoolean(PLAYER_STATE, simpleExoPlayer.getPlayWhenReady());
    }

    /*
   Code to release the Exo Player.
   */
    private void releasePlayer() {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }

    /*
    Stopping the working of the exoplayer when the UI screen is not visible.
    */
    @Override
    public void onStop() {
        super.onStop();
        simpleExoPlayerView.setPlayer(null);
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            releasePlayer();
        }
    }

    /*
    Releasing the player when the fragment is destroyed.
    */
    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayerView.setPlayer(null);
    }
}
