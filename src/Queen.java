// Наследование от абстрактного класса ChessPiece.
class Queen extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public Queen(String color) {
        super(color);
    }

    // метод, для возврата цвета фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // метод, для проверки границы доски и отсутствия движения на месте, реализация движения "по вертикали/горизонтали и по диагонали"
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // проверяем выход за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверяем, что ферзь не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверяем конечную позицию: запрет бить фигуру своего цвета
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return false;
        }

        // движение по вертикали по горизонтали
        boolean isStraightMove = (line == toLine || column == toColumn);

        // движение по диагонали
        boolean isDiagonalMove = (Math.abs(line - toLine) == Math.abs(column - toColumn));

        if (!isStraightMove && !isDiagonalMove) {
            return false;
        }

        // логика прямого движения
        if (isStraightMove) {
            // проверяем движение по горизонтали
            if (line == toLine) {

                // движение по горизонтали: определяем диапазон колонок для проверки
                int start = Math.min(column, toColumn) + 1;
                int end = Math.max(column, toColumn);

                // последовательно проверяем каждую клетку на пути
                for (int i = start; i < end; i++) {

                    // если на клетке [line][i] есть фигура
                    if (chessBoard.board[line][i] != null) {
                        return false; // ход по горизонтали не возможен
                    }
                }
            } else {
                // движение по вертикали: определяем диапазон линий для проверки
                int start = Math.min(line, toLine) +1;
                int end = Math.max(line, toLine);

                // проверяем движение по вертикали
                for (int i = start; i < end; i++) {
                    // если на клетке [i][column] есть фигура
                    if (chessBoard.board[i][column] != null) {
                        return false; // ход по вертикали не возможен
                    }
                }
            }
            return true; // ход по прямой возможен
        }

        // логика для диагонального движения
        if (isDiagonalMove) {

            // если целевая линия больше текущей, то движение вниз (направление +1); если меньше, то движение вверх (направление -1)
            int lineDirection = (toLine > line) ? 1 : -1;

            // если целевая колонка больше текущей, то движение вправо (направление +1); если меньше, то движение влево (направление -1)
            int columnDirection = (toColumn > column) ? 1 : -1;

            // проверка первой клетки после начальной
            int currentLine = line + lineDirection;
            int currentColumn = column + columnDirection;

            // проверка всех промежуточных клеток по диагонали: пока не достигли целевой линии и целевой колонки
            while (currentLine != toLine && currentColumn != toColumn) {

                // если на текущей промежуточной клетке есть любая фигура
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // ход не возможен
                }
                // переходим к следующей клетке по диагонали
                currentLine += lineDirection;
                currentColumn += columnDirection;
            }
            return true; // ход по диагонали возможен
        }
        return false;

    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "Q";
    }
}
