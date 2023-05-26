package pl.it.camp.sklep.model.product;

public class Monitor extends Product{
    public Monitor(String code, String name, double price, int quantity) {
        super(code, "Monitor", name, price, quantity);
    }

    public Monitor(String[] vars) {
        super(vars);
    }
}
