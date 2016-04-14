package PST2;

import PST2.UI.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu 
{
    private GraphicObject[] tabGO;                                              //Tableau contenant tous les objets graphiques sur la view Menu
    
    public Menu()
    {
        initGraphicObjects();
    }
    
    private void initGraphicObjects()
    {
        StratEdge se = StratEdge.getSE();
        tabGO = new GraphicObject[3];
        tabGO[0] = new Background(se, se.getState().getNum());
        tabGO[1] = new Button(se, se.getW()/2 - 300, se.getH()/2 - 150, 600, 50, 500, "Jouer", "Miama.ttf");
        tabGO[2] = new Button(se, 100, 300, 100, 50, 500, "Quitter", "Miama.ttf");
    }
    
    /*protected void display()
    {
        ArrayList<GraphicObject> display = StratEdge.getSE().getDisplay();
        display.removeAll(display);
        display.addAll(Arrays.asList(tabGO));
    }*/
}
