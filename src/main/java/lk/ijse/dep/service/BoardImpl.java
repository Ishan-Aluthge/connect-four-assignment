package lk.ijse.dep.service;

public class BoardImpl implements Board {
     Piece[][] pieces;
     BoardUI boardUI;

     public BoardImpl(BoardUI boardUI) {
          this.boardUI=boardUI;
          pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
          setArrayValues();
          System.out.println("Array initialized");
     }

     public void setArrayValues(){
          for (int i=0; i<NUM_OF_COLS; i++){
               for(int j=0; j<NUM_OF_ROWS; j++){
                    pieces[i][j]=Piece.EMPTY;
               }
          }
          System.out.println("Values set Piece.Empty");
     }

     @Override
     public BoardUI getBoardUI() {
          return boardUI;
     }

     @Override
     public int findNextAvailableSpot(int col) {
          System.out.println("in findNextAvailableSpot method");
          for (int i = 0; i < pieces[col].length; i++){
               if (pieces[col][i].equals(Piece.EMPTY))
                    return i;
          }
          return -1;
     }

     @Override
     public boolean isLegalMove(int col) {
          System.out.println("in islegalMove method");
          for (int i= 0; i< pieces[col].length; i++){
               if(pieces[col][i].equals(Piece.EMPTY)){
                    System.out.println("legal move");
                    return true;
               }
          }
          System.out.println("illegal move");
          return false;
     }

     @Override
     public boolean existLegalMoves() {
          System.out.println("in existLegalMoves method ");
          for (Piece[] elementGroup : pieces){
               for (Piece ele : elementGroup){
                    if (ele.equals(Piece.EMPTY))
                         return false;
               }
          }
          return true;
     }

     @Override
     public void updateMove(int col, Piece move) {
          System.out.println("in updateMove method");
          pieces[col][findNextAvailableSpot(col)]=move;
         //boardUI.update(col,!move.equals(Piece.GREEN));
     }

     @Override
     public void updateMove (int col, int row,Piece move){
          pieces[col][row]= move;
         // boardUI.update(col,!move.equals(Piece.GREEN));
     }

     @Override
     public Winner findWinner() {
          System.out.println("Find Winner Method");
          l1: for (int r = 0; r < NUM_OF_ROWS; r++) {
               int Human = 0;
               int Ai = 0;
               for (int i = 0; i < 4; i++) {
                    switch (pieces[i][r]) {
                         case GREEN: Ai++;break;
                         case BLUE: Human++;break;
                         case EMPTY: break;
                    }
               }
               if (Ai == 4) {
                    boardUI.notifyWinner(new Winner(Piece.GREEN, 0, r, 3, r));
                    break l1;
               } else if (Human == 4) {
                    boardUI.notifyWinner(new Winner(Piece.BLUE, 0, r, 3, r));
                    break l1;
               } else if (existLegalMoves()) {
                    boardUI.notifyWinner(new Winner(Piece.EMPTY));
                    break l1;
               } else {
                    for (int m = 0; m < NUM_OF_ROWS; m++) {
                         int Human1 = 0;
                         int Ai1 = 0;
                         for (int j = 1; j < 5; j++) {
                              switch (pieces[j][m]) {
                                   case GREEN: Ai1++;break;
                                   case BLUE: Human1++;break;
                                   case EMPTY:break;
                              }
                         }
                         if (Ai1 == 4) {
                              boardUI.notifyWinner(new Winner(Piece.GREEN, 1, m, 4, m));
                              break l1;
                         } else if (Human1 == 4) {
                              boardUI.notifyWinner(new Winner(Piece.BLUE, 1, m, 4, m));
                              break l1;
                         } else if (Ai1 != 4 & Human1 != 4 & existLegalMoves()) {
                              boardUI.notifyWinner(new Winner(Piece.EMPTY));
                         } else {
                              for (int n = 0; n < NUM_OF_ROWS; n++) {
                                   int Human2 = 0;
                                   int Ai2 = 0;
                                   for (int k = 2; k < 6; k++) {
                                        switch (pieces[k][n]) {
                                             case GREEN:Ai2++;break;
                                             case BLUE:Human2++;break;
                                             case EMPTY:break;
                                        }
                                   }
                                   if (Ai2 == 4) {
                                        boardUI.notifyWinner(new Winner(Piece.GREEN, 2, n, 6, n));
                                        System.out.println("winner found ptn 3: Ai");
                                        break l1;
                                   }else if (Human1 == 4) {
                                        boardUI.notifyWinner(new Winner(Piece.BLUE, 2, n, 6,n));
                                        System.out.println("winner found ptn 3: human");
                                        break l1;
                                   } else if (Ai2 != 4 & Human2 != 4 & existLegalMoves()) {
                                        boardUI.notifyWinner(new Winner(Piece.EMPTY));
                                        System.out.println("winner not exists ptn 3: ");
                                        break l1;
                                   }
                              }
                         }
                    }
               }
          }
          l2: for (int r = 0; r < NUM_OF_COLS; r++) {
               int Human = 0;
               int Ai = 0;
               for (int i = 0; i < 4; i++) {
                    switch (pieces[r][i]) {
                         case GREEN:
                              Ai++;
                              break;
                         case BLUE:
                              Human++;
                              break;
                         case EMPTY:
                              break;
                    }
               }
               if (Ai == 4) {
                    boardUI.notifyWinner(new Winner(Piece.GREEN, r, 0, r, 3));
                    break l2;
               } else if (Human == 4) {
                    boardUI.notifyWinner(new Winner(Piece.BLUE, r, 0, r, 3));
                    break l2;
               } else if (existLegalMoves()) {
                    boardUI.notifyWinner(new Winner(Piece.EMPTY));
                    break l2;
               } else {
                    for (int m = 0; m < NUM_OF_COLS; m++) {
                         int Human1 = 0;
                         int Ai1 = 0;
                         for (int j = 1; j < 5; j++) {
                              switch (pieces[m][j]) {
                                   case GREEN:Ai1++;break;
                                   case BLUE:Human1++;break;
                                   case EMPTY: break;
                              }
                         }
                         if (Ai1 == 4) {
                              boardUI.notifyWinner(new Winner(Piece.GREEN, r, 1, r, 4));
                              break l2;
                         } else if (Human1 == 4) {
                              boardUI.notifyWinner(new Winner(Piece.BLUE, r, 1, r, 4));
                              break l2;
                         } else if (Ai1 != 4 & Human1 != 4 & existLegalMoves()) {
                              boardUI.notifyWinner(new Winner(Piece.EMPTY));
                         }
                    }
               }
          }
          //return null;
           return new Winner(Piece.EMPTY);
     }
}
