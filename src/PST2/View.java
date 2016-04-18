package PST2;

import PST2.UI.GraphicObject;

public abstract class View
{
    protected GraphicObject[] tabGO;                                            //Tableau contenant tous les objets graphiques de la view
    
    public View()
    {
        
    }
    
    public GraphicObject[] getGO()
    {
        if(tabGO == null)
            initGraphicObjects();
        return tabGO;
    }
    
    public abstract void initGraphicObjects();
    public abstract void display();
}
