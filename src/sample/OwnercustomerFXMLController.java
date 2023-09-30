
package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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

public class OwnercustomerFXMLController implements Initializable {

Owner owner = Owner.getInstance();
public TableView<Customer> oc_table;
public TableColumn<Customer,String> col_username;
public TableColumn<Customer,String> col_password;
public TableColumn<Customer,Integer> col_points;
public TextField textfield_username;
public TextField textfield_password;
File customersFile = new File("customers.txt");

    @FXML
    private Button addCustomer;   
        
    @FXML
    private Button deleteCustomer;
    
    @FXML
    private Button back;
    
    @FXML
    private Button logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       col_username.setCellValueFactory(new PropertyValueFactory<>("Col_username"));
       col_password.setCellValueFactory(new PropertyValueFactory<>("Col_password"));
       col_points.setCellValueFactory(new PropertyValueFactory<>("Col_points"));
       oc_table.setItems(observableList);
       
       try{
            Scanner customerScanner = new Scanner(customersFile);
            while(customerScanner.hasNext()){
              String[] eachLine = customerScanner.nextLine().split("---");
              Customer p = new Customer(eachLine[0],eachLine[1],Double.parseDouble(eachLine[2]),eachLine[3]);
//              System.out.println(eachLine[1]);
              
              oc_table.getItems().add(p);
              
            }
            customerScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File customers.txt not found");
            e.printStackTrace();  
        }   
    }
    
ObservableList<Customer> observableList = FXCollections.observableArrayList ();
               
    public void buttonAdd(ActionEvent actionEvent){
       Customer customer = new Customer(textfield_username.getText(),textfield_password.getText(),0.0,"Silver");
       oc_table.getItems().add(customer);
       owner.createCustomer(textfield_username.getText(), textfield_password.getText(), 0.0, "Silver");
       
    }
    
    public void buttonDelete (ActionEvent actionEvent){
    ObservableList<Customer> allCustomer,SingleCustomer;
    allCustomer=oc_table.getItems();
    SingleCustomer= oc_table.getSelectionModel().getSelectedItems();
    Customer p = oc_table.getSelectionModel().getSelectedItem();
    owner.deleteCustomer(p.getCol_username());
    SingleCustomer.forEach(allCustomer::remove);
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