package Snake_Game;

import java.util.Objects;

public class Node {
    private final int row;
    private final int column;

    public Node(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.row && column == node.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
