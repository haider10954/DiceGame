package com.safi.dicegame.ui.setupGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.safi.dicegame.R;
import com.safi.dicegame.ui.gamePlay.GamePlayActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SetupGameActivity extends AppCompatActivity {
    private SetupGameViewModel sharedViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_setup_game);

        sharedViewModel = new ViewModelProvider(this).get(SetupGameViewModel.class);

        sharedViewModel.game.observe(this, game -> {
            finish();
            GamePlayActivity.startActivity(this, game);
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SetupGameActivity.class);
        context.startActivity(intent);
    }
}
