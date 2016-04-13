package PST2.UI;

import PST2.*;
import processing.core.PGraphics;

import static PST2.Game.C;

public class Checker extends GraphicObject
{
    public static final int W = 800;
    private PGraphics img;
    
    public Checker(StratEdge se, int x, int y)
    {
        super(se, x, y, W, W);
    }

    @Override
    public void init()
    {
        img = se.createGraphics(W, W);
        img.beginDraw();
        for(int i = 0; i < C; i++)
            for(int j = 0; j < C; j++)
            {
                img.fill((i+j) % 2 * 255);
                img.rect(i * w/C, j * h/C, w/C, h/C);
            }
        img.endDraw();
    }
    
    @Override
    public void draw() 
    {
        image(img, 0, 0); 
    }
    
    @Override
    public void mousePressed(int x, int y) {}

    @Override
    public void mouseMoved(int x, int y) {}
}
