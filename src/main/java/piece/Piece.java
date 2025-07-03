package piece;

import app.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
  public BufferedImage image;
  public int x, y;
  public int col, row, preCol, preRow;
  public int color;

  public Piece( int col, int row, int color) {
    this.col = col;
    this.row = row;
    this.color = color;
    x = getX(col);
    y = getY(row);
    preCol = col;
    preRow = row;
  }

  public BufferedImage getImage(String imagePath) {
    BufferedImage bImage = null;

    try {
      bImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bImage;
  }

  public int getX(int col) {
    return col * Board.SQUARE_SIZE;
  }

  public int getY(int row) {
    return row * Board.SQUARE_SIZE;
  }

  public int getCol(int x){
    return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
  }

  public int getRow(int y) {
    return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
  }

  public void updatePosition(){
    x = getX(col);
    y = getY(row);
    preCol = getCol(x);
    preRow = getRow(y);
  }

  public void draw(Graphics2D g2) {
    g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
  }
}
