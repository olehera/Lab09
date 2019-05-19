package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	private Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	@FXML
    private ComboBox<Country> cbxStato;
	
	@FXML
    private Button btnTrova;

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		int anno;
		
		try {
		      anno = Integer.parseInt(txtAnno.getText().trim());
		} catch(NumberFormatException nfe) {
		      txtResult.setText("Devi inserire un numero intero!");
		      return ;
		}
		
		if (anno < 1816 || anno > 2016) {
			txtResult.setText("Inserire un anno tra il 1816 e 2016");
			txtAnno.clear();
			return ;
		}
		
		model.creaGrafo(anno);
		txtResult.setText(model.elencoStati());
		txtResult.appendText("Numero di componenti connesse nel grafo: "+model.componentiConnesse());
		cbxStato.getItems().addAll(model.getCountry());
	}
	
	@FXML
    void doTrovaVicini(ActionEvent event) {
		txtResult.setText("Lista Vicini: \n\n");
		
		for (Country c : model.trovaVicini(cbxStato.getValue()))
			txtResult.appendText(c+"\n");
    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		assert cbxStato != null : "fx:id=\"cbxStato\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Borders.fxml'.";
	}
}
