package PST2;

import processing.core.*; 

public class Piece 
{
    public final String IMGPATH = "res/img/";
    public String name;
    public int x;
    public int y;
    public boolean alive;
    public PImage icon; 
    
    public Piece(String name, int x, int y, PImage icon)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.icon = icon;
        alive = true;
    }
}
