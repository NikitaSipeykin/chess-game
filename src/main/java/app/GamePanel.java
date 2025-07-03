package app;

import piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
  public static final int WIDTH = 1100;
  public static final int HEIGHT = 800;
  final int FPS = 60;
  Thread gameThread;
  Board board = new Board();
  Mouse mouse = new Mouse();

  //PIECES
  public static ArrayList<Piece> pieces = new ArrayList<>();
  public static ArrayList<Piece> simPieces = new ArrayList<>();
  Piece activePiece;

  //COLOR
  public static final int WHITE = 0;
  public static final int BLACK = 1;
  int currentColor = WHITE;

  public GamePanel() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.black);

    addMouseMotionListener(mouse);
    addMouseListener(mouse);

    setPieces();
    copyPieces(pieces, simPieces );
  }

  public void launchGame() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  public void setPieces(){
    //WHITE TEAM
    pieces.add(new Pawn(0, 6, WHITE));
    pieces.add(new Pawn(1, 6, WHITE));
    pieces.add(new Pawn(2, 6, WHITE));
    pieces.add(new Pawn(3, 6, WHITE));
    pieces.add(new Pawn( 4, 6, WHITE));
    pieces.add(new Pawn(5, 6, WHITE));
    pieces.add(new Pawn(6, 6, WHITE));
    pieces.add(new Pawn( 7, 6, WHITE));
    pieces.add(new Rook(0, 7, WHITE));
    pieces.add(new Rook( 7, 7, WHITE));
    pieces.add(new Knight(1, 7, WHITE));
    pieces.add(new Knight( 6, 7, WHITE));
    pieces.add(new Bishop(2, 7, WHITE));
    pieces.add(new Bishop(5, 7, WHITE));
    pieces.add(new Queen(3, 7, WHITE));
    pieces.add(new King(4, 7, WHITE));

    //BLACK TEAM
    pieces.add(new Pawn(0, 1, BLACK));
    pieces.add(new Pawn( 1, 1, BLACK));
    pieces.add(new Pawn( 2, 1, BLACK));
    pieces.add(new Pawn( 3, 1, BLACK));
    pieces.add(new Pawn(4, 1, BLACK));
    pieces.add(new Pawn(5, 1, BLACK));
    pieces.add(new Pawn(6, 1, BLACK));
    pieces.add(new Pawn(7, 1, BLACK));
    pieces.add(new Rook(0, 0, BLACK));
    pieces.add(new Rook(7, 0, BLACK));
    pieces.add(new Knight(1, 0, BLACK));
    pieces.add(new Knight(6, 0, BLACK));
    pieces.add(new Bishop(2, 0, BLACK));
    pieces.add(new Bishop(5, 0, BLACK));
    pieces.add(new Queen(3, 0, BLACK));
    pieces.add(new King(4, 0, BLACK));
  }

  private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
    target.clear();
    for (int i = 0; i < source.size(); i++) {
      target.add(source.get(i));
    }
  }

  @Override
  public void run() {
    double drawInterval = 1000000000d / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
    }
  }

  public void update() {
    if (mouse.pressed){
       if (activePiece == null){
         for (Piece piece :
             simPieces) {
           if (piece.color == currentColor
               && piece.col == mouse.x / Board.SQUARE_SIZE
               && piece.row == mouse.y / Board.SQUARE_SIZE){
             activePiece = piece;
           }
         }
       }
       else {
         simulate();
       }
    }

    if (!mouse.pressed){
      if (activePiece != null){
        activePiece.updatePosition();
        activePiece = null;
      }
    }
  }

  private void simulate() {
    activePiece.x = mouse.x - Board.HALF_SQUARE_SIZE;
    activePiece.y = mouse.y - Board.HALF_SQUARE_SIZE;

    activePiece.col = activePiece.getCol(activePiece.x);
    activePiece.row = activePiece.getRow(activePiece.y);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    //BOARD
    board.draw(g2);

    for (Piece p :
        simPieces) {
      p.draw(g2);
    }
    if (activePiece != null){
      g2.setColor(Color.white);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
      g2.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE,
          Board.SQUARE_SIZE, Board.SQUARE_SIZE);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
      activePiece.draw(g2);
    }
  }

}
