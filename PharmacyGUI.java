/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class PharmacyGUI {
    private final List<Order> orderList;
    private final List<Drug> drugList;
    private final JTextArea textArea;  // Updated variable name
    private JPanel drugsInfoPanel;

    private boolean initialized;
    public int pharmacyCapacity;

    public PharmacyGUI(JTextArea textArea) {
        this.textArea = textArea;
        orderList = new ArrayList<>();
        drugList = new ArrayList<>();
        drugsInfoPanel = new JPanel(); // Initialize the drugsInfoPanel
        drugsInfoPanel.setLayout(new BoxLayout(drugsInfoPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        drugsInfoPanel.setBackground(new Color(34, 192, 113));// set color
    }
    public void initializeSystem() {
        if (!initialized) {
            pharmacyCapacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the pharmacy capacity (maximum number of drugs):"));
            initialized = true;
        } else {
            JOptionPane.showMessageDialog(null, "System is already initialized.");
        }
    }

    public void addDrug() {
        try {
            if (drugList.size() >= pharmacyCapacity) {
                throw new Exception("Maximum pharmacy capacity (" + pharmacyCapacity + ") reached. Please remove a drug before adding more.");
            }

            int id;
            do {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug Id:"));
                if (isIdAlreadyUsed(id)) {
                    JOptionPane.showMessageDialog(null, "ID already entered! Please enter a different ID.");
                }
            } while (isIdAlreadyUsed(id));
            int price = Integer.parseInt(JOptionPane.showInputDialog("Enter drug price:"));
            String category = (String) JOptionPane.showInputDialog(
                    null, "Select drug category:",
                    "Category", JOptionPane.QUESTION_MESSAGE, null,
                    Drug.getCategoryOptions(), Drug.getCategoryOptions()[0]
            );
            int availableQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter drug quantity:"));
            Drug newDrug = new Drug(id, price, category, availableQuantity);
            drugList.add(newDrug);
            updateDrugsInfoPanel(); // Update the display of available drugs
            textArea.append("Drug added successfully!\n");  // Updated variable
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numeric values.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }



    public void removeDrug() {
        try {
            int removedId = Integer.parseInt(JOptionPane.showInputDialog("Enter the drug Id to remove:"));

            if (!isIdInList(removedId, drugList)) {
                throw new Exception("Drug with ID " + removedId + " not found.");
            }

            drugList.removeIf(drug -> drug.getId() == removedId);

            updateDrugsInfoPanel();

            textArea.append("Drug removed successfully!\n");  // Updated variable
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid numeric ID.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void placeOrder() {
        try {
            int drugId = Integer.parseInt(JOptionPane.showInputDialog("Enter drug Id for the order:"));

            if (!isIdInList(drugId, drugList)) {
                throw new Exception("Drug with ID " + drugId + " not found.");
            }

            Drug selectedDrug = findDrugById(drugId);

            // Display the price for a single drug
            JOptionPane.showMessageDialog(null, "Price for a single drug (ID " + drugId + "): " + selectedDrug.getPrice());

            int availableQuantity = selectedDrug.getAvailableQuantity();
            int quantity;

            do {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity for the order:"));

                if (quantity > availableQuantity) {

                    JOptionPane.showMessageDialog(null,
                            "The available amount of drugs (ID " + drugId + ") is: " + availableQuantity,
                            "Insufficient Quantity", JOptionPane.WARNING_MESSAGE);
                }

            } while (quantity > availableQuantity);

            selectedDrug.setAvailableQuantity(availableQuantity - quantity);

            if (selectedDrug.getAvailableQuantity() <= 0) {
                drugList.remove(selectedDrug);
                JOptionPane.showMessageDialog(null, "Drug (ID " + drugId + ") removed as quantity is now zero.");
            }

            updateDrugsInfoPanel();

            double unitPrice = selectedDrug.getPrice();
            double totalPrice = quantity * selectedDrug.calculateFinalPrice();

            Order order = new Order(selectedDrug, quantity, totalPrice);
            orderList.add(order);

            textArea.append("Order placed successfully!\n");  // Updated variable
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid numeric ID or quantity.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private boolean isIdInList(int id, List<Drug> drugList) {
        for (Drug drug : drugList) {
            if (drug.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void calculateTotalSales() {
        double totalSales = calculateTotalSalesValue();
        JOptionPane.showMessageDialog(null, "Total sales for the day: " + totalSales);
    }

    private double calculateTotalSalesValue() {
        double totalSales = 0.0;
        for (Order order : orderList) {
            totalSales += order.getTotalPrice();
        }
        return totalSales;
    }


    private Drug findDrugById(int id) {
        for (Drug drug : drugList) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    private boolean isIdAlreadyUsed(int id) {
        for (Drug drug : drugList) {
            if (drug.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void updateDrugsInfoPanel() {
        drugsInfoPanel.removeAll();
        JLabel drugsInfoLabel = new JLabel("Available Drugs:");
        drugsInfoPanel.add(drugsInfoLabel);

        for (Drug drug : drugList) {
            JLabel drugLabel = new JLabel("ID: " + drug.getId() +
                    " | Type: " + drug.getCategory() +
                    " | Price: " + drug.getPrice() +
                    " | Quantity: " + drug.getAvailableQuantity());
            drugsInfoPanel.add(drugLabel);
        }

        drugsInfoPanel.revalidate();
        drugsInfoPanel.repaint();
    }

    public void setDrugsInfoPanel(JPanel drugsInfoPanel) {
        this.drugsInfoPanel = drugsInfoPanel;
        updateDrugsInfoPanel();
    }
}
