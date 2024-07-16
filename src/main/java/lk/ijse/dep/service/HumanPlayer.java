package lk.ijse.dep.service;

import java.awt.event.WindowEvent;

public class HumanPlayer extends Player{
    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {
        if (board.isLegalMove(col)){
            board.updateMove(col,Piece.BLUE);
            board.getBoardUI().update(col,true);
            Winner winner = board.findWinner();
            if (!winner.getWinningPiece().equals(Piece.EMPTY)){
                this.board.getBoardUI().notifyWinner(winner);
            }else if (this.board.existLegalMoves()){
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
