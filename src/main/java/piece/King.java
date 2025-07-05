package piece;

import app.GamePanel;
import app.Type;

public class King extends Piece{
  public King(int col, int row, int color) {
    super(col, row, color);
    type = Type.KING;

    if (color == GamePanel.WHITE){
      image = getImage("/pixel_pieces/w-king");
    }else {
      image = getImage("/pixel_pieces/b-king");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow)){
      if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 ||
          Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1){
        return isValidSquare(targetCol, targetRow);
      }
      //CASTLING
      if (!moved){
        if (targetCol == preCol + 2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)){
          for (Piece piece :
              GamePanel.simPieces) {
            if (piece.col == preCol + 3 && piece.row == preRow && !piece.moved){
              GamePanel.castlingPiece = piece;
              return true;
            }
          }
        }
        if (targetCol == preCol - 2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)){
          Piece[] p = new Piece[2];
          for (Piece piece :
              GamePanel.simPieces) {
            if (piece.col == preCol - 3 && piece.row == targetRow){
              p[0] = piece;
            }
            if (piece.col == preCol - 4 && piece.row == targetRow){
              p[1] = piece;
            }
            if (p[0] == null && p[1] != null && !p[1].moved){
              GamePanel.castlingPiece = p[1];
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
