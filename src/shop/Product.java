package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mecatheclau
 */
public class Product extends Dashboard{
    
    public Product() {
        init();
    }
    
    public  void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(223, 239, 255));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Products");
        setLayout(new BorderLayout());
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product().setVisible(true);
            }
        });
    }
    
}
