package pokedex;

import java.io.Serializable;

/**
 * Type object class, subclass of Types
 * @author Bethany
 */
public class Type implements Serializable {
    
    public String name;
    public String url;
    
    /**
     * Default constructor
     */
    public Type()
    {
        name = "";
        url = "";
    }
    
    /**
     * Constructor that takes in parameters
     * @param inName name of type
     * @param inUrl URL for type
     */
    public Type(String inName, String inUrl)
    {
        name = inName;
        url = inUrl;
    }
    
    /**
     * Method to get the type name
     * @return name of type
     */
    public String getTypeName()
    {
        return name;
    }
}
