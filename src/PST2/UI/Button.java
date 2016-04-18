package PST2.UI;

import PST2.StratEdge;
import processing.core.PFont;

public class Button extends GraphicObject
{

    private static final String IMGPATH = "res/font/";
    private final String text;
    private PFont font;
    
    public Button(StratEdge se, int x, int y, int w, int h, int size, String text, String font)
    {
        super(se, x, y, w, h);
        System.out.println(x);
        this.text = text;
        this.font = se.createFont(IMGPATH + font, size);
    }
    
    @Override
    public void init() {}

    @Override
    public void draw() 
    {
        se.textFont(font);
        se.fill(255);
        rect(0, 0, w, h);
        se.fill(0);
        text(text, w/2, h/2);
    }

    @Override
    public void mousePressed(int x, int y)
    {
        switch(text)
        {
            case "Jouer":
                se.setView(se.getGame());
                break;
            case "Quitter":
                System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {}
}