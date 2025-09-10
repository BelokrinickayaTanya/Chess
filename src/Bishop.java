// Наследование от абстрактного класса ChessPiece.
class Bishop extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    // метод, для возврата цвета фигуры
    @Override
    public String getColor() {
        return this.color;
    }

    // метод, для проверки границы доски и отсутствия движения на месте, реализация движения "по диагонали"
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка выхода за границы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверяем, что слон не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // вычисляем смещение - абсолютные значения разницы координат
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);

        // основное правило для слона - движение вверх по диагонали
        if (deltaLine != deltaColumn) {
            return  false; // движение не по диагонали
        }

        // определяем направление движения:
        int lineDirection = (toLine > line) ? 1 : -1; // 1 = вниз; - 1 = вверх
        int columnDirection = (toColumn > column) ? 1 : -1; // 1 = вправо; - 1 = влево

        // проверяем путь: нет ли препятствий на диагонали
        int currentLine = line + lineDirection;
        int currentColumn = column + columnDirection;

        // проверяем пошагово все клетки на пути к целевой позиции
        while (currentLine != toLine && currentColumn != toColumn) {
            // если на пути фигура - ход не возможен
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // препятствие на пути
            }

            // переходим к следующей клетке по диагонали
            currentLine += lineDirection;
            currentColumn += columnDirection;
        }

        // проверяем целевую позицию: нельзя бить фигуру своего цвета
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return false; // в целевой клетке фигура того же цвета
        }
        return true; // все проверки пройдены: допускаем ход

    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "B";
    }
}
