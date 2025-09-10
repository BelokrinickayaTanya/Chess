// Наследование от абстрактного класса ChessPiece.
class King extends ChessPiece {

    // конструктор, который принимает цвет фигуры
    public King(String color) {
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

        // проверяем, что король не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверяем конечную позицию: запрет бить фигуру своего цвета
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return  false;
        }

        // вычисляем разницу координат: определяем расстояние перемещения по вертикали и горизонтали
        int lineDiff = Math.abs(line - toLine);
        int columnDiff = Math.abs(column - toColumn);

        // основная логика движения короля: движение на одну клетку в любом направлении, исключаем стояние на месте
        if ((lineDiff <= 1 && columnDiff <=1) && (lineDiff != 0 || columnDiff != 0)) {
            return !isUnderAttack(chessBoard, toLine, toColumn);
        }
        return false;
    }

    // метод для проверки, находится ли указанная клетка под атакой вражеских фигур
    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        // перебираем все линии доски
        for (int i = 0; i < 8; i++) {
            // перебираем все колонки в текущей линии
            for (int j = 0; j < 8; j++) {
                // получаем фигуру из текущей клетки [i][j]
                ChessPiece piece = board.board[i][j];
                // проверяем наличие фигуры и ее цвет
                if (piece != null && !piece.getColor().equals(this.color)) {
                    // проверяем может дти вражеская фигура атаковать целевую клетку
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true; // клетка под атакой
                    }
                }
            }
        }
        return false; // клетка безопасна
    }

    // метод возвращает символ фигуры
    @Override
    public String getSymbol() {
        return "K";
    }
}
