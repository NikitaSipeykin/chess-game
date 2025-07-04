package piece;

import app.GamePanel;

public class Queen extends Piece{
  public Queen(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-queen");
    }else {
      image = getImage("/simple_chess_pieces/b-queen");
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
