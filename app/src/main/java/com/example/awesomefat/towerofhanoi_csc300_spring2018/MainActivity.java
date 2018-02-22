package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Tower[] towers = new Tower[3];
    private Disk placeholder = null;
    private ViewGroup placeholderVG;
    private Button[] towerButtons = new Button[3];
    private boolean shouldPop = true;
    private int movesCounter = 0;
    private TextView movesCounterTV;
    private ViewGroup tower2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.towerButtons[0] = this.findViewById(R.id.tower0Button);
        this.towerButtons[1] = this.findViewById(R.id.tower1Button);
        this.towerButtons[2] = this.findViewById(R.id.tower2Button);

        this.placeholderVG = this.findViewById(R.id.placeHolderLL);

        this.movesCounterTV = this.findViewById(R.id.movesCounterTV);

        Button disk0 = this.findViewById(R.id.disk0Small);
        Button disk1 = this.findViewById(R.id.disk1Medium);
        Button disk2 = this.findViewById(R.id.disk2Large);

        Disk d0 = new Disk(0, disk0);
        Disk d1 = new Disk(1, disk1);
        Disk d2 = new Disk(2, disk2);

        ViewGroup tower0 = this.findViewById(R.id.tower0);
        ViewGroup tower1 = this.findViewById(R.id.tower1);

        tower2 = this.findViewById(R.id.tower2);

        Tower t0 = new Tower(tower0);
        Tower t1 = new Tower(tower1);
        Tower t2 = new Tower(tower2);

        this.towers[0] = t0;
        this.towers[1] = t1;
        this.towers[2] = t2;

        //initialize our game
        tower0.removeAllViews();
        this.towers[0].push(d2);
        this.towers[0].push(d1);
        this.towers[0].push(d0);
    }

    private void tryToPopFromTower(int towerIndex)
    {
        Disk temp = this.towers[towerIndex].pop();
        if(temp != null)
        {
            this.placeholder = temp;
            this.placeholderVG.addView(temp.getDiskVisual());
            this.shouldPop = false;
            for(Button b : this.towerButtons)
            {
                b.setText("PUSH");
            }
            this.movesCounter += 1;
            this.movesCounterTV.setText("Moves Counter: " + this.movesCounter);
        }
        else{
            Toast.makeText(this, "Illegal Move!", Toast.LENGTH_SHORT).show();
        }
    }

    private void tryToPushToTower(int towerIndex)
    {
        Disk temp = this.towers[towerIndex].peek();
        if(temp == null || this.placeholder.getSize() < temp.getSize())
        {
            this.placeholderVG.removeAllViews();
            this.towers[towerIndex].push(this.placeholder);
            this.placeholder = null;
            this.shouldPop = true;
            for(Button b : this.towerButtons)
            {
                b.setText("POP");
            }
            this.movesCounter += 1;
            this.movesCounterTV.setText("Moves Counter: " + this.movesCounter);
        }
        else{
            Toast.makeText(this, "Illegal Move!", Toast.LENGTH_SHORT).show();
        }
    }

    public void tower0ButtonPressed(View v)
    {
        if(this.shouldPop)
        {
            this.tryToPopFromTower(0);
        }
        else
        {
            this.tryToPushToTower(0);
        }
    }

    public void tower1ButtonPressed(View v)
    {
        if(this.shouldPop)
        {
            this.tryToPopFromTower(1);
        }
        else
        {
            this.tryToPushToTower(1);
        }
    }

    public void tower2ButtonPressed(View v)
    {
        if(this.shouldPop)
        {
            this.tryToPopFromTower(2);
        }
        else
        {
            this.tryToPushToTower(2);
        }
        if(this.tower2.getChildCount() == 3){
            Toast.makeText(this, "Game Won!\nMoves: " + this.movesCounter, Toast.LENGTH_LONG).show();
            for(Button b : this.towerButtons)
            {
                b.setEnabled(false);
                b.setVisibility(View.INVISIBLE);
            }
            Button restartBtn = findViewById(R.id.restartBtn);
            restartBtn.setVisibility(View.VISIBLE);
            restartBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MainActivity.super.recreate();
                }
            });
        }
    }

}
