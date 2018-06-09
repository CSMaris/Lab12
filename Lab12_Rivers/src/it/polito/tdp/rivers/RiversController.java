
package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.SimulazioneResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    

    @FXML
    void doFill(ActionEvent event) {
    	River r=boxRiver.getValue();
    	
    	txtStartDate.setText(model.getMIN(r.getId())+"");
    	txtEndDate.setText(model.getMAX(r.getId())+"");
    	txtNumMeasurements.setText(model.getCOUNT(r.getId())+"");
    	txtFMed.setText(model.getAVG(r.getId())+"");

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	int idRiver=boxRiver.getValue().getId();
    	float k=Float.parseFloat(txtK.getText());
    	double fmed=Double.parseDouble(txtFMed.getText());
    	List<Flow> flows=model.getFlows(idRiver);
    	int numGiorni=flows.size();
    	
    	SimulazioneResults res=model.getResults(k, fmed, numGiorni, flows);
    	
    	txtResult.appendText("Risultati simulazione: \n");
    	txtResult.appendText("C media: "+res.getAvgC()+"\n");
    	txtResult.appendText("Numero di giorni out: "+res.getOutdays());
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
    
    private Model model;

	public void setModel(Model model) {
		this.model=model;
		
		boxRiver.getItems().addAll(model.getRivers());
		
		
		
	}
}
