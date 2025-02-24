package utils;

import java.util.List;

public class Puzzle {
    private int N, M, P;
    private String puzzleType;
    private List<String> puzzleShapes;

    public Puzzle(int N, int M, int P, String puzzleType, List<String> puzzleShapes) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.puzzleType = puzzleType;
        this.puzzleShapes = puzzleShapes;
    }

    // Getter untuk N, M, P dan puzzleShapes
    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getP() {
        return P;
    }

    public String getPuzzleType() {
        return puzzleType;
    }

    public List<String> getPuzzleShapes() {
        return puzzleShapes;
    }
}
