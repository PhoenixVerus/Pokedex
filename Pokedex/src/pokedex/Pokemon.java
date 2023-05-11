package pokedex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 * Pokemon object class that stores primary Pokemon data
 * @author Bethany
 */
public class Pokemon implements Serializable {
    
    public String name;
    public transient StringProperty nameProperty;
    public String nickname;
    boolean favorite;
    public int id;
    public Sprites sprites;
    public ArrayList<Stats> stats;
    public ArrayList<Types> types;
    
    /**
     * Default constructor
     */
    public Pokemon()
    {
        name = "";
        nameProperty = new SimpleStringProperty(this, "name", "");
        nickname = "";
        favorite = false;
        id = 0;
        sprites = new Sprites();
        stats = new ArrayList<>();
        types = new ArrayList<>();
    }
    
    /**
     * Method to get Pokemon's name
     * @return Pokemon's name
     */
    public String getName()
    {
        nameProperty.set(name);
        return name;
    }
    
    /**
     * Method to set Pokemon's name
     * @param in name to set Pokemon name variable to
     */
    public void setName(String in)
    {
        name = in;
        nameProperty.set(in);
    }
    
    /**
     * Returns the string property of the name property
     * @return string property of the name property
     */
    public StringProperty getNameProperty()
    {
        nameProperty.set(name);
        return nameProperty;
    }
    
    /**
     * Returns the Pokemon's nickname
     * @return nickname of Pokemon
     */
    public String getNickname()
    {
        return nickname;
    }
    
    /**
     * Sets new nickname for Pokemon
     * @param in new nickname for Pokemon
     */
    public void setNickname(String in)
    {
        nickname = in;
    }
    
    /**
     * Returns if a Pokemon is set to a favorite or not
     * @return if Pokemon is a favorite or not
     */
    public boolean getFavorite()
    {
        return favorite;
    }
    
    /**
     * Sets if a Pokemon is a favorite or not
     * @param in is Pokemon a favorite or not
     */
    public void setFavorite(boolean in)
    {
        favorite = in;
    }
    
    /**
     * Method to retrieve id number
     * @return id number
     */
    public int getId()
    {
        return id;
    }
   
    /**
     * Method to set id to new number
     * @param in new id number
     */
    public void setId(int in)
    {
        id = in;
    }
    
    // sprites (image) methods
    /**
     * Returns sprites (image) object
     * @return sprites (image) object
     */
    public Sprites getSprites()
    {
        return sprites;
    }
    
    /**
     * Returns types object in form of a string
     * @return string listing types (categories) of Pokemon
     */
    public String getTypes()
    {
        String str = "";
        for (int i = 0; i < types.size(); i++)
        {
            str += types.get(i).getType().getTypeName();
            if (i != types.size() - 1 )
            {
                str += ", ";
            }
        }    
        return str;
    }
    
    /**
     * Returns stats objects in form of a string
     * @return string listing each stat name and its value
     */
    public String getStats()
    {
        String str = "";
        for (int i = 0; i < stats.size(); i++)
        {
            str += stats.get(i).getStat().getStatName() + ": " + stats.get(i).getStatBase();
            if (i != stats.size() - 1 )
            {
                str += "\n";
            }
        }
        return str;
    }
    
    /**
     * To string method for Pokemon object
     * @return string representing Pokemon object
     */
    @Override
    public String toString()
    {
        String out = name.substring(0, 1).toUpperCase() + name.substring(1);
        return out;
    }
    
    /**
     * Comparison (equals) method
     * @param object object to compare against
     * @return if object is same or not
     */
    @Override
    public boolean equals(Object object)
    {
        boolean areEqual;
        
        if (this == object)
        {
            areEqual = true;
        }
        else if (object == null)
        {
            areEqual = false;
        }
        else if (getClass() != object.getClass())
        {
            areEqual = false;
        }
        else
        {
            Pokemon other = (Pokemon) object;
            
            if (getName().equals(other.getName())
                    && getNameProperty().equals(other.getNameProperty())
                    && getNickname().equals(other.getNickname())
                    && getFavorite() == other.getFavorite()
                    && getId() == other.getId()
                    && getSprites().equals(other.getSprites())
                    && getTypes().equals(other.getTypes()))
            {
                areEqual = true;
            }
            else
            {
                areEqual = false;
            }
        }

        return areEqual;
    }
    
    /**
     * Method required for SimpleStringProperty to be written out in ObjectOutputStream
     * @param out file output
     * @throws IOException 
     */
    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.defaultWriteObject();
        out.writeObject(getName());
    }

    /**
     * Method required for SimpleStringProperty to be read in from ObjectInputStream
     * @param in file input
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        nameProperty = new SimpleStringProperty((String) in.readObject());
    }
    
    /**
     * Extractor method
     */
    public static Callback<Pokemon, Observable[]> extractor = p -> new Observable[]
       {p.getNameProperty()};
}
