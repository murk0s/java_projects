abstract public class ChessPiece {
    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract public String getColor();

    abstract public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract public String getSymbol();

    protected boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }


    protected boolean checkEmptyOrOtherColor(ChessBoard chessBoard, int toLine, int toColumn) {
        if (chessBoard.board[toLine][toColumn] == null)
            return true;
        else //если позиция не пуста, проверяем, чтобы был противник
            return (!chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) ? true : false;

    }

}