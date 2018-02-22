package com.example.awesomefat.towerofhanoi_csc300_spring2018;

import android.view.ViewGroup;

/**
 * Created by awesomefat on 2/15/18.
 */

public class Tower
{
    private Disk top;
    private ViewGroup towerVisual;

    public Tower(ViewGroup towerVisual)
    {
        this.top = null;
        this.towerVisual = towerVisual;
    }

    public void push(Disk d)
    {
        //equivalent to add front for linked lists
        if(this.top == null)
        {
            this.top = d;
        }
        else
        {
            d.setNextDisk(this.top);
            this.top = d;
        }
        this.towerVisual.addView(d.getDiskVisual(), 0);
    }

    public Disk peek()
    {
        return this.top;
    }

    public Disk pop()
    {
        Disk disk2Remove = this.top;

        if(disk2Remove != null)
        {
            this.top = disk2Remove.getNextDisk();
            disk2Remove.setNextDisk(null);
            this.towerVisual.removeViewAt(0);
        }
        return disk2Remove;
    }
}
