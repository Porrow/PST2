package PST2;

import processing.core.*; 

public class ChessLeague extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main(new String[]{PST2.ChessLeague.class.getName()});
        
    }
    @Override
    public void setup() 
    {
        size(400, 400);
    }
    
    @Override
    public void draw() 
    {
        line(0, 0, 400, 400);
    }

}
