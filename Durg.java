/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
public class Durg {
    private int id;
    private int price;
    private String category;
    private int availableQuantity;

    public Durg(int id, int price, String category, int availableQuantity) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.availableQuantity = availableQuantity;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double calculateFinalPrice() {
        return category.equals("cosmetics") ? price *1.2: price;
    }
    public static String[] getCategoryOptions() {
        return new String[]{"cosmetics", "prescription drugs", "other"};
    }

}
