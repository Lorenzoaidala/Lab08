/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Rotta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	txtResult.clear();
    	try {
    		String input = distanzaMinima.getText();
    		if(input=="") {
    			txtResult.setText("DEVI inserire un numero");
    			return;
    		}
    			
    		int dmin = Integer.parseInt(input);
    		model.creaGrafo(dmin);
    		txtResult.appendText(String.format("Il grafo è stato creato con %d vertici e %d archi.\n", model.getNVertici(), model.getNArchi()));
    		for(Rotta r : model.getRotteSalvate()) {
    			txtResult.appendText(r.toString()+"\n");
    			if(r.getPeso()<dmin) {
    				System.out.println(r.toString()+"\n");
    			}
    		}
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Il valore inserito DEVE essere un numero INTERO.");
    		throw new RuntimeException("Inserire un numero");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
