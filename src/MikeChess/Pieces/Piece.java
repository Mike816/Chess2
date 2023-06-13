package MikeChess.Pieces;
import MikeChess.Board;
import java.util.Scanner;

public abstract class Piece {
    private int x_val;
    private int y_val;
    private String type;
    private String side;
    private boolean isAlive;



    // side is true for white, false for black
    public Piece(int myX, int myY, String myType, String mySide, Board myBoard){
        x_val = myX;
        y_val = myY;
        type = myType;
        side = mySide;
        isAlive = true;
         myBoard.setPiece_board(this, myX, myY);
        myBoard.setChess_board("_" + mySide + myType, myX, myY);

        //add the pieces to the arraylists for the threamaps
        if(myType.equals("p")){
            myBoard.addPawn(this, mySide);
        }
        else if(!myType.equals("k")){
            myBoard.addPiece(this, mySide);
        }
        else{
            myBoard.setKing(this, mySide);
        }

    }

    /**
     *
     * @return true if the piece is alive, false if it is not
     */
    public boolean isAlive(){
        return isAlive;
    }

    public String getType(){
        return type;
    }

    public int get_x(){
        return x_val;
    }

    public int get_y(){
        return y_val;
    }

    public String get_side(){
        return side;
    }

    public void show_moves(Board board){

    }

    public void setDead(){
        isAlive = false;
    }

    public void setAlive(){
        isAlive = true;
    }

    public boolean makeMove(int y, int x, Board myBoard){
        return false;
    }
    public boolean makeMove(int y, int x, Board myBoard, Scanner input){
        return false;
    }

    //if the move is valid, make the move and delete any piece at that position from piece_board
    //return true if the move is made, false if it is not
    //also clear the movement indicators (the * things)
    public boolean update_pos(int x, int y, Board myBoard){


        if(myBoard.get_Chess_Board(x, y).substring(0,1).equals("*")){

            if(!myBoard.get_Chess_Board(x, y).equals("*__")){

                myBoard.get_Piece_Board(x, y).setDead();
                myBoard.setPiece_board(null, x, y);
                myBoard.setPiece_board(this, x, y);
                myBoard.setPiece_board(null, x_val, y_val);

                myBoard.setChess_board("*__", x_val, y_val);
                myBoard.setChess_board("_" + this.get_side() + this.getType(), x, y);
                myBoard.clear_movement_indicators();
                x_val =x;
                y_val = y;
                myBoard.addMove(this.getType(), y, x);
                return true;
            }
            else{
                myBoard.setPiece_board(null, x, y);
                myBoard.setPiece_board(this, x, y);
                myBoard.setPiece_board(null, x_val, y_val);

                myBoard.setChess_board("*__", x_val, y_val);
                myBoard.setChess_board("_" + this.get_side() + this.getType(), x, y);
                myBoard.clear_movement_indicators();
                x_val =x;
                y_val = y;
                myBoard.addMove(this.getType(), y, x);
                return true;
            }

        }
        return false;

    }

    /**
     *
     * @param x the col myArray[][x]
     * @param y the row myArray[y][]
     * @param myBoard
     * @return true if the move is valid, false if it is not
     */
    public boolean tryMove(int x, int y, Board myBoard, String mySide){
        myBoard.clear_movement_indicators();

        String[][] chessBoard = myBoard.getWhole_Chess_board();
        Piece[][] pieceBoard = myBoard.getWholePieceBoard();

        //store the data
        String temp = chessBoard[y][x];
        String myPiece = chessBoard[this.get_y()][this.get_x()];

        // if the square to move to is not empty

        if(!temp.equals("___")){
             Piece TempPiece = pieceBoard[y][x];
            Piece myPiece_PieceBoard = pieceBoard[this.get_y()][this.get_x()];



            //test move the pieces
            chessBoard[y][x] = myPiece;
            chessBoard[this.get_y()][this.get_x()] = "___";

            myBoard.get_Piece_Board(x, y).setDead();
            myBoard.setPiece_board(null, x, y);
            myBoard.setPiece_board(this, x, y);
            myBoard.setPiece_board(null, x_val, y_val);

            //calculate the effects
            myBoard.updateThreatMap();
            if( (mySide.equals("b") && !myBoard.isBlackInCheck()) || (mySide.equals("w") && !myBoard.isWhiteInCheck()) ){
                //reset
                chessBoard[y][x] = temp;
                chessBoard[this.get_y()][this.get_x()] = myPiece;
                TempPiece.setAlive();
                myBoard.setPiece_board(TempPiece, x, y);
                myBoard.setPiece_board(this, x_val, y_val);

                return true;
            }

            //reset
            chessBoard[y][x] = temp;
            chessBoard[this.get_y()][this.get_x()] = myPiece;
            TempPiece.setAlive();
            myBoard.setPiece_board(TempPiece, x, y);
            myBoard.setPiece_board(this, x_val, y_val);

            return false;
        }
        else{

            //test move the pieces
            chessBoard[y][x] = myPiece;
            chessBoard[this.get_y()][this.get_x()] = "___";


            myBoard.setPiece_board(null, x, y);
            myBoard.setPiece_board(this, x, y);
            myBoard.setPiece_board(null, x_val, y_val);

            //calculate the effects
            myBoard.updateThreatMap();
            if( (mySide.equals("b") && !myBoard.isBlackInCheck()) || (mySide.equals("w") && !myBoard.isWhiteInCheck()) ){
                //reset
                chessBoard[y][x] = temp;
                chessBoard[this.get_y()][this.get_x()] = myPiece;
                myBoard.setPiece_board(this, x_val, y_val);

                return true;
            }
            //reset
            chessBoard[y][x] = temp;
            chessBoard[this.get_y()][this.get_x()] = myPiece;
            myBoard.setPiece_board(this, x_val, y_val);

            return false;
        }

    }

    /**
     * basicly just showMoves but it marks capturing your own pieces as a valid move (I made it for threatmaps)
     */
    public void diagMoves(Board myBoard){

    }

}


