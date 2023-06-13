package MikeChess;

import MikeChess.Pieces.Pawn;
import MikeChess.Pieces.Piece;


import java.util.*;


public class Board {
    private String[][] chess_board; // graphical representation of the board
    private Piece[][] piece_board; // logical representation of the board
    // note that white's threanted squares are squares that white king CANNOT move to. It is the same for black
    private boolean[][] white_threantedSquares;
    private boolean[][] black_threantedSquares;
    private ArrayList<Piece> WhitePieces;
    private ArrayList<Piece> BlackPieces;
    private ArrayList<Piece> whitePawn;
    private ArrayList<Piece> blackPawn;
    private Piece whiteKing;
    private Piece blackKing;
    private ArrayList<String> moveHistory;



    public Board(){
        chess_board = new String[8][8];
         piece_board = new Piece[8][8];
        white_threantedSquares = new boolean[8][8];
        black_threantedSquares = new boolean[8][8];
        WhitePieces = new ArrayList<>();
        BlackPieces = new ArrayList<>();
        whitePawn = new ArrayList<>();
        blackPawn = new ArrayList<>();

        for(String[] temp : chess_board){
            Arrays.fill(temp, "___");
        }
        moveHistory = new ArrayList<>();

    }

//    /**
//     *
//     * @return 1 for white win, 2 for black win, 3 for draw, 0 for nothing yet
//     */
//    public int win_lose_draw(){
//        //white
//
//        //checks for number of legal moves by white
//        int whiteLegalMoves = 0;
//        for(int i = 0; i< WhitePieces.size(); i++){
//            WhitePieces.get(i).tr
//        }
//
//    }

    /**
     *
     * @return 0 for no win, 1 for white win, 2 for black win
     */
    public int checkWin(){
        if(!whiteKing.isAlive()){
            return 1;
        }
        if(!blackKing.isAlive()){
            return 2;
        }
        return 0;
    }

    /**
     *
     * @return true if in check, false if not in check
     */
    public boolean isBlackInCheck(){
        if(black_threantedSquares[blackKing.get_y()][blackKing.get_x()] == true){
            return true;
        }
        return false;

    }

    public boolean isWhiteInCheck(){
        if(white_threantedSquares[whiteKing.get_y()][whiteKing.get_x()] == true){
            return true;
        }
        return false;

    }

    public void setKing(Piece myPiece, String mySide){
        if(mySide.equals("b")){
            blackKing = myPiece;
        }
        else{
            whiteKing = myPiece;
        }
    }

    public Piece getPiece(int x, int y){
        return piece_board[y][x];
    }

    public void addPawn(Piece myPiece, String mySide){
        if(mySide.equals("w")){
            whitePawn.add(myPiece);
        }
        else{
            blackPawn.add(myPiece);

        }
    }

    public void addPiece(Piece myPiece, String mySide){
        if(mySide.equals("w")){
            WhitePieces.add(myPiece);
        }
        else{
            BlackPieces.add(myPiece);
        }

    }



    public void updateThreatMap(){
        clearThreatMaps();
        //black
        clear_movement_indicators();
        for(int i = 0; i< WhitePieces.size(); i++){
            Piece myPiece = WhitePieces.get(i);
            if(myPiece.isAlive()){
                myPiece.diagMoves(this);
                white_threantedSquares[myPiece.get_y()][myPiece.get_x()] = true;
            }

        }
        for(int i = 0; i<whitePawn.size(); i++){
            Piece myPiece = whitePawn.get(i);
            if(myPiece.isAlive()){
                myPiece.diagMoves(this);
                white_threantedSquares[myPiece.get_y()][myPiece.get_x()] = true;
            }

        }
        whiteKing.diagMoves(this);

        for(int i = 0; i< 8; i++){
            for(int t = 0; t< 8; t++){
                if(chess_board[i][t].substring(0,1).equals("*")){
                    black_threantedSquares[i][t] = true;
                }
            }
        }



        //White
        clear_movement_indicators();
        for(int i = 0; i< BlackPieces.size(); i++){
            Piece myPiece = BlackPieces.get(i);
            if(myPiece.isAlive()){
                myPiece.diagMoves(this);
                black_threantedSquares[myPiece.get_y()][myPiece.get_x()] = true;
            }


        }
        for(int i = 0; i<blackPawn.size(); i++){
            Piece myPiece = blackPawn.get(i);
            if(myPiece.isAlive()){
                myPiece.diagMoves(this);
                black_threantedSquares[myPiece.get_y()][myPiece.get_x()] = true;
            }


        }
        blackKing.diagMoves(this);

        for(int i = 0; i< 8; i++){
            for(int t = 0; t< 8; t++){
                if(chess_board[i][t].substring(0,1).equals("*")){
                    white_threantedSquares[i][t] = true;
                }
            }
        }


        clear_movement_indicators();


    }




    /**
     *
     * @param side w for white and b for black
     * @return a 2d array of the sqaures being attacked by oppoents
     */
    public boolean[][] getThreatMap(String side){
        if(side.equals("w")){
            return white_threantedSquares;
        }
        else{
            return black_threantedSquares;
        }
    }
    /**
     * Only used for testing
     */
    public void printThreatMap(){
        System.out.println("This is white's threat map");
        for(int i =0; i< 8; i++){
            for(int t = 0; t< 8; t++){
                System.out.print(white_threantedSquares[i][t] + " ");
            }
            System.out.println();
        }

        System.out.println("This is black's threat map");
        for(int i =0; i< 8; i++){
            for(int t = 0; t< 8; t++){
                System.out.print(black_threantedSquares[i][t] + " ");
            }
            System.out.println();
        }
    }

    public void clearThreatMaps(){
        for(boolean[] temp : white_threantedSquares){
            Arrays.fill(temp, false);
        }
        for(boolean[] temp : black_threantedSquares){
            Arrays.fill(temp, false);
        }
    }


    //add a move to move history
    //stotred as piece + col + row
    public void addMove(String piece, int y, int x){
        moveHistory.add(piece + x + y);
    }

    /**
     * true if white to move, false if black to move
     */
    public boolean whooseMove(){
        if(moveHistory.size()%2 == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // returns N if no last move
    public String getLastMove(){
        if (moveHistory.size() != 0){
            return (moveHistory.get(moveHistory.size()-1));
        }
        else{
            return "N";
        }

    }

    public String[][] getWhole_Chess_board(){
        return chess_board;
    }
    public void setPiece_board(Piece myPiece, int x, int y){
        this.piece_board[y][x] = myPiece;
    }
    public void setChess_board(String myString, int x, int y){
        this.chess_board[y][x] = myString;
    }

    public void fen_to_board(String fen){
        int col = 0;
        int row = 0;
        char[] pos = fen.toCharArray();

        for (char i : pos){
            if( i == ' '){
                break;
            }
            else if(i == '/'){
                row +=1;
                col = 0;
            }
            else if (Character.isDigit(i)){
                for (int t = 0; t< Character.getNumericValue(i); t++){
                    chess_board[row][col] = "___";
                    col += 1;
                }
            }
            else if(Character.isUpperCase(i)){
                chess_board[row][col] = "_b" + Character.toLowerCase(i);
                col +=1;
            }
            else{
                chess_board[row][col] = "_w" + i;
                col +=1;
            }
        }
    }

    public String getPos(int row, int col){
        return chess_board[row][col];
    }

    public void setPos(int row, int col, String set){
        chess_board[row][col] = set;
    }

    public String get_Chess_Board(int x, int y){
        return chess_board[y][x];
    }

    public Piece[][] getWholePieceBoard(){
        return piece_board;
    }

    public Piece get_Piece_Board(int x, int y){ return piece_board[y][x];}

    public void clear_movement_indicators(){
        for(int i =0; i<chess_board.length;i++){
            for(int t = 0; t<chess_board[0].length; t++){
                if(chess_board[i][t].substring(0, 1).equals("*")){
                    chess_board[i][t] = "_" + chess_board[i][t].substring(1);
                }
            }
        }
    }

    public void print_board_fromBlack(){
        System.out.print("     ");
        for(int i = 1; i <=8; i++){
            System.out.print(i +"   ");
        }
        System.out.println();
        for(int i = 0; i<chess_board.length; i++){
            System.out.print((i+1) +"   ");
            for(int t = 0; t<chess_board[0].length; t++){
                System.out.print(chess_board[i][t] + " ");
            }
            System.out.println();
        }
    }


    public void print_board_fromWhite(){
        System.out.print("     ");
        for(int i = 8; i >0; i--){
            System.out.print(i +"   ");
        }
        System.out.println();
        for(int i = chess_board.length-1;i>=0;  i--){
            System.out.print((i+1) +"   ");
            for(int t = chess_board[0].length-1; t>=0; t--){
                System.out.print(chess_board[i][t] + " ");
            }
            System.out.println();
        }

    }



}

