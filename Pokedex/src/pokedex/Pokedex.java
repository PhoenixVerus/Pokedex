package pokedex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Pokedex class to call the PokeAPI and retrieve Pokemon data
 * @author Bethany
 */
public class Pokedex {
    
    private Gson gson;
    
    public Pokedex()
    {
        gson = new Gson();
    }
    
    /**
     * Method to retrieve a random Pokemon from the PokeAPI
     * @return a random Pokemon from the original release
     */
    public Pokemon getPokemon()
    {
        // Call the PokeAPI and receive a JSON String.
        int randomID = (int)((Math.random() * 149)+1) ;
        String url = "https://pokeapi.co/api/v2/pokemon/" + randomID;
        String json = callApi(url);

        //System.out.println("JSON = " + json);
        Pokemon pokemon;
        
        if(json.startsWith("{"))
        {
            // Parse the JSON into a Pokemon object
            pokemon = gson.fromJson(json, new TypeToken<Pokemon>(){}.getType());
        }
        else
        {
            // String probably contains an error message from the server.
            System.out.println(json);
            pokemon = new Pokemon();
        }

        return pokemon;
    }

    /**
     * Method to read in the JSON from the PokeAPI
     * @param rawUrl URL for the raw JSON call
     * @return JSON retrieved from URL
     */
    public static String callApi(String rawUrl)
    {
        // Set up variables to call the URL and read the result.
        URL url;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader = null;
        String jsonResult = "";

        try
        {
            // Create the URL with the address to the server.
            url = new URL(rawUrl);
            
            // Call the url and create a Buffered Reader on the result.
            inputStream = url.openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
                
            // Keep reading lines while more still exist.
            // Append the result to a String.  Should use a StringBuilder if we
            // are expecting a lot of lines.
            while (line != null) {
                jsonResult += line;
                line = reader.readLine();
            }
        }
        catch (MalformedURLException malformedURLException) {
            // URL was bad.
            jsonResult = malformedURLException.getMessage();
        }
        catch (IOException ioException) {
            // An error occurred during the reading process.
            jsonResult = ioException.getMessage();
        }
        finally
        {
            // Close the reader and the underlying objects.
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ioException) {
                jsonResult += ioException.getMessage();
            }
        }
        
        return jsonResult;
    }
}
