package PST2;

import PST2.Piece.SEPiece;
import PST2.IO.Read;
import PST2.Piece.*;
import PST2.UI.*;
import processing.core.*; 
import processing.event.MouseEvent;

public class StratEdge extends PApplet
{
    /*Constantes*/
    private final String TITLE = "Strat::Edge";                                 //Nom fen
    private final int FPS = 60;
    //private final String IMGPATH = "res/img/";                                  //Chemin d'accès aux images
    //private final String[] PN = {"Roi", "Reine", "Fou",                         //Noms des pièces (provisoire)
    //                            "Chevalier", "Tour", "Pion"};
    
    /*Variables*/
    public PGraphics g2;
    private int w;                                                              //Largeur fenêtre
    private int h;                                                              //Hauteur fenêtre
    private Game game;                                                          //Objet qui gère le jeu
    private static StratEdge se;

    public static void main(String[] args)
    {
        PApplet.main(new String[]{PST2.StratEdge.class.getName()});
    }
    
    @Override
    public void settings()
    {
        se = this;
        
        w = 1920;
        h = 1080;
        //fullScreen();
        size(w, h);                                                             //Taille de la fenêtre
    }
    
    @Override
    public void setup()
    {
        SEPiece.load(new Read());                                               //Charge tout ce qui concerne les pièces
        
        frameRate(FPS);
        surface.setTitle(TITLE);                                                //Modifie le titre de la fen
        
        background(100);                                                        //Couleur d'arrière plan dans la fenêtre
        stroke (0);
        
        Team t1 = new Team(new int[]{4, 3, 2, 1, 0, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5}, Piece.TEAM1);
        Team t2 = new Team(new int[]{10, 9, 8, 7, 6, 8, 9, 10, 11, 11, 11, 11, 11, 11, 11, 11}, Piece.TEAM2);
        game = new Game(t1, t2);
        game.initGraphicObjects();
    }
    
    @Override
    public void draw() 
    {
        //g2 = createGraphics(1920, 1080);
        //g2.beginDraw();
        for(GraphicObject go : game.getGO())
            go.draw();
        //g2.endDraw();
        //image(g2, 0, 0, w, h);
    }
    
    @Override
    public void mousePressed(MouseEvent event)
    {
        for(GraphicObject go : game.getGO())
            if(go.isOn(event.getX(), event.getY()))
                go.mousePressed(event.getX(), event.getY());
    }
    
    /*Getters*/
    public static StratEdge getSE(){return se;}
    public int getW(){return w;}
    public int getH(){return h;}
    public double getFPS(){return frameRate;}
    public Game getGame(){return game;}
}