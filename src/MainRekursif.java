import java.util.Locale;
import java.io.OutputStream;
import java.io.PrintStream;

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
        

        // [DITAMBAHKAN] Dataset khusus untuk eksperimen n = 10.
        Lagu[] dataset10 = new Lagu[] {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 3.81),
            new Lagu("Yellow", "Coldplay", 4.29),
            new Lagu("Viva La Vida", "Coldplay", 4.52),
            new Lagu("Photograph", "Ed Sheeran", 4.18),
            new Lagu("The Scientist", "Coldplay", 5.09),
            new Lagu("Shape of You", "Ed Sheeran", 3.90),
            new Lagu("Paradise", "Coldplay", 4.01),
            new Lagu("Castle on the Hill", "Ed Sheeran", 4.21)
        };


        PlaylistRekursif app = new PlaylistRekursif(dataset5);
        Lagu[] list = app.getPlaylist();
        int n = list.length;


        //Menghitung total durasi
        double total = PlaylistRekursif.totalDurasi(list, n);

        double maxDurasi = PlaylistRekursif.cariDurasiTerpanjang(list, n - 1);
        Lagu laguMax = PlaylistRekursif.cariLaguTerpanjang(list, n - 1);

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
        System.out.println("\nPlaylist dari belakang:");
        PlaylistRekursif.tampilkanMundur(list, n - 1);
        // ===== Akhir Bagian Anggota 3 =====
        
     // [DITAMBAHKAN] Bagian pengukuran waktu dan growth rate.
        System.out.println("\n=== PENGUKURAN WAKTU EKSEKUSI ===");
        System.out.println("Waktu rata-rata per eksekusi (ms)");

        System.out.printf(
            "%-12s %-20s %-24s %-24s%n",
            "Jumlah Lagu",
            "totalDurasi",
            "tampilkanMundur",
            "cariDurasiTerpanjang"
        );

        // Menggunakan dataset yang sama dengan program utama.
        // Pastikan dataset mempunyai minimal 10 lagu.
        ujiPengukuran(dataset5, 3);
        ujiPengukuran(dataset5, 5);
        ujiPengukuran(dataset10, 10);

    }
    
 // [DITAMBAHKAN] Mengukur rata-rata waktu eksekusi fungsi.
    private static double ukurWaktuMs(Runnable tugas, int pengulangan) {
        // Pemanasan awal
        for (int i = 0; i < 100; i++) {
            tugas.run();
        }

        long mulai = System.nanoTime();

        for (int i = 0; i < pengulangan; i++) {
            tugas.run();
        }

        long selesai = System.nanoTime();

        return (selesai - mulai) / 1_000_000.0 / pengulangan;
    }

    // [DITAMBAHKAN] Mengukur ketiga fungsi untuk jumlah lagu tertentu.
    private static void ujiPengukuran(Lagu[] list, int n) {
        int pengulangan = 10000;

        double waktuTotal = ukurWaktuMs(
            () -> PlaylistRekursif.totalDurasi(list, n),
            pengulangan
        );

        double waktuTerpanjang = ukurWaktuMs(
            () -> PlaylistRekursif.cariDurasiTerpanjang(list, n - 1),
            pengulangan
        );

        // Menonaktifkan output sementara agar proses cetak
        // tidak mendominasi pengukuran reverse traversal.
        PrintStream outputAsli = System.out;
        double waktuMundur;

        try {
            System.setOut(
                new PrintStream(OutputStream.nullOutputStream())
            );

            waktuMundur = ukurWaktuMs(
                () -> PlaylistRekursif.tampilkanMundur(list, n - 1),
                pengulangan
            );
        } finally {
            System.setOut(outputAsli);
        }

        System.out.printf(
            "%-12d %-20.6f %-24.6f %-24.6f%n",
            n, waktuTotal, waktuMundur, waktuTerpanjang
        );
    }

}
