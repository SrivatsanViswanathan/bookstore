package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerCostScreen {

    private static Owner owner = Owner.getInstance();
    private Customer selectedCustomer;
      
    @FXML private Button continueShopping;   
    
    @FXML private Button logout;
    @FXML private Label priceLabel;
    @FXML private Label pointLabel;
    @FXML private Label statusLabel;

    public void initDataBuy(Customer c, double bookPrice){
        selectedCustomer = c;
        selectedCustomer.addPoints(bookPrice);
        priceLabel.setText(String.valueOf(bookPrice));
        pointLabel.setText(String.valueOf((int)selectedCustomer.getPoints()));
        selectedCustomer.setStatusState();
        statusLabel.setText(selectedCustomer.getStatusState());
        for(Customer temp : owner.allCustomers){
            if(selectedCustomer.getUsername().equals(temp.getUsername()) && selectedCustomer.getPassword().equals(temp.getPassword())){
                temp.setPoints(selectedCustomer.getPoints());
                owner.updateCustomersToFileFromList(owner.allCustomers);
            }   
        }
    }
    
    public void initDataRedeemBuy(Customer c, double bookPrice){
        selectedCustomer = c;
        selectedCustomer.removePoints(bookPrice);
        priceLabel.setText(String.valueOf(selectedCustomer.getBalance()));
        selectedCustomer.addPoints(selectedCustomer.getBalance());
        pointLabel.setText(String.valueOf((int)selectedCustomer.getPoints()));
        selectedCustomer.setStatusState();
        statusLabel.setText(selectedCustomer.getStatusState());
        for(Customer temp : owner.allCustomers){
            if(selectedCustomer.getUsername().equals(temp.getUsername()) && selectedCustomer.getPassword().equals(temp.getPassword())){
                temp.setPoints(selectedCustomer.getPoints());
                owner.updateCustomersToFileFromList(owner.allCustomers);
            }   
        }
    }
    
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");

    }
    
    public void continueShopping(ActionEvent event) throws IOException {
//        Main m = new Main();
//        m.changeScene("CustomerMenu.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerMenu.fxml"));
        Parent view = loader.load();
        Scene viewScene = new Scene(view);

//        Customer c = new Customer("Bob", "123", 0, "Silver");

        CustomerMenu customerMenu = loader.getController();
        customerMenu.initData(selectedCustomer);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();

    }
}
