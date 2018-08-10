package me.akshanshjain.garnish.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.util.ArrayList;

import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepDetailFragment extends Fragment {

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private ArrayList<StepsItem> stepsItemArrayList;

    private TextView description;

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

        simpleExoPlayerView = rootView.findViewById(R.id.simple_exo_player_step_detail);
        stepsItemArrayList = new ArrayList<>();

        description = rootView.findViewById(R.id.step_description_detail);

        //Testing the working of the player.
        initializePlayer(Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4"));

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

            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext().getApplicationContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            String userAgent = Util.getUserAgent(getContext(), "Garnish");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
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
    Releasing the player when the fragment is destroyed.
    */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }
}
