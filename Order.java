/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
public class Order {
    private final Drug drug;
    private final int quantity;
    private final double totalPrice;

    public Order(Drug drug, int quantity, double totalPrice) {
        this.drug = drug;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
