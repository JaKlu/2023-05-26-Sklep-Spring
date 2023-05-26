package pl.it.camp.sklep.model.product;

public class Computer extends Product {

    public Computer(String code, String name, double price, int quantity) {
        super(code, "Komputer", name, price, quantity);
    }

    public Computer(String[] vars) {
        super(vars);
    }
}
