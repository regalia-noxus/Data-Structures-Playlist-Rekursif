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
    public static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0) { // Base case: semua lagu sudah ditampilkan.
            return;
        }

        list[index].tampilkanInfo();
        tampilkanMundur(list, index - 1); // Recursive case.
    }
    // ===== akkhir bagian anggota 3 =====
}
