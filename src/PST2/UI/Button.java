package PST2.UI;

import PST2.StratEdge;

public class Button extends GraphicObject
{   
    private static final String IMGPATH = "res/img/button/";
    
    private final int ID;
    private final int OPA1 = 80, OPA2 = 190;
    private int IMG1, IMG2;
    private Text t = null;
    private int img;
    private int opacity = 80;
    
    public Button(StratEdge se, Text t, int id)
    {
        super(se, t.getX() - (se.width-se.getW())/2, t.getY() - (se.height-se.getH())/2, t.getW(), t.getH());
        this.t = t;
        this.ID = id;
    }
    
    public Button(StratEdge se, int x, int y, int img1, int img2, int id)
    {
        super(se, x, y, 0, 0, IMGPATH);
        this.IMG1 = this.img = img1;
        this.IMG2 = img2;
        this.w = tabImg[img1].width;
        this.h = tabImg[img1].height;
        this.ID = id;
    }
    
    private void changeState(boolean state)                                     //state : false : bouton inactif, true : bouton actif
    {
        if(t != null)
            if(state)
                opacity = OPA2;
            else
                opacity = OPA1;
        else
            if(state)
                img = IMG2;
            else
                img = IMG1;
    }
    
    @Override
    public void init() {}

    @Override
    public void draw() 
    {
        if(t != null)
        {
            se.fill(255, opacity);
            rect(0, 0, w, h, 80);
        }
        else
            image(tabImg[img], 0, 0);
    }

    @Override
    public void mousePressed(int x, int y)
    {
        switch(ID)
        {
            case 0:                                                             //Actions lors d'un clic de souris sur le bouton jouer
                se.setView(1);
                changeState(false);
                break;
            case 1:                                                             //Actions lors d'un clic de souris sur le bouton quitter
                StratEdge.getSE().exit();
                break;
            case 2:
                se.setView(0);
                changeState(false);
                break;
        }
    }

    @Override
    public void mouseMoved(int x, int y)
    {
        if(isOn(x, y))
            changeState(true);
        else
            changeState(false);
            
        if(se.getViews()[0].getGO()[2].isOn(x, y) || se.getViews()[0].getGO()[4].isOn(x, y))
            se.getSurface().setCursor(12);                                      //Modification de l'apparence du curseur de la souris lorsque l'on est au-dessus d'un bouton
        else
            se.getSurface().setCursor(0);
    }
}