// Наследование от абстрактного класса ChessPiece.
class Rook extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public Rook(String color) {
        super(color);
    }

    // метод, для возврата цвета фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // метод, для проверки границы доски и отсутствия движения на месте, реализация движения "вертикали или по диагонали"
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // проверка выхода за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверяем, что ладья не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверяем движение по диагонали
        if (line != toLine && column != toColumn) {
            return false;
        }

        // проверяем путь движения по диагонали: наличие фигур на пути
        if (line == toLine) {
            int start = Math.min(column, toColumn) + 1;
            int end = Math.max(column, toColumn);

            for (int i = start; i < end; i++) {
                if (chessBoard.board[line][i] != null) {
                    return false;
                }
            }
        }

        // проверяем путь движения по вертикали:
        if (column == toColumn) {
            int start = Math.min(line, toLine) + 1;
            int end = Math.max(line, toLine);

            for (int i = start; i < end; i++) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        }


        // проверяем конечную позицию: запрет бить фигуру своего цвета, разрешено бить противника и ходить на пустые клетки
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return false;
        }
        return true;
    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "R";
    }
}

