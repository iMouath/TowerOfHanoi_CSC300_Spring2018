package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewGroup placeHolderLL;
    private ViewGroup tower0LL;
    private ViewGroup tower1LL;
    private ViewGroup tower2LL;
    private Button tower0Button;
    private Button tower1Button;
    private Button tower2Button;
    private Button disk0SmallButton;
    private Button disk1MediumButton;
    private Button disk2LargeButton;
    private Tower tower0;
    private Tower tower1;
    private Tower tower2;
    private Disk disk0; //small
    private Disk disk1; //medium
    private Disk disk2; //large

    private Disk temp = null;

    private boolean popping = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.placeHolderLL = findViewById(R.id.placeHolderLL);
        this.tower0LL = findViewById(R.id.tower0);
        this.tower1LL = findViewById(R.id.tower1);
        this.tower2LL = findViewById(R.id.tower2);
        this.tower0Button = findViewById(R.id.tower0Button);
        this.tower1Button = findViewById(R.id.tower1Button);
        this.tower2Button = findViewById(R.id.tower2Button);
        this.disk0SmallButton = findViewById(R.id.disk0Small);
        this.disk1MediumButton = findViewById(R.id.disk1Medium);
        this.disk2LargeButton = findViewById(R.id.disk2Large);


        this.tower0 = new Tower(tower0LL);
        this.tower1 = new Tower(tower1LL);
        this.tower2 = new Tower(tower2LL);

        disk0 = new Disk(1, disk0SmallButton);
        disk1 = new Disk(2, disk1MediumButton);
        disk2 = new Disk(3, disk2LargeButton);

        disk2.setNextDisk(disk1); //medium sits on big
        disk1.setNextDisk(disk0); //small sits on medium

        this.tower0.push(disk2);
        this.tower0.push(disk1);
        this.tower0.push(disk0);


        System.out.print("BLAH");
    }

    public void onClickButton(View view) {
        if(this.popping){
            //Popping
            if (view == this.tower0Button) {
                //Tower 0
                try{
                    View diskToPop = this.tower0LL.getChildAt(0);
                    this.tower0LL.removeViewAt(0);
                    this.placeHolderLL.addView(diskToPop);
                    this.tower0.pop();
                    this.disk1.setNextDisk(null);

                    this.tower0Button.setText("PUSH");
                    this.tower1Button.setText("PUSH");
                    this.tower2Button.setText("PUSH");
                    this.popping = false;
                }catch (Exception e){
                    Toast.makeText(this, "Probably Empty tower", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            if (view == this.tower1Button) {
                //Tower 1
                try{
                    View diskToPop = this.tower1LL.getChildAt(0);
                    this.tower1LL.removeViewAt(0);
                    this.placeHolderLL.addView(diskToPop);
                    this.tower1.pop();

                    this.tower0Button.setText("PUSH");
                    this.tower1Button.setText("PUSH");
                    this.tower2Button.setText("PUSH");
                    this.popping = false;
                }catch (Exception e){
                    Toast.makeText(this, "Probably Empty tower", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            if (view == this.tower2Button) {
                //Tower 2
                try{
                    View diskToPop = this.tower2LL.getChildAt(0);
                    this.tower2LL.removeViewAt(0);
                    this.placeHolderLL.addView(diskToPop);
                    this.tower2.pop();

                    this.tower0Button.setText("PUSH");
                    this.tower1Button.setText("PUSH");
                    this.tower2Button.setText("PUSH");
                    this.popping = false;
                }catch (Exception e){
                    Toast.makeText(this, "Probably Empty tower", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
        else {
            //Pushing
            if (view == this.tower0Button) {
                //Tower 0
                View diskToPush = this.placeHolderLL.getChildAt(0);
                this.placeHolderLL.removeViewAt(0);
                this.tower0LL.addView(diskToPush);


                this.tower0Button.setText("POP");
                this.tower1Button.setText("POP");
                this.tower2Button.setText("POP");
                this.popping = true;
            }
            if(view == this.tower1Button){
                //Tower 1
                View diskToPush = this.placeHolderLL.getChildAt(0);
                this.placeHolderLL.removeViewAt(0);
                this.tower1LL.addView(diskToPush);


                this.tower0Button.setText("POP");
                this.tower1Button.setText("POP");
                this.tower2Button.setText("POP");
                this.popping = true;
            }
            if(view == this.tower2Button){
                //Tower 2
                View diskToPush = this.placeHolderLL.getChildAt(0);
                this.placeHolderLL.removeViewAt(0);
                this.tower2LL.addView(diskToPush);


                this.tower0Button.setText("POP");
                this.tower1Button.setText("POP");
                this.tower2Button.setText("POP");
                this.popping = true;
            }
        }
    }
}
