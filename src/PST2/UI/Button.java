package PST2.UI;

import PST2.Game;
import PST2.Piece.Piece;
import PST2.Piece.Team;
import PST2.StratEdge;
import processing.core.PFont;

public class Button extends GraphicObject
{

    private static final String IMGPATH = "res/font/";
    private final String text;
    private PFont font;
    
    public Button(StratEdge se, int x, int y, int w, int h, int size, String text, String font)
    {
        super(se, x, y, w, h);
        System.out.println(x);
        this.text = text;
        this.font = se.createFont(IMGPATH + font, size);
    }
    
    @Override
    public void init() {}

    @Override
    public void draw() 
    {
        se.textFont(font);
        rect(x, y, w, h);
        text(text, x, y);
    }

    @Override
    public void mousePressed(int x, int y)
    {
        /*System.out.println("Bonjour");
        if(text.equals("Jouer"))
        {
            StratEdge.getSE().setState(StratEdge.State.Play);
            Team t1 = new Team(new int[]{4, 3, 2, 1, 0, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5}, Piece.TEAM1);
            Team t2 = new Team(new int[]{10, 9, 8, 7, 6, 8, 9, 10, 11, 11, 11, 11, 11, 11, 11, 11}, Piece.TEAM2);
            StratEdge.getSE().game = new Game(t1, t2);
            StratEdge.getSE().game.display();
        }*/
    }

    @Override
    public void mouseMoved(int x, int y) {}
}