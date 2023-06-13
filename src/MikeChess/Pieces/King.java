package MikeChess.Pieces;

import MikeChess.Board;

public class King extends Piece{

    private boolean hasMoved;
    public King(int x, int y, String mySide, Board myBoard){
        super(x, y, "k", mySide, myBoard);
        hasMoved = false;
    }



    @Override
    public void diagMoves(Board myBoard) {
        int row = this.get_y();
        int col = this.get_x();

        String[][] grid = myBoard.getWhole_Chess_board();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, rows - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, cols - 1); j++) {
                if (i == row && j == col) {
                    continue; // Skip the center point
                }
                grid[i][j] = "*" + grid[i][j].substring(1);
            }
        }
    }

    public void displayKingCastle(Board myBoard){
        if(!this.hasMoved){

            //white
            if(this.get_side().equals("w")){
                boolean[][] myMap = myBoard.getThreatMap("w");
                //long castle
                if( !myMap[0][3] && !myMap[0][2] && !myMap[0][1]){


                }
                //short castle
                if( !myMap[0][5] && !myMap[0][6]){

                }




            }
            //black
            else{

            }
        }



    }

    @Override
    public void show_moves(Board myBoard) {

        int row = this.get_y();
        int col = this.get_x();

        boolean[][] threatMap = myBoard.getThreatMap(this.get_side());

        String[][] grid = myBoard.getWhole_Chess_board();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, rows - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, cols - 1); j++) {
                if (i == row && j == col) {
                    continue; // Skip the center point
                }
                // if that square is not controlled by the oppoent and it is not one of your own pieces
                if (!threatMap[i][j] && !grid[i][j].substring(1, 2).equals(this.get_side())){
                    grid[i][j] = "*" + grid[i][j].substring(1);
                }

            }
        }


    }

    @Override
    public boolean makeMove(int y, int x, Board myBoard){
        hasMoved = true;
        return this.update_pos(x, y, myBoard);
    }
}
