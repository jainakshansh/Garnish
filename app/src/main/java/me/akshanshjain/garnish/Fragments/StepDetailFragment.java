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
    private boolean isPlayWhenReady;
    private static final String PLAYER_POS = "PLAYER_POS";
    private long playerPosition;

    private Uri mediaUri;

    //Mandatory constructor for instantiating the fragment.
    public StepDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /*
        Inflates the fragment layout and carries out all the operations related to it.
        */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        isPlayWhenReady = true;

        //Referencing the imageview from the XML.
        thumbnail = rootView.findViewById(R.id.thumbnail_iv_frag_step_det);
        /*
        Retaining the player position and state saved before the  lifecycle change.
        */
        if (savedInstanceState != null) {
            playerPosition = savedInstanceState.getLong(PLAYER_POS);
            isPlayWhenReady = savedInstanceState.getBoolean(PLAYER_STATE);
        }

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

        //Setting the uri details into the player.
        if (stepsItemArrayList.get(clickedPosition).getVideoUrl() != null) {
            mediaUri = Uri.parse(stepsItemArrayList.get(clickedPosition).getVideoUrl());
            initializePlayer(mediaUri);
        } else if (stepsItemArrayList.get(clickedPosition).getThumbnailUrl() != null) {
            if (!stepsItemArrayList.get(clickedPosition).getThumbnailUrl().endsWith("mp4")) {
                //Setting the image into the image view below the video view.
                simpleExoPlayerView.setVisibility(View.GONE);
                Picasso.get()
                        .load(stepsItemArrayList.get(clickedPosition).getThumbnailUrl())
                        .into(thumbnail);
                thumbnail.setVisibility(View.VISIBLE);
            } else {
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.no_video);
                simpleExoPlayerView.setDefaultArtwork(bitmap);
            }
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.no_video);
            simpleExoPlayerView.setDefaultArtwork(bitmap);
            Toast.makeText(getContext(), "Video not available!", Toast.LENGTH_SHORT).show();
        }

        //Returning the root view.
        return rootView;
    }

    /*
    Initializing the Exo player.
    Passing in the media uri sample to be played.
    */
    private void initializePlayer(Uri mediaUri) {
        if (simpleExoPlayer == null) {

            //Creating an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            //Preparing the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "Garnish");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.seekTo(playerPosition);
            simpleExoPlayer.setPlayWhenReady(isPlayWhenReady);
            simpleExoPlayer.prepare(mediaSource);
        }
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

    @Override
    public void onResume() {
        super.onResume();
        initializePlayer(mediaUri);
    }

    /*
    Releasing the player when the fragment is destroyed.
    */

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayerView.setPlayer(null);
        if (simpleExoPlayer != null) {
            releasePlayer();
        }
    }

    /*
    Saving the current playing position and player state which can be retained after rotation or lifecycle changes.
    */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        long currentPos = simpleExoPlayer.getCurrentPosition();
        isPlayWhenReady = simpleExoPlayer.getPlayWhenReady();
        outState.putLong(PLAYER_POS, currentPos);
        outState.putBoolean(PLAYER_STATE, isPlayWhenReady);
        Log.d("ADebug", "" + isPlayWhenReady);
    }
}
