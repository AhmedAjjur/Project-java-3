/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import com.mysql.cj.protocol.Resultset;
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
public class TabelViewPaneController implements Initializable {

    @FXML
    private TextField textfieldid;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldDescription;
    @FXML
    private TableColumn<Books, Integer> tcID;
    @FXML
    private TableColumn<Books, String> tcName;
    @FXML
    private TableColumn<Books, String> tcDescription;
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
    @FXML
    private TableView<Books> tableView;
    
    Statement statement;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection  connection = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement?serverTimezone=UTC", "root", "");
            
                    this.statement = connection.createStatement();
                    
                    } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcDescription.setCellValueFactory(new PropertyValueFactory("description"));
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
        
                event-> showSelectedBook()
        );
    }    

    @FXML
    private void buttonshowHandel(ActionEvent event) throws SQLException {
        
        ResultSet rs = this.statement.executeQuery("Select * From Books");
        tableView.getItems().clear();
        while(rs.next()){
            Books book = new Books();
            
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setDescription(rs.getString("description"));
            
            tableView.getItems().add(book);
            
        }
    }

    @FXML
    private void buttoninsertHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldid.getText());
        String name = textfieldName.getText();
        String description = textfieldDescription.getText();
        
        String sql = "Insert Into books values(" + id + ", '" + name + "' , '" + description + "' )";
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonupdateHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldid.getText());
        String name = textfieldName.getText();
        String description = textfieldDescription.getText();
        String sql = "Update books Set name = '" + name + "' , description = '" + description + "' Where id = " + id ;
        
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttondeleteHandel(ActionEvent event) throws SQLException {
        
        Integer id = Integer.parseInt(textfieldid.getText());
        String sql = "Delete From books Where id = "+ id;
        this.statement.executeUpdate(sql);
    }
    
    @FXML
    private void buttonresetHandel(ActionEvent event) {
        resetControls();
    }
    private void resetControls(){
        textfieldid.setText("");
        textfieldName.setText("");
        textfieldDescription.setText("");
        tableView.getItems().clear();
        
    }
    
    private void showSelectedBook(){
        
        Books books = tableView.getSelectionModel().getSelectedItem();
        
        if(books != null){
        textfieldid.setText(String.valueOf(books.getId()));
        textfieldName.setText(books.getName());
        textfieldDescription.setText(books.getDescription());
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
