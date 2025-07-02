package piece;

import app.GamePanel;

public class Rook extends Piece{
  public Rook(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-rook");
    }else {
      image = getImage("/simple_chess_pieces/b-rook");
    }
  }
}
