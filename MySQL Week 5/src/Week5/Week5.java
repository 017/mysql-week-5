package Week5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author 017
 *
 */
public class Week5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String connString = "jdbc:mysql://localhost:3306/pizza_db";
		final String SELECT_QUERY = "SELECT * FROM pizzas WHERE pizza_id = ?";
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connection conn = DriverManager.getConnection(connString, "root", "root");
			System.out.println("Connected to DB Successfully.");
			System.out.print("Enter Pizza ID Number: ");
			String pizzaID = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
			ps.setString(1, pizzaID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Pizza ID: " + rs.getInt(1) + " Pizza Name: " + rs.getString(2) + " Pizza Price: " + rs.getFloat(3));
			}
		} catch (SQLException e) {
			System.out.println("Error connecting to DB.");
			e.printStackTrace();
		}
		scanner.close();
		
	}

}
