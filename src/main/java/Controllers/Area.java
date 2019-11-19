package Controllers;
import java.sql.PreparedStatement;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
@Path("Area/")
public class Area {
    public static Connection db = null;

    @POST
    @Path("userArea")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String userArea(
            @FormDataParam("uTown") String uTown,@FormDataParam("id") Integer id) {
        try {
            if (uTown == null || id == null ) {
                throw new Exception("One or more fields empty in HTTP request");
            }
            System.out.println("Town entered =" + uTown);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Area (Town,userID) VALUES (?,?)");
            ps.setString(1, uTown);
            ps.setInt(2, id);
            ps.execute();
            return "{\"status\":\"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }
}


    //public static void Locate() { // Name of the method
      //  try { //The try-catch allows my project to diagnose problems which occur during runtime and display an error message
        //    System.out.println("Enter your nearest town: "); //Output from the program to provoke a response which the project can recognise.
          //  Scanner scanner = new Scanner(System.in); //Scanner is an instance which reads the input of the users.
            //String nTown = scanner.nextLine(); //This is the scanner instance reading and storing the input as a variable
            //PreparedStatement ps = db.prepareStatement("INSERT INTO Area(town)VALUES (?)");
            //The line above is the statement which is commencing the communication to the database and is in a format which SQL understands - The keyword is INSERT
            //ps.setString(1, nTown); //This assigns the variables above (?,?,?,?) to the respective inputted variable
        //} catch (Exception exception) { // As stated earlier; the catch statement will display an error message if an error is found
          //  System.out.println("Database error: " + exception.getMessage()); //This displays the message including the error (if present)
        //}
    //}
//}




