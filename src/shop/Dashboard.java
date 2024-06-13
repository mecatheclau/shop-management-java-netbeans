/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mecatheclau
 */
public class Dashboard extends JFrame{
    JPanel mainPnl, menuPnl, contentPanel, topHeaderPnl, contentWrapper, mainContainer;
    
    DBConnection connection = new DBConnection();
    Connection conn = connection.connect();
    Statement statement;
    
    public Dashboard() {
        init();
        loadDashboardUI();
    }
    
    private void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(233, 240, 240));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Dashboard");
        setLayout(new BorderLayout());
    }
    
    public void loadDashboardUI() {
        mainPnl = new JPanel(new BorderLayout());
        menuPnl = new JPanel();
        menuPnl.setLayout(new BoxLayout(menuPnl, BoxLayout.PAGE_AXIS));
        contentPanel = new JPanel();
        
        menuPnl.setPreferredSize(new Dimension(300, getHeight()));
        menuPnl.setBackground(Color.white);
        menuPnl.setBorder(new EmptyBorder(10, 20, 10, 10));
        
        JLabel menuTitle = new JLabel("Menu");
        menuTitle.setFont(new Font("Arial", 0, 25));
        
        JLabel usersLbl = new JLabel("Users");
        usersLbl.setFont(new Font("Arial", 0, 20));
        usersLbl.setCursor(new Cursor(12));
        
        JLabel productsLbl = new JLabel("Products");
        productsLbl.setFont(new Font("Arial", 0, 20));
        productsLbl.setCursor(new Cursor(12));
        
        JLabel addProductLbl = new JLabel("Add Product");
        addProductLbl.setFont(new Font("Arial", 0, 20));
        addProductLbl.setCursor(new Cursor(12));
        
        JSeparator separator1 = new JSeparator();
        separator1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
                
        menuPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPnl.add(menuTitle);
        menuPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPnl.add(separator1);
        menuPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPnl.add(usersLbl);
        menuPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPnl.add(productsLbl);
        menuPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPnl.add(addProductLbl);
        menuPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        
        Border border = BorderFactory.createMatteBorder(60,30, 0, 30, new Color(233, 240, 240));
        contentPanel.setBorder(border);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                
        topHeaderPnl = new JPanel();   
        topHeaderPnl.setPreferredSize(new Dimension(0, 60));
        topHeaderPnl.setSize(0, 30);
        topHeaderPnl.setBackground(Color.white);
        topHeaderPnl.add(new JLabel("Header"));
        
        contentWrapper = new JPanel();
        contentWrapper.setBackground(Color.white);
        contentWrapper.setLayout(new BorderLayout());
        contentWrapper.add(new JLabel("contentWrapper"));
                
        mainContainer = new JPanel(); 
        mainContainer.setLayout(new BorderLayout());
        
        contentPanel.add(contentWrapper);
        
        mainContainer.add(topHeaderPnl, BorderLayout.NORTH);
        mainContainer.add(contentPanel);
        
        mainPnl.add(menuPnl, BorderLayout.WEST);
        mainPnl.add(mainContainer, BorderLayout.CENTER);
        
        add(mainPnl);
        
        usersLbl.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                usersLblMouseClicked(me);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        addProductLbl.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                AddProductMouseClicked(me);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        productsLbl.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                loadproducts(me);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
                       
    }
    
    public void usersLblMouseClicked(java.awt.event.MouseEvent me) {
        this.setTitle("List of users");
        JPanel usersPnl = new JPanel();
        usersPnl.setBackground(Color.white);
        usersPnl.setLayout(new BorderLayout());
        usersPnl.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));
        JPanel titlePnl = new JPanel();
        titlePnl.setLayout(new BoxLayout(titlePnl, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Users");
        title.setFont(new Font("arial", Font.BOLD, 40));
        titlePnl.setBackground(Color.white);

        JSeparator sep = new JSeparator();
        titlePnl.add(title, BorderLayout.NORTH);
        titlePnl.add(Box.createRigidArea(new Dimension(0,20)));
        titlePnl.add(sep);
        
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                
        String[] columnNames = {"ID", "First name", "Last name", "username"};
        DefaultTableModel usersDTM = new DefaultTableModel(columnNames, 0);
        JTable usersTable = new JTable(usersDTM);
        
        JScrollPane scrollPane = new JScrollPane(usersTable);
        usersTable.setFillsViewportHeight(true);
        usersTable.getColumnModel().getColumn(0);
                
        container.add(scrollPane);
        
        Object[][] data = {};
        try {
            statement = this.conn.createStatement();
            String usersSQL = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(usersSQL);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                };
                usersDTM.addRow(row);
            }
        } catch (SQLException e) {
        }
        
        contentWrapper.removeAll();
        usersPnl.add(titlePnl, BorderLayout.NORTH);
        usersPnl.add(container, BorderLayout.CENTER);
        contentWrapper.add(usersPnl, BorderLayout.PAGE_START);
        contentWrapper.revalidate();
        contentWrapper.repaint();
       
    }
        
    BufferedImage img = null;
    public void AddProductMouseClicked(java.awt.event.MouseEvent me) {
        this.setTitle("Add products");
        JPanel addproductPnl = new JPanel();
        addproductPnl.setBackground(Color.white);
        addproductPnl.setLayout(new BorderLayout());
        addproductPnl.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));
        JPanel titlePnl = new JPanel();
        titlePnl.setLayout(new BoxLayout(titlePnl, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Add product");
        title.setFont(new Font("arial", Font.BOLD, 40));
        titlePnl.setBackground(Color.white);

        JSeparator sep = new JSeparator();
        titlePnl.add(title, BorderLayout.NORTH);
        titlePnl.add(Box.createRigidArea(new Dimension(0,20)));
        titlePnl.add(sep);
        
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        JPanel namePnl = new JPanel();
        namePnl.setBackground(Color.white);
        JLabel nameLbl = new JLabel("Name of the product:          ");
        nameLbl.setFont(new Font("arial", 0, 20));
        JTextField nameTxtFld = new JTextField(30);
        nameTxtFld.setFont(new Font("arial", 0, 20));
        namePnl.add(nameLbl);
        namePnl.add(nameTxtFld);
        
        JPanel pricePnl = new JPanel();
        pricePnl.setBackground(Color.white);
        JLabel priceLbl = new JLabel("Price of the product:          ");
        priceLbl.setFont(new Font("arial", 0, 20));
        JTextField priceTxtFld = new JTextField(30);
        priceTxtFld.setFont(new Font("arial", 0, 20));
        pricePnl.add(priceLbl);
        pricePnl.add(priceTxtFld);
        
        JPanel quantityPnl = new JPanel();
        quantityPnl.setBackground(Color.white);
        JLabel quantityLbl = new JLabel("Quantity of the product:     ");
        quantityLbl.setFont(new Font("arial", 0, 20));
        JTextField quantityTxtFld = new JTextField(30);
        quantityTxtFld.setFont(new Font("arial", 0, 20));
        quantityPnl.add(quantityLbl);
        quantityPnl.add(quantityTxtFld);
        
        
        JPanel buttonPnl = new JPanel();
        buttonPnl.setBackground(Color.white);
        JButton submitBtn = new JButton("Add product");
        submitBtn.setBackground(new Color(20, 40, 200));
        submitBtn.setForeground(Color.white);
        submitBtn.setFont(new Font("arial", 0, 20));
        buttonPnl.add(submitBtn);
        
        JPanel coverPnl = new JPanel();
        coverPnl.setLayout(new BoxLayout(coverPnl, BoxLayout.PAGE_AXIS));
        coverPnl.setBackground(Color.white);
        JLabel coverLbl = new JLabel();
        JButton changeCoverLbl = new JButton("Change cover");
        changeCoverLbl.setBackground(Color.white);
        
        try {
            img = ImageIO.read(Dashboard.class.getResource("images/image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        changeCoverLbl.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                img = bufferUploadedImage(ae);
                if (img != null) {
                    coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
                } else {
                    showAlert("Error uploading", "error");
                }
            }
        });
               
        coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
        coverLbl.setPreferredSize(new Dimension(260, 260));
        coverPnl.add(coverLbl);
        coverPnl.add(changeCoverLbl);
        
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.add(namePnl);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(pricePnl);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(quantityPnl);
        container.add(Box.createRigidArea(new Dimension(0, 40)));
        container.add(buttonPnl);
        
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Boolean inserted = saveProduct(ae, nameTxtFld.getText(), priceTxtFld.getText(), quantityTxtFld.getText(), img);
                if (inserted) {
                    nameTxtFld.setText("");
                    priceTxtFld.setText("");
                    quantityTxtFld.setText("");
                    try {
                        img = ImageIO.read(Dashboard.class.getResource("images/image.png"));
                        coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        contentWrapper.removeAll();
        addproductPnl.add(titlePnl, BorderLayout.NORTH);
        addproductPnl.add(container, BorderLayout.WEST);
        addproductPnl.add(coverPnl, BorderLayout.CENTER);
        contentWrapper.add(addproductPnl, BorderLayout.PAGE_START);
        contentWrapper.revalidate();
        contentWrapper.repaint();
    }
    
    public void editProductActionListener(java.awt.event.ActionEvent ae, int productId, String name, String price, String quantity, String productCover) {
        this.setTitle("Edit product");
        JPanel addproductPnl = new JPanel();
        addproductPnl.setBackground(Color.white);
        addproductPnl.setLayout(new BorderLayout());
        addproductPnl.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));
        JPanel titlePnl = new JPanel();
        titlePnl.setLayout(new BoxLayout(titlePnl, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Edit product");
        title.setFont(new Font("arial", Font.BOLD, 40));
        titlePnl.setBackground(Color.white);

        JSeparator sep = new JSeparator();
        titlePnl.add(title, BorderLayout.NORTH);
        titlePnl.add(Box.createRigidArea(new Dimension(0,20)));
        titlePnl.add(sep);
        
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        JPanel namePnl = new JPanel();
        namePnl.setBackground(Color.white);
        JLabel nameLbl = new JLabel("Name of the product:          ");
        nameLbl.setFont(new Font("arial", 0, 20));
        JTextField nameTxtFld = new JTextField(name, 30);
        nameTxtFld.setFont(new Font("arial", 0, 20));
        namePnl.add(nameLbl);
        namePnl.add(nameTxtFld);
        
        JPanel pricePnl = new JPanel();
        pricePnl.setBackground(Color.white);
        JLabel priceLbl = new JLabel("Price of the product:          ");
        priceLbl.setFont(new Font("arial", 0, 20));
        JTextField priceTxtFld = new JTextField(price, 30);
        priceTxtFld.setFont(new Font("arial", 0, 20));
        pricePnl.add(priceLbl);
        pricePnl.add(priceTxtFld);
        
        JPanel quantityPnl = new JPanel();
        quantityPnl.setBackground(Color.white);
        JLabel quantityLbl = new JLabel("Quantity of the product:     ");
        quantityLbl.setFont(new Font("arial", 0, 20));
        JTextField quantityTxtFld = new JTextField(quantity, 30);
        quantityTxtFld.setFont(new Font("arial", 0, 20));
        quantityPnl.add(quantityLbl);
        quantityPnl.add(quantityTxtFld);
        
        
        JPanel buttonPnl = new JPanel();
        buttonPnl.setBackground(Color.white);
        JButton submitBtn = new JButton("Update product");
        submitBtn.setBackground(new Color(20, 40, 200));
        submitBtn.setForeground(Color.white);
        submitBtn.setFont(new Font("arial", 0, 20));
        buttonPnl.add(submitBtn);
        
        JPanel coverPnl = new JPanel();
        coverPnl.setLayout(new BoxLayout(coverPnl, BoxLayout.PAGE_AXIS));
        coverPnl.setBackground(Color.white);
        JLabel coverLbl = new JLabel();
        JButton changeCoverLbl = new JButton("Change cover");
        changeCoverLbl.setBackground(Color.white);
        
        try {
            img = ImageIO.read(Dashboard.class.getResource(productCover));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        changeCoverLbl.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                img = bufferUploadedImage(ae);
                if (img != null) {
                    coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
                } else {
                    showAlert("Error uploading", "error");
                }
            }
        });
               
        coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
        coverLbl.setPreferredSize(new Dimension(260, 260));
        coverPnl.add(coverLbl);
        coverPnl.add(changeCoverLbl);
        
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.add(namePnl);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(pricePnl);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(quantityPnl);
        container.add(Box.createRigidArea(new Dimension(0, 40)));
        container.add(buttonPnl);
        
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Boolean inserted = editProduct(ae, productId, nameTxtFld.getText(), priceTxtFld.getText(), quantityTxtFld.getText(), img);
            }
        });
        
        contentWrapper.removeAll();
        addproductPnl.add(titlePnl, BorderLayout.NORTH);
        addproductPnl.add(container, BorderLayout.WEST);
        addproductPnl.add(coverPnl, BorderLayout.CENTER);
        contentWrapper.add(addproductPnl, BorderLayout.PAGE_START);
        contentWrapper.revalidate();
        contentWrapper.repaint();
       
    }
    
    public void deleteProductActionListener(java.awt.event.ActionEvent ae, int productId, String name, String price, String quantity, String productCover) {
        
        JDialog deleteDialog = new JDialog(this, true);
        deleteDialog.setTitle("Delete Product");
        deleteDialog.setSize(400, 200);
        deleteDialog.setLocationRelativeTo(this);
        
        JPanel deletePnl = new JPanel();
        deletePnl.setBackground(Color.white);
        deletePnl.setLayout(new BoxLayout(deletePnl, BoxLayout.Y_AXIS));
        
        JPanel lblPnl = new JPanel();
        JPanel btnPnl = new JPanel();
        
        JLabel productLabel = new JLabel("Are you sure to delete "+name);
        JButton confirm = new JButton("Yes, I am sure");
        confirm.setBackground(Color.red);
        confirm.setForeground(Color.white);
        lblPnl.add(productLabel);
        btnPnl.add(confirm);
        deletePnl.add(lblPnl);
        deletePnl.add(btnPnl);
        
        deleteDialog.add(deletePnl);
        
        confirm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                deleteProduct(productId, productCover);
                deleteDialog.dispose();
            }
        });
        
        
        deleteDialog.setVisible(true);
    }
    
    public BufferedImage bufferUploadedImage(java.awt.event.ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        File selectedFile;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try {
                img = ImageIO.read(selectedFile);
                return img;
            } catch (IOException e) {
                System.err.println("Error: "+e.getMessage());;
                return img;
            }
        }
        return img;
    }
    
    public void deleteProduct(int productId, String productCover) {
        try {
            File cover = new File("src/shop/images/"+productCover);
            Files.deleteIfExists(cover.toPath());
            String insertSQL = String.format("DELETE FROM products WHERE id=%s", productId);
            statement = this.conn.createStatement();
            int deleted;
            deleted = statement.executeUpdate(insertSQL);
            if (deleted > 0) {
                showAlert("product deleted successfully!", "success");
                loadproducts(null);
            }
            else {
                showAlert("Product not deleted.", "error");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            showAlert("Something went wrong, please tr again.", "error");
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean saveProduct(java.awt.event.ActionEvent ae, String name, String price, String quantity, BufferedImage img) {
        if (price.isBlank() | name.isBlank() | quantity.isBlank()) {
            showAlert("All fields are required", "error");
            return false;
        } else {
             
            try {
                File imagesDir = new File("src/shop/images");
                if (!imagesDir.exists()) {
                    imagesDir.mkdirs();
                }

                File destFile = new File(imagesDir, name+".png");
                ImageIO.write(img, "png", destFile);

                String imageURL = "images/"+name+".png";
                String insertSQL = String.format("INSERT INTO products(name, price, quantity, cover) VALUES('%s', '%s', '%s', '%s')", name, price, quantity, imageURL);
                statement = this.conn.createStatement();
                int inserted;
                inserted = statement.executeUpdate(insertSQL);
                if (inserted > 0) {
                    showAlert("New product was inserted successfully! add another", "success");
                    return true;
                }
                else {
                    return false;
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                showAlert("Something went wrong, please tr again.", "error");
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
    
    public boolean editProduct(java.awt.event.ActionEvent ae, int productId, String name, String price, String quantity, BufferedImage img) {
        if (price.isBlank() | name.isBlank() | quantity.isBlank()) {
            showAlert("All fields are required", "error");
            return false;
        } else {
             
            try {
                File imagesDir = new File("src/shop/images");
                if (!imagesDir.exists()) {
                    imagesDir.mkdirs();
                }

                File destFile = new File(imagesDir, name+".png");
                ImageIO.write(img, "png", destFile);

                String imageURL = "images/"+name+".png";
                String insertSQL = String.format("UPDATE products SET name='%s', price='%s', quantity='%s', cover='%s' WHERE id=%s", name, price, quantity, imageURL, productId);
                statement = this.conn.createStatement();
                int inserted;
                inserted = statement.executeUpdate(insertSQL);
                if (inserted > 0) {
                    showAlert("product updated successfully!", "success");
                    return true;
                }
                else {
                    return false;
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                showAlert("Something went wrong, please tr again.", "error");
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
    
    public void loadproducts(java.awt.event.MouseEvent me) {
        
        contentWrapper.removeAll();
            
        this.setTitle("List of products");
        JPanel productsPnl = new JPanel();
        productsPnl.setBackground(Color.white);
        productsPnl.setLayout(new BorderLayout());
        productsPnl.setBorder(BorderFactory.createEmptyBorder(20,20,0,20)); 
        
        JPanel titlePnl = new JPanel();
        titlePnl.setLayout(new BoxLayout(titlePnl, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("List of products");
        title.setFont(new Font("arial", Font.BOLD, 40));
        titlePnl.setBackground(Color.white);

        JSeparator sep = new JSeparator();
        titlePnl.add(title, BorderLayout.NORTH);
        titlePnl.add(Box.createRigidArea(new Dimension(0,20)));
        titlePnl.add(sep);
        
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setPreferredSize(new Dimension(1500, getHeight()));
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        productsPnl.add(titlePnl, BorderLayout.NORTH);
        
            
        try {
            String productsSQL = "SELECT * FROM products";
            statement = this.conn.createStatement();
            ResultSet selectedProducts = statement.executeQuery(productsSQL);
            
            while(selectedProducts.next()) {
                
                JPanel coverPnl = new JPanel();
                JLabel coverLbl = new JLabel();
        
                JPanel productContainerPnl = new JPanel();
                productContainerPnl.setBackground(Color.white);
                productContainerPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                BufferedImage img = null;
                img = ImageIO.read(Dashboard.class.getResource(selectedProducts.getString("cover")));
                if (img != null) {
                    coverLbl.setIcon(new ImageIcon(img.getScaledInstance(250, 250, 2)));
                }
                
                coverPnl.add(coverLbl);
                
                JPanel productPnl = new JPanel();
                productPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                productPnl.setLayout(new BoxLayout(productPnl, BoxLayout.PAGE_AXIS));
                
                int productId = Integer.parseInt(selectedProducts.getString("id"));
                String productName = selectedProducts.getString("name");
                String productPrice = selectedProducts.getString("price");
                String productQuantity = selectedProducts.getString("quantity");
                String productCover = selectedProducts.getString("cover");
                
                JPanel productNamePnl = new JPanel(new BorderLayout());
                JLabel productNameLbl = new JLabel("Name: "+productName);
                productNameLbl.setFont(new Font("arial", 0, 20));
                productNamePnl.add(productNameLbl, BorderLayout.WEST);
                
                JPanel productPriceQuantityePnl = new JPanel(new BorderLayout());
                JLabel productPriceLbl = new JLabel("Price: "+productPrice);
                productPriceLbl.setFont(new Font("arial", 0, 20));
                JLabel productQuantityLbl = new JLabel("Quantity: "+productQuantity);
                productQuantityLbl.setFont(new Font("arial", 0, 20));
                productPriceQuantityePnl.add(productPriceLbl, BorderLayout.NORTH);
                productPriceQuantityePnl.add(productQuantityLbl, BorderLayout.SOUTH);
                
                JPanel editProductPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
                
                JButton editProductBtn = new JButton("Edit");
//                editProductBtn.setFont(new Font("arial", 0, 18));
                editProductBtn.setBackground(new Color(200, 190, 80));
                editProductBtn.setForeground(Color.black);
                
                JButton deleteProductBtn = new JButton("Delete");
//                deleteProductBtn.setFont(new Font("arial", 0, 18));
                deleteProductBtn.setBackground(new Color(200, 20, 50));
                deleteProductBtn.setForeground(Color.white);
                
                editProductPnl.add(editProductBtn);
                editProductPnl.add(deleteProductBtn);
                
                productPnl.add(coverPnl);
                productPnl.add(productNamePnl);
                productPnl.add(productPriceQuantityePnl);
                productPnl.add(editProductPnl);
                productContainerPnl.add(productPnl);
                container.add(productContainerPnl);
                
                editProductBtn.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        editProductActionListener(ae, productId, productName, productPrice, productQuantity, productCover);
                    }
                });
                
                deleteProductBtn.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        deleteProductActionListener(ae, productId, productName, productPrice, productQuantity, productCover);
                    }
                });
            }
                        
            JScrollPane productScrollPane = new JScrollPane(container);
            productScrollPane.add(Box.createRigidArea(new Dimension(0, 30)));
            productScrollPane.setPreferredSize(new Dimension(1500, 790));
            productScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            container.add(Box.createRigidArea(new Dimension(0, 30)));

        
            productsPnl.add(productScrollPane, BorderLayout.WEST);
            contentWrapper.add(productsPnl, BorderLayout.PAGE_START);
            
            contentWrapper.revalidate();
            contentWrapper.repaint();
            
        } catch (SQLException ex) {
            System.err.println("Error:"+ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void showAlert(String msg, String icon) {
        AlertDialog alertDialog = new AlertDialog(this, rootPaneCheckingEnabled);
        alertDialog.setAlert(msg, icon);
        alertDialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
}


