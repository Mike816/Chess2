package MikeChess;
import MikeChess.Pieces.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();

        //white
        Pawn Wpawn1 = new Pawn(0, 1, "w", board);
        Pawn Wpawn2 = new Pawn(1, 1, "w", board);
        Pawn Wpawn3 = new Pawn(2, 1, "w", board);
        Pawn Wpawn4 = new Pawn(3, 1, "w", board);
        Pawn Wpawn5 = new Pawn(4, 1, "w", board);
        Pawn Wpawn6 = new Pawn(5, 1, "w", board);
        Pawn Wpawn7 = new Pawn(6, 1, "w", board);
        Pawn Wpawn8 = new Pawn(7, 1, "w", board);

        Bishop Wbishop1 = new Bishop(2, 0, "w", board);
        Bishop Wbishop2 = new Bishop(5, 0, "w", board);

        Knight Wknight1 = new Knight(1, 0, "w", board);
        Knight Wknight2 = new Knight(6, 0, "w", board);

        Rook Wrook1 = new Rook(0,0, "w", board);
        Rook Wrook2 = new Rook(0,7, "w", board);

        Queen Wqueen1 = new Queen(4, 0, "w", board);
        King Wking1 = new King(3, 0, "w", board);


        //black
        Pawn Bpawn1 = new Pawn(0, 6, "b", board);
        Pawn Bpawn2 = new Pawn(1, 6, "b", board);
        Pawn Bpawn3 = new Pawn(2, 6, "b", board);
        Pawn Bpawn4 = new Pawn(3, 6, "b", board);
        Pawn Bpawn5 = new Pawn(4, 6, "b", board);
        Pawn Bpawn6 = new Pawn(5, 6, "b", board);
        Pawn Bpawn7 = new Pawn(6, 6, "b", board);
        Pawn Bpawn8 = new Pawn(7, 6, "b", board);



        Bishop Bbishop1 = new Bishop(2, 7, "b", board);
        Bishop Bbishop2 = new Bishop(5, 7, "b", board);

        Knight Bknight1 = new Knight(1, 7, "b", board);
        Knight Bknight2 = new Knight(6, 7, "b", board);

        Rook Brook1 = new Rook(7,0, "b", board);
        Rook Brook2 = new Rook(7,7, "b", board);

        Queen Bqueen1 = new Queen(4, 7, "b", board);
        King Bking1 = new King(3, 7, "b", board);

        board.updateThreatMap();
        board.print_board_fromWhite();
        System.out.println();
        int row;
        int col;
        Piece myPiece;

        boolean hasWin = false;
        while(!hasWin){
            while (true){
                System.out.println("Select a piece (col  row) ");
                board.updateThreatMap();

                try{
                    col = input.nextInt()-1;
                    row = input.nextInt()-1;

                    input.nextLine();

                    myPiece = board.getPiece(col, row);

                    if((myPiece.get_side().equals("w") && board.whooseMove()) || (myPiece.get_side().equals("b") && !board.whooseMove()) ){
                        myPiece.show_moves(board);
                        if(board.whooseMove()){
                            board.print_board_fromWhite();

                        }
                        else{
                            board.print_board_fromBlack();
                        }
                        break;
                    }

                }
                catch (Exception E){
                }

            }

            while(true){

                try{
                    System.out.println("Make a move (col, row) or enter (-1) to undo");
                    row = input.nextInt()-1;
                    if(row == -2){
                        break;
                    }
                    col = input.nextInt()-1;

                    input.nextLine();

                    //if my move is valid
                    if(myPiece.tryMove(col, row, board, myPiece.get_side())){
                        myPiece.show_moves(board);
                        //make the move
                        if(!myPiece.getType().equals("p") ){
                            myPiece.makeMove(col, row, board);

                        }
                        else{
                            myPiece.makeMove(col, row, board, input);
                        }
                    }
                    int win = board.checkWin();
                    if(win == 0){
                        continue;
                    }
                    else if(win == 1){
                        System.out.println("White wins!");
                        break;
                    }
                    else if(win == 2) {
                        System.out.println("Black wins!");
                        break;
                    }


                    if(board.whooseMove()){
                        board.print_board_fromWhite();
                    }
                    else{
                        board.print_board_fromBlack();
                    }
                    break;
                }
                catch (Exception e){ }
            }
            System.out.println("gg");
            input.close();

        }

    }
}