package MikeChess.Pieces;

import MikeChess.Board;

public class Knight extends Piece {
    private static final int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    private static final int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

    public Knight(int x, int y, String mySide, Board myBoard){
        super(x, y, "n", mySide, myBoard);
    }

    @Override
    public void show_moves(Board myBoard) {
        int x = this.get_x();
        int y = this.get_y();
        String side = this.get_side();
        String[][] board = myBoard.getWhole_Chess_board();

        //
        for(int i =0; i< board.length; i++){
            int col = x + X[i];
            int row = y + Y[i];

            // count valid moves
            if (col >= 0 && row >= 0 && col < board[0].length && row < board.length ){
                if(board[row][col].equals("___")){

                        board[row][col] = "*__";


                }
                else if(!board[row][col].substring(1,2).equals(side)){

                        board[row][col] = "*" + board[row][col].substring(1);


                }
            }


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

        //
        for(int i =0; i< board.length; i++){
            int col = x + X[i];
            int row = y + Y[i];

            // count valid moves
            if (col >= 0 && row >= 0 && col < board[0].length && row < board.length ){
                if(board[row][col].equals("___")){
                    board[row][col] = "*__";
                }
                else if(board[row][col].substring(1,2).equals(side)){
                    board[row][col] = "*" + board[row][col].substring(1);
                }
            }


        }
    }
}
