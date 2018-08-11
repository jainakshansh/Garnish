package me.akshanshjain.garnish.Fragments;

import android.graphics.Typeface;
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

    private Typeface QLight;

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private ArrayList<StepsItem> stepsItemArrayList;

    private TextView description;
    private static final String STEPS_KEY = "STEPSINFO";
    private static final String CLICKED_POSITION = "CLICKEDPOSITION";
    private int clickedPosition;

    //Mandatory constructor for instantiating the fragment.
    public StepDetailFragment() {
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

        //Setting the uri details into the player.
        if (stepsItemArrayList.get(clickedPosition).getVideoUrl() != null) {
            initializePlayer(Uri.parse(stepsItemArrayList.get(clickedPosition).getVideoUrl()));
        } else if (stepsItemArrayList.get(clickedPosition).getThumbnailUrl() != null) {
            initializePlayer(Uri.parse(stepsItemArrayList.get(clickedPosition).getThumbnailUrl()));
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
