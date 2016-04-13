package PST2.UI;

import PST2.Piece.Piece;
import PST2.*;

import static PST2.Game.C;

public class Armies extends GraphicObject
{
    private static final String IMGPATH = "res/img/piece/";
    
    private final Game game;
    
    public Armies(StratEdge se, int x, int y) 
    {
        super(se, x, y, Checker.W, Checker.W, IMGPATH);
        game = se.getGame();
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
            boolean[][] pMoves = game.getSelection().getMoves(game.getChecker());
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
            game.changeSelection(nSelec);                                       //On modifie la sélection
            return;                                                             //On s'arrête ici
        }
        boolean[][] pMoves = selec.getMoves(game.getChecker());                 //On récupère les mouvements potentiels de la sélection
        if(pMoves[ry*C / h][rx*C / w])                                          //Si le clic est sur une case où le mouvements est autorisé...
        {
            Piece[][] checker = game.getChecker();                              //On récupère l'ensemble des pièces sur le terrain
            checker[selec.getY()][selec.getX()] = null;                         //On supprime la pièce sélectionnée de la case où elle se trouve
            selec.setPos(rx*C / w, ry*C / h);                                   //On modifie ses coordonnées
            checker[selec.getY()][selec.getX()] = selec;                        //On déplace la pièce sélectionnée sur la case sélectionnée
            game.setChecker(checker);                                           //On applique les modifications apportées au terrain
            game.setTurn();                                                     //On passe au tour suivant
            game.setSelection(null);                                            //On annule la sélection
        }
        else
            game.changeSelection(nSelec);                                       //On modifie la sélection
    }

    @Override
    public void mouseMoved(int x, int y) {}
}
