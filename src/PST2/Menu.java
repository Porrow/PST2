package PST2;

import PST2.UI.*;

public class Menu extends View
{
    
    public Menu()
    {
        
    }
    
    @Override
    public void initGraphicObjects()
    {
        StratEdge se = StratEdge.getSE();
        tabGO = new GraphicObject[4];
        tabGO[0] = new Background(se, 0);
        tabGO[1] = new Button(se, se.getW()/2 - 300, se.getH()/2 - 500, 600, 300, 200, "Strat::Edge", "Miama.ttf");
        tabGO[2] = new Button(se, se.getW()/2 - 200, se.getH()/2 - 100, 400, 200, 150, "Jouer", "Miama.ttf");
        tabGO[3] = new Button(se, se.getW()/2 - 150, se.getH()/2 + 300, 300, 200, 80, "Quitter", "Miama.ttf");
    }
    
    @Override
    public void display()
    {
        /*ArrayList<GraphicObject> display = StratEdge.getSE().getDisplay();
        display.removeAll(display);
        display.addAll(Arrays.asList(tabGO));*/
    }
}
