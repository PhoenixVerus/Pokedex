package pokedex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Bethany
 */
public class FXMLController implements Initializable {

    // creation of scenebuilder elements
    @FXML
    private ImageView imageViewPokedex;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldNickname;
    @FXML
    private CheckBox checkboxFavorite;
    @FXML
    private TextField textFieldType;
    @FXML
    private TextArea textAreaStats;
    @FXML
    private ListView listViewPokemon;
    @FXML
    private ImageView imageViewPokemonFront;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonSaveToFile;
    @FXML
    private Button buttonLoadFromFile;
    @FXML
    private Button buttonLoadFromAPI;
    @FXML
    private TextArea console;
    
    private Pokemon pokemon;
    // primary background image
    private Image imagePokedex = new Image(String.valueOf(new File("Pokedex.jpg")));
    
    // components needed for updatable list view
    private ObservableList<Pokemon> pokemonList = FXCollections.observableArrayList(Pokemon.extractor);
    private ChangeListener<Pokemon> pokemonChangeListener;
    private Pokemon selectedPokemon;
    
    /**
     * Initializes the controller class, sets change listener for list view
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // set background Pokedex image
        imageViewPokedex.setImage(imagePokedex);
        
        listViewPokemon.setItems(pokemonList);
        
        // Set up the change listener.
        listViewPokemon.getSelectionModel().selectedItemProperty().addListener(
            pokemonChangeListener = (observable, oldValue, newValue) -> {
                // Set the selected Student.
                selectedPokemon = newValue;
                
                if (newValue != null) {
                    // Populate the textfields with the Pokemon's data.
                    textFieldName.setText(selectedPokemon.getName());
                    textFieldNickname.setText(selectedPokemon.getNickname());
                    checkboxFavorite.setSelected(selectedPokemon.getFavorite());
                    textFieldType.setText(selectedPokemon.getTypes());
                    textAreaStats.setText(selectedPokemon.getStats());
                    Image image = new Image(selectedPokemon.getSprites().getFrontImage());
                    imageViewPokemonFront.setImage(image);
                }
                else {
                    // Clear the controls if a Pokemon is not selected.
                    textFieldName.setText("");
                    textFieldNickname.setText("");
                    checkboxFavorite.setSelected(false);
                    textFieldType.setText("");
                    textAreaStats.setText("");
                }
            }
        );

    }
    
    /**
     * Pulls from the API to generate data on a random Pokemon and display
     * that data in the appropriate text fields
     */
    public void findPokemon()
    {
        // send text update to console
        console.setText(console.getText() + "Searching for neaby pokémon...\n");
        console.appendText("");
        
        //create new random Pokemon
        pokemon = new Pokedex().getPokemon();
        
        //set text fields and image
        textFieldName.setText(pokemon.getName());
        textFieldType.setText(pokemon.getTypes());
        textAreaStats.setText(pokemon.getStats());
        
        try
        {
            Image image = new Image(pokemon.getSprites().getFrontImage());
            imageViewPokemonFront.setImage(image);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Image not found.");
        }
        
        // set text update to console
        console.setText(console.getText() + "A wild " + pokemon + " has appeared!\n");
        console.appendText("");
    }
    
    /**
     * Button to call the method that calls the API to generate a random Pokemon
     * @param actionEvent button click
     */
    @FXML
    private void buttonFindPokemon(ActionEvent actionEvent) 
    {
        findPokemon();
    }
    
    /**
     * Button to add a Pokemon to the Pokedex list
     * @param actionevent button click
     */
    @FXML
    private void buttonAddToPokedex(ActionEvent actionevent)
    {
        // add pokemon to Pokedex and update
        pokemonList.add(pokemon);
        listViewPokemon.setItems(pokemonList);
        listViewPokemon.getSelectionModel().select(pokemon);
        
        // send text update to console
        console.setText(console.getText() + "You have added " + pokemon + " to your Pokédex.\n");
        console.appendText("");
    }
    
    /**
     * Button to update data changes made to a Pokemon stored in the Pokedex
     * @param actionEvent button click
     */
    @FXML
    private void buttonUpdateAction(ActionEvent actionEvent) {
        try
        {
            // Get the pokemon object the user selected from the ListView.
            Pokemon pokemonUpdate = (Pokemon) listViewPokemon.getSelectionModel().getSelectedItem();

            // Temporarily remove the change listener while we adjust the data
            listViewPokemon.getSelectionModel().selectedItemProperty().removeListener(pokemonChangeListener);

            // Get the updated data from the controls (base stats and types are not editable)
            String name = textFieldName.getText();
            String nickname = textFieldNickname.getText();
            boolean favorite = checkboxFavorite.isSelected();

            // Update the data in the Pokemon object
            pokemonUpdate.setName(name);
            pokemonUpdate.setNickname(nickname);
            pokemonUpdate.setFavorite(favorite);

            // Add the change listener back in.
            listViewPokemon.getSelectionModel().selectedItemProperty().addListener(pokemonChangeListener);
        
            // send text update to console
            console.setText(console.getText() + "Pokemon updated in Pokedex.\n");
            console.appendText("");
        }
        catch(NullPointerException e)
        {
            // send text update to console with error
            console.setText(console.getText() + "Failed to update Pokédex; no saved Pokemon was selected.\n");
            console.appendText(""); 
        }
    }
    
    /**
     * Button to remove a pokemon from the Pokedex list
     * @param actionEvent button click
     */
    @FXML
    private void buttonDeleteAction(ActionEvent actionEvent) 
    {
        // Remove selected Pokemon from the Pokedex
        pokemonList.remove(selectedPokemon);
    }
    
    /**
     * Button to save Pokedex data to a set file
     * @param actionEvent 
     */
    @FXML
    private void buttonSavePokedex(ActionEvent actionEvent)
    {
        // create output streams as resources in use in try section
        try(
            FileOutputStream fileOutputStream = new FileOutputStream("pokedex.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            )
        {
            // write out the observable list to a file
            objectOutputStream.writeObject(new ArrayList<Pokemon>(pokemonList));
            
            // send text update to the console
            console.setText(console.getText() + "You have successfully saved your Pokédex.\n");
            console.appendText("");
        }
        catch (IOException e)
        {
            // send text update to the console that there was an error
            console.setText(console.getText() + "Error saving your Pokédex.\n");
            console.appendText("");
            e.printStackTrace();
        }
    }
    
    /**
     * Button to load Pokedex data from a set file and display in the listView
     * @param actionEvent button click
     */
    @FXML
    public void buttonLoadPokedex(ActionEvent actionEvent)
    {
        
        // create input streams as resources in try section
        try (
                FileInputStream fis = new FileInputStream("pokedex.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                )
        {

            ArrayList<Pokemon> pokemons = (ArrayList<Pokemon>) ois.readObject();
            // update the observablelist with items from the arraylist
            pokemonList = FXCollections.observableList(pokemons);
            // make sure the list view updates with the new data
            listViewPokemon.setItems(pokemonList);
            
            // send text to console
            console.setText(console.getText() + "Pokédex loaded.\n");
            console.appendText("");
            
        }
        catch (IOException e)
        {
            System.out.println("Error: IO Exception.");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error: Class not found.");
        }
        
    }
    
}
