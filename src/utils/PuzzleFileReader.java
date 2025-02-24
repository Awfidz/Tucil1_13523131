package utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PuzzleFileReader {

    public static Puzzle readPuzzleFile(String fileName) throws IOException {
        Path filePath = Paths.get("input", fileName);
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        
        String[] firstLine = reader.readLine().split(" ");
        int rows = Integer.parseInt(firstLine[0]);
        int cols = Integer.parseInt(firstLine[1]);
        int piecesCount = Integer.parseInt(firstLine[2]);
        
        String puzzleType = reader.readLine().trim();
        List<String> puzzlePieces = new ArrayList<>();
        StringBuilder pieceShape = new StringBuilder();
        int pieceCounter = 0;

        while (pieceCounter < piecesCount) {
            String line = reader.readLine();
            if (line == null) {
                puzzlePieces.add(pieceShape.toString().trim());
                break;
            }
            pieceShape.append(line).append(System.lineSeparator());
            pieceCounter++;
        }
        reader.close();
        return new Puzzle(rows, cols, piecesCount, puzzleType, puzzlePieces);
    }
}
