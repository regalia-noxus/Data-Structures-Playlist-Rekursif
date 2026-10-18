import java.util.Locale;

public class MainRekursif {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Lagu[] dataset5 = new Lagu[] {
                new Lagu("Perfect", "Ed Sheeran", 4.23),
                new Lagu("Shivers", "Ed Sheeran", 3.50),
                new Lagu("Fix You", "Coldplay", 3.81),
                new Lagu("Yellow", "Coldplay", 4.29),
                new Lagu("Viva La Vida", "Coldplay", 4.52)
        };

        PlaylistRekursif app = new PlaylistRekursif(dataset5);
        Lagu[] list = app.getPlaylist();
        int n = list.length;

        //Menghitung total durasi
        long startTotal = System.nanoTime();
        double total = PlaylistRekursif.totalDurasi(list, n);
        long endTotal = System.nanoTime();
        long timeTotalMs = Math.round((endTotal - startTotal) / 1_000_000.0);

        //cariDurasiTerpanjang() dan pencarian objek lagu terpanjang
        long startMax = System.nanoTime();
        double maxDurasi = PlaylistRekursif.cariDurasiTerpanjang(list, n - 1);
        Lagu laguMax = PlaylistRekursif.cariLaguTerpanjang(list, n - 1);
        long endMax = System.nanoTime();
        long timeMaxMs = Math.round((endMax - startMax) / 1_000_000.0);

        // --- MENAMPILKAN OUTPUT SESUAI SPESIFIKASI SOAL ---
        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + n);
        System.out.printf("Total durasi : %.2f menit%n", total);

        if (laguMax != null) {
            System.out.printf("Lagu terpanjang : \"%s\" - %s (%.2f menit)%n",
                    laguMax.getJudul(), laguMax.getArtis(), maxDurasi);
        } else {
            System.out.printf("Lagu terpanjang : %.2f menit%n", maxDurasi);
        }

        // ===== Bagian Anggota 3: Reverse Traversal Rekursif =====
        long startReverse = System.nanoTime();
        PlaylistRekursif.tampilkanMundur(list, n - 1);
        long endReverse = System.nanoTime();
        long timeReverseMs = Math.round((endReverse - startReverse) / 1_000_000.0);
        // ===== Akhir Bagian Anggota 3 =====

        System.out.println("");
        // Tampilkan waktu eksekusi masing-masing operasi
        System.out.println("Execution Time (totalDurasi): " + timeTotalMs + " ms");
        System.out.println("Execution Time (tampilkanMundur): " + timeReverseMs + " ms");
        System.out.println("Execution Time (cariDurasiTerpanjang): " + timeMaxMs + " ms");

        //Pengukuran waktu untuk n = 3, 5, 10 ---
        System.out.println();
        jalankanEksperimenWaktu();
    }

    public static void jalankanEksperimenWaktu() {
        System.out.println("====================================================================================");
        System.out.println("              TABEL EKSPERIMEN PENGUKURAN WAKTU EKSEKUSI (n = 3, 5, 10)             ");
        System.out.println("====================================================================================");

        // Dataset master 10 lagu
        Lagu[] masterData = new Lagu[] {
                new Lagu("Perfect", "Ed Sheeran", 4.23),
                new Lagu("Shivers", "Ed Sheeran", 3.50),
                new Lagu("Fix You", "Coldplay", 3.81),
                new Lagu("Yellow", "Coldplay", 4.29),
                new Lagu("Viva La Vida", "Coldplay", 4.52),
                new Lagu("Someone Like You", "Adele", 4.45),
                new Lagu("Bohemian Rhapsody", "Queen", 5.55),
                new Lagu("Photograph", "Ed Sheeran", 4.19),
                new Lagu("The Scientist", "Coldplay", 5.09),
                new Lagu("Shape of You", "Ed Sheeran", 3.53)
        };

        int[] ukuranArray = {3, 5, 10};

        // Warm-up JVM agar JIT compiler telah mengoptimasi bytecode sebelum benchmarking
        for (int i = 0; i < 50_000; i++) {
            PlaylistRekursif.totalDurasi(masterData, 5);
            PlaylistRekursif.cariDurasiTerpanjang(masterData, 4);
            tampilkanMundurQuiet(masterData, 4);
        }

        System.out.printf("%-17s | %-15s | %-18s | %-22s%n",
                "Jumlah Lagu (n)", "totalDurasi()", "tampilkanMundur()", "cariDurasiTerpanjang()");
        System.out.println("------------------------------------------------------------------------");

        for (int ukuran : ukuranArray) {
            Lagu[] subList = new Lagu[ukuran];
            System.arraycopy(masterData, 0, subList, 0, ukuran);

            // menggunakan batch iteration agar terukur dalam skala milidetik dan memperlihatkan growth rate linear O(n)
            int iterasi = 150_000;

            // Ukur totalDurasi()
            long startTotal = System.nanoTime();
            for (int i = 0; i < iterasi; i++) {
                PlaylistRekursif.totalDurasi(subList, ukuran);
            }
            long endTotal = System.nanoTime();
            long msTotal = Math.round((endTotal - startTotal) / 1_000_000.0);

            // Ukur tampilkanMundurQuiet()
            long startReverse = System.nanoTime();
            for (int i = 0; i < iterasi; i++) {
                tampilkanMundurQuiet(subList, ukuran - 1);
            }
            long endReverse = System.nanoTime();
            long msReverse = Math.round((endReverse - startReverse) / 1_000_000.0);

            // Ukur cariDurasiTerpanjang()
            long startMax = System.nanoTime();
            for (int i = 0; i < iterasi; i++) {
                PlaylistRekursif.cariDurasiTerpanjang(subList, ukuran - 1);
            }
            long endMax = System.nanoTime();
            long msMax = Math.round((endMax - startMax) / 1_000_000.0);

            System.out.printf("%-17d | %-15s | %-18s | %-22s%n",
                    ukuran, msTotal + " ms", msReverse + " ms", msMax + " ms");
        }
        System.out.println("====================================================================================");
    }
    
    //Versi traversal rekursif tanpa mencetak ke console untuk keperluan benchmarking murni
    private static void tampilkanMundurQuiet(Lagu[] list, int index) {
        if (list == null || index < 0 || index >= list.length) {
            return;
        }
        // Operasi baca data
        list[index].getJudul();
        tampilkanMundurQuiet(list, index - 1);
    }

}
