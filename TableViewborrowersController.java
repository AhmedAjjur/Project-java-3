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
public class TableViewborrowersController implements Initializable {

    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonReset;
    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldfirstName;
    @FXML
    private TextField textfieldlastName;
    @FXML
    private TextField textfieldMobile;
    @FXML
    private TextField textfieldEmail;
    @FXML
    private TextField textfieldAddress;
    @FXML
    private TextField textfieldGender;
    @FXML
    private TableColumn<Borrowers, Integer> tcID;
    @FXML
    private TableColumn<Borrowers, String> tcfirstName;
    @FXML
    private TableColumn<Borrowers, String> tclastName;
    @FXML
    private TableColumn<Borrowers, Integer> tcMobile;
    @FXML
    private TableColumn<Borrowers, String> tcEmail;
    @FXML
    private TableColumn<Borrowers, String> tcAddress;
    @FXML
    private TableColumn<Borrowers, Integer> tcGender;
    @FXML
    private TableView<Borrowers> tableViewBorrowers;
    
    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement?serverTimezone=UTC", "root", "");
            
                    this.statement = connection.createStatement();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcfirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tclastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tcMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        tcEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tcAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tcGender.setCellValueFactory(new PropertyValueFactory("gender"));
        
        tableViewBorrowers.getSelectionModel().selectedItemProperty().addListener(
        
                    event-> showSelectedBorrowers()
        );
        
        
    }    

    @FXML
    private void buttonShowHandel(ActionEvent event) throws SQLException {
        
        ResultSet rs = this.statement.executeQuery("Select * From Borrowers");
        tableViewBorrowers.getItems().clear();
        
        while(rs.next()){
            Borrowers borrowers = new Borrowers();
            borrowers.setId(rs.getInt("id"));
            borrowers.setFirstName(rs.getString("firstName"));
            borrowers.setLastName(rs.getString("lastName"));
            borrowers.setMobile(rs.getInt("mobile"));
            borrowers.setEmail(rs.getString("email"));
            borrowers.setAddress(rs.getString("address"));
            borrowers.setGender(rs.getInt("gender"));
            tableViewBorrowers.getItems().add(borrowers);
            
            
        }
    }

    @FXML
    private void buttonInsertHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldID.getText());
        String Fname = textfieldfirstName.getText();
        String Lname = textfieldlastName.getText();
        Integer mobile = Integer.parseInt(textfieldMobile.getText());
        String email = textfieldEmail.getText();
        String address = textfieldAddress.getText();
        Integer gender = Integer.parseInt(textfieldGender.getText());
        
        String sql = "Insert Into borrowers values(" + id + ", '" + Fname + "' , '" + Lname + "' ," + mobile +", '" + email + "' , '" + address + "' ," + gender + ")";
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonUpdateHandel(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(textfieldID.getText());
        String Fname = textfieldfirstName.getText();
        String Lname = textfieldlastName.getText();
        Integer mobile = Integer.parseInt(textfieldMobile.getText());
        String email = textfieldEmail.getText();
        String address = textfieldAddress.getText();
        Integer gender = Integer.parseInt(textfieldGender.getText());
        
        String sql = "Update borrowers Set firstName = '" + Fname +"' ,lastName = '" + Lname +"' , mobile = " + mobile + ", email = '" + email + "' , address = '" + address + "' , gender = "+ gender + " Where id = " + id;
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonDeleteHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldID.getText());
        String sql = "Delete From borrowers Where id = "+ id;
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonResetHandel(ActionEvent event) {
        resetControl();
    }
    
    private void resetControl(){
        
        textfieldID.setText("");
        textfieldfirstName.setText("");
        textfieldlastName.setText("");
        textfieldMobile.setText("");
        textfieldEmail.setText("");
        textfieldAddress.setText("");
        textfieldGender.setText("");
        tableViewBorrowers.getItems().clear();
        
    }
    
    private void showSelectedBorrowers(){
        
        Borrowers borrowers = tableViewBorrowers.getSelectionModel().getSelectedItem();
        
        if(borrowers != null){
            textfieldID.setText(String.valueOf(borrowers.getId()));
            textfieldfirstName.setText(borrowers.getFirstName());
            textfieldlastName.setText(borrowers.getLastName());
            textfieldMobile.setText(String.valueOf(borrowers.getMobile()));
            textfieldEmail.setText(borrowers.getEmail());
            textfieldAddress.setText(borrowers.getAddress());
            textfieldGender.setText(String.valueOf(borrowers.getGender()));
              
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
