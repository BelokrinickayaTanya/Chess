// Наследование от абстрактного класса ChessPiece.
class Horse extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public Horse(String color) {
        super(color);
    }

    // метод, для возврата цвета фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // метод, для проверки границы доски и отсутствия движения на месте, реализация движения буквой "Г"
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // проверяем выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        // проверяем, что конь не остается на месте
        if (line == toLine && column == toColumn) {
            return false;
        }
        // вычисляем разницу в координатах
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);

        // конь ходит буквой "Г": 2+1 или 1+2 клетки
        return (deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2);

    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "H";
    }
}
