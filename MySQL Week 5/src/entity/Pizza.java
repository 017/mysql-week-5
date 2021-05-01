package entity;

public class Pizza {
	private int pizza_id;
	private String pizza_name;
	private float pizza_price;
	
	public Pizza(int pizza_id_input, String pizza_name_input, float pizza_price_input) {
		this.setPizzaID(pizza_id_input);
		this.setPizzaName(pizza_name_input);
		this.setPizzaPrice(pizza_price_input);
	}
	
	public int getPizzaID() {
		return this.pizza_id;
	}
	
	public String getPizzaName() {
		return this.pizza_name;
	}
	
	public float getPizzaPrice() {
		return this.pizza_price;
	}
	
	public void setPizzaID(int new_pizza_id) {
		this.pizza_id = new_pizza_id;
	}
	
	public void setPizzaName(String new_pizza_name) {
		this.pizza_name = new_pizza_name;
	}
	
	public void setPizzaPrice(float new_pizza_price) {
		this.pizza_price = new_pizza_price;
	}
}
