package Server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.sqlite.SQLiteConfig;
import java.sql.*;
import java.util.Scanner;
import org.eclipse.jetty.servlet.*;
import org.glassfish.jersey.servlet.*;

import javax.ws.rs.GET;


public class Main {
    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("ProjectDB.db");

        ResourceConfig config = new ResourceConfig();
        config.packages("Controllers");
        config.register(MultiPartFeature.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Add();     //CREATE
        // Select();  //READ
        // Update();  //UPDATE
        // Delete();  //DESTROY
        closeDatabase();

    }

    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase() {
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
    //public static void Select(){
       // try{
           // PreparedStatement ps = db.prepareStatement("Select AccName, FavFood from AccountName");
           // ResultSet results = ps.executeQuery();
           // while (results.next()){
           //     String AccName = results.getString(1);
           //     String Food = results.getString(2);
           //     System.out.println("Username: " + AccName +" Favourite Food: " + Food);
           // }
      //  }catch (Exception exception){
       //     System.out.println("Database error: " + exception.getMessage());
     //   }
   // }
    //public static void Add(){
        //try{
            //Scanner scanner = new Scanner(System.in);
           // String name = scanner.nextLine();
           // String fave = scanner.nextLine();
           // PreparedStatement ps = db.prepareStatement("INSERT INTO AccountName(AccName,FavFood)VALUES (?,?)");
           // ps.setString(1, name);
           // ps.setString(2, fave);
            //ps.executeUpdate();
        //} catch (Exception exception) {
            //System.out.println("Database error: " + exception.getMessage());
       // }
  //  }
   // private static void Delete(){
       // try{
         //   Scanner scanner = new Scanner(System.in);
        //    System.out.println("What account name do you want to delete?:-");
        //    String x = scanner.nextLine();
        //    PreparedStatement ps = db.prepareStatement("DELETE FROM AccountName WHERE main.AccountName.FavFood= ?");
       //     ps.setString(1, x);
       //     ps.executeUpdate();
      //  }catch (Exception exception){
       //     System.out.println("Database error: "+ exception.getMessage());
      //  }
   // }
  //  private static void Update(){
       // try{
          //  Scanner scanner = new Scanner(System.in);
         //   System.out.println("Enter new name:");
          //  String newName = scanner.nextLine();
          //  System.out.println("enter new food:");
          //  String newfood = scanner.nextLine();
          //  System.out.println("Enter target name:");
          //  String target = scanner.nextLine();
          //  PreparedStatement ps = db.prepareStatement("UPDATE main.AccountName SET main.AccountName.AccName = ?, main.AccountName.FavFood = ? WHERE main.AccountName.AccName=?");
          //  ps.setString(1, newName);
          //  ps.setString(2,newfood);
         //   ps.setString(3, target);

       // }catch(Exception exception){
        //    System.out.println("Database error: "+ exception.getMessage());
      //  }
  //  }

//}
