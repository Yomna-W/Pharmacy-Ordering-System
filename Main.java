/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            JTextArea textArea = new JTextArea();
            PharmacyGUI pharmacyGUI = new PharmacyGUI(textArea);
            
            pharmacyGUI.initializeSystem();
            
            createAndShowGUI(pharmacyGUI, textArea);  // Pass textArea to the method
        });
    }

    private static void createAndShowGUI(PharmacyGUI pharmacyGUI, JTextArea textArea) {

        // Create and set up the window
        JFrame frame = new JFrame("Pharmacy System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Create a JTextArea for displaying output
        textArea.setEditable(false);
        textArea.setBackground(new Color(200, 255, 253));  // Set the background color
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);


        // Create a JPanel for displaying drugs info
        JPanel drugsInfoPanel = new JPanel();
        drugsInfoPanel.setPreferredSize(new Dimension(400, Integer.MAX_VALUE));
        pharmacyGUI.setDrugsInfoPanel(drugsInfoPanel);

        // Create buttons
        JButton addDrugButton = new JButton("Add Drug:");
        JButton removeDrugButton = new JButton("Remove Drug:");
        JButton placeOrderButton = new JButton("Place Order:");
        JButton calculateTotalSalesButton = new JButton(" Total Sales:");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        addDrugButton.addActionListener(e -> pharmacyGUI.addDrug());
        removeDrugButton.addActionListener(e -> pharmacyGUI.removeDrug());
        placeOrderButton.addActionListener(e -> pharmacyGUI.placeOrder());
        calculateTotalSalesButton.addActionListener(e -> pharmacyGUI.calculateTotalSales());
        exitButton.addActionListener(e -> System.exit(0));

        // Set buttons background color
        addDrugButton.setBackground(new Color(166,245,226));
        removeDrugButton.setBackground(new Color(166,245,226));
        placeOrderButton.setBackground(new Color(166,245,226));
        calculateTotalSalesButton.setBackground(new Color(166,245,226));
        exitButton.setBackground(new Color(166,245,226));

        // Create a JPanel for displaying buttons and add it to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
        buttonPanel.add(addDrugButton);
        buttonPanel.add(removeDrugButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(calculateTotalSalesButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(drugsInfoPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
