# Tucil1_13523131
## a. Penjelasan Singkat Program
Program ini dirancang untuk menyelesaikan masalah puzzle menggunakan metode brute force murni. Program ini mengimplementasikan sebuah algoritma untuk mencoba semua kemungkinan penempatan potongan puzzle pada papan dengan berbagai rotasi dan refleksi hingga solusi ditemukan. Papan puzzle diwakili sebagai matriks dua dimensi, dan potongan puzzle diwakili dengan bentuk string yang kemudian diubah menjadi matriks boolean.

## b. Requirement Program dan Instalasi Tertentu Bila Ada
Program ini ditulis dalam bahasa pemrograman Java dan membutuhkan JDK untuk menjalankannya. 

Requirement:

-Java 8 atau lebih baru
-IDE atau editor teks seperti IntelliJ IDEA, Eclipse, atau Notepad++ untuk menulis dan mengedit kode.
-Command line interface (CLI) atau terminal untuk menjalankan program jika tidak menggunakan IDE.

## c. Cara Menjalankan dan Menggunakan Program
Untuk mengkompilasi dan menjalankan program, ikuti langkah-langkah berikut:

Buka terminal atau command prompt di direktori tempat file Java berada.

Pastikan Anda memiliki JDK yang terinstal dengan menjalankan perintah:

```bash
java -version
```
Jika JDK sudah terinstal, Anda akan melihat versi Java yang terpasang.

Kompilasi file Java dengan menggunakan perintah berikut:

```bash
javac javac -d bin src/iqpuzzler/*.java src/utils/*.java  src/Main.java
```
Perintah ini akan menghasilkan file Main.class beserta file PuzzleSolver.class, PuzzleBoard.class, dan kelas lainnya yang diperlukan.

Setelah berhasil dikompilasi, Anda dapat menjalankan program dengan perintah:

```bash
java -cp bin Main
```
