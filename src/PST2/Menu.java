package PST2;

import PST2.UI.*;

public class Menu extends View
{
    
    @Override
    public void initGraphicObjects()
    {
        StratEdge se = StratEdge.getSE();
        tabGO = new GraphicObject[6];
        tabGO[0] = new Background(se, 0);
        tabGO[1] = new Text(se, se.getW()/2 - 300, se.getH()/2 - 500, 600, 350, 250, "Strat::Edge", "Miama.ttf");
        tabGO[3] = new Text(se, se.getW()/2 - 200, se.getH()/2 - 100, 400, 200, 150, "Jouer", "Miama.ttf");
        tabGO[2] = new Button(se, (Text)tabGO[3]);
        tabGO[5] = new Text(se, se.getW()/2 - 150, se.getH()/2 + 300, 300, 200, 80, "Quitter", "Miama.ttf");
        tabGO[4] = new Button(se, (Text)tabGO[5]);
    }
}
