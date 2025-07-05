package piece;

import app.GamePanel;
import app.Type;

public class Queen extends Piece{
  public Queen(int col, int row, int color) {
    super(col, row, color);
    type = Type.QUEEN;

    if (color == GamePanel.WHITE){
      image = getImage("/pixel_pieces/w-queen");
    }else {
      image = getImage("/pixel_pieces/b-queen");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
      if (targetCol == preCol && targetRow == preRow){
        return isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow);
      }
    }

    if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
      return isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow);
    }
    return false;
  }
}
