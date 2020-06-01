/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author compulife
 */
public class Lab extends Application {
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        Pane paneTableView = FXMLLoader.load(getClass().getResource("TabelViewPane.fxml"));
        Map<String , Pane> mapPane = new TreeMap<>();
        mapPane.put("tableView", paneTableView);
        
        
        Pane TableViewBorrowers = FXMLLoader.load(getClass().getResource("TableViewborrowers.fxml"));
         Map<String , Pane> mapBorrowers = new TreeMap<>();
        mapBorrowers.put("tableViewBorrowers", TableViewBorrowers);
        
        
        
        Pane TableViewBbook = FXMLLoader.load(getClass().getResource("TableViewBbooks.fxml"));
         Map<String , Pane> mapBbook = new TreeMap<>();
        mapBbook.put("tableViewBbook", TableViewBbook);
        
        
        
        Pane TableViewUsers = FXMLLoader.load(getClass().getResource("TableViewUsers.fxml"));
        Map<String , Pane> mapUsers = new TreeMap<>();
        mapUsers.put("tableViewU", TableViewUsers);
        
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("Fream.fxml"));
        
        Scene scene = new Scene(root ,500 ,500);
        
        
        //mapBbook.get("tableViewBbook")
        
        primaryStage.setTitle("Library management system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
