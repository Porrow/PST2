package PST2.Capacity;

import PST2.Piece.Piece;
import PST2.StratEdge;

/**
 *
 * @author mad
 */
public class Phoenix extends Capacity
{

    private static final int COOL = 10;                                          //Cooldown propre à chaque capacité
    private final static int CAST = 0;                                          //Temps de Cast propre à chaque capacité
    private final int lifemax;

    public Phoenix(Piece piece, int id)
    {
        super(piece, COOL, CAST, id, false, "Renaissance");
        lifemax = piece.getLife();
        init();
    }

    @Override
    public void setfire()
    {
        if(!piece.isAlive())
        {
            Piece[][] checker = StratEdge.getSE().getGame().getChecker();
            piece.setLife(lifemax / 4);
            piece.revive();

//            if(piece.getLife()==0);
//                piece.setLife(1);
            int[] pos = getNearest(piece.getY(), piece.getX());
            piece.setPos(pos[1], pos[0]);
            checker[piece.getY()][piece.getX()] = piece;
            piece.getCapacity1().set();
            piece.getCapacity2().set();
            cooldown = COOL;
            StratEdge.getSE().getGame().promotion(piece);
        }
    }

    @Override
    public void power()
    {
        if(isAvailable() && !piece.isAlive())
            setfire();
        if(!piece.isAlive() && !isAvailable())
            reset();
    }

    private int[] getNearest(int x, int y)
    {
        Piece[][] vchecker = StratEdge.getSE().getGame().getChecker();
        if(vchecker[x][y] == null)
            return new int[]
            {
                x, y
            };
        else
        {
            int i = 0;
            while(i < 10)
            {
                for(int i2 = y - 1 - i; i2 <= y + 1 + i; ++i2)
                    for(int j2 = x - 1 - i; j2 <= x + 1 + i; ++j2)
                        try
                        {
                            if(vchecker[j2][i2] == null)
                                return new int[]
                                {
                                    j2, i2
                                };
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {

                        }
                ++i;
            }
        }
        return null;
    }

    @Override
    protected void reset()
    {
        cooldown = -1;
    }

}
