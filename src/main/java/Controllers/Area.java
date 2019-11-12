package Controllers;

import java.sql.PreparedStatement;
import java.util.Scanner;
import org.sqlite.SQLiteConfig;
import java.sql.*;
public class Area {
    public static Connection db = null;

    public static void Locate() { // Name of the method
        try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
            System.out.println("Enter your nearest town: "); //Output from the program to provoke a response which the project can recognise.
            Scanner scanner = new Scanner(System.in); //Scanner is an instance which reads the input of the users.
            String nTown = scanner.nextLine(); //This is the scanner instance reading and storing the input as a variable
            PreparedStatement ps = db.prepareStatement("INSERT INTO Area(town)VALUES (?)");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands - The keyword is INSERT
            ps.setString(1, nTown); //This assigns the variables above (?,?,?,?) to the respective inputted variable
        } catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
            System.out.println("Database error: " + exception.getMessage()); //This displays the message including the error (if present)
        }
    }
}




