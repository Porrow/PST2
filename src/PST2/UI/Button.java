package PST2.UI;

import PST2.StratEdge;

public class Button extends GraphicObject
{   
    private final Text t;
    
    public Button(StratEdge se, Text t)
    {
        super(se, t.getX(), t.getY(), t.getW(), t.getH());
        this.t = t;
    }
    
    @Override
    public void init() {}

    @Override
    public void draw() 
    {
        se.fill(255, 50);
        rect(0, 0, w, h);
    }

    @Override
    public void mousePressed(int x, int y)
    {
        switch(t.getText())
        {
            case "Jouer":                                                       //Actions lors d'un clic de souris sur le bouton jouer
                se.setView(se.getGame());
                break;
            case "Quitter":                                                     //Actions lors d'un clic de souris sur le bouton quitter
                StratEdge.getSE().exit();
        }
    }

    @Override
    public void mouseMoved(int x, int y)
    {
        se.getSurface().setCursor(12);                                          //Modification de l'apparence du curseur de la souris lorsque l'on est au-dessus d'un bouton
    }
}