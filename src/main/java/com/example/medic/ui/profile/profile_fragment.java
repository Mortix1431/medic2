package com.example.medic.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.medic.R;

import java.io.InputStream;

public class profile_fragment extends Fragment {

    private profileViewModel mViewModel;

    public static profile_fragment newInstance() {
        return new profile_fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(profileViewModel.class);
        new DownloadImageTask((ImageView) getView().findViewById(R.id.card_avatar))
                .execute("http://45.132.1.160/bochi.png");
        // TODO: Use the ViewModel
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        new DownloadImageTask((ImageView) getView().findViewById(R.id.card_avatar))
                .execute("http://45.132.1.160/bochi.png");
    }*/

}

