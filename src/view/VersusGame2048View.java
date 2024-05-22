package view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class VersusGame2048View extends JFrame {

	private Game2048 game1;
	private Game2048 game2;
	
	private BoardView boardView1;
	private BoardView boardView2;
	
	public VersusGame2048View() {
		super("2048");

		game1 = new Game2048();
		game2 = new Game2048();
		
		initVersusGUI();
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	private void stopGame() {
		boardView1.clearKeyBindings();
		boardView2.clearKeyBindings();
	}
	
	private void initVersusGUI() {
		setSize(900, 600);
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(1, 2));
		boardView1 = new BoardView(game1.getBoard(), true);
		boardView2 = new BoardView(game2.getBoard());
		
		Listener listener = new Listener();
		add(listener);
		
		listener.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
            	
            	Board board1 = game1.getBoard();
            	Board board2 = game2.getBoard();
            		
            	// Check board 1
            	if(board1.doesValueExist((int)Math.pow(NumberTile.base, NumberTile.goalExponent))
            			|| !board2.doesMoveExist()) {
                	stopGame();
            		boardView1.drawGameOver(true);
            		boardView2.drawGameOver(false);
            	}
            	// Check board 2
            	if(board2.doesValueExist((int)Math.pow(NumberTile.base, NumberTile.goalExponent))
            			|| !board1.doesMoveExist()) {
                	stopGame();
            		boardView2.drawGameOver(true);
            		boardView1.drawGameOver(false);
            	}
            	
//            	System.out.println(board1.getCountForValue(DeadTile.threshold));
            	System.out.println(board2.getCountForValue(DeadTile.threshold));
            	System.out.println(board1.getDeadTileCount());

            	// Check if value with base^dead tile exponent exists
            	if(board1.getDeadThresholdValueCount() > board2.getDeadTileCount()) { 
            		board2.spawnDeadTile();
            		boardView1.repaint();
            		boardView2.repaint();
            	}
            	if(board2.getDeadThresholdValueCount() > board1.getDeadTileCount()) {
            		board1.spawnDeadTile();
            		boardView1.repaint();
            		boardView2.repaint();
            	}
            }

            public void keyPressed(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
		
		container.add(boardView1);
		container.add(boardView2);
		
		this.add(container);
	}
}
