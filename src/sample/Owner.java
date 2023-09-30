/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;
import java.util.*;
import java.io.*;

public class Owner extends User{
    
    File customersFile = new File("customers.txt ");
    
    private static Owner instance;
    List <Customer> allCustomers = new ArrayList<>();
    List <Book> allBooks = new ArrayList<>();
    
    //Using Singleton Design Pattern (only one owner can be created)
    private Owner(){
        super("admin","admin"); 
    }
    public static Owner getInstance(){
        if(instance == null){
            instance = new Owner();
        }
        return instance;
    }
    public void readCustomersFromFileIntoMAINList(){
        try{
            Scanner customerScanner = new Scanner(customersFile);
            while(customerScanner.hasNext()){
              String[] eachLine = customerScanner.nextLine().split("---");
              createCustomer(eachLine[0],eachLine[1],Double.parseDouble(eachLine[2]),eachLine[3]);
            }
            customerScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File customers.txt not found");
            e.printStackTrace();  
        }   
    }
    private void writeCustomersToFileFromList(List<Customer> customers){
        try{
            FileWriter customerWriter = new FileWriter(customersFile);
            for(Customer c: customers ){
                String customerLine = c.getUsername() + "---" + c.getPassword() + "---" + c.getPoints() + "---" + c.getStatusState() + "\n";
                customerWriter.write(customerLine);
            }
            customerWriter.close();
        }catch(IOException e){
           System.out.println("An error occurred.");
                e.printStackTrace(); 
        }
    }       
//Write to "books.txt" from any ArrayList<Book> List
    private void writeBooksToFileFromList(List<Book> books){
        try{
            FileWriter bookWriter = new FileWriter(booksFile);
            for(Book b: books ){
                String bookLine = b.getBookName() + "---" + b.getBookPrice() + "\n";
                bookWriter.write(bookLine);
            }
            bookWriter.close();  
        }catch(IOException e){
           System.out.println("An error occurred.");
                e.printStackTrace(); 
        }
    }    
    //Scan data from "books.txt" and put it into the ArrayList<Book> "allBooks"  
    public void readBooksFromFileIntoMAINList(){
        try{
            Scanner booksScanner = new Scanner(booksFile);
            while(booksScanner.hasNext()){
              String[] eachLine = booksScanner.nextLine().split("---");
              createBook(eachLine[0],Double.parseDouble(eachLine[eachLine.length-1])); 
            }
            booksScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File books.txt not found");
            e.printStackTrace();  
        }
        
    }
    
    void updateCustomersToFileFromList(List<Customer> customers){
        try{
            FileWriter customerWriter = new FileWriter(customersFile);
            for(Customer c: customers ){
                String customerLine = c.getUsername() + "---" + c.getPassword() + "---" + c.getPoints() + "---" + c.getStatusState() + "\n";
                customerWriter.write(customerLine);
            }
            customerWriter.close();
        }catch(IOException e){
           System.out.println("An error occurred.");
                e.printStackTrace(); 
        }
    } 
    
    //Owners control over all BOOK COMMANDS
    public void checkNumberOfBooks(){
        int num = allBooks.size();
        System.out.println(num);
    }   
    public void createBook(String bookName, double bookPrice){
        Book book = new Book(bookName,bookPrice);
        allBooks.add(book);
        writeBooksToFileFromList(allBooks);
    }
    public void deleteBook(String bookName){
        int index = 0;
        int check = 0;
        for(Book b : allBooks){
            if(b.getBookName().equals(bookName)){
                index = allBooks.indexOf(b);
                System.out.println("Book Found");
                check = 1;
            }else{
              System.out.println("Book Not Found");  
            }  
        }
        if(check == 1){
            allBooks.remove(index);
        }else
            System.out.println("NOTHING IS REMOVED");
        //Copy Collection into temporary Collection
         List <Book> allBooksCopy = new ArrayList<>();
         Iterator<Book> iterator = allBooks.iterator();
        try {
            while(iterator.hasNext()){
                allBooksCopy.add((Book) iterator.next().clone());
            }
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        //clear all the books data in the "allBooks" ArrayList and write it to the file to achive an empty books.txt file
        allBooks.clear();
        writeBooksToFileFromList(allBooks);
        writeBooksToFileFromList(allBooksCopy);
        //Read all books data from "book.txt" into "allBooks" and then empty the "allBookCopy" array
        readBooksFromFileIntoMAINList();
        allBooksCopy.clear();  
    }
    public void printAllBooks(){
        for(Book b: allBooks){
            System.out.println(b.getBookName() + "---" + b.getBookPrice());
        }
    }    
    public String getBookInfo(String bookName){
        String output = "Book Not Found";
        for(Book b : allBooks){
            if(b.getBookName().equals(bookName)){
                output = b.getBookName() + "---" + b.getBookPrice();
            }
        }
        return output;
    }
   //Owners control over all CUSTOMER COMMANDS
    public void createCustomer(String username, String password, double points, String status){
        Customer customer = new Customer(username, password, points, status);
        allCustomers.add(customer);
        writeCustomersToFileFromList(allCustomers);    
    }
    public void deleteCustomer(String customerName){
        int index = 0;
        int check = 0;
        for(Customer c : allCustomers){
            if(c.getUsername().equals(customerName)){
                index = allCustomers.indexOf(c);
                System.out.println("Customer Found");
                check = 1;        
            }else{
                System.out.println("Customer Not Found");
            }
        }
        if(check == 1){
            allCustomers.remove(index);
        }else{
            System.out.println("NOTHING IS REMOVED");
        }
        List <Customer> allCustomersCopy = new ArrayList<>();
        Iterator<Customer> iterator = allCustomers.iterator();
        try{
            while(iterator.hasNext()){
                allCustomersCopy.add((Customer)iterator.next().clone());
            }
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        allCustomers.clear();
        writeCustomersToFileFromList(allCustomers);
        writeCustomersToFileFromList(allCustomersCopy);
        readCustomersFromFileIntoMAINList();
        allCustomersCopy.clear();   
    }
    public String getCustomerInfo(String customerName){
        String output = "Customer not Found";
        for(Customer c : allCustomers){
            if(c.getUsername().equals(customerName)){
                output = c.getUsername() + "---" + c.getPassword();
            }
        }
        return output;
    }
    public void printAllCustomers(){
        for(Customer c: allCustomers){
            System.out.println(c.getUsername());
        }
    }
}
