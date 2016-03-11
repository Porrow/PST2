package PST2;

import PST2.IO.Read;
import PST2.UI.*;
import processing.core.*; 
import processing.event.MouseEvent;

public class StratEdge extends PApplet
{
    /*Constantes*/
    private final String TITLE = "Strat::Edge";                                 //Nom fen
    private final int FPS = 60;
    private final boolean DEBUG = true;                                         //True : affiche des informations de debuggage et de performance
    private final String IMGPATH = "res/img/";                                  //Chemin d'accès aux images
    private final String[] PN = {"Roi", "Reine", "Fou",                         //Noms des pièces (provisoire)
                                "Chevalier", "Tour", "Pion"};
    
    /*Variables*/
    private int w;                                                              //Largeur fenêtre
    private int h;                                                              //Hauteur fenêtre
    private Game game;                                                          //Objet qui gère le jeu

    public static void main(String[] args)
    {
        PApplet.main(new String[]{PST2.StratEdge.class.getName()});
    }
    
    @Override
    public void settings()
    {
        w = 1920;
        h = 1080;
        //fullScreen();
        size(w, h);                                                             //Taille de la fenêtre
    }
    
    @Override
    public void setup()
    {
        Piece.load(new Read(this));                                             //Charge tout ce qui concerne les pièces
        
        frameRate(FPS);
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        
        background (200);                                                       //Couleur d'arrière plan dans la fenêtre
        stroke (0);
        
        game = new Game(new int[]{4, 3, 2, 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5}, new int[]{4, 3, 2, 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5});//A mettre dans Event
        game.initGraphicObjects(this);
    }
    
    @Override
    public void draw() 
    {
        for(GraphicObject go : game.getGO())
            go.draw();
        /*stroke(255,0,0);
        point(50,50);*/
        /*Exemple d'événement:
        if (mousePressed)
            fill(0);
        else
            fill(255);
        rect(mouseX, mouseY, 80, 80);*/
        if(DEBUG)                                                               //Si le mode debug est actif
        {
            fill(255);
            stroke(0);
            strokeWeight(2);
            rect(3, 3, 50, 35);
            fill(0);
            text((int)(frameRate)+" FPS", 5, 18);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent event)
    {
        for(GraphicObject go : game.getGO())
            if(go.isOn(event.getX(), event.getY()))
                go.mouseClicked(event.getX(), event.getY());
    }
    
    /*Getters*/
    public int getW(){return w;}
    public int getH(){return h;}
    public Game getGame(){return game;}

}