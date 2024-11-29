public class King extends ChessPiece{

    public King(String color) {
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


        if (Math.abs(column - toColumn) == 1 && Math.abs(line - toLine) == 1 //проверка диагональ на 1
                        || Math.abs(column - toColumn) == 1 && line == toLine //проверка влево, вправо на 1
                        || column == toColumn && Math.abs(line - toLine) == 1) //проверка вверх, вниз на 1
            return true;
        else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column){
        for (int i = 0; i < board.board.length; i++)
            for (int j = 0; j < board.board[0].length; j++){
                if (board.board[i][j] == null) continue;
                if (i == line && j == line) continue;
                if (board.board[i][j].canMoveToPosition(board, i, j, line, column) && !board.board[i][j].getColor().equals(this.getColor())) return true;
            }
        return false;
    }
}
