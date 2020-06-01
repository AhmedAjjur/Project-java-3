/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author compulife
 */
public class FreamController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonBookHandel(ActionEvent event) throws IOException{
        
        Parent tableViewBook = FXMLLoader.load(getClass().getResource("TabelViewPane.fxml"));
        Scene tableBook = new Scene(tableViewBook);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableBook);
        window.show();
    }

    @FXML
    private void buttonBorrowerHandel(ActionEvent event) throws IOException {
        
        Parent tableViewBook = FXMLLoader.load(getClass().getResource("TableViewborrowers.fxml"));
        Scene tableBook = new Scene(tableViewBook);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableBook);
        window.show();
    }

    @FXML
    private void buttonBorrBhandel(ActionEvent event) throws IOException {
        
        Parent tableViewBook = FXMLLoader.load(getClass().getResource("TableViewBbooks.fxml"));
        Scene tableBook = new Scene(tableViewBook);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableBook);
        window.show();
    }

    @FXML
    private void buttonUserHandel(ActionEvent event) throws IOException {
        
        Parent tableViewBook = FXMLLoader.load(getClass().getResource("TableViewUsers.fxml"));
        Scene tableBook = new Scene(tableViewBook);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableBook);
        window.show();
    }
    
}
