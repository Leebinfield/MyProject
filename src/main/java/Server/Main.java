package Server;
import java.util.Scanner;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.sqlite.SQLiteConfig;
import java.sql.*;
import org.eclipse.jetty.servlet.*;
import org.glassfish.jersey.servlet.*;



public class Main {
    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("ProjectDB.db");
        //Select();
        //Add();
        //Delete();
       // Update();
        ResourceConfig config = new ResourceConfig();
        config.packages("Controllers");
        config.register(MultiPartFeature.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");
        //closeDatabase();
        try {
            server.start();
            System.out.println("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void openDatabase(String dbFile) { //This is the name of the method for recalling purposes
        try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            Class.forName("org.sqlite.JDBC"); // Statements which prepare and work with the database software to communicate with IntelliJ
            SQLiteConfig config = new SQLiteConfig(); // *
            config.enforceForeignKeys(true); // 'Foreign keys' are keys which link tables.
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties()); // setting 'db' to the connection of the database to be used later
            // This is also where the connection to the database is completed
            System.out.println("Database connection successfully established."); // Final output of the method which informs of the connection to the database
        } catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database connection error: " + exception.getMessage()); //This displays the message including the error (if present)
        }
    }
    private static void closeDatabase() { //This is the name of the method for recalling purposes
        try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            db.close(); //This closes the connection from the database.
            System.out.println("Disconnected from database.");
        } catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database disconnection error: " + exception.getMessage()); //This displays the message including the error (if present)
        }
    }

    public static void Select() {//This is the name of the method for recalling purposes
        try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            PreparedStatement ps = db.prepareStatement("Select ID,Lastname,Firstname from Accounts");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands
            //The keyword is 'SELECT' and is called different to the other 3 CRUD statements, the communication with the database is nearer the start.
            ResultSet results = ps.executeQuery();
            while (results.next()) { //Simple while loop to allow the project to produce all accounts eg. not stop at 5 accounts
                String ID = results.getString(1); //This puts data received from the database into variables which can be called later
                String Lastname = results.getString(2); // *
                String Firstname = results.getString(3); // *
                System.out.println("User ID " + ID + " Lastname: " + Lastname + " Firstname:" + Firstname); //Final output statement of the loop - After every output it starts again
            }
        } catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database error: " + exception.getMessage()); //This displays the message including the error (if present)
        }
    }

    public static void Add() { //This is the name of the method for recalling purposes
        try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            Scanner scanner = new Scanner(System.in); //Scanner is an instance which reads the input of the users.
            System.out.println("Enter the name:"); //1.A message which gets displayed to the user provoking an answer
            String name = scanner.nextLine(); //2.This is the scanner instance reading and storing the input as a variable
            System.out.println("Enter the PP:");//1
            String price = scanner.nextLine();//2
            System.out.println("Enter the postcode:");//1
            String postcode = scanner.nextLine();//2
            System.out.println("Enter the RestaurantID:");//1
            String RestID = scanner.nextLine();//2
            PreparedStatement ps = db.prepareStatement("INSERT INTO Restaurant(Rname,price,postcode,RestID)VALUES (?,?,?,?)");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands
            ps.setString(1, name); //This assigns the variables above (?,?,?,?) to the respective inputted variable
            ps.setString(2, price);// -
            ps.setString(3, postcode);// -
            ps.setString(4, RestID);// -
            ps.executeUpdate(); // This commences the transfer of data to the database
        } catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database error: " + exception.getMessage()); //This displays the message including the error (if present)
        }
    }

    private static void Delete() { //This is the name of the method
        try { // The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            Scanner scanner = new Scanner(System.in);//Scanner is an instance which reads the input of the users.
            System.out.println("Enter the lastname of the user:");//1.A message which gets displayed to the user provoking an answer
            String x = scanner.nextLine();//2.This is the scanner instance reading and storing the input as a variable
            PreparedStatement ps = db.prepareStatement("DELETE FROM Accounts WHERE main.Accounts.Lastname= ?");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands
            ps.setString(1, x);//This assigns the variables above (?) to the respective inputted variable
            ps.executeUpdate();// This commences the transfer of data to the database
        } catch (Exception exception) {// As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database error: " + exception.getMessage());//This displays the message including the error (if present)
        }
    }

    private static void Update() {//This is the name of the method
        try { // The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            Scanner scanner = new Scanner(System.in);//Scanner is an instance which reads the input of the users.
            System.out.println("Enter new name:");//1.A message which gets displayed to the user provoking an answer
            String newFName = scanner.nextLine();//2.This is the scanner instance reading and storing the input as a variable
            System.out.println("enter new food:");//1.A message which gets displayed to the user provoking an answer
            String newLName = scanner.nextLine();//2.This is the scanner instance reading and storing the input as a variable
            System.out.println("Enter target name:");//1.A message which gets displayed to the user provoking an answer
            String target = scanner.nextLine();//2.This is the scanner instance reading and storing the input as a variable
            PreparedStatement ps = db.prepareStatement("UPDATE main.Accounts SET main.Accounts.Firstname = ?, main.Accounts.Lastname = ? WHERE main.Accounts.Lastname=?");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands. UPDATE is the keyword.
            // This updates an account in the database WHERE the account holders lastname (for now - it will be accountID later) is used to change and update my database with new values
            ps.setString(1, newFName);//This assigns the variables above (?) to the respective inputted variable
            ps.setString(2, newLName);//This assigns the variables above (?) to the respective inputted variable
            ps.setString(3, target);//This assigns the variables above (?) to the respective inputted variable

        } catch (Exception exception) {// As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database error: " + exception.getMessage());//This displays the message including the error (if present)
        }
    }

}
