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
    }

}
