package piece;

import app.GamePanel;
import app.Type;

public class Rook extends Piece{
  public Rook(int col, int row, int color) {
    super(col, row, color);
    type = Type.ROOK;

    if (color == GamePanel.WHITE){
      image = getImage("/pixel_pieces/w-rook");
    }else {
      image = getImage("/pixel_pieces/b-rook");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
      if (targetCol == preCol || targetRow == preRow){
        return isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow);
      }
    }
    return false;
  }
}
