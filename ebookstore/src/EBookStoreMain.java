 import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EBookStoreMain {
    //Create collection of Objects
    static List <Books> listOfBooks = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        // Main method where all the other methods are called
        // Menu for user selection
        sqlIn();
        int userInputInt = 1;

        //Option to see list of books
        Scanner seeBooks = new Scanner(System.in);
        System.out.print("Y\t\tTo see all books in the System\nN\t\tTo go to main menu\nEnter Here: ");
        String seeAllBooks = seeBooks.next().toLowerCase();

        if (seeAllBooks.equals("y")){
            for(int i = 0; i < listOfBooks.toArray().length; i++){
                System.out.print(listOfBooks.get(i).toString());
            }
        }

        while (userInputInt != 0){
            // Main menu
            Scanner userInput = new Scanner(System.in);
            System.out.print("\n1.\t" + "Enter book\n" + "2.\t" + "Update book\n" + "3.\t" + "Delete book\n" + "4.\t" + "Search books\n" + "0.\t" + "Exit\n" + "Enter Here: ");
            userInputInt =  userInput.nextInt();

            if (userInputInt == 1){
                enter();
            }

            else if(userInputInt == 2){
                update();
            }

            else if(userInputInt == 3){
                delete();
            }

            else if(userInputInt == 4){
                search();
            }

            else if( userInputInt == 0){
                sqlOut();
                System.out.print("\nGoodbye!");
            }

            else {
                System.out.print("\nWrong Choice, Try Again!");
            }
        }
    }

    public static void sqlIn() throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "adamkraftk", "johnadam1@RSA");
        Statement statement = connection.createStatement();
        ResultSet results;

        // Calls the data from SQL
        results = statement.executeQuery("select * from ebooks");


        while (results.next()) {
            // Turns Sql data into Book object
            Books logBooks = new Books(results.getInt("id"), results.getString("Title"), results.getString("Author"), results.getInt("Qty"));
            EBookStoreMain.listOfBooks.add(logBooks);
        }

        //Close Connection
        results.close();
        statement.close();
        connection.close();
    }

    public static void sqlOut() throws SQLException {
        // SQL connection to overwrite previous SQL data
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "adamkraftk", "johnadam1@RSA");
        PreparedStatement delete = connection.prepareStatement("delete from ebooks");

        // Delete previous data
        delete.executeUpdate();

        for(int i = 0; i < listOfBooks.toArray().length; i++){
            //Append new data to SQL
           PreparedStatement sqlBooksAdd = connection.prepareStatement("Insert into ebooks values('" + listOfBooks.get(i).getId() + "','" + listOfBooks.get(i).getTitle() + "','" + listOfBooks.get(i).getAuthor() + "','" + listOfBooks.get(i).getQty() + "')");
            sqlBooksAdd.executeUpdate();
        }

        //CLose Connection
        delete.close();
        connection.close();
    }

    public static void search(){
        // Get book id.
        Scanner userSearchInput = new Scanner(System.in);
        System.out.print("\nWhat is the id of the book you are looking for: ");
        int bookId = userSearchInput.nextInt();
        int controller = 0;

        // Print book
        for (int i = 0; i < listOfBooks.toArray().length; i++){
            if (listOfBooks.get(i).getId() == bookId){
                controller = 1;
                System.out.print(listOfBooks.get(i));
            }
        }
        if(controller == 0){
            System.out.print("\nBook not found!");
        }
    }

    public static void update(){

        // Get book id
        Scanner userSearchInput = new Scanner(System.in);
        System.out.print("\nWhat is the id of the book you would like to update: ");
        int bookId = userSearchInput.nextInt();
        int controller = 0;

        // Update book information
        for (int i = 0; i < listOfBooks.toArray().length; i++){
            if (listOfBooks.get(i).getId() == bookId){

                controller = 1;
                System.out.print(listOfBooks.get(i).toString());

                Scanner newIdInput = new Scanner(System.in);
                System.out.print("\nEnter a new Id to reassign this books Id: ");
                int newId = newIdInput.nextInt();

                Scanner newTitleInput = new Scanner(System.in);
                System.out.print("\nEnter new Title name to rename the Title of this book: ");
                String newTitle = newTitleInput.nextLine();

                Scanner newAuthorInput = new Scanner(System.in);
                System.out.print("\nEnter new Author name to rename the Author of this book: ");
                String newAuthor = newAuthorInput.nextLine();

                Scanner newQtyInput = new Scanner(System.in);
                System.out.print("\nEnter new Quantity in numbers to update the amount of books: ");
                int newQty = newQtyInput.nextInt();

                listOfBooks.get(i).setId(newId);
                listOfBooks.get(i).setTitle(newTitle);
                listOfBooks.get(i).setAuthor(newAuthor);
                listOfBooks.get(i).setQty(newQty);

                System.out.print(listOfBooks.get(i).toString());
            }
        }

        if(controller == 0){
            System.out.print("\nBook not found!");
        }
    }

    public static void enter(){

        // Enter new book
        Scanner newIdInput = new Scanner(System.in);
        System.out.print("\nEnter an Id for this book: ");
        int newId = newIdInput.nextInt();

        Scanner newTitleInput = new Scanner(System.in);
        System.out.print("\nEnter a Title for this book: ");
        String newTitle = newTitleInput.nextLine();

        Scanner newAuthorInput = new Scanner(System.in);
        System.out.print("\nEnter an Author name for this book: ");
        String newAuthor = newAuthorInput.nextLine();

        Scanner newQtyInput = new Scanner(System.in);
        System.out.print("\nEnter a Quantity in numbers for the amount of books: ");
        int newQty = newQtyInput.nextInt();

        // Make new book an object
        Books enterBooks = new Books(newId, newTitle, newAuthor, newQty);
        System.out.print(enterBooks);
        listOfBooks.add(enterBooks);
    }

    public static void delete(){

        // Search for book
        Scanner userSearchInput = new Scanner(System.in);
        System.out.print("\nWhat is the id of the book you would like to update: ");
        int bookId = userSearchInput.nextInt();
        int controller = 0;

        //Delete book
        for (int i = 0; i < listOfBooks.toArray().length; i++){

            if(listOfBooks.get(i).getId() == bookId){
                controller = 1;
                System.out.print("\nDeleting book:\n" + listOfBooks.get(i).toString());
                listOfBooks.remove(i);
            }
        }

        if(controller == 0){
            System.out.print("\nBook not found!");
        }

    }
}
