package lk.ijse.dep.service;

import java.util.Random;

public class AiPlayer extends Player {

    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {
        Random r = new Random();
        do{
            col = (r.nextInt(6));
        }while (!board.isLegalMove(col));
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);
        Winner winner = board.findWinner();
        if (!winner.getWinningPiece().equals(Piece.EMPTY)) {
            board.getBoardUI().notifyWinner(winner);
        } else if (board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}
