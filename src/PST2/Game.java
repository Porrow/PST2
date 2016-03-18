package PST2;

import PST2.Piece.Piece;
import PST2.UI.*;
import java.util.ArrayList;

public class Game
{
    public static final int C = 8;                                              //Nombre de cases sur une ligne / colonne
    
    private static final int[] INIT = {4, 3, 2, 0, 1, 2, 3, 4,                  //Positions initiales des pièces
                                       5, 5, 5, 5, 5, 5, 5, 5};
    
    private GraphicObject[] tabGO = new GraphicObject[3];                       //Tableau contenant tous les objets graphiques sur la view jeu
    
    private ArrayList<Piece> team1 = new ArrayList<>();                         //Piece de l'équipe 1
    private ArrayList<Piece> team2 = new ArrayList<>();                         //Piece de l'équipe 2
    private Piece[][] checker = new Piece[C][C];                                //Terrain : case vide : null; case non vide : Piece qui est dessus
    private Piece selection = null;                                             //Pièce sélectionné
    
    protected Game(int[] team1, int[] team2)
    {
        int[][] pcs = Piece.getPieces();
        String[] ns = Piece.getNames();
        Piece p;
        int id;
        for(int i = 0; i < team1.length; i++)
        {
            id = team1[i];
            p = new Piece(ns[id], pcs[id][0], 0, id, pcs[id][1], pcs[id][2], pcs[id][3], i % C, i / C);
            this.team1.add(p);
            checker[i / C][i % C] = p;
            id = team2[i];
            p = new Piece(ns[id], pcs[id][0], 1, id, pcs[id][1], pcs[id][2], pcs[id][3], i % C, (C - 1) - i / C);
            this.team2.add(p);
            checker[(C - 1) - i / C][i % C] = p;
        }
        
    }
    
    protected void initGraphicObjects(StratEdge se)
    {
        tabGO[0] = new Checker(se, (se.getW()-Checker.W) / 2, (se.getH()-Checker.W) / 2);
        tabGO[1] = new Armies(se, (se.getW()-Checker.W) / 2, (se.getH()-Checker.W) / 2);
        tabGO[2] = new Debug(se, 3, 3, 50, 40);
    }
    
    /*Getters*/
    public Piece[][] getChecker(){return checker;}
    public GraphicObject[] getGO(){return tabGO;}
    public Piece getSelection(){return selection;}
    
    /*Setters*/
    public void setChecker(Piece[][] nChecker){checker = nChecker;}
    public void setSelection(Piece selec){selection = selec;}
}
