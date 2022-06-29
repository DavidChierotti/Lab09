/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

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

public class FXMLController {

	Model model=new Model();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="cmbStates"
    private ComboBox<Country> cmbStates; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void CercaStatiRaggiungibili(ActionEvent event) {
    	if(txtAnno.equals("")) {
    		Country c=cmbStates.getValue();
            String s=model.visitaGrafo(c,2016);
            txtResult.setText(s);
    	}
    	else {
        Country c=cmbStates.getValue();
        String s=model.visitaGrafo(c,Integer.parseInt(txtAnno.getText()));
        txtResult.setText(s);
        }
    }

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	if(txtAnno.getText().equals("")||Integer.parseInt(txtAnno.getText())<1816||Integer.parseInt(txtAnno.getText())>2016)
    		txtResult.setText("Inserire anno compreso tra 1816 e 2016");
    	else {
      int anno=Integer.parseInt(txtAnno.getText());
      String s=model.creaGrafo(anno);
      txtResult.setText(s);
      }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStates != null : "fx:id=\"cmbStates\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbStates.getItems().clear();
        for(Country c:this.model.popolaCmb()) {
        	cmbStates.getItems().add(c);
        }
    }
    void setModel(Model model) {
    	this.model=model;
    }
}

