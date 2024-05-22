package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

import model.*;

public class BoardView extends JPanel {
	
	public static final Color backgroundColor = new Color(250, 248, 239);
	public static final Color boardColor = new Color(187, 173, 160);
	
	private Board board;
	private ScoreView scoreView;
	private double width;
	private JLabel resultLabel;
	
	// MARK: Constructors
	
	private void genericInit(Board board) {
		this.board = board;
		this.setOpaque(true);
		this.setBackground(BoardView.backgroundColor);
		setupResultLabel();
	}
	
	private void setupResultLabel() {
		resultLabel = new JLabel();
		this.add(resultLabel);
		resultLabel.setVisible(false);
		resultLabel.setFont(FontHelper.getDefaultFontOfSize(16));
	}
	
	public BoardView(Board board) {
		genericInit(board);
		initKeyBindings();
		this.scoreView = new ScoreView(board, 0, this.getWidth(), false);
	}
	
	public BoardView(Board board, boolean altKeyBindings) {
		genericInit(board);
		if(altKeyBindings) {
			initAltKeyBindings();
		} else {
			initKeyBindings();
		}
		this.scoreView = new ScoreView(board, 0, 0, altKeyBindings);
	}
	
	// MARK: Key Bindings
	
	public void initKeyBindings() {
		InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = this.getActionMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
		actionMap.put("UP", new ShiftAction(Board.Direction.UP, board, this));
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
		actionMap.put("DOWN", new ShiftAction(Board.Direction.DOWN, board, this));
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
		actionMap.put("LEFT", new ShiftAction(Board.Direction.LEFT, board, this));
		 
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
		actionMap.put("RIGHT", new ShiftAction(Board.Direction.RIGHT, board, this));
	}
	
	public void initAltKeyBindings() {
		InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = this.getActionMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "UP");
		actionMap.put("UP", new ShiftAction(Board.Direction.UP, board, this));
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "DOWN");
		actionMap.put("DOWN", new ShiftAction(Board.Direction.DOWN, board, this));
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "LEFT");
		actionMap.put("LEFT", new ShiftAction(Board.Direction.LEFT, board, this));
		 
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "RIGHT");
		actionMap.put("RIGHT", new ShiftAction(Board.Direction.RIGHT, board, this));
	}
	
	public void clearKeyBindings() {
		this.getInputMap().clear();
		this.getActionMap().clear();
	}
	
	// MARK: Drawing
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		layoutBoard(g);
		scoreView.draw(g);
	}
	
	private void layoutBoard(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				 RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(boardColor);
		
		int boardWidth = (int)getSize().getWidth() - 2 * TileView.padding;
		g2d.fillRoundRect(TileView.padding, 3 * TileView.padding, 
				boardWidth, boardWidth, 10, 10);
		
		scoreView.setY((int)getSize().getWidth() + 2 * TileView.padding);
		
		int numRows = board.getNumRows();
		int numCols = board.getNumCols();
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				drawTile(g2d, row, col);
			}
		}
	}
	
	private void drawTile(Graphics g, int row, int col) {
		Graphics2D g2d = (Graphics2D)g;
		
		// Background & Color
		int size = calculateTileSize();
		Tile tile = board.getTileAtCell(row, col);
		TileView tileView = new TileView(tile, row, col, size);
		tileView.draw(g2d);
	}
	
	public void drawGameOver(boolean won) {
		String result = "You " + (won ? "win" : "lose") + "!";
		resultLabel.setText(result);
		resultLabel.setVisible(true);
	}
	
	private int calculateTileSize() {
		this.width = getSize().getWidth();
		
		double widthWithPaddingRemoved = (double)(this.width - TileView.padding * (board.getNumCols() + 3));
		return (int)(widthWithPaddingRemoved / (double)board.getNumCols());
	}
	
	public int getWidth() {
		return (int)getSize().getWidth();
	}
	
}
