/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
public class TableViewBbooksController implements Initializable {

    @FXML
    private TextField textFieldBookID;
    @FXML
    private TextField textFieldBorrowerID;
    @FXML
    private TextField textFieldBorrowerDate;
    @FXML
    private TextField textFieldReturnDate;
    @FXML
    private TableView<borrowersbooks> TableViewBbook;
    @FXML
    private TableColumn<borrowersbooks, Integer> tcBookID;
    @FXML
    private TableColumn<borrowersbooks, Integer> tcBorrowerID;
    @FXML
    private TableColumn<borrowersbooks, Date> tcBorrowerDate;
    @FXML
    private TableColumn<borrowersbooks, Date> tcReturnDate;
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
    
    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  int
        try {
            // TODO

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement?serverTimezone=UTC", "root", "");
            this.statement = connection.createStatement();
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
        
        tcBookID.setCellValueFactory(new PropertyValueFactory("bookID"));
        tcBorrowerID.setCellValueFactory(new PropertyValueFactory("borrowerID"));
        tcBorrowerDate.setCellValueFactory(new PropertyValueFactory("borrowerDate"));
        tcReturnDate.setCellValueFactory(new PropertyValueFactory("returnDate"));
        
        TableViewBbook.getSelectionModel().selectedItemProperty().addListener(
        
                event-> showSelectedBbook()
        );
        
    }    

    @FXML
    private void buttonShowHandel(ActionEvent event) throws SQLException {
        
        ResultSet rset = this.statement.executeQuery("Select * From borrowersbooks");
        TableViewBbook.getItems().clear();
        while(rset.next()){
            borrowersbooks borrowersbook = new borrowersbooks();
            
            borrowersbook.setBookID(rset.getInt("bookID"));
            borrowersbook.setBorrowerID(rset.getInt("borrowerID"));
            borrowersbook.setBorrowerDate(rset.getDate("borrowerDate"));
            borrowersbook.setReturnDate(rset.getDate("returnDate"));
            
            TableViewBbook.getItems().add(borrowersbook);
            
        }
    }

    @FXML
    private void buttonInsertHandel(ActionEvent event) throws SQLException {
        
        Integer bookid = Integer.parseInt(textFieldBookID.getText());
        Integer borrowerid = Integer.parseInt(textFieldBorrowerID.getText());
        String borrowerdate = textFieldBorrowerDate.getText();
        String returndate = textFieldReturnDate.getText();
        
        String sql ="Insert Into borrowersbooks values(" + bookid + ", " + borrowerid + ", '" + borrowerdate + "' , '" + returndate + "' )";
        this.statement.executeUpdate(sql);
        
    }

    @FXML
    private void buttonUpdateHandel(ActionEvent event) throws SQLException {
        
        Integer bookid = Integer.parseInt(textFieldBookID.getText());
        Integer borrowerid = Integer.parseInt(textFieldBorrowerID.getText());
        String borrowerdate = textFieldBorrowerDate.getText();
        String returndate = textFieldReturnDate.getText();
        
        String sql = "Update borrowersbooks Set bookID = " + bookid + ", borrowerID = " + borrowerid + ", borrowerDate = '" + borrowerdate + "' , returnDate = '" + returndate + "' Where bookID = " + bookid;
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonDeleteHandel(ActionEvent event) throws SQLException {
        
        Integer bookid = Integer.parseInt(textFieldBookID.getText());
        String sql = "Delete From borrowersbooks Where bookID = " + bookid;
        this.statement.executeUpdate(sql);
        
    }

    @FXML
    private void buttonResetHandel(ActionEvent event) {
        resetControls();
    }
    
    private void resetControls(){
        textFieldBookID.setText("");
        textFieldBorrowerID.setText("");
        textFieldBorrowerDate.setText("");
        textFieldReturnDate.setText("");
        TableViewBbook.getItems().clear();
    }
    
    private void showSelectedBbook(){
        
        borrowersbooks  borrowersbook = TableViewBbook.getSelectionModel().getSelectedItem();
        if(borrowersbook != null){
            textFieldBookID.setText(String.valueOf(borrowersbook.getBookID()));
            textFieldBorrowerID.setText(String.valueOf(borrowersbook.getBorrowerID()));
            textFieldBorrowerDate.setText(String.valueOf(borrowersbook.getBorrowerDate()));
            textFieldReturnDate.setText(String.valueOf(borrowersbook.getReturnDate()));
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
