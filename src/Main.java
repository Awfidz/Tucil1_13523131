import iqpuzzler.*;
import utils.*;
import java.io.*;
import java.util.*;


import java.nio.file.Path;  
import java.nio.file.Paths; 


public class Main {
    private static int iterations = 0;

    // Fungsi rekursif untuk mencoba solusi dengan brute force
    private static boolean solve(PuzzleBoard board, List<PuzzlePiece> piece, int index) {
        if (index == piece.size()) {
            if (board.isFull()) {
                board.displayBoard();
                return true;
            }
            return false;
        }

        PuzzlePiece currentPiece = piece.get(index);
        List<PuzzlePiece> orientations = currentPiece.getAllOrientations();

        for (PuzzlePiece orient : orientations) {
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    iterations++;
                    if (board.canPlacePuzzlePiece(orient, i, j)) {
                        board.placePuzzlePiece(orient, i, j, (char) ('A' + index));
                        if (solve(board, piece, index + 1)) return true;
                        board.removePuzzlePiece(orient, i, j); 
                    }
                }
            }
        }

        return false;
    }

    // Fungsi untuk menyimpan hasil ke file
    private static void saveResult(PuzzleBoard board, long startTime, long endTime, String fileName) {
        Path filePath = Paths.get("test", fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.write("Bentuk Akhir Papan:\n");
            board.saveToFile(writer);  // Menyimpan papan ke file

            double time = (endTime - startTime) * 1E-6;
            writer.write("\nWaktu Pencarian: " + String.format("%.3f", time) + " ms\n");
            writer.write("Jumlah Iterasi: " + iterations + "\n");

            System.out.println("Hasil " + fileName + " berhasil disimpan dalam folder test!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan hasil: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Scanner consoleScanner = new Scanner(System.in)) {
            System.out.print("Masukkan nama file test case (.txt): ");
            String fileName = consoleScanner.nextLine();
            // Menggunakan PuzzleFileReader untuk membaca file dan mengembalikan puzzle
            Puzzle puzzle = PuzzleFileReader.readPuzzleFile(fileName);

            PuzzleBoard board = new PuzzleBoard(puzzle.getN(), puzzle.getM());
            List<PuzzlePiece> pieces = new ArrayList<>();
            for (String shape : puzzle.getPuzzleShapes()) {
                pieces.add(new PuzzlePiece(shape));
            }

            // Mulai perhitungan waktu untuk mencari solusi
            long startTime = System.nanoTime();
            boolean result = solve(board, pieces, 0);
            long endTime = System.nanoTime();

            if (!result) System.out.println("Tidak ada solusi yang ditemukan.");
            System.out.printf("Waktu pencarian: %.3f ms\n", (endTime - startTime) * 1E-6);
            System.out.println("Jumlah iterasi: " + iterations);

            // Menanyakan kepada pengguna apakah ingin menyimpan hasil
            String input;
            while (true) {
                System.out.print("Apakah Anda ingin menyimpan hasil (y/n)? ");
                input = consoleScanner.nextLine().trim().toLowerCase();

                if ("y".equals(input)) {
                    System.out.print("Nama file simpanan: ");
                    String saveFileName = consoleScanner.nextLine();
                    saveResult(board, startTime, endTime, saveFileName + ".txt");
                    break; 
                } else if ("n".equals(input)) {
                    System.out.println("Program dihentikan.");
                    break; 
                } else {
                    System.out.println("Input tidak sesuai!");
                }
            }

        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
