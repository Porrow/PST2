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
        //String a;
        //char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for(int i = 0; i < C; i++)
        {
            //a = String.valueOf(i+1);
            //g.fill(0);
            //g.text(a, x, y+i*h/8);
            for(int j = 0; j < C; j++)
            {
                //g.fill(0);
                //g.text(b[j], 90+j*h/8, 35);
                se.g.fill((i+j) % 2 * 255);
                rect(i * w/C, j * h/C, w/C, h/C);
            }
        }
    }
    
    @Override
    public void mouseClicked(int x, int y) {}

    @Override
    public void mouseMoved(int x, int y) {}
}
