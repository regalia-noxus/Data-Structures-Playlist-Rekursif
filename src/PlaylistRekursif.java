public class PlaylistRekursif {
    private Lagu[] playlist;

    // Constructor untuk menginisialisasi playlist
    public PlaylistRekursif(Lagu[] playlist) {
        this.playlist = playlist;
    }

    // Getter untuk array playlist
    public Lagu[] getPlaylist() {
        return playlist;
    }

    // Fungsi rekursif untuk menghitung total durasi lagu dalam playlist
    public static double totalDurasi(Lagu[] list, int n) {
        if (list == null || n <= 0) {
            return 0.0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    // ===== Bagian Anggota 3: Reverse Traversal Rekursif =====
    // Menampilkan lagu dari indeks terakhir ke pertama dengan kompleksitas O(n).
    // menggunakan rekursi dengan kompleksitas waktu O(n).
    public static void tampilkanMundur(Lagu[] list, int index) {
        // Base case: berhenti jika indeks sudah kurang dari 0.
        if (list == null || index < 0) {
            return;
        }

        // Menampilkan nomor, judul, dan artis lagu.
        System.out.println(
            (index + 1) + ". " +
            list[index].getJudul() + " - " +
            list[index].getArtis()
        );

        // Recursive case: berpindah ke indeks sebelumnya.
        tampilkanMundur(list, index - 1);
    }
    // ===== akkhir bagian anggota 3 =====

    //mencari nilai durasi terpanjang di dalam playlist secara rekursif
    public static double cariDurasiTerpanjang(Lagu[] list, int index) {
        //mengecek list lagu
        if (list == null || list.length == 0 || index < 0) {
            return 0.0;
        }
        //base case
        if (index == 0) {
            return list[0].getDurasi();
        }
        //recursive case
        double maxSebelumnya = cariDurasiTerpanjang(list, index - 1);
        return Math.max(list[index].getDurasi(), maxSebelumnya);
    }

    //mencari objek Lagu yang memiliki durasi terpanjang secara rekursif
    //sehingga dapat menampilkan judul dan nama artis pada output.
    public static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (list == null || list.length == 0 || index < 0) {
            return null;
        }
        // Base case
        if (index == 0) {
            return list[0];
        }
        // Recursive case
        Lagu maxSebelumnya = cariLaguTerpanjang(list, index - 1);
        if (list[index].getDurasi() >= maxSebelumnya.getDurasi()) {
            return list[index];
        } else {
            return maxSebelumnya;
        }
    }
}
