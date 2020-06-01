/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author compulife
 */
public class TableViewUsersController implements Initializable {

    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldPassword;
    @FXML
    private TableView<users> TableViewUsers;
    @FXML
    private TableColumn<users, Integer> tcID;
    @FXML
    private TableColumn<users, String> tcName;
    @FXML
    private TableColumn<users, String> tcPassword;
    @FXML
    private Button buttonshow;
    @FXML
    private Button buttoninsert;
    @FXML
    private Button buttonupdate;
    @FXML
    private Button buttondelete;
    @FXML
    private Button buttonreset;
    
    
    Statement statements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connections = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement?serverTimezone=UTC", "root", "");
                    this.statements = connections.createStatement();
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
        
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcPassword.setCellValueFactory(new PropertyValueFactory("passwords"));
        
        TableViewUsers.getSelectionModel().selectedItemProperty().addListener(
        
                event-> showSelectedUsers()
        );
    }    

    @FXML
    private void buttonshowHandel(ActionEvent event) throws SQLException {
        
        ResultSet res = this.statements.executeQuery("Select * From users");
        TableViewUsers.getItems().clear();
        while(res.next()){
            users user = new users();
            
            user.setId(res.getInt("id"));
            user.setName(res.getString("name"));
            user.setPasswords(res.getString("passwords"));
            
            TableViewUsers.getItems().add(user);
            
        } 
        
    }

    @FXML
    private void buttoninsertHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldID.getText());
        String name = textfieldName.getText();
        String passwords = textfieldPassword.getText();
        
        String sql = "Insert Into users values(" + id + ", '" + name + "' , '" + passwords + "' )";
        this.statements.executeUpdate(sql);
        
    }

    @FXML
    private void buttonupdateHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldID.getText());
        String name = textfieldName.getText();
        String passwords = textfieldPassword.getText();
        
        String sql = "Update users Set name = '" + name + "' , passwords = '" + passwords + "' Where id = " + id;
        this.statements.executeUpdate(sql);
    }

    @FXML
    private void buttondeleteHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldID.getText());
        String sql = "Delete From users Where id = "+ id;
        this.statements.executeUpdate(sql);
    }

    @FXML
    private void buttonresetHandel(ActionEvent event) {
        resetControl();
    }
    
    private void resetControl(){
        textfieldID.setText("");
        textfieldName.setText("");
        textfieldPassword.setText("");
        TableViewUsers.getItems().clear();
    }
    
    private void showSelectedUsers(){
        
        users use = TableViewUsers.getSelectionModel().getSelectedItem();
        
        if(use != null){
            textfieldID.setText(String.valueOf(use.getId()));
            textfieldName.setText(use.getName());
            textfieldPassword.setText(use.getPasswords());
            
        }
        
    }

    @FXML
    private void buttonBack(ActionEvent event) throws IOException {
        
        Parent tableViewBook = FXMLLoader.load(getClass().getResource("Fream.fxml"));
        Scene tableBook = new Scene(tableViewBook);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableBook);
        window.show();
    }
    
}
