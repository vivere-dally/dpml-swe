package sbuciu.sudoku.model;

import java.util.Objects;

public class Pos {
    public final short r;
    public final short c;
    public final boolean noPos;

    public Pos(short r, short c) {
        this.r = r;
        this.c = c;
        noPos = false;
    }

    private Pos() {
        r = c = -1;
        noPos = true;
    }

    public static Pos noPos() {
        return new Pos();
    }

    @Override
    public String toString() {
        return String.format("P(%d,%d)", r, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return r == pos.r && c == pos.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}
