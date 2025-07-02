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
}
