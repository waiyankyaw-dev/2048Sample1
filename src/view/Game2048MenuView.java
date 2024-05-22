package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

public class Game2048MenuView extends JFrame implements ActionListener {

	private JButton singlePlayerButton;
	private JButton multiPlayerButton;

	public Game2048MenuView() {
		super("2048");
		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	public void initGUI() {
		setSize(200, 100);
		
		JPanel panel = new JPanel();
		
		singlePlayerButton = new JButton("Classic");
		panel.add(singlePlayerButton);
		singlePlayerButton.addActionListener(this);
		
		multiPlayerButton = new JButton("Versus");
		panel.add(multiPlayerButton);
		multiPlayerButton.addActionListener(this);
		
		JPanel wrapper = new JPanel(new GridBagLayout());
		wrapper.add(panel);
		add(wrapper);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		this.setVisible(false);

		if(source == singlePlayerButton) {
			System.out.println("Classic");
			new Game2048View();
		} else if(source == multiPlayerButton) {
			System.out.println("Versus");
			new VersusGame2048View();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Game2048MenuView menu = new Game2048MenuView();
				menu.setVisible(true);
			}
		});
	}

}
