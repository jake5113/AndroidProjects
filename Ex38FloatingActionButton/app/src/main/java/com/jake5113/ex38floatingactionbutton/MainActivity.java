package com.jake5113.ex38floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ExtendedFloatingActionButton extFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "clicked FAB", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });

        extFab = findViewById(R.id.ext_fab);
        extFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extFab.isExtended()) {
                    CoordinatorLayout layout = findViewById(R.id.snackbar_container);
                    Snackbar.make(layout, "clicked ADD", Snackbar.LENGTH_INDEFINITE)
                            .setAction("확인", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "TOAST", Toast.LENGTH_SHORT).show();
                                    extFab.shrink();
                                }
                            }).show();

                } else extFab.extend();
            }
        });

    }
}