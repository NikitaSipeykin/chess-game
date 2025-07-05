package piece;

import app.GamePanel;
import app.Type;

public class Bishop extends Piece{
  public Bishop(int col, int row, int color) {
    super(col, row, color);
    type = Type.BISHOP;

    if (color == GamePanel.WHITE){
      image = getImage("/pixel_pieces/w-bishop");
    }else {
      image = getImage("/pixel_pieces/b-bishop");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
      if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
        return isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow);
      }
    }
    return false;
  }
}
