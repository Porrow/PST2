package PST2.Capacity;

import PST2.Piece.Piece;
import java.util.ArrayList;

public class Suicide extends Capacity {

    private static final int COOL = 0;                                          //Cooldown propre à chaque capacité
    private final static int CAST = 0;                                          //Temps de Cast propre à chaque capacité

    public Suicide(Piece piece) {
        super(piece, COOL, CAST, true);
        init();
    }

    @Override
    public void power() {
        ArrayList<Piece> around = getAround(piece.getX(), piece.getY());
        for (Piece p : around) {
            p.setLife(p.getLife() - 50);
            if (p.getLife() <= 0)
                p.kill();
        }
        piece.kill();
    }

}
