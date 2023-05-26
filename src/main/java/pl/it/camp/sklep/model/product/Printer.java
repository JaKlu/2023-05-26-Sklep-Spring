package pl.it.camp.sklep.model.product;

public class Printer extends Product {
    public Printer(String code, String name, double price, int quantity) {
        super(code, "Drukarka", name, price, quantity);
    }

    public Printer(String[] vars) {
        super(vars);
    }
}
