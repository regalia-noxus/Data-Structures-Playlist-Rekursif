// class lagu berfungsi untuk merepresentasikan entitas data lagu
public class Lagu {
    // Enkapsulasi: Atribut dibuat private agar tidak bisa diakses langsung dari luar class
    private String judul;
    private String artis;
    private double durasi;

    // Constructor untuk menginisialisasi atribut objek Lagu saat dibuat
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter dan Setter untuk mengakses serta mengubah nilai atribut private
    // Getter untuk mengambil nilai judul lagu.
    public String getJudul() {
        return judul;
    }

    // Setter untuk mengubah nilai judul lagu.
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter untuk mengambil nama artis.
    public String getArtis() {
        return artis;
    }

    // Setter untuk mengubah nama artis.
    public void setArtis(String artis) {
        this.artis = artis;
    }

    // Getter untuk mengambil durasi lagu.
    public double getDurasi() {
        return durasi;
    }

    // Setter untuk mengubah durasi lagu
    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method untuk menampilkan detail informasi lagu
    public void tampilkanInfo() {
        System.out.println("- " + judul + " | Artis: " + artis + " | Durasi: " + durasi + " menit");
    }
}
