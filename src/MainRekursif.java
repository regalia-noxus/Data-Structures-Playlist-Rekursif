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

        // --- MENAMPILKAN OUTPUT SESUAI SPESIFIKASI SOAL ---
        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + n);
        System.out.printf("Total durasi : %.2f menit%n", total);
    }

}
