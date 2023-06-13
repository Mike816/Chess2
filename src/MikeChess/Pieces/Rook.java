package MikeChess.Pieces;

import MikeChess.Board;

public class Rook extends Piece{

    private boolean hasMoved;
    public Rook(int y, int x, String mySide, Board myBoard){
        super(x, y, "r", mySide, myBoard);
        hasMoved = false;
    }

    // puts *__ in the first pos of the chess_board if it is a valid move
    @Override
    public void show_moves(Board myBoard){
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();
        String[][] board = myBoard.getWhole_Chess_board();
        String temp;

        //left
        int col = x-1;
        while(col >= 0){
            temp = board[y][col];
            if(temp.equals("___")){

                    board[y][col] = "*__";


            }
            else if(!temp.substring(1, 2).equals(side)){

                    board[y][col] = "*" + temp.substring(1);

                break;
            }
            else{
                break;
            }
            col--;
        }

        //right
        col = x+1;
        while(col < 8){
            temp = board[y][col];
            if(temp.equals("___")){

                    board[y][col] = "*__";

            }
            else if(!temp.substring(1, 2).equals(side)){

                    board[y][col] = "*" + temp.substring(1);

                break;
            }
            else{
                break;
            }
            col++;
        }

        //down
        int row = y+1;
        while(row<8){
            temp = board[row][x];
            if(temp.equals("___")){

                    board[row][x] = "*__";

            }
            else if(!temp.substring(1, 2).equals(side)){

                    board[row][x] = "*" + temp.substring(1);

                break;
            }
            else{
                break;
            }
            row++;
        }

        //up
        row = y-1;
        while(row>=0){
            temp = board[row][x];
            if(temp.equals("___")){

                    board[row][x] = "*__";

            }
            else if(!temp.substring(1, 2).equals(side)){

                    board[row][x] = "*" + temp.substring(1);

                break;
            }
            else{
                break;
            }
            row--;
        }

    }

    @Override
    public void diagMoves(Board myBoard){
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();
        String[][] board = myBoard.getWhole_Chess_board();
        String temp;

        //left
        int col = x-1;
        while(col >= 0){
            temp = board[y][col];
            if(temp.equals("___")){
                board[y][col] = "*__";
            }
            else if(temp.substring(1, 2).equals(side)){
                board[y][col] = "*" + temp.substring(1);
                break;
            }
            else{
                break;
            }
            col--;
        }

        //right
        col = x+1;
        while(col < 8){
            temp = board[y][col];
            if(temp.equals("___")){
                board[y][col] = "*__";
            }
            else if(temp.substring(1, 2).equals(side)){
                board[y][col] = "*" + temp.substring(1);
                break;
            }
            else{
                break;
            }
            col++;
        }

        //down
        int row = y+1;
        while(row<8){
            temp = board[row][x];
            if(temp.equals("___")){
                board[row][x] = "*__";
            }
            else if(temp.substring(1, 2).equals(side)){
                board[row][x] = "*" + temp.substring(1);
                break;
            }
            else{
                break;
            }
            row++;
        }

        //up
        row = y-1;
        while(row>=0){
            temp = board[row][x];
            if(temp.equals("___")){
                board[row][x] = "*__";
            }
            else if(temp.substring(1, 2).equals(side)){
                board[row][x] = "*" + temp.substring(1);
                break;
            }
            else{
                break;
            }
            row--;
        }
    }
    //update the position of the piece, returns true if the move is valid, and false if it is not.
    // The move will not be made if it is invalid
    @Override
    public boolean makeMove(int y, int x, Board myBoard){
        hasMoved = true;
        return this.update_pos(y, x, myBoard);
    }

    public boolean hasMoved(){
        return hasMoved;
    }

}
