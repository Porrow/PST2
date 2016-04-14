package PST2.UI;

import PST2.StratEdge;

public class Background extends GraphicObject
{
    private static final String IMGPATH = "res/img/background/";
    
    public Background(StratEdge se)
    {
        super(se, 0, 0, se.getW(), se.getH(), IMGPATH);
    }

    @Override
    public void init(){}
    
    @Override
    public void draw() 
    {
        image(tabImg[0], 0, 0);
    }
    
    @Override
    public void mousePressed(int x, int y) {}

    @Override
    public void mouseMoved(int x, int y) {}
}
