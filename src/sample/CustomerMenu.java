package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerMenu implements Initializable {

    Owner owner = Owner.getInstance();
    public TableView<Book> customer_book_table;
    public TableColumn<Book,String> col_bookname;
    public TableColumn<Book,Double> col_bookprice;
    private Customer selectedCustomer;
    
    @FXML private Button buy;   
    @FXML private Button redeembuy;
    @FXML private Button logout;
    @FXML private TextArea customer_info;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       col_bookname.setCellValueFactory(new PropertyValueFactory<>("Col_bookname"));
       col_bookprice.setCellValueFactory(new PropertyValueFactory<>("Col_bookprice"));
       customer_book_table.setItems(observableList);
    }
    
    ObservableList<Book> observableList = FXCollections.observableArrayList (
//      new Person( "Harry Potter",  10.0)
        read()
    );
    
    public void initData(Customer c){
        selectedCustomer = c;
        customer_info.setText("Welcome " + selectedCustomer.getUsername() +
                ", you have "+ String.valueOf((int)selectedCustomer.getPoints()) + 
                " points. Your membership status is " + String.valueOf(selectedCustomer.getStatusState()));
    }
    
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
          
    }
    
    public void buy(ActionEvent event) throws IOException {
//        Main m = new Main();
//        m.changeScene("CustomerCostScreen.fxml");
//        read();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerCostScreen.fxml"));
        Parent view = loader.load();
        Scene viewScene = new Scene(view);
        CustomerCostScreen costScreen = loader.getController();
        
        ObservableList<Book> allBook,SingleBook;
        allBook=customer_book_table.getItems();
        SingleBook = customer_book_table.getSelectionModel().getSelectedItems();
        Book p = customer_book_table.getSelectionModel().getSelectedItem();
        costScreen.initDataBuy(selectedCustomer, p.getCol_bookprice());
        
        owner.deleteBook(p.getCol_bookname());
        SingleBook.forEach(allBook::remove);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
        
    }
    
    public void redeemBuy(ActionEvent event) throws IOException {
//        Main m = new Main();
//        m.changeScene("CustomerCostScreen.fxml");
//        read();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerCostScreen.fxml"));
        Parent view = loader.load();
        Scene viewScene = new Scene(view);
        CustomerCostScreen costScreen = loader.getController();
        ObservableList<Book> allBook,SingleBook;
        allBook=customer_book_table.getItems();
        SingleBook = customer_book_table.getSelectionModel().getSelectedItems();
        Book p = customer_book_table.getSelectionModel().getSelectedItem();
        costScreen.initDataRedeemBuy(selectedCustomer, p.getCol_bookprice());
        
        owner.deleteBook(p.getCol_bookname());
        SingleBook.forEach(allBook::remove);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
    
    
    // Effects: Reads from the "books.txt" file and returns the arraylist of books.  
    public ArrayList<Book> read() {  
        String[] input;
        double inputPrice;
//        owner.readBooksFromFileIntoMAINList();/////////////////////////////////////////////////////////////////////////////
        ArrayList<Book> result = new ArrayList<Book>();
        try { 
            File rec = new File("books.txt");    
//            System.out.println(rec.createNewFile());
            if(rec.createNewFile()){
                System.out.println("File created: " + rec.getName());
            }
            Scanner fileScanner = new Scanner(rec); 
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
//              System.out.println(data);
                input = data.split("---");
//              for(int i =0; i < input.length; i++){
//                    System.out.println(input[i]);
//              }
                inputPrice = Double.parseDouble(input[1]);
//                System.out.println(input[0]);
//                System.out.println(inputPrice);
                result.add(new Book(input[0], inputPrice));
            }
            fileScanner.close();
        }catch(IOException e){             
            System.out.println("An error occurred.");             
            e.printStackTrace();         
        }   
        return result;
        
    } 

    // Effects: Appends the specified message, msg, to the      
    // associated file.     
    public void write(String msg) {   
        try { 
            FileWriter a = new FileWriter("books.txt", true);
            a.write(msg);
            a.close();
        } catch (IOException e) {             
            System.out.println("An error occurred.");
            e.printStackTrace();         
        }     
    }   
}
