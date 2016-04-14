package PST2.UI;

import PST2.*;
import java.io.File;
import processing.core.*;

public abstract class GraphicObject
{   
    private static final String[] ACCEPTED_EXT = {".png", ".jpg", ".gif"};      //Extensions d'image autorisées
    protected final StratEdge se;
    protected final int x;
    protected final int y;
    protected final int w;
    protected final int h;
    protected PImage[] tabImg;
    
    protected GraphicObject(StratEdge se, int x, int y, int w, int h)
    {
        this.se = se;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    protected GraphicObject(StratEdge se, int x, int y, int w, int h, String path)
    {
        this(se, x, y, w, h);
        tabImg = loadImages(path);
        init();
    }
    
    private PImage[] loadImages(String path)                                    //Charge un dossier d'image dans un tableau de PImage
    {
        String na;
        File[] files = new File(path).listFiles();
        PImage[] imgs = new PImage[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            na = files[i].getName();
            String ext = na.substring(na.lastIndexOf("."));
            for(String a_ext : ACCEPTED_EXT)
                if(ext.equals(a_ext))
                {   
                    ind = Integer.parseInt(na.substring(0, na.lastIndexOf(".")));
                    imgs[ind] = se.loadImage(path + na);
                }
        }
        return imgs;
    }

    protected void image(PImage i, int rx, int ry)
    {
        se.g.image(i, x + rx, y + ry);
    }
    
    protected void rect(int rx, int ry, int w, int h)
    {
        se.g.rect(x + rx, y + ry, w, h);
    }
    
    protected void ellipse(int rx, int ry, int w, int h)
    {
        se.g.ellipseMode(PApplet.CENTER);
        se.g.ellipse(x + rx, y + ry, w, h);
    }
    
    protected void text(String txt, int rx, int ry)
    {
        se.g.text(txt, x + rx, y + ry);
    }
    
    /*Getters*/
    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return w;}
    public int getH(){return h;}
    public int getRX(int px){return px - x;}                                    //Position relative en x
    public int getRY(int py){return py - y;}                                    //Position relative en y
    public boolean isOn(int mx, int my)                                         //Renvoie true si le point (mx, my) appartient au GO
    {
        return (mx >= x) && (mx < x+w) && (my >= y) && (my < y+h);
    }
    
    /*Abstract methods*/
    public abstract void init();                                                //Gestion de l'initialisation du composant graphique
    public abstract void draw();                                                //Gestion de l'affichage du composant à l'aide de processing (60 ips)
    public abstract void mousePressed(int x, int y);                            //Gestion des clics de souris
    public abstract void mouseMoved(int x, int y);                              //Gestion du mouvement de la souris
}