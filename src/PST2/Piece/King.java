package PST2.Piece;

import static PST2.Game.C;
import PST2.StratEdge;

public class King extends SEPiece
{
    
    public King(String NAME, boolean team, int image, int attack, int defense, int life, int x, int y)
    {
        super(NAME, KING, team, image, attack, defense, life, x, y);
    }
    
    @Override
    public boolean[][] getMoves(Piece[][] checker)                              //Gestion des mouvements particuliers du roi
    {
        super.getMoves(checker);                                                //On remplie pMoves de manière classique
        
        if(!firstMove)
        {
            boolean canRoque = !checker[getY()][0].getFM();
            for(int i = 1; i < 4; i++)
                canRoque &= checker[getY()][i] == null;
            if(canRoque)
                pMoves[getY()][2] = true;
            canRoque = !checker[getY()][C-1].getFM();
            for(int i = 5; i < 7; i++)
                canRoque &= checker[getY()][i] == null;
            if(canRoque)
                pMoves[getY()][6] = true;
        }
        
        return pMoves;
    }
    
    @Override
    public void setPos(int nX, int nY)
    {
        if(Math.abs(nX-getX()) == 2)
        {
            int x, nx;
            Piece[][] checker = StratEdge.getSE().getGame().getChecker();
            if(nX-getX() < 0)
            {
                x = 0;
                nx = 3;
            }
            else
            {
                x = C-1;
                nx = 5;
            }
            checker[getY()][x].setPos(nx, getY());                              //On modifie ses coordonnées
            checker[getY()][nx] = checker[getY()][x];                           //On déplace la pièce sélectionnée sur la case sélectionnée
            checker[getY()][x] = null;                                          //On supprime la pièce sélectionnée de la case où elle se trouve
        }
        super.setPos(nX, nY);
    }
}
