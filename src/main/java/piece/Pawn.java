package piece;

import app.GamePanel;

public class Pawn extends Piece{
  public Pawn(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-pawn");
    }else {
      image = getImage("/simple_chess_pieces/b-pawn");
    }
  }
}
