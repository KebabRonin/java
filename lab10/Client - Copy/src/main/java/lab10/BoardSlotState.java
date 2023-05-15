package lab10;

public enum BoardSlotState {
    Empty,
    White,
    Black;

    @Override
    public String toString() {
        return switch (this) {
            case Empty -> "-----";
            case White -> "White";
            case Black -> "Black";
        };
    }
}
