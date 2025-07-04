package piece;

import app.Board;
import app.GamePanel;
import app.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
  public Type type;
  public BufferedImage image;
  public int x, y;
  public int col, row, preCol, preRow;
  public int color;
  public Piece hittingPiece;
  public boolean moved, twoStepped;

  public Piece(int col, int row, int color) {
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

  public int getCol(int x) {
    return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
  }

  public int getRow(int y) {
    return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
  }

  public void updatePosition() {
    //TO CHECK EN PASSANT
    if (type == Type.PAWN){
      if (Math.abs(row - preRow) == 2){
        twoStepped = true;
      }
    }
    x = getX(col);
    y = getY(row);
    preCol = getCol(x);
    preRow = getRow(y);
    moved = true;
  }

  public void draw(Graphics2D g2) {
    g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
  }

  public boolean isWithinBoard(int targetCol, int targetRow) {
    return targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7;
  }

  public Piece getHittingPosition(int targetCol, int targetRow) {
    for (Piece piece : GamePanel.simPieces) {
      if (piece.col == targetCol && piece.row == targetRow && piece != this) {
        return piece;
      }
    }
    return null;
  }

  public boolean isValidSquare(int targetCol, int targetRow) {
    hittingPiece = getHittingPosition(targetCol, targetRow);

    if (hittingPiece == null) {
      return true;
    } else {
      if (hittingPiece.color != this.color) {
        return true;
      } else hittingPiece = null;
    }
    return false;
  }

  public boolean isSameSquare(int targetCol, int targetRow) {
    return targetCol == preCol && targetRow == preRow;
  }

  public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
    for (int c = preCol - 1; c > targetCol; c--) {
      for (Piece piece : GamePanel.simPieces) {
        if (piece.col == c && piece.row == targetRow) {
          hittingPiece = piece;
          return true;
        }
      }
    }

    for (int c = preCol + 1; c < targetCol; c++) {
      for (Piece piece : GamePanel.simPieces) {
        if (piece.col == c && piece.row == targetRow) {
          hittingPiece = piece;
          return true;
        }
      }
    }

    for (int r = preRow - 1; r > targetRow; r--) {
      for (Piece piece : GamePanel.simPieces) {
        if (piece.col == targetCol && piece.row == r) {
          hittingPiece = piece;
          return true;
        }
      }
    }

    for (int r = preRow + 1; r < targetRow; r++) {
      for (Piece piece : GamePanel.simPieces) {
        if (piece.col == targetCol && piece.row == r) {
          hittingPiece = piece;
          return true;
        }
      }
    }

    return false;
  }

  public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow){
    if (targetRow < preRow){
      for (int c = preCol-1; c > targetCol; c--) {
        int difference = Math.abs(c - preCol);
        for (Piece piece :
            GamePanel.simPieces) {
          if (piece.col == c && piece.row == preRow - difference){
            hittingPiece = piece;
            return true;
          }
        }
      }

      for (int c = preCol+1; c < targetCol; c++) {
        int difference = Math.abs(c - preCol);
        for (Piece piece :
            GamePanel.simPieces) {
          if (piece.col == c && piece.row == preRow - difference){
            hittingPiece = piece;
            return true;
          }
        }
      }
    }

    if (targetRow > preRow){
      for (int c = preCol-1; c > targetCol; c--) {
        int difference = Math.abs(c - preCol);
        for (Piece piece :
            GamePanel.simPieces) {
          if (piece.col == c && piece.row == preRow + difference){
            hittingPiece = piece;
            return true;
          }
        }
      }

      for (int c = preCol+1; c < targetCol; c++) {
        int difference = Math.abs(c - preCol);
        for (Piece piece :
            GamePanel.simPieces) {
          if (piece.col == c && piece.row == preRow + difference){
            hittingPiece = piece;
            return true;
          }
        }
      }
    }
    return false;
  }

  public int getIndex() {
    for (int index = 0; index < GamePanel.simPieces.size(); index++) {
      if (GamePanel.simPieces.get(index) == this) {
        return index;
      }
    }
    return 0;
  }

  public boolean canMove(int targetCol, int targetRow) {
    return false;
  }

  public void resetPosition() {
    col = preCol;
    row = preRow;
    x = getX(col);
    y = getY(row);
  }
}
