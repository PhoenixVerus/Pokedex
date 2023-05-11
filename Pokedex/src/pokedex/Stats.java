package pokedex;

import java.io.Serializable;

/**
 * Stats object that contains the primary stat data for a Pokemon
 * @author Bethany
 */
public class Stats implements Serializable {
    
    public int base_stat;
    public int effort;
    public Stat stat;
    
    /**
     * Default constructor
     */
    public Stats()
    {
        base_stat = 0;
        effort = 0;
        stat = new Stat();
    }
    
    /**
     * Constructor that takes in arguments
     * @param inBase_Stat integer value of base stat
     * @param inEffort integer value of specific Pokemon's effort
     * @param inStat Stat object that contains the name and URL of the stat
     */
    public Stats(int inBase_Stat, int inEffort, Stat inStat)
    {
        base_stat = inBase_Stat;
        effort = inEffort;
        stat = inStat;
    }
    
    /**
     * Returns the stat object variable
     * @return stat object variable
     */
    public Stat getStat()
    {
        return stat;
    }
    
    /**
     * Returns the integer value of the base stat
     * @return value of the base stat
     */
    public int getStatBase()
    {
        return base_stat;
    }
}
