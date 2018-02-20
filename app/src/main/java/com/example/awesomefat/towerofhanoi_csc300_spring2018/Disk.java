package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.widget.Button;

/**
 * Created by awesomefat on 2/15/18.
 */

public class Disk
{
    private int size;
    private Disk nextDisk;
    private Button button;

    public Disk(int size, Button button)
    {
        this.size = size;
        this.nextDisk = null;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public int getSize() {
        return size;
    }

    public Disk getNextDisk() {
        return nextDisk;
    }

    public void setNextDisk(Disk nextDisk) {
        this.nextDisk = nextDisk;
    }
}
