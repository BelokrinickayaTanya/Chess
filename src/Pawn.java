// Наследование от абстрактного класса ChessPiece.
class Pawn extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public Pawn(String color) {
        super(color);
    }

    // метод, для возврата цвета фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // метод, для проверки границы доски и отсутствия движения на месте, реализация движения "вперед по вертикали"
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверяем, что пешка не остается на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверяем попытку движения влево или вправо
        if (column != toColumn) {
            return false;
        }

        // вычисляем разницу в рядах для определения направления движения
        int deltaLine = toLine - line;

        // движение вверх по доске для белых пешек
        if ("White".equals(this.color)) {
            // ход на 1 клетку вперед
            if (deltaLine == 1) {
                return true;
            }

            // ход со стартовой позиции на 2 клетки вперед
            if (line == 1 && deltaLine == 2) {
                return true;
            }
        }

        // движение вниз по доске для черных пешек
        if ("Black".equals(this.color)) {
            // ход на 1 клетку вперед (отрицательное смещение)
            if (deltaLine == -1) {
                return true;
            }

            // ход со стартовой позиции на 2 клетки вперед
            if (line == 6 && deltaLine == -2) {
                return true;
            }
        }
        // случай - ход не возможен
        return false;

    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "P";
    }
}
