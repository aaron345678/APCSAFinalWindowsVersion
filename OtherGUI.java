import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.GridLayout;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OtherGUI {
    private static String total = "";
    private static JLabel totalLabel; 
    private static boolean isDeposit = true; 
    private static UserInteraction userInteraction = new UserInteraction();
    private static JFrame frame = new JFrame("Banking App");
    private static User currentUser;
    private static String username;
    private static String password;
    public static void run() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 2500);
        showScreen1();
    }

    // Creates a button with an image file as its background.
    private static JButton makeImageButton(String imageFile, int width, int height) {
        JButton button = null;

        try {
            button = new JButton(new ImageIcon(((new ImageIcon(imageFile)).getImage()).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
        } catch (Exception e) {
            System.out.println("Failed to load image: " + imageFile);
        }

        return button;
    }

    private static void showScreen1() {
        frame.getContentPane().removeAll();

        JButton startButton = makeImageButton("start.png", 1500, 1000);

        //startButton.setBounds(370, 300, 200, 50);
        frame.add(startButton);

        startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen2();
                }
            });

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private static void showScreen2() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("login.png");
        if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Failed to load image: login.png");
            return;
        }

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        JButton signInButton = makeImageButton("login-button.png", 455, 230);
        JButton createAccountButton = makeImageButton("createnewaccount.png", 455, 230);
        JButton backButton = makeImageButton("back.png", 300, 90);

        signInButton.setOpaque(false);
        signInButton.setContentAreaFilled(false);
        signInButton.setBorderPainted(false);
        signInButton.setFocusPainted(false);

        createAccountButton.setOpaque(false);
        createAccountButton.setContentAreaFilled(false);
        createAccountButton.setBorderPainted(false);
        createAccountButton.setFocusPainted(false);

        signInButton.setBounds(445, 633, 435, 210);
        createAccountButton.setBounds(1075,633, 435, 210);
        //backButton.setBounds(1500, 800, 435, 210);

        signInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showSignInScreen();
                }
            });

        createAccountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showCreateAccountScreen();
                }
            });

        /*
        backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        showScreen1();
        }
        });
         */

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        layeredPane.add(signInButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(createAccountButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

        layeredPane.setLayout(null);

        frame.add(layeredPane, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private static void showSignInScreen() {
        frame.getContentPane().removeAll();
        frame.setLayout(null);

        /*
        ImageIcon backgroundIcon = new ImageIcon("loginBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundLabel);
         */

        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(null); 
        userInputPanel.setOpaque(false); 
        userInputPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton signInButton = new JButton("Sign In");
        JButton backButton = new JButton("Back");

        usernameLabel.setBounds(50, 200, 100, 30);
        usernameField.setBounds(160, 200, 300, 30);
        passwordLabel.setBounds(50, 250, 100, 30);
        passwordField.setBounds(160, 250, 300, 30);
        signInButton.setBounds(160, 300, 200, 50);
        backButton.setBounds(370, 300, 200, 50);

        userInputPanel.add(usernameLabel);
        userInputPanel.add(usernameField);
        userInputPanel.add(passwordLabel);
        userInputPanel.add(passwordField);
        userInputPanel.add(signInButton);
        userInputPanel.add(backButton);

        frame.add(userInputPanel);

        signInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    if(userInteraction.login(new User(username,password))) {
                        JOptionPane.showMessageDialog(frame, "Signed in as: " + username);
                        currentUser = userInteraction.getUser(username);
                        frame.getContentPane().removeAll();
                        showScreen3();
                        
                    } else {
                       JOptionPane.showMessageDialog(frame,"Failed login");
                    }

                   
                }
            });

        backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen2();
                }
            });

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private static void showCreateAccountScreen() {
        frame.getContentPane().removeAll();

        // Panel
        JPanel panel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        // Background, on top of @panel
        ImageIcon imageIcon = new ImageIcon("createAccountBackground.png");
        JLabel backgroundLabel = new JLabel(imageIcon);

        backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth()-50, imageIcon.getIconHeight()-200);
        panel.add(backgroundLabel);
        backgroundLabel.setLayout(new GridBagLayout());

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JTextField depositField = new JTextField(15);

        JButton createAccountButton = makeImageButton("createnewaccount.png", 400, 90);
        JButton backButton = makeImageButton("back.png", 400, 90);

        Font bigFont = new Font(usernameField.getFont().getName(), Font.PLAIN, 20);
        usernameField.setFont(bigFont);
        passwordField.setFont(bigFont);
        depositField.setFont(bigFont);
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        depositField.setHorizontalAlignment(JTextField.CENTER);

        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        backgroundLabel.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        backgroundLabel.add(usernameField, gbc);
        gbc.gridy++;
        backgroundLabel.add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        backgroundLabel.add(passwordField, gbc);
        gbc.gridy++;
        backgroundLabel.add(new JLabel("Initial Deposit:"), gbc);
        gbc.gridy++;
        backgroundLabel.add(depositField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2;
        backgroundLabel.add(createAccountButton, gbc);
        gbc.gridy++;
        backgroundLabel.add(backButton, gbc);
        
        createAccountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    String depositStr = depositField.getText();

                    if (username.isEmpty() || password.isEmpty() || depositStr.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                        return; 
                    }

                    //Check if deposit amount is valid
                    double deposit = 0;
                    try {
                        deposit = Double.parseDouble(depositStr);
                        if (deposit < 0) {
                            throw new NumberFormatException(); 
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid deposit amount.");
                        return; 
                    }
                    // TODO: Validate whether username already existed
                    if(userInteraction.checkUserNull(username)) {
                        userInteraction.addUser(new User(username,password),Double.parseDouble(depositStr));
                        userInteraction.saveNewUsers();
                        currentUser = userInteraction.getUser(username);
                        showScreen3();
                    } else {
                        JOptionPane.showMessageDialog(frame,"Failed to login(User already exists");
                    }
                    // Update account balance
                    

                    
                }
            });

        backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen2();
                }
            });


        frame.add(panel);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

        createAccountButton.setVisible(true);
        backButton.setVisible(true);
    }

    private static void showScreen3() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("depowith.png");
        if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Failed to load image: depowith.png");
            return;
        }

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        JButton depositButton = makeImageButton("depositButton.png", 620, 265);
        JButton withdrawButton = makeImageButton("withdrawButton.png", 620, 255);
        JButton backButton = makeImageButton("back.png", 400, 170);

        depositButton.setOpaque(false);
        depositButton.setContentAreaFilled(false);
        depositButton.setBorderPainted(false);
        depositButton.setFocusPainted(false);
        depositButton.setBounds(300, 415, 510, 195);

        withdrawButton.setOpaque(false);
        withdrawButton.setContentAreaFilled(false);
        withdrawButton.setBorderPainted(false);
        withdrawButton.setFocusPainted(false);
        withdrawButton.setBounds(1100, 415, 510, 195);

        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(830, 860, 280, 145);

        Dimension buttonSize = new Dimension(500, 250);
        depositButton.setPreferredSize(buttonSize);
        withdrawButton.setPreferredSize(buttonSize);

        depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showDepositScreen();
                }
            });

        withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showWithdrawScreen();
                }
            });

        backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen2();
                }
            });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        layeredPane.add(depositButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(withdrawButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

        layeredPane.setLayout(null);

        frame.add(layeredPane, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private static JButton createNumButton(String s, int fontSize) {
        JButton button = new JButton(s);
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return button;
    }

    // Update total label after button pressed.
    private static void updateTotalLabel(String totalStr) {
        totalLabel.setText(totalStr); // Update totalLabel
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 80));
    }

    private static int decimalPlaces(String totalStr) {
        int dotPlace = totalStr.indexOf('.');

        // No dot in string
        if (dotPlace < 0)
            return -1;
        return totalStr.length() - dotPlace;
    }

    private static void showDepositScreen() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        JButton[] numberButtons = new JButton[11];
        panel.setLayout(new GridLayout(4, 6));
        
        /*
        ImageIcon imageIcon = new ImageIcon("pink.png");
        JLabel backgroundLabel = new JLabel(imageIcon);

        backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        panel.add(backgroundLabel);
        */

        total = "";
        totalLabel = new JLabel(total);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        totalLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(totalLabel);

        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = makeImageButton(Integer.toString(i)+".png", 300,150);
            int finalI = i;
            numberButtons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Only process input if decimal places less than 2
                        if (decimalPlaces(total) <= 2) {
                            total += Integer.toString(finalI);
                            // Remove the first char if it starts with 
                            // 0 but not follow up with dot
                            if (total.length() >=2 &&
                            total.charAt(0) == '0' &&
                            total.charAt(1) != '.') {
                                total = total.substring(1, total.length());
                            }
                            updateTotalLabel(total);
                        }
                    }
                });
            panel.add(numberButtons[i]);
        }

        numberButtons[0] = makeImageButton("0.png", 300, 150);
        numberButtons[0].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Only process input if decimal places less than 2
                    if (decimalPlaces(total) <= 2) {
                        total += "0";
                        updateTotalLabel(total);
                    }
                }
            });
        panel.add(numberButtons[0]);

        JButton dotButton = makeImageButton("dot.png", 300, 150);
        dotButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Only process input if no dot in total string
                    if (decimalPlaces(total) < 0) {
                        if (!total.isEmpty()) {
                            total += ".";
                        } else {
                            total += "0.";
                        }
                        updateTotalLabel(total);
                    }
                }
            });
        panel.add(dotButton);

        JButton delButton = makeImageButton("delete.png", 300, 150);
        delButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!total.isEmpty()) {
                        // Remove the last char.
                        total = total.substring(0, total.length() - 1);
                    } else {
                        total += "";
                    }
                    updateTotalLabel(total);
                }
            });
        panel.add(delButton);

        numberButtons[10] = makeImageButton("enter.png", 300, 150);
        numberButtons[10].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Process deposit
                    if (!total.isEmpty()) {
                        double amount = Double.parseDouble(total);

                        // Update account balance
                        
                        userInteraction.changeMoney(currentUser,amount);
                        JOptionPane.showMessageDialog(frame, "Deposited: " + amount + ", Balance: " + userInteraction.getPersonalMoney(currentUser));
                        total = "";
                        updateTotalLabel(total);
                        userInteraction.save();
                        showScreen3();
                    }
                }
            });
        panel.add(numberButtons[10]);

        JButton backButton = makeImageButton("back2.png", 300,150);
        backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen3();
                }
            });
        panel.add(backButton);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private static void showWithdrawScreen() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        JButton[] numberButtons = new JButton[11];
        panel.setLayout(new GridLayout(4, 6));

        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = makeImageButton(Integer.toString(i)+".png", 300,150);
            int finalI = i;
            numberButtons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Only process input if decimal places less than 2
                        if (decimalPlaces(total) <= 2) {
                            total += Integer.toString(finalI);
                            // Remove the first char if it starts with 
                            // 0 but not follow up with dot
                            if (total.length() >=2 &&
                            total.charAt(0) == '0' &&
                            total.charAt(1) != '.') {
                                total = total.substring(1, total.length());
                            }
                            updateTotalLabel(total);
                        }
                    }
                });
            panel.add(numberButtons[i]);
        }

        numberButtons[0] = makeImageButton("0.png", 300, 150);
        numberButtons[0].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Only process input if decimal places less than 2
                    if (decimalPlaces(total) <= 2) {
                        total += "0";
                        updateTotalLabel(total);
                    }
                }
            });
        panel.add(numberButtons[0]);

        JButton dotButton = makeImageButton("dot.png", 300, 150);
        dotButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Only process input if no dot in total string
                    if (decimalPlaces(total) < 0) {
                        if (!total.isEmpty()) {
                            total += ".";
                        } else {
                            total += "0.";
                        }
                        updateTotalLabel(total);
                    }
                }
            });
        panel.add(dotButton);

        JButton delButton = makeImageButton("delete.png", 300, 150);
        delButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!total.isEmpty()) {
                        // Remove the last char.
                        total = total.substring(0, total.length() - 1);
                    } else {
                        total += "";
                    }
                    updateTotalLabel(total);
                }
            });
        panel.add(delButton);

        numberButtons[10] = makeImageButton("enter.png", 300, 150);
        numberButtons[10].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!total.isEmpty()) {
                        double amount = Double.parseDouble(total);
                        double balance = userInteraction.getPersonalMoney(currentUser);
                        if (balance >= amount) {
                            // Update account balance
                            balance -= amount;
                            userInteraction.changeMoney(currentUser,-amount);
                            // Process withdrawal
                            JOptionPane.showMessageDialog(frame, "Withdrawn: " + amount + ", Balance: " + balance);
                            total = "";
                            updateTotalLabel(total);
                            userInteraction.save();
                            showScreen3();
                        } else { // Insufficient account balance
                            JOptionPane.showMessageDialog(frame, "Insufficient account balance, current balance: " + balance);
                        }
                    }
                }
            });
        panel.add(numberButtons[10]);

        JButton backButton = makeImageButton("back2.png", 300, 150);
        backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showScreen3();
                }
            });
        panel.add(backButton);

        total = "";
        totalLabel = new JLabel(total);
        panel.add(totalLabel);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    /*
    private static void overlayImage(String imagePath) {
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setLayout(new OverlayLayout(layeredPane));

    ImageIcon imageIcon = new ImageIcon(imagePath);
    if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
    System.out.println("Failed to load image: " + imagePath);
    return;
    }

    JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setAlignmentX(0.5f);
    imageLabel.setAlignmentY(0.5f);

    layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
    JPanel existingContent = (JPanel) frame.getContentPane();
    layeredPane.add(existingContent, JLayeredPane.PALETTE_LAYER);

    frame.setContentPane(layeredPane);

    frame.revalidate();
    frame.repaint();
    }
     */

}

