package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class OwnerMenu {

      
    @FXML
    private Button book;   
        
    @FXML
    private Button customer;
    
    @FXML
    private Button logout;

    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");

    }
    
    public void ownerBook(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OwnerBookFXML.fxml");

    }
    
    public void ownerCustomer(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("OwnercustomerFXML.fxml");

    }
}
