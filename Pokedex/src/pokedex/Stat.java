package pokedex;

import java.io.Serializable;

/**
 * Stat object class, a subclass of Stats
 * @author Bethany
 */
public class Stat implements Serializable {
    
    public String name;
    public String url;
    
    /**
     * Default constructor
     */
    public Stat()
    {
        name = "";
        url = "";
    }
    
    /**
     * Constructor that takes in parameters
     * @param inName name of the stat
     * @param inUrl URL for the stat with more info
     */
    public Stat(String inName, String inUrl)
    {
        name = inName;
        url = inUrl;
    }
    
    /**
     * Method to retrieve the name of the stat
     * @return name of the stat
     */
    public String getStatName()
    {
        return name;
    }
}
