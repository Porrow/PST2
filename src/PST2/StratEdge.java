package PST2;

import processing.core.*; 

public class StratEdge extends PApplet
{
    public final String IMGPATH = "res/img/";                                   //Chemin d'accès aux images
    public final String[] PN = {"Roi", "Reine", "Fou",                          //Noms des pièces
                                "Chevalier", "Tour", "Pion"};
    public final int[] INIT = {4, 3, 2, 0, 1, 2, 3, 4,                          //Positions initiales des pièces
                               5, 5, 5, 5, 5, 5, 5, 5};
    
    public int n = 600;
    public PImage[] tabImg = new PImage[12];                                    //Tableau contenant toutes les images
    public Piece[] white = new Piece[INIT.length];                              //1ère armée
    public Piece[] black = new Piece[INIT.length];                              //2ème armée

    public static void main(String[] args)
    {
        PApplet.main(new String[]{PST2.StratEdge.class.getName()});
    }
    public void loadImages()
    {
        tabImg[0]  = loadImage(IMGPATH + "RB.png");
        tabImg[1]  = loadImage(IMGPATH + "DB.png");
        tabImg[2]  = loadImage(IMGPATH + "FB.png");
        tabImg[3]  = loadImage(IMGPATH + "CB.png");
        tabImg[4]  = loadImage(IMGPATH + "TB.png");
        tabImg[5]  = loadImage(IMGPATH + "PB.png");
        tabImg[6]  = loadImage(IMGPATH + "RN.png");
        tabImg[7]  = loadImage(IMGPATH + "DN.png");
        tabImg[8]  = loadImage(IMGPATH + "FN.png");
        tabImg[9]  = loadImage(IMGPATH + "CN.png");
        tabImg[10] = loadImage(IMGPATH + "TN.png");
        tabImg[11] = loadImage(IMGPATH + "PN.png");
    }
    public void createArmies()
    {
        int i, j;
        for(i = 0, j = INIT.length-1; i < INIT.length; i++, j--)
        {
            white[i] = new Piece(PN[INIT[i]], i % 8, i / 8, tabImg[INIT[i]]);
            black[i] = new Piece(PN[INIT[i]], i % 8, j / 8 + 6, tabImg[INIT[i] + 6]);
        }
    }

    @Override
    public void setup() 
    {
        loadImages();                                                           //Charge l'intégralité des images
        createArmies();                                                         //Initialise les armées
        size (650, 650);                                                        //Taille de la fenêtre
        background (255, 255, 255);                                             //Couleur d'arrière plan dans la fenêtre
        stroke (0, 0, 0);                       
        rectMode (CENTER);
        imageMode (CENTER);
        textSize (40);
        textAlign (CENTER);
    }
    
    @Override
    public void draw() 
    {
        showChecker();
        showArmies();
        /*Exemple d'événement:
        if (mousePressed) 
        {
            fill(0);
        } 
        else 
        {
            fill(255);
        }
        rect(mouseX, mouseY, 80, 80);*/
    }
    void showChecker () 
    {
        String a;
        char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (int i = 0; i < 8; i++) {
            a = String.valueOf(i+1);
            fill (0, 0, 0);
            text (a, 25, 100+i*n/8);
            for (int j = 0; j < 8; j++) {
                fill (0, 0, 0);
                text (b[j], 90+j*n/8, 35);
                if ((i+j)%2 == 1) {
                    fill (0, 0, 0);
                } 
                else {
                    fill (255, 255, 255);
                }
                rect (50+(2*i+1)*n/16, 50+(2*j+1)*n/16, n/8, n/8);
            }
        }
    }

    void showArmies () 
    {
        //stroke (255, 255, 255);
        for(int i = 0; i < white.length; i++)
        {
            image (white[i].icon, 87+(white[i].x*n/8), 87+(white[i].y*n/8), n/9, n/9);
            image (black[i].icon, 87+(black[i].x*n/8), 87+(black[i].y*n/8), n/9, n/9);
        }
    }

}
