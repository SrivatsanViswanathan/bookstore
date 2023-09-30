package sample;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

public class LogIn {

    public LogIn() {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;



    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        Owner owner = Owner.getInstance();
        
        if(username.getText().equals(owner.getUsername()) && password.getText().equals(owner.getPassword())){
            wrongLogIn.setText("Owner Login Successful!");
            m.changeScene("OwnerMenu.fxml");
        }else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogIn.setText("Please enter your data.");
        }
        else {
            wrongLogIn.setText("Wrong username or password!");
        }
        for(Customer c : owner.allCustomers){
            if(username.getText().equals(c.getUsername()) && password.getText().equals(c.getPassword())){
                wrongLogIn.setText("Customer Login Successful!");
//                m.changeScene("CustomerMenu.fxml");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CustomerMenu.fxml"));
                Parent view = loader.load();
                Scene viewScene = new Scene(view);
                
                CustomerMenu customerMenu = loader.getController();
                customerMenu.initData(c);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            }   
        }

    }

    private void checkLogin() throws IOException {
        
        
    }


}