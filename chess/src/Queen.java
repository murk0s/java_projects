public class Queen  extends ChessPiece{
    public Queen(String color) {
        super(color);
    }
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!(checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn))) //проверка, что позиции в пределах поля
            return false;
        if (!checkEmptyOrOtherColor(chessBoard, toLine, toColumn)) //проверка, что в новой позиции либо пусто, либо противник
            return false;

        //взято из bishop *начало
        if (Math.abs(column - toColumn) == Math.abs(line - toLine) && column != toColumn) { //проверяем, что это движение по диагонали
            //определяем направления движения
            int dLine = (line < toLine) ? 1 : -1;
            int dColumn = (column < toColumn) ? 1 : -1;

            for (int i = 1; i < Math.abs(line - toLine); i++)
                if (chessBoard.board[line + dLine*i][column + dColumn*i] != null) //если хоть в какой-то позиции на пути есть фигура
                    return false;

            return true; //если цикл пройден, значит путь свободен
        }
        //*конец


        //взято из rook *начало
        if (column != toColumn && line == toLine) {
            int d = (column < toColumn) ? 1 : -1; //определяем направление движения
            for (int i = 1; i < Math.abs(column - toColumn); i++ )
                if (chessBoard.board[line][column + d*i] != null) //если хоть в какой-то позиции на пути есть фигура
                    return false;
            return true; //если цикл пройден, значит путь свободен
        }

        if (column == toColumn && line != toLine) {
            int d = (line < toLine) ? 1 : -1; //определяем направление движения
            for (int i = 1; i < Math.abs(line - toLine); i++ )
                if (chessBoard.board[line + d*i][column] != null) //если хоть в какой-то позиции на пути есть фигура
                    return false;
            return true; //если цикл пройден, значит путь свободен
        }
        //*конец


        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

}
