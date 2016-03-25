package PST2.UI;

import PST2.*;

import static PST2.Game.C;

public class Checker extends GraphicObject
{
    public static final int W = 800;
    
    public Checker(StratEdge se, int x, int y)
    {
        super(se, x, y, W, W);
    }

    @Override
    public void draw() 
    {
        for(int i = 0; i < C; i++)
            for(int j = 0; j < C; j++)
            {
                se.g.fill((i+j) % 2 * 255);
                rect(i * w/C, j * h/C, w/C, h/C);
            }
    }
    
    @Override
    public void mousePressed(int x, int y) {}

    @Override
    public void mouseMoved(int x, int y) {}
}
