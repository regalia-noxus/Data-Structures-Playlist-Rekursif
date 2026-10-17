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

        //Pengukuran waktu cariDurasiTerpanjang() dan pencarian objek lagu terpanjang
        long startMax = System.nanoTime();
        double maxDurasi = PlaylistRekursif.cariDurasiTerpanjang(list, n - 1);
        Lagu laguMax = PlaylistRekursif.cariLaguTerpanjang(list, n - 1);
        long endMax = System.nanoTime();
        long timeMaxMs = Math.round((endMax - startMax) / 1_000_000.0);

        //tampilkan waktu eksekusi masing-masing operasi
        System.out.println("Execution Time (cariDurasiTerpanjang): " + timeMaxMs + " ms");

        //Menghitung total durasi
        double total = PlaylistRekursif.totalDurasi(list, n);

        // --- MENAMPILKAN OUTPUT SESUAI SPESIFIKASI SOAL ---
        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + n);
        System.out.printf("Total durasi : %.2f menit%n", total);

        // ===== Bagian Anggota 3: Reverse Traversal Rekursif =====
        System.out.println("\nPlaylist dari belakang:");
        PlaylistRekursif.tampilkanMundur(list, n - 1);
        // ===== Akhir Bagian Anggota 3 =====
    }

}
