package main;

import org.jdesktop.swingx.prompt.PromptSupport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.AbstractBorder;


public class GUI {

	private JFrame window; 
	private JLabel title;
	private JLabel subtitle;
	private JButton newChat;
	private JButton send;
	private JTextArea chat;
	private JTextField prompt;
	
	private static final Color c1 = new Color(34, 40, 49);
	private static final Color c2 = new Color(49, 54, 63);
	private static final Color c3 = new Color(118, 171, 174);
	private static final Color c4 = new Color(238, 238, 238);
	
	public GUI() {
		
		initializeWindow();
		
		addTitleAndSubtitle();
		
		addNewChatButton();
		
		addTextArea();
		
		addSendButton();
		
		addTextField();
		
	}

	public JButton getNewChat() {
		return newChat;
	}

	public JButton getSend() {
		return send;
	}

	public JTextArea getChat() {
		return chat;
	}
	
	public JTextField getPrompt() {
		return prompt;
	}
	
	private void addSendButton() {
		
		send = new JButton();
		//send.setEnabled(false);
		ImageIcon icon = new ImageIcon("send-message.png");
		send.setIcon(icon);
		send.setFocusable(false);
		send.setBackground(c3);
		send.setBounds(1355, 705, 45, 50);
		
		window.add(send);
		
		// Create an Action to be performed when Enter key is pressed
        Action clickAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send.doClick(); // Simulate button click
            }
        };
        
        // Bind the Enter key to the clickAction
        window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickAction");
        window.getRootPane().getActionMap().put("clickAction", clickAction);
        
	}

	private void addTextField() {
		
		prompt = new JTextField();
		prompt.setBackground(c2);
		prompt.setBounds(500, 705, 850, 50);
		prompt.setCaretColor(c4);
		prompt.setFont(new Font("Arial", Font.BOLD, 17));
		prompt.setForeground(c4);
		Border border = new RoundBorder(c3, 3, 20);
		prompt.setBorder(border);
		PromptSupport.setPrompt(" Message Slim ...", prompt);
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIDE_PROMPT, prompt);
	    PromptSupport.setForeground(c3, prompt); // Set the color of the prompt text
	    
//	    // Add DocumentListener to textField
//        prompt.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                updateButtonState();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                updateButtonState();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                updateButtonState();
//            }
//
//            private void updateButtonState() {
//                // Enable button if text field is not empty, disable otherwise
//                send.setEnabled(!prompt.getText().trim().isEmpty());
//            }
//        });
	    
	    prompt.setToolTipText(null);
	    window.add(prompt);
		
	}

	private void addTextArea() {
		
		chat = new JTextArea();
		chat.setEditable(false);
		chat.setBackground(c2);
        chat.setFont(new Font("Arial", Font.BOLD, 17));
        chat.setForeground(c4);
        chat.setCaretColor(c3);
        chat.setLineWrap(true); // Enable line wrapping
        chat.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(chat);
		scrollPane.setBounds(500, 135, 900, 530);
		Border border = new RoundBorder(c3, 3, 20);
		scrollPane.setBorder(border);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = c1; // Customize thumb color
                this.thumbDarkShadowColor = c1; // Customize thumb dark shadow color
                this.thumbHighlightColor = c1; // Customize thumb highlight color
                this.thumbLightShadowColor = c1; // Customize thumb light shadow color
                this.trackColor = c2; // Customize track color
                this.trackHighlightColor = c2; // Customize track highlight color
            }
            
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(c2); // Customize decrement button background color
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(c2); // Customize increment button background color
                return button;
            }
            
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c1); // Customize thumb color
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }
            
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(11, super.getPreferredSize(c).height); // Adjusts the width of the scrollbar
            }
        });
        
        chat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                    // Get the scroll amount
                    int scrollAmount = verticalScrollBar.getUnitIncrement();
                    
                    // Scroll up if up key is pressed
                    if (keyCode == KeyEvent.VK_UP) {
                        verticalScrollBar.setValue(verticalScrollBar.getValue() - scrollAmount);
                    }
                    // Scroll down if down key is pressed
                    else if (keyCode == KeyEvent.VK_DOWN) {
                        verticalScrollBar.setValue(verticalScrollBar.getValue() + scrollAmount);
                    }
                }
            }
        });
        
        window.add(scrollPane);
		
	}

	private void addNewChatButton() {
		
		newChat = new JButton("New chat");
		newChat.setFocusable(false);
		newChat.setBackground(c3);
		newChat.setForeground(c4);
		newChat.setFont(new Font("Arial", Font.BOLD, 15));
		newChat.setBounds(1400, 40, 100, 50);
		window.add(newChat);
		
	}

	private void addTitleAndSubtitle() {
		
		title = new JLabel("Slim");
		title.setFont(new Font("Arial", Font.BOLD, 38));
		title.setForeground(c4);
		title.setBounds(35, 30, 100, 30);
		window.add(title);
		
		subtitle = new JLabel("Your AI assistant for learning programming");
		subtitle.setFont(new Font("Arial", Font.BOLD, 22));
		subtitle.setForeground(c3);
		subtitle.setBounds(35, 50, 500, 50);
		window.add(subtitle);
		
	}

	private void initializeWindow() {
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 500);
		window.setLocationRelativeTo(null);
		window.setLayout(null);
		window.getContentPane().setBackground(c1);
		ImageIcon icon = new ImageIcon("logo-dark-1.png");
        Image image = icon.getImage();
        window.setIconImage(image);
		
	}

	public void show() {
		window.setVisible(true);
	}
	
	private static class RoundBorder extends AbstractBorder {
	        private Color color;
	        private int thickness;
	        private int radius;

	        public RoundBorder(Color color, int thickness, int radius) {
	            this.color = color;
	            this.thickness = thickness;
	            this.radius = radius;
	        }

	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setColor(color);
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2d.setStroke(new BasicStroke(thickness));
	            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
	            g2d.dispose();
	        }

	        @Override
	        public Insets getBorderInsets(Component c) {
	            return new Insets(thickness, thickness, thickness, thickness);
	        }

	        @Override
	        public Insets getBorderInsets(Component c, Insets insets) {
	            insets.left = insets.top = insets.right = insets.bottom = thickness;
	            return insets;
	        }

	        @Override
	        public boolean isBorderOpaque() {
	            return true;
	        }
	    }
	
}
