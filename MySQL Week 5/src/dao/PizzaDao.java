package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pizza;

public class PizzaDao {
	private Connection connection;
	private final String GET_PIZZAS_QUERY = "SELECT * FROM pizzas";
	private final String GET_PIZZA_BY_ID_QUERY = "SELECT * FROM pizzas WHERE pizza_id = ?";
	private final String DELETE_PIZZA_BY_ID_QUERY = "DELETE FROM pizzas WHERE pizza_id = ?";
	private final String EDIT_PIZZA_BY_ID_QUERY = "UPDATE pizzas SET pizza_name=?, pizza_price=? WHERE pizza_id = ?";
	private final String CREATE_NEW_PIZZA_QUERY = "INSERT INTO pizzas(pizza_id, pizza_name, pizza_price) VALUES(?,?,?)";
	
	public PizzaDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Pizza> getPizzas() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_PIZZAS_QUERY).executeQuery();
		List<Pizza> pizzas = new ArrayList<Pizza>();
		
		while (rs.next()) {
			pizzas.add(populatePizzas(
					rs.getInt(1),
					rs.getString(2),
					rs.getFloat(3)
					));
		}
		return pizzas;
	}
	
	public Pizza getPizzaByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PIZZA_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populatePizzas(
				rs.getInt(1),
				rs.getString(2),
				rs.getFloat(3)
				);
	}
	
	public void editPizzaByID(int pizza_target_id, String newPizzaName, Float newPizzaPrice) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_PIZZA_BY_ID_QUERY);
		ps.setString(1, newPizzaName);
		ps.setFloat(2, newPizzaPrice);
		ps.setInt(3, pizza_target_id);
		ps.executeUpdate();
	}
	
	public void deletePizzaByID(int pizza_target_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PIZZA_BY_ID_QUERY);
		ps.setInt(1, pizza_target_id);
		ps.executeUpdate();
	}
	
	public void createNewPizza(int pizzaID, String pizzaName, float pizzaPrice) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_PIZZA_QUERY);
		ps.setInt(1, pizzaID);
		ps.setString(2, pizzaName);
		ps.setFloat(3, pizzaPrice);
		ps.executeUpdate();
	}

	private Pizza populatePizzas(int id, String name, float price) {
		return new Pizza(id, name, price);
	}
}


