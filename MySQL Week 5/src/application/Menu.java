package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import java.util.List;
import entity.Pizza;
import dao.PizzaDao;

public class Menu {
	private PizzaDao pizzaDao = new PizzaDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Pizzas", 
			"Display a Pizza", 
			"Create A Pizza", 
			"Delete A Pizza",
			"Edit a Pizza");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {

				if (selection.equals("1")) {
					this.displayPizzas();
				} else if (selection.equals("2")) {
					this.displayPizza();
				} else if (selection.equals("3")) {
					this.createPizza();
				} else if (selection.equals("4")) {
					this.deletePizza();
				} else if (selection.equals("5")) {
					this.editPizza();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue.");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}

	private void editPizza() throws SQLException {
		System.out.print("Enter Pizza ID to Edit: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter New Pizza Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter New Pizza Price: ");
		Float price = Float.parseFloat(scanner.nextLine());
		pizzaDao.editPizzaByID(id, name, price);
	}

	private void deletePizza() throws SQLException {
		System.out.print("Enter Pizza ID to Delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		pizzaDao.deletePizzaByID(id);
		
	}

	private void createPizza() throws SQLException {
		System.out.print("Enter Pizza ID: ");
		int pizzaID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Pizza Name: ");
		String pizzaName = scanner.nextLine();
		System.out.print("Enter Pizza Price: ");
		Float pizzaPrice = Float.parseFloat(scanner.nextLine());
		pizzaDao.createNewPizza(pizzaID, pizzaName, pizzaPrice);
	}

	private void displayPizza() throws SQLException {
		System.out.print("Enter pizza ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Pizza pizza = pizzaDao.getPizzaByID(id);
		System.out.println(pizza.getPizzaID() + ": " + pizza.getPizzaName());
		System.out.println("\tPizza ID: " + pizza.getPizzaID() + " | Pizza Name:" + pizza.getPizzaName() + " | Pizza Price: " + pizza.getPizzaPrice());
	}

	private void displayPizzas() throws SQLException {
		List<Pizza> pizzas = pizzaDao.getPizzas();
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.getPizzaID() + ": " + pizza.getPizzaName() + " (" + pizza.getPizzaPrice() + ")");
		}
	}

	private void printMenu() {
		System.out.println("Select an Option: \n");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + "] " + options.get(i));
		}
	}
}
