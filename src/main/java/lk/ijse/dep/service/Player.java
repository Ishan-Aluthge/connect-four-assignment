package lk.ijse.dep.service;

public class Player {
    protected BoardUI newBoard;
    Board board;

    public Player(Board board){
        this.board=board;
    }

    public void movePiece(int col){
        board.getBoardUI().update(col,true);
    }
}
