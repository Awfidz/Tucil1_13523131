package iqpuzzler;

import java.util.*;

public class PuzzlePiece {
    private char[][] pieceShape;
    private int height;
    private int width;

    public PuzzlePiece(String shapeRepresentation) {
        String[] lines = shapeRepresentation.split("\n");
        this.height = lines.length;
        this.width = 0;
        for (String line : lines) {
            if (line.length() > width) {
                width = line.length();
            }
        }
        width -= 1;
        pieceShape = new char[height][width];

        for (int i = 0; i < height; i++) {
            String line = lines[i];
            for (int j = 0; j < width; j++) {
                try {
                    char cc = line.charAt(j);
                    if (cc >= 65 && cc <= 90) pieceShape[i][j] = cc;
                    else pieceShape[i][j] = '.';
                } catch (Exception e) {
                    pieceShape[i][j] = '.';
                }
            }
        }
    }

    public PuzzlePiece(char[][] shape) {
        this.height = shape.length;
        this.width = shape[0].length;
        pieceShape = new char[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(shape[i], 0, pieceShape[i], 0, width);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getShape() {
        return pieceShape;
    }

    public PuzzlePiece rotate() {
        int newHeight = width;
        int newWidth = height;
        char[][] newShape = new char[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                newShape[i][j] = pieceShape[height - 1 - j][i];
            }
        }
        return new PuzzlePiece(newShape);
    }

    public PuzzlePiece mirror() {
        char[][] newShape = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newShape[i][j] = pieceShape[i][width - 1 - j];
            }
        }
        return new PuzzlePiece(newShape);
    }

    public List<PuzzlePiece> getAllOrientations() {
        List<PuzzlePiece> orientations = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        PuzzlePiece current = this;
        for (int i = 0; i < 4; i++) {
            String rep = current.toString();
            if (!seen.contains(rep)) {
                orientations.add(current);
                seen.add(rep);
            }
            PuzzlePiece mirrored = current.mirror();
            rep = mirrored.toString();
            if (!seen.contains(rep)) {
                orientations.add(mirrored);
                seen.add(rep);
            }
            current = current.rotate();
        }
        return orientations;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            sb.append(new String(pieceShape[i]));
            if (i < height - 1) sb.append("\n");
        }
        return sb.toString();
    }
}
