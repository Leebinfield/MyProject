import java.sql.PreparedStatement;
import java.util.Scanner;
import org.sqlite.SQLiteConfig;
import java.sql.*;
public class Area {
    public static Connection db = null;

    public static void Locate() {
        try {
            System.out.println("Enter your nearest town: ");
            Scanner scanner = new Scanner(System.in);
            String nTown = scanner.nextLine();
            PreparedStatement ps = db.prepareStatement("INSERT INTO Area(town)VALUES (?)");
            ps.setString(1, nTown);
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
}




