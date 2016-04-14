package PST2;

import PST2.Piece.*;
import PST2.UI.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game
{
    public static final int C = 8;                                              //Nombre de cases sur une ligne / colonne
    
    private GraphicObject[] tabGO;                                              //Tableau contenant tous les objets graphiques sur la view jeu
    private int turn = 0;                                                       //Tour actuel -> détermine qui doit jouer
    private Piece[][] checker = new Piece[C][C];                                //Terrain : case vide : null; case non vide : Piece qui est dessus
    private Piece selection = null;                                             //Pièce sélectionnée
    
    protected Game(Team t1, Team t2)                                            //t1 : team du haut, t2 : team du bas
    {
        for(int i = 0; i < t1.get().length; i++)
        {
            checker[i / C][i % C] = t1.get(i);
            checker[(C - 1) - i / C][i % C] = t2.get(i);
        }
    }
    
    protected void initGraphicObjects()
    {
        StratEdge se = StratEdge.getSE();
        tabGO = new GraphicObject[4];
        tabGO[0] = new Background(se, se.getState().getNum());
        tabGO[1] = new Checker(se, (se.getW()-Checker.W) / 2, (se.getH()-Checker.W) / 2);
        tabGO[2] = new Armies(se, tabGO[1].getX(), tabGO[1].getY());
        tabGO[3] = new Debug(se, 3, 3, 70, 40);
    }
    
    /*protected void display()
    {
        ArrayList<GraphicObject> display = StratEdge.getSE().getDisplay();
        display.removeAll(display);
        display.addAll(Arrays.asList(tabGO));
    }*/
    
    /*Getters*/
    public Piece[][] getChecker(){return checker;}
    public GraphicObject[] getGO(){return tabGO;}
    public Piece getSelection(){return selection;}
    public int getTurn(){return turn;}
    
    /*Setters*/
    public void setChecker(Piece[][] nChecker){checker = nChecker;}
    public void setSelection(Piece nSelec){selection = nSelec;}
    public void setTurn(){turn++;}
}
