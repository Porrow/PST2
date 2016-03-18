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
                        //System.out.println("i : "+i+" ;j : "+j);
                        se.g.noStroke();
                        if(game.getSelection().getTeam() == 0)
                            se.g.fill(0, 150, 0, 70);
                        else
                            se.g.fill(150, 0, 0, 70);
                        ellipse(j * w/C + w/C/2, i * w/C + w/C/2, 50, 50);
                    }
        }
        
    }
    
    @Override
    public void mouseClicked(int x, int y) 
    {
        int rx = getRX(x), ry = getRY(y);
        Piece selec = game.getSelection();
        if(selec == null)
        {
            game.setSelection(game.getChecker()[ry*C / h][rx*C / w]);           //On modifie éventuellement la sélection   
            return;
        }
        boolean[][] pMoves = selec.getMoves(game.getChecker());
        if(pMoves[ry*C / h][rx*C / w])
        {
            Piece[][] checker = game.getChecker();
            checker[selec.getY()][selec.getX()] = null;
            selec.setX(rx*C / w);
            selec.setY(ry*C / h);
            checker[selec.getY()][selec.getX()] = selec;
            game.setChecker(checker);
        }
        else
            game.setSelection(game.getChecker()[ry*C / h][rx*C / w]);
    }

    @Override
    public void mouseMoved(int x, int y) {}
}
