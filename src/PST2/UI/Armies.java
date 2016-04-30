package PST2.UI;

import PST2.Piece.Piece;
import PST2.*;

import static PST2.Game.C;

public class Armies extends GraphicObject
{
    private static final String IMGPATH = "res/img/piece/";
    
    private final Game game;
    private boolean[][] pMoves;
    
    public Armies(StratEdge se, int x, int y) 
    {
        super(se, x, y, Checker.W, Checker.W, IMGPATH);
        game = se.getGame();
    }
    
    public void changeSelection(Piece nSelec)                                   //Modifie la sélection si le tour est correct
    {
        if(nSelec == null)
        {
            game.setSelection(null);
            return;
        }
        int t = nSelec.getTeam() ? 1 : 0;                                           
        if(game.getTurn() % 2 == t)                                             //Si c'est au tour de l'équipe de nSelec
        {
            game.setSelection(nSelec);                                          //On modifie la sélection
            pMoves = nSelec.getMoves(game.getChecker());                        //On met à jour les mouvements
        }
    }
    
    @Override
    public void init(){}
    
    @Override
    public void draw()
    {
        for(Piece[] line : game.getChecker())
            for(Piece pi : line)
                if(pi != null)
                    image(tabImg[pi.getImg()], pi.getX()* w/C, pi.getY() * h/C);
        if(game.getSelection() != null)
        {
            for(int i = 0; i < pMoves.length; i++)
                for(int j = 0; j < pMoves[i].length; j++)
                    if(pMoves[i][j])
                    {
                        se.g.noStroke();
                        if(!game.getSelection().getTeam())
                            se.g.fill(0, 150, 0, 70);
                        else
                            se.g.fill(150, 0, 0, 70);
                        ellipse(j * w/C + w/C/2, i * w/C + w/C/2, 50, 50);
                    }
        }
        
    }
    
    @Override
    public void mousePressed(int x, int y)                                      //Gestion des clics de souris
    {
        int rx = getRX(x), ry = getRY(y);                                       //On récupère les coordonnées de la souris relativement au GO
        Piece selec = game.getSelection();                                      //On récupère la pièce sélectionnée
        Piece nSelec = game.getChecker()[ry*C / h][rx*C / w];                   //On récupère la pièce sous la souris
        if(selec == null)                                                       //Si il n'y a pas de pièce sélectionnée
        {
            changeSelection(nSelec);                                            //On modifie (éventuellement) la sélection
            return;                                                             //On s'arrête ici
        }
        pMoves = selec.getMoves(game.getChecker());                             //On récupère les mouvements potentiels de la sélection
        if(pMoves[ry*C / h][rx*C / w])                                          //Si le clic est sur une case où le mouvements est autorisé...
            selec.move(rx*C / w, ry*C / h);                                     //On déplace la pièce sur la bonne case
        else
            changeSelection(nSelec);                                            //On modifie la sélection
    }

    @Override
    public void mouseMoved(int x, int y) {}
}
