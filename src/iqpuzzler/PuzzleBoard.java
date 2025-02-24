package iqpuzzler;

import java.io.BufferedWriter;
import java.io.IOException;
import iqpuzzler.PuzzlePiece;

public class PuzzleBoard {
    private final char[][] grid;
    private int rows, cols;

    private static final String[] COLORS = 
    {
        "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m",
        "\u001B[36m", "\u001B[37m", "\u001B[90m", "\u001B[91m", "\u001B[92m",
        "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m", "\u001B[97m"
    };

    public PuzzleBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public boolean canPlacePuzzlePiece(PuzzlePiece piece, int row, int col) {
        char[][] shape = piece.getShape();
        int height = piece.getHeight();
        int width = piece.getWidth();

        if (row + height > rows || col + width > cols) return false;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j] != '.' && grid[row + i][col + j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public void placePuzzlePiece(PuzzlePiece piece, int row, int col, char pieceType) {
        char[][] shape = piece.getShape();
        int height = piece.getHeight();
        int width = piece.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j] != '.') {
                    grid[row + i][col + j] = pieceType;
                }
            }
        }
    }

    public void removePuzzlePiece(PuzzlePiece piece, int row, int col) {
        char[][] shape = piece.getShape();
        int height = piece.getHeight();
        int width = piece.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (shape[i][j] != '.') {
                    grid[row + i][col + j] = '.';
                }
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.') return false;
            }
        }
        return true;
    }

    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i][j];
                if (cell == '.') System.out.print("\u001B[0m" + cell + " ");
                else {
                    int index = (cell - 'A') % COLORS.length;
                    System.out.print(COLORS[index] + cell + " \u001B[0m");
                }
            }
            System.out.println();
        }
    }

    public void saveToFile(BufferedWriter writer) throws IOException {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                writer.write(grid[i][j] + " ");
            }
            writer.newLine();
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
