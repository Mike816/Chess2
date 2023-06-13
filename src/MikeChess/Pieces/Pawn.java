package MikeChess.Pieces;

import MikeChess.Board;
import java.util.Scanner;

public class Pawn extends Piece {
    private boolean hasMoved;
    // -1 direction for black, +1 direction for white
    private int direction;
    String temp;

    public Pawn(int x, int y, String mySide, Board myBoard){
        super(x, y, "p", mySide, myBoard);
        if((y== 6 && mySide.equals("b")) || (y == 1 && mySide.equals("w"))){
            hasMoved = false;
        }
        else{
            hasMoved = true;
        }

        if(mySide.equals("b")){
            direction = -1;
        }
        else{
            direction = 1;
        }
    }

    /**
     * Only displays the diagonal moves | used to help to develop the threatMap
     * @param myBoard the chess board
     */
    @Override
    public void diagMoves(Board myBoard){
        int x = this.get_x();
        int y = this.get_y();
        String[][] board = myBoard.getWhole_Chess_board();
        String temp;

        if(x+1< board[0].length){
            temp = board[y+direction][x+1];

                board[y+direction][x+1] = "*" + temp.substring(1);

        }

        //left
        if(x-1> board.length){
            temp = board[y+direction][x-1];

                board[y+direction][x-1] = "*" + temp.substring(1);

        }

    }

    @Override
    public void show_moves(Board myBoard) {
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();
        String[][] board = myBoard.getWhole_Chess_board();
        String temp;

        //forward moves
        if( (direction == -1 && y != 0)  ||(direction == 1 && y !=7) ){
            if(!hasMoved){
                if(board[y+direction][x].equals("___")){

                        board[y+direction][x] = "*__";

                    if(board[y+direction*2][x].equals("___")){

                            board[y+direction*2][x] = "*__";


                    }
                }

            }
            else{
                if(board[y+direction][x].equals("___")) {

                        board[y +direction][x] = "*__";


                }
            }

            //diagonal captures
            //right
            if(x+1< board[0].length){
                temp = board[y+direction][x+1];
                if(!temp.equals("___") && !temp.substring(1,2).equals(this.get_side())){

                        board[y+direction][x+1] = "*" + temp.substring(1);


                }
            }

            //left
            if(x-1> board.length){
                temp = board[y+direction][x-1];
                if(!temp.equals("___") && !temp.substring(1,2).equals(this.get_side())){

                        board[y+direction][x-1] = "*" + temp.substring(1);


                }
            }

            //enpassent
            //if it is on the same row
            String t = myBoard.getLastMove();
            if(!t.equals("N") && t.substring(2,3).equals(Integer.toString(y))){
                // if it is one square away col wise
                if(Math.abs(Integer.parseInt(t.substring(1,2))-x) == 1){

                        board[Integer.parseInt(t.substring(2,3))+direction][Integer.parseInt(t.substring(1,2))] = "*__";

                }
            }
        }







    }



    //return false if promotion failed, true if it didn't
    public boolean promotion(String piece, Board myBoard){
        if((this.get_y()==7 && direction == 1) || (this.get_y()==0 && direction == -1)){

            if(piece.equals("q")){
                Queen myQueen = new Queen(this.get_x(), this.get_y(), this.get_side(), myBoard);
                myBoard.setChess_board("_" + this.get_side() + "q", this.get_x(), this.get_y());
            }
            else if(piece.equals("n")){
                Knight myKnight = new Knight(this.get_x(), this.get_y(), this.get_side(), myBoard);
                myBoard.setChess_board("_" + this.get_side() + "n", this.get_x(), this.get_y());
            }
            else if(piece.equals("r")){
                Rook myRook = new Rook(this.get_y(), this.get_x(), this.get_side(), myBoard);
                myBoard.setChess_board("_" + this.get_side() + "r", this.get_x(), this.get_y());
            }
            else if(piece.equals("b")){
                Bishop myBishop = new Bishop(this.get_x(), this.get_y(), this.get_side(), myBoard);
                myBoard.setChess_board("_" + this.get_side() + "b", this.get_x(), this.get_y());
            }
            else{
                return false;
            }


        }
        else{
            return false;
        }

        this.setDead();
        myBoard.setPiece_board(null, this.get_x(), this.get_y());
        return true;
    }
    @Override
    public boolean makeMove(int y, int x, Board myBoard, Scanner input){
        //check if the move is an enpassnet
        int last_y = this.get_y();
        if(myBoard.get_Chess_Board(x, y).equals("*__") && !(myBoard.get_Chess_Board(x, y-direction).equals("___"))){
            this.update_pos(x, y, myBoard);
            myBoard.setChess_board("___", x, y-direction);
            return true;
        }
        //normal move
        else{
            if(this.makeMove(y, x, myBoard)){
                if (this.update_pos(x, y, myBoard)){
                    if(y > last_y+ direction){
                        hasMoved = true;
                    }
                    if((this.get_y()==7 && direction == 1) || (this.get_y()==0 && direction == -1)){
                        while (true){
                            try{
                                System.out.println("What piece do you want to promote to? ");
                                String temp = input.next();
                                if(promotion(temp, myBoard)){
                                    return true;
                                }


                            }
                            catch (Exception e){
                                System.out.println("Please input that again. \n q for Queen, b for Bishop, n for knight, and r for rook.");
                            }
                        }
                    }

                }
                return true;
            }
            else{
                return false;
            }

        }

    }

}
