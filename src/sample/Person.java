
package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
   
    public SimpleStringProperty col_bookname;
    public SimpleDoubleProperty col_bookprice;       
    public SimpleIntegerProperty col_points;
    public SimpleStringProperty col_username,col_pass;

    public Person(int col_points, String col_username, String col_pass) {
        this.col_points = new SimpleIntegerProperty(col_points);
        this.col_username = new SimpleStringProperty(col_username);
        this.col_pass = new SimpleStringProperty (col_pass);
    }
    
    public Person( String col_bookname, Double col_bookprice) {
        this.col_bookname = new SimpleStringProperty(col_bookname);
        this.col_bookprice = new SimpleDoubleProperty (col_bookprice);
    }

    public int getCol_points() {
        return col_points.get();
    }

    public void setCol_points(int col_points) {
        this.col_points = new SimpleIntegerProperty(col_points);
    }

    public String getCol_username() {
        return col_username.get();
    }

    public void setCol_username(String col_username) {
        this.col_username = new SimpleStringProperty(col_username);
    }

    public String getCol_pass() {
        return col_pass.get();
    }

    public void setCol_pass(String col_pass) {
        this.col_pass = new SimpleStringProperty (col_pass);
    }


    public String getCol_bookname() {
        return col_bookname.get();
    }

    public void setCol_bookname(String col_bookname) {
        this.col_bookname = new SimpleStringProperty(col_bookname);
    }

    public Double getCol_bookprice() {
        return col_bookprice.get();
    }

    public void setCol_bookprice(Double col_bookprice) {
        this.col_bookprice = new SimpleDoubleProperty (col_bookprice);
    }

   

   
    
}
