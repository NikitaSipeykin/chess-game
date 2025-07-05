package piece;

import app.GamePanel;
import app.Type;

public class Pawn extends Piece{
  public Pawn(int col, int row, int color) {
    super(col, row, color);
    type = Type.PAWN;

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-pawn");
    }else {
      image = getImage("/simple_chess_pieces/b-pawn");
    }
  }

  @Override
  public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
      int moveValue;
      if (color == GamePanel.WHITE){
        moveValue = -1;
      }else {
        moveValue = 1;
      }

      hittingPiece = getHittingPosition(targetCol, targetRow);

      if (targetCol == preCol && targetRow == preRow + moveValue && hittingPiece == null){
        return true;
      }
      if (targetCol == preCol && targetRow == preRow + moveValue * 2  && hittingPiece == null && !moved &&
          !pieceIsOnStraightLine(targetCol, targetRow)){
        return true;
      }
      if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingPiece != null &&
          hittingPiece.color != color){
        return true;
      }

      //EN PASSANT
      if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue){
        for (Piece piece :
            GamePanel.simPieces) {
          if (piece.col == targetCol && piece.row == preRow && piece.twoStepped){
            hittingPiece = piece;
            return true;
          }
        }
      }
    }
    return false;
  }
}
