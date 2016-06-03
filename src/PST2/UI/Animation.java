package PST2.UI;

import PST2.Game;
import PST2.Piece.*;
import PST2.StratEdge;

public class Animation extends GraphicObject implements Runnable
{
    private static final String IMGPATH = "res/img/piece/";
    
    public static boolean stop = false;
    public static SEPiece p = null;
    private long time = 0;
    public static final int TIME = 40;
    private static final int SPEED = 10;
    private static final int COLLI = 1;
    private static final int TIC = 5;
    
    public Animation(StratEdge se, int x, int y)
    {
        super(se, x - (se.width-se.getW())/2, y - (se.height-se.getH())/2, Checker.W, Checker.W, IMGPATH);
    }

    @Override
    public void run()
    {
        Game g = se.getGame();
        Promotion promo = (Promotion)g.getGO()[6];
        while(!stop)
        {
            if(p != null)
            {
                if(SPEED <= time)
                {
                    time -= SPEED;
                    p.x1 -= p.vect[0];
                    p.y1 -= p.vect[1];
                    p.resetAnim();
                    if(p.d < COLLI)
                    {
                        p.move(p.cx, p.cy, g.getChecker());                     //On déplace la pièce sur la bonne case
                        g.promotion(p);                                         //Tentative de promotion
                        if(!promo.isVisible())
                            g.setTurn();                                        //On passe au tour suivant
                        p = null;
                    }
                }
                time += TIC;
            }
            try {Thread.sleep(TIC);}
            catch (InterruptedException e) {}
        }
    }
    
    @Override
    public void init()
    {}

    @Override
    public void draw()
    {
        if(p != null)
            image(tabImg[p.getImg()], (int)p.x1, (int)p.y1);
    }

    @Override
    public void mousePressed(int x, int y)
    {}

    @Override
    public void mouseMoved(int x, int y)
    {}
}
