package com.safi.dicegame.ui.gamePlay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.safi.dicegame.R;
import com.safi.dicegame.data.Game;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GamePlayActivity extends AppCompatActivity {
    private static final String PARAM_GAME = "GAME";
    private GamePlayViewModel sharedViewModel;

    private Game getGameArg() {
        return (Game) getIntent().getExtras().getSerializable(PARAM_GAME);
    }

    public static void startActivity(Context context, Game game) {
        Intent intent = new Intent(context, GamePlayActivity.class);
        intent.putExtra(PARAM_GAME, game);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_game_play);
        sharedViewModel = new ViewModelProvider(this).get(GamePlayViewModel.class);
        sharedViewModel.setGame(getGameArg());
    }
}
