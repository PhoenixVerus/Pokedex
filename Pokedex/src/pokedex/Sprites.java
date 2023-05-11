package pokedex;

import java.io.Serializable;

/**
 * Sprites object class that contains image data for the Pokemon
 * @author Bethany
 */
public class Sprites implements Serializable {
    
    public String back_default;
    public String front_default;
    
    /**
     * Default constructor
     */
    public Sprites()
    {
        back_default = "";
        front_default = "";
    }
    
    /**
     * Constructor that takes in parameters
     * @param inBack URL for image of back of Pokemon
     * @param inFront URL for image of front of Pokemon
     */
    public Sprites(String inBack, String inFront)
    {
        back_default = inBack;
        front_default = inFront;
    }
    
    /**
     * Method to retrieve the URL for the front facing image
     * @return URL string for front facing image
     */
    public String getFrontImage()
    {
        return front_default;
    }
    
    /**
     * Method to retrieve the URL for the back facing image
     * @return URL string for back facing image
     */
    public String getBackImage()
    {
        return back_default;
    }
    
}
