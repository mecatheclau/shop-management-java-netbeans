package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;
import java.sql.Statement;
import javax.swing.JSeparator;

/**
 *
 * @author mecatheclau
 */
public class Home extends JFrame {
    JPanel loginPanel;
    JButton loginBtn;
    JLabel usernameLbl, passwordLbl, titleLbl;
    JTextField username;
    JPasswordField password;
    
    Statement statement = null;

    public Home() {
        init();
        LoginForm();
    }

    private void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(223, 239, 255));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Home");
        setLayout(new BorderLayout());
    }

    private void LoginForm() {
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setBorder(new DropShadowBorder(5, new Color(150, 150, 150, 150)));

        titleLbl = new JLabel("Login form");
        titleLbl.setFont(new Font("arial bold", 0, 20));
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameLbl = new JLabel("Username");
        usernameLbl.setFont(new Font("arial", 0, 20));

        username = new JTextField("mecatheclau", 20);
        username.setFont(new Font("arial", 0, 20));

        passwordLbl = new JLabel("Password");
        passwordLbl.setFont(new Font("arial", 0, 20));

        password = new JPasswordField("mecatheclau", 20);
        password.setFont(new Font("arial", 0, 20));

        JPanel usernamePnl = new JPanel();
        usernamePnl.add(usernameLbl);
        usernamePnl.add(username);
        usernamePnl.setBackground(Color.WHITE);

        JPanel passwordPnl = new JPanel();
        passwordPnl.add(passwordLbl);
        passwordPnl.add(password);
        passwordPnl.setBackground(Color.WHITE);

        JPanel loginBtnPnl = new JPanel();
        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(23, 100, 230));
        loginBtn.setForeground(Color.white);
        loginBtnPnl.add(loginBtn);
        loginBtnPnl.setBackground(Color.WHITE);
        
        JSeparator separator = new JSeparator();

        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(titleLbl);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(separator);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(usernamePnl);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(passwordPnl);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(loginBtnPnl);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(223, 239, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(loginPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
        
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        
         

    }
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
            DBConnection DB = new DBConnection();
            Connection conn = DB.connect();

            String loginUsername = username.getText();
            String loginPassword = password.getText();
            
            if (!loginUsername.isBlank() && !loginPassword.isBlank()) {
                String selectUserSQL = String.format("SELECT * FROM users WHERE username='%s' AND password='%s'", loginUsername, loginPassword);

                ResultSet rowSelected = null;
                try {
                    statement = conn.createStatement();
                    rowSelected = statement.executeQuery(selectUserSQL);
                    
                    if (rowSelected.next()) {
                        dispose();
                        Dashboard dashboard = new Dashboard();
                        dashboard.setVisible(true);
                    } else {
                        showAlert("Invalid username or password.", "error");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                showAlert("Username and password are required", "error");
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
                new Home().setVisible(true);
            }
        });
    }
}
