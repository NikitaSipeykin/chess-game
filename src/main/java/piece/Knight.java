package piece;

import app.GamePanel;

public class Knight extends Piece{
  public Knight(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-knight");
    }else {
      image = getImage("/simple_chess_pieces/b-knight");
    }
  }
}
