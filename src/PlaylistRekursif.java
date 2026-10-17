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
}
