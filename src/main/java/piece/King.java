package piece;

import app.GamePanel;

public class King extends Piece{
  public King(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-king");
    }else {
      image = getImage("/simple_chess_pieces/b-king");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow)){
      if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 ||
          Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1){
        return isValidSquare(targetCol, targetRow);
      }
    }
    return false;
  }
}
