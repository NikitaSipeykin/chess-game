package piece;

import app.GamePanel;

public class Bishop extends Piece{
  public Bishop(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-bishop");
    }else {
      image = getImage("/simple_chess_pieces/b-bishop");
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
