package piece;

import app.GamePanel;

public class King extends Piece{
  public King(int col, int row, int color) {
    super(col, row, color);

    if (color == GamePanel.WHITE){
      image = getImage("/simple_chess_pieces/w-king");
    }else {
      image = getImage("/simple_chess_pieces/b-king");
    }
  }
}
