/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book implements Cloneable {
    
    private String bookName;
    private double bookPrice;
    public SimpleStringProperty col_bookname;
    public SimpleDoubleProperty col_bookprice;
    
    
    
    Book( String col_bookname, Double col_bookprice) {
        this.col_bookname = new SimpleStringProperty(col_bookname);
        this.col_bookprice = new SimpleDoubleProperty (col_bookprice);
    }
    
    Book( String col_bookname, double col_bookprice) {
        this.col_bookname = new SimpleStringProperty(col_bookname);
        this.col_bookprice = new SimpleDoubleProperty (col_bookprice);
        this.bookName = col_bookname;
        this.bookPrice = col_bookprice;
    }
    
    
    public String getBookName(){
        return this.bookName;
    }
    
    public double getBookPrice(){
        return this.bookPrice;
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
    
    @Override
    public Book clone() throws CloneNotSupportedException {
            return ((Book)super.clone());
    }
            
 
   
}
