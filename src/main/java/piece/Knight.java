package piece;

import app.GamePanel;
import app.Type;

public class Knight extends Piece{
  public Knight(int col, int row, int color) {
    super(col, row, color);
    type = Type.KNIGHT;

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-knight");
    }else {
      image = getImage("/simple_chess_pieces/b-knight");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow)){
      if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2){
        return isValidSquare(targetCol, targetRow);
      }
    }
    return false;
  }
}
