package pokedex;

import java.io.Serializable;

/**
 * Types object class
 * @author Bethany
 */
public class Types implements Serializable {
    
    public int slot;
    public Type type;
    
    /**
     * Default constructor
     */
    public Types()
    {
        slot = 0;
        type = new Type();
    }
    
    /**
     * Constructor that takes in parameters
     * @param inSlot which slot the type uses in the official Pokedex
     * @param inType type object that contains the name and URL
     */
    public Types(int inSlot, Type inType)
    {
        slot = inSlot;
        type = inType;
    }
    
    /**
     * Method to return the type
     * @return type object
     */
    public Type getType()
    {
        return type;
    }
}
