package belenkov.samsung.ru.gametestgroup3.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import belenkov.samsung.ru.gametestgroup3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurface(this));
    }
}
