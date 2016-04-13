package PST2.UI;

import PST2.StratEdge;

public class Debug extends GraphicObject
{
    private final boolean DEBUG = true;                                         //True : affiche des informations de debuggage et de performance

    public Debug(StratEdge se, int x, int y, int w, int h)
    {
        super(se, x, y, w, h);
    }

    @Override
    public void init(){}
    
    @Override
    public void draw()
    {
        if(DEBUG) //Si le mode debug est actif
        {
            se.g.fill(255);
            se.g.stroke(0);
            se.g.strokeWeight(2);
            rect(0, 0, w, h);
            se.g.fill(0);
            text((int) (se.getFPS()) + " FPS", 5, 18);
        }
    }

    @Override
    public void mousePressed(int x, int y){}

    @Override
    public void mouseMoved(int x, int y){}
}
