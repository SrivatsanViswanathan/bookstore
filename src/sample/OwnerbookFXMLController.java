package sample;


import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OwnerbookFXMLController implements Initializable {

Owner owner = Owner.getInstance();
public TableView<Book> ob_table;
public TableColumn<Book,String> col_bookname;
public TableColumn<Book,Double> col_bookprice;
public TextField textfield_bookname;
public TextField textfield_bookprice;
File booksFile = new File("books.txt");
    @FXML
    private Button addBook;   
        
    @FXML
    private Button deleteBook;
    
    @FXML
    private Button back;
    
    @FXML
    private Button logout;  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       col_bookname.setCellValueFactory(new PropertyValueFactory<>("Col_bookname"));
       col_bookprice.setCellValueFactory(new PropertyValueFactory<>("Col_bookprice"));
       ob_table.setItems(observableList);
       
        try{
            Scanner booksScanner = new Scanner(booksFile);
            while(booksScanner.hasNext()){
              String[] eachLine = booksScanner.nextLine().split("---");
              Book p = new Book(eachLine[0],Double.parseDouble(eachLine[eachLine.length-1])); 
              ob_table.getItems().add(p);
            }
            booksScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File books.txt not found");
            e.printStackTrace();  
        } 
    }
ObservableList<Book> observableList = FXCollections.observableArrayList ();

    public void buttonAddbook(ActionEvent actionEvent){
       Book book = new Book(textfield_bookname.getText(),Double.parseDouble(textfield_bookprice.getText()));
       ob_table.getItems().add(book);
       
       owner.createBook(textfield_bookname.getText(), Double.parseDouble(textfield_bookprice.getText()));
       
    }
 
    
    public void buttonDeletebook (ActionEvent actionEvent){
    ObservableList<Book> allBook,SingleBook;
    allBook=ob_table.getItems();
    SingleBook = ob_table.getSelectionModel().getSelectedItems();
    Book p = ob_table.getSelectionModel().getSelectedItem();
    owner.deleteBook(p.getCol_bookname());
    SingleBook.forEach(allBook::remove);
    //Getting the book
   
   
   
    }
    
    public void buttonBack (ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("OwnerMenu.fxml");
     }
     
      public void buttonLogout (ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
      }
}