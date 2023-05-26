package pl.it.camp.sklep.model.product;

import pl.it.camp.sklep.model.Writable;

public class Product implements Writable {
    private final String code;
    private final String type;
    private final String name;
    private final double price;
    private int quantity;

    public Product(String code, String type, String name, double price, int quantity) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String[] vars) {
        this(vars[0], vars[1], vars[2], Double.parseDouble(vars[3]), Integer.parseInt(vars[4]));
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Kod: ")
                .append(this.getCode())
                .append(" | ")
                .append(this.getType())
                .append(" | ")
                .append(this.getName())
                .append(" | Cena: ")
                .append(this.getPrice())
                .append(" | Ilość: ")
                .append(this.getQuantity())
                .append(" szt.")
                .toString();
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toCSV() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(";")
                .append(this.getCode())
                .append(";")
                .append(this.getType())
                .append(";")
                .append(this.getName())
                .append(";")
                .append(this.getPrice())
                .append(";")
                .append(this.getQuantity())
                .toString();
    }
}
