package PST2.Capacity;

public abstract class Active extends Capacity
{
    protected int cooldown;                                                     //Cooldown en cours
    
    public Active(int cooldown)
    {
        this.cooldown=cooldown;
    }
    
    public boolean isAvailable() 
    {
        return (cooldown==0);
    }
}
