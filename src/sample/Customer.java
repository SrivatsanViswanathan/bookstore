/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer extends User implements Cloneable{
    

    double priceOfBook;
    double points;
    double balance = 0;
    String status = "Silver";
    public SimpleDoubleProperty col_points;
    public SimpleStringProperty col_username,col_pass;
    
    // customer Constructor(username, password, double points, string status) needed in this format also I need a way to, I need to be able to set the points of a customer from the constructor, 
    public Customer(String username, String password, double points, String status){
        super(username,password);
        this.points = points;
        this.status = status;
        this.col_points = new SimpleDoubleProperty(points);
        this.col_username = new SimpleStringProperty(username);
        this.col_pass = new SimpleStringProperty (password);
    }
    public Double getBookPriceFromFile(String bookName) throws FileNotFoundException{
        Scanner booksScanner = new Scanner(booksFile);
        while(booksScanner.hasNext()){
            String[] eachLine = booksScanner.nextLine().split("---");
            if(eachLine[1].equals(bookName)){
              priceOfBook = Double.parseDouble(eachLine[eachLine.length-1]);
            }
        }    
        booksScanner.close();
        return priceOfBook;
    }
    
    public void setPoints(double your_points) {
        if (your_points >= 0) {
            this.points = your_points;
        }
    }
    
    public void addPoints(double value) {
        points = points + value*10;
    }
    
    public void removePoints(double value) {
        if (points >= value*100) {
            System.out.println(points);
            points = points - value*100;
            System.out.println(points);
            balance = 0;
            System.out.println(value);
            System.out.println(balance);
        }
        else {
            balance = value - points*0.01;
            points = 0;
        }
    }
    
    public void endBalance(double value) {
        balance = value - points*0.01;
        if (balance < 0) {
            balance = 0;
        }
    }
    
    public double getPoints() {
        return points;
    }
    
    public double getBalance() {
        return balance;
    }
    
    
    public void setStatusState() {
        if (points < 1000) {
            status = "Silver";
        }
        else if (points >= 1000) {
            status = "Gold";
        }
    }
    
    public String getStatusState() {
        return status;
    }
    
    @Override
    public String toString() {
        System.out.println(getUsername() + "    " + getPassword() + "    " + getPoints() +  "    " + getStatusState());
        System.out.println("Price of book: " + getBalance());
        return String.format(getUsername() + "---" +  getPassword() + "---" + getPoints() + "---" + getStatusState());
    }
    
    @Override
    
    public Customer clone() throws CloneNotSupportedException{
        return (Customer)super.clone();
    }
    public Double getCol_points() {
        return col_points.get();
    }

    public void setCol_points(int col_points) {
        this.col_points = new SimpleDoubleProperty(col_points);
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
}

