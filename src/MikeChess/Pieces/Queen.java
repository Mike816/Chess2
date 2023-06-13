package MikeChess.Pieces;

import MikeChess.Board;
import MikeChess.Main;

public class Queen extends Piece {

    public Queen(int x, int y, String mySide, Board myBoard){
        super(x, y, "q", mySide, myBoard);
    }

    @Override
    public void show_moves(Board myBoard) {
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

        //diagonal checks
        row = y-1;
        col = x-1;

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
    public boolean makeMove(int y, int x, Board myBoard){
        return this.update_pos(x, y, myBoard);
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

        //diagonal checks
        row = y-1;
        col = x-1;

        while (row >= 0 && col >= 0 && board[row][col].equals("___")){
            board[row][col] = "*__";
            row--;
            col--;
        }

        if(row>=0 && col>=0 ){
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

        if(row< board.length && col < board[0].length ){
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

        if(row >= 0 && col < board[0].length ){
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

        if(row < board.length && col >= 0 ){
            board[row][col] = "*" + board[row][col].substring(1);
        }
    }
}
