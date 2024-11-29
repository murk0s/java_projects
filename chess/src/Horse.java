public class Horse extends ChessPiece{

    public Horse(String color) {
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

        if ((Math.abs(line-toLine)==1 && Math.abs(column-toColumn)==2 //проверка на движение коня
                || Math.abs(line-toLine)==2 && Math.abs(column-toColumn)==1)
        )
            return true;
        else return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
