import java.io.PrintStream;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (this.checkPos(startLine) && this.checkPos(startColumn)) {
            if (!this.nowPlayer.equals(this.board[startLine][startColumn].getColor())) {
                return false;
            } else if (!this.board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                return false;
            } else {
                if (this.board[startLine][startColumn].getSymbol().equals("K") || this.board[startLine][startColumn].getSymbol().equals("R")) {
                    this.board[startLine][startColumn].check = false;
                }

                this.board[endLine][endColumn] = this.board[startLine][startColumn];
                this.board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            }
        } else {
            return false;
        }
    }

    public void printBoard() {
        System.out.println("Turn " + this.nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for(int i = 7; i > -1; --i) {
            System.out.print("" + i + "\t");

            for(int j = 0; j < 8; ++j) {
                if (this.board[i][j] == null) {
                    System.out.print("..\t");
                } else {
                    PrintStream var10000 = System.out;
                    String var10001 = this.board[i][j].getSymbol();
                    var10000.print(var10001 + this.board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }

            System.out.println();
            System.out.println();
        }

        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (this.nowPlayer.equals("White")) {
            if (this.board[0][0] != null && this.board[0][4] != null) {
                if (this.board[0][0].getSymbol().equals("R") && this.board[0][4].getSymbol().equals("K") && this.board[0][1] == null && this.board[0][2] == null && this.board[0][3] == null) {
                    if (this.board[0][0].getColor().equals("White") && this.board[0][4].getColor().equals("White") && this.board[0][0].check && this.board[0][4].check && !(new King("White")).isUnderAttack(this, 0, 2)) {
                        this.board[0][4] = null;
                        this.board[0][1] = new King("White");
                        this.board[0][1].check = false;
                        this.board[0][0] = null;
                        this.board[0][2] = new Rook("White");
                        this.board[0][2].check = false;
                        this.nowPlayer = "Black";
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (this.board[7][0] != null && this.board[7][4] != null) {
            if (this.board[7][0].getSymbol().equals("R") && this.board[7][4].getSymbol().equals("K") && this.board[7][1] == null && this.board[7][2] == null && this.board[7][3] == null) {
                if (this.board[7][0].getColor().equals("Black") && this.board[7][4].getColor().equals("Black") && this.board[7][0].check && this.board[7][4].check && !(new King("Black")).isUnderAttack(this, 7, 2)) {
                    this.board[7][4] = null;
                    this.board[7][1] = new King("Black");
                    this.board[7][1].check = false;
                    this.board[7][0] = null;
                    this.board[7][2] = new Rook("Black");
                    this.board[7][2].check = false;
                    this.nowPlayer = "White";
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean castling7() {
        if (this.nowPlayer.equals("White")) {
            if (this.board[0][7] != null && this.board[0][4] != null) {
                if (this.board[0][7].getSymbol().equals("R") && this.board[0][4].getSymbol().equals("K") && this.board[0][6] == null && this.board[0][5] == null) {
                    if (this.board[0][7].getColor().equals("White") && this.board[0][4].getColor().equals("White") && this.board[0][7].check && this.board[0][4].check && !(new King("White")).isUnderAttack(this, 0, 6)) {
                        this.board[0][4] = null;
                        this.board[0][6] = new King("White");
                        this.board[0][6].check = false;
                        this.board[0][7] = null;
                        this.board[0][5] = new Rook("White");
                        this.board[0][5].check = false;
                        this.nowPlayer = "Black";
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (this.board[7][7] != null && this.board[7][4] != null) {
            if (this.board[7][7].getSymbol().equals("R") && this.board[7][4].getSymbol().equals("K") && this.board[7][6] == null && this.board[7][5] == null) {
                if (this.board[7][7].getColor().equals("Black") && this.board[7][4].getColor().equals("Black") && this.board[7][7].check && this.board[7][4].check && !(new King("Black")).isUnderAttack(this, 7, 6)) {
                    this.board[7][4] = null;
                    this.board[7][6] = new King("Black");
                    this.board[7][6].check = false;
                    this.board[7][7] = null;
                    this.board[7][5] = new Rook("Black");
                    this.board[7][5].check = false;
                    this.nowPlayer = "White";
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
