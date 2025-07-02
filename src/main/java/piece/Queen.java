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
}
