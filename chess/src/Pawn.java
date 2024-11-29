public class Pawn extends ChessPiece{
    public Pawn(String color) {
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


        if (isItMove(line, column, toLine, toColumn) && chessBoard.board[toLine][toColumn] == null){ //если ход это движение вперед и новая позиция пуста
            if (Math.abs(line - toLine) == 2) //если пешка с начальной позиции скачет через ряд, проверка, что с начальной позиции реализована в isItMove
                return (chessBoard.board[(line + toLine)/2][toColumn] == null) ? true : false; //если нет препятствий для прыжка через ряд
            else
                return true; //если пешка просто движется на клетку вперед, ничего не проверяем
        }

        if (isItKill(line, column, toLine, toColumn) && chessBoard.board[toLine][toColumn] != null) //если ход похож на атаку, проверяем, чтобы позиция была не пуста
            return (!chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) ? true : false; //и чтобы там был противник


        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    private boolean isItMove(int line, int column, int toLine, int toColumn) {//проверка, что пешка движется вперед
        if (column == toColumn && (
                color.equals("White") && (line - toLine == -1 || (line == 1 && toLine == 3) )
                        ||
                        (color.equals("Black") && (line - toLine == 1 || (line == 6 && toLine == 4)))))
            return true;
        else return false;
    }

    private boolean isItKill(int line, int column, int toLine, int toColumn) {//проверка, что пешка атакует
        if (Math.abs(column - toColumn) == 1 && (
                color.equals("White") && (line - toLine == -1 )
                        ||
                        (color.equals("Black") && (line - toLine == 1))))
            return true;
        else return false;
    }

}
