package PST2.Capacity;

import PST2.Piece.Piece;
import PST2.StratEdge;
import java.util.ArrayList;

public class Reset extends Capacity {

    private static final int COOL = 10;                                         //Cooldown propre à chaque capacité
    private final static int CAST = 0;                                          //Temps de Cast propre à chaque capacité

    public Reset(Piece piece, int id) {
        super(piece, COOL, CAST, id, true, "Recharge des capacités");
        init();
    }

    @Override
    public void setfire() {
        ArrayList<Capacity> pass = getPassive(piece.getTeam());
        ArrayList<Capacity> act = getActive();

        for (Capacity p : pass)
            if (p.getCurrentCool() != -1)
                p.setCool(0);
        for (Capacity p : act)
            if (p.piece.getTeam() == piece.getTeam())
                if (p.getCurrentCool() != -1)
                    p.setCool(0);
        StratEdge.getSE().getGame().setSelection(null);
        StratEdge.getSE().getGame().setTurn();
    }

    @Override
    protected void reset() {}

}
