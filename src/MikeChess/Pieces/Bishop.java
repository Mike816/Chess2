package MikeChess.Pieces;

import MikeChess.Board;

public class Bishop extends Piece{

    public Bishop(int x, int y, String mySide, Board myBoard){
        super(x, y, "b", mySide, myBoard);
    }

    @Override
    public void show_moves(Board myBoard){
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();


        String[][] board = myBoard.getWhole_Chess_board();

        // Check diagonal up-left
        int row = y-1;
        int col = x-1;

        while (row >= 0 && col >= 0 && board[row][col].equals("___")){

                board[row][col] = "*__";

            row--;
            col--;
        }

        if(row>=0 && col>=0 && !board[row][col].substring(1, 2).equals(side)){

                board[row][col] = "*" + board[row][col].substring(1);


        }

        // Check diagonal down-right
        row = y + 1;
        col = x + 1;

        while (row < board.length && col < board[0].length && board[row][col].equals("___")) {

                board[row][col] = "*__";

            row++;
            col++;
        }

        if(row< board.length && col < board[0].length && !board[row][col].substring(1, 2).equals(side)){

                board[row][col] = "*" + board[row][col].substring(1);


        }

        // Check diagonal up-right
        row = y - 1;
        col = x + 1;

        while (row >= 0 && col < board[0].length && board[row][col].equals("___")) {

                board[row][col] = "*" + board[row][col].substring(1);

            row--;
            col++;
        }

        if(row >= 0 && col < board[0].length && !board[row][col].substring(1, 2).equals(side)){

                board[row][col] = "*" + board[row][col].substring(1);


        }

        // Check diagonal down-left
        row = y + 1;
        col = x - 1;

        while (row < board.length && col >= 0 && board[row][col].equals("___")) {

                board[row][col] = "*" + board[row][col].substring(1);


            row++;
            col--;
        }

        if(row < board.length && col >= 0  && !board[row][col].substring(1, 2).equals(side)){

                board[row][col] = "*" + board[row][col].substring(1);


        }


    }


    @Override
    public void diagMoves(Board myBoard){
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();
        String temp;

        String[][] board = myBoard.getWhole_Chess_board();

        // Check diagonal up-left
        int row = y-1;
        int col = x-1;

        while (row >= 0 && col >= 0 && board[row][col].equals("___")){
            board[row][col] = "*__";
            row--;
            col--;
        }

        if(row>=0 && col>=0 && board[row][col].substring(1, 2).equals(side)){
            board[row][col] = "*" + board[row][col].substring(1);
        }

        // Check diagonal down-right
        row = y + 1;
        col = x + 1;

        while (row < board.length && col < board[0].length && board[row][col].equals("___")) {
            board[row][col] = "*__";
            row++;
            col++;
        }

        if(row< board.length && col < board[0].length && board[row][col].substring(1, 2).equals(side)){
            board[row][col] = "*" + board[row][col].substring(1);
        }

        // Check diagonal up-right
        row = y - 1;
        col = x + 1;

        while (row >= 0 && col < board[0].length && board[row][col].equals("___")) {
            board[row][col] = "*" + board[row][col].substring(1);
            row--;
            col++;
        }

        if(row >= 0 && col < board[0].length && board[row][col].substring(1, 2).equals(side)){
            board[row][col] = "*" + board[row][col].substring(1);
        }

        // Check diagonal down-left
        row = y + 1;
        col = x - 1;

        while (row < board.length && col >= 0 && board[row][col].equals("___")) {
            board[row][col] = "*" + board[row][col].substring(1);
            row++;
            col--;
        }

        if(row < board.length && col >= 0  && board[row][col].substring(1, 2).equals(side)){
            board[row][col] = "*" + board[row][col].substring(1);
        }
    }

    //update the position of the piece, returns true if the move is valid, and false if it is not.
    // The move will not be made if it is invalid
    @Override
    public boolean makeMove(int y, int x, Board myBoard){
        return this.update_pos(x, y, myBoard);
    }

}
