package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        Owner owner = Owner.getInstance();
        owner.readBooksFromFileIntoMAINList();
        owner.readCustomersFromFileIntoMAINList();
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
//        coe528_final_project.Owner dann = coe528_final_project.Owner.getInstance();
//        dann.printAllBooks();
        launch(args);
        
        
    }
}
