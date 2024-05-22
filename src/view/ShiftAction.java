package view;

import model.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ShiftAction extends AbstractAction {

	private Board boardRef;
	private BoardView viewRef;
	private Board.Direction direction;
	
	public ShiftAction(Board.Direction direction, Board board, BoardView view) {
		this.boardRef = board;
		this.viewRef = view;
		this.direction = direction;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boardRef.shift(this.direction);
		if(boardRef.getHasChanged()) {
			boardRef.spawnRandomTile();
			viewRef.repaint();
		}
	}
	
}
