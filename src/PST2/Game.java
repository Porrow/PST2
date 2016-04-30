package PST2;

import PST2.Piece.*;
import PST2.UI.*;

public class Game extends View
{
    public static final int C = 8;                                              //Nombre de cases sur une ligne / colonne
    
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
    
    @Override
    public void initGraphicObjects()
    {
        StratEdge se = StratEdge.getSE();
        tabGO = new GraphicObject[4];
        tabGO[0] = new Background(se, 1);
        tabGO[1] = new Checker(se, (se.getW()-Checker.W) / 2, (se.getH()-Checker.W) / 2);
        tabGO[2] = new Armies(se, tabGO[1].getX(), tabGO[1].getY());
        tabGO[3] = new Debug(se, 3, 3, 70, 40);
    }
    
    /*Getters*/
    public Piece[][] getChecker(){return checker;}
    public Piece getSelection(){return selection;}
    public int getTurn(){return turn;}
    public boolean[][] getMoves(boolean team)                                   //Renvoie tous les mouvements pouvant être effectués par une équipe
    {
        boolean[][] moves = new boolean[C][C];                                  //Matrice : true mouvement autorisé; false sinon
        boolean[][] pmoves;
        for(Piece[] ligne : checker)                                            //On parcourt chacune des pièces du plateau
            for(Piece p : ligne)
                if(p != null && p.getTeam() == team)                            //On vérifie que la pièce est non nulle et dans la bonne équipe
                {
                    pmoves = p.getMoves(checker);                               //On récupère les mouvements autorisés de la pièce
                    for(int i = 0; i < pmoves.length; i++)                      //On parcourt chaque case du plateau
                        for(int j = 0; j < pmoves[i].length; j++)
                            moves[i][j] |= pmoves[i][j];                        //On ajoute les nouveaux mouvements autorisés
                }
        return moves;            
    }
    public boolean isCheck(boolean team)                                        //Renvoie true si le roi de l'équipe team est en echec, false sinon
    {
        boolean[][] moves = getMoves(!team);                                    //Récupère les possibilités de mouvements adverses
        for(int i = 0; i < moves.length; i++)                                   //Pour chacune des cases du terrain
            for(int j = 0; j < moves[i].length; j++)
                if(moves[i][j] && checker[i][j] != null)                        //Si l'adversaire peut capturer la pièce sur la case
                    if(checker[i][j].getType() == Piece.KING)                   //Si la pièce sur la case est le roi
                        return true;                                            //Alors le roi est en échec
        return false;
    }
    
    /*Setters*/
    public void setSelection(Piece nSelec){selection = nSelec;}
    public void setTurn()
    {
        turn++;
        if(isCheck(turn%2 == 1))                                                //Vérification qu'il n'y a pas de position d'échec
            System.out.println("Echec !");
    }
}
