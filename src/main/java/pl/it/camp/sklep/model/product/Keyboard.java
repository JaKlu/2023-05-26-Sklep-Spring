package pl.it.camp.sklep.model.product;

public class Keyboard extends Product {
    public Keyboard(String code, String name, double price, int quantity) {
        super(code, "Klawiatura", name, price, quantity);
    }

    public Keyboard(String[] vars) {
        super(vars);
    }
}
