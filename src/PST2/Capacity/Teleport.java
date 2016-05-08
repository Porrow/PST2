package PST2.Capacity;

import static PST2.Game.C;
import PST2.Piece.Piece;
import PST2.StratEdge;

public class Teleport extends Active 
{
    public static final int COOL=5;                                             //Cooldown propre à chaque capacité
    Piece[][] tab;
    
    public Teleport(StratEdge se) 
    {
        super(COOL);
        tab = se.getGame().getChecker();
    }
   
    @Override
    public void power() 
    {
        if(isAvailable())
        {
            boolean[][] pMoves =getMoves(tab);  
            
            cooldown=COOL;
        }
    }

    public boolean[][] getMoves(Piece[][] checker)
    {
        boolean[][] pMoves = new boolean[C][C];  
        for (int i=0;i<tab.length;++i)                                           //On parcourt chacune des pièces du plateau
            for (int j=0;j<tab.length;++j)
                    pMoves[i][j]=(tab[i][j]==null);
        return pMoves;
    }
    
    @Override
    public void init() {}

}
