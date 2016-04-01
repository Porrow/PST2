package PST2;

import PST2.Piece.*;
import PST2.UI.*;

public class Game
{
    public static final int C = 8;                                              //Nombre de cases sur une ligne / colonne
    
    private GraphicObject[] tabGO;                                              //Tableau contenant tous les objets graphiques sur la view jeu
    private int turn = 0;                                                       //Tour actuel -> détermine qui doit jouer
    
    //private ArrayList<Piece> team1 = new ArrayList<>();                         //Piece de l'équipe 1
    //private ArrayList<Piece> team2 = new ArrayList<>();                         //Piece de l'équipe 2
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
        tabGO = new GraphicObject[3];
        tabGO[0] = new Checker(se, (se.getW()-Checker.W) / 2, (se.getH()-Checker.W) / 2);
        tabGO[1] = new Armies(se, tabGO[0].getX(), tabGO[0].getY());
        tabGO[2] = new Debug(se, 3, 3, 50, 40);
    }
    
    public void changeSelection(Piece nSelec)
    {
        if(nSelec == null)
        {
            setSelection(null);
            return;
        }
        int t = nSelec.getTeam() ? 1 : 0;                                           
        if(getTurn() % 2 == t)                                                  //Si c'est au tour de l'équipe de nSelec
            setSelection(nSelec);                                               //On modifie la sélection
    }
    
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
