import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasOnline {
    static String[] namaMahasiswa = { "Azza", "Angga", "Rio" },
            namaLengkap = { "Azzahra Attaqina", "Moh Angga Tegar Primadana", "Rio Tri Prayogo" },
            nimMahasiswa = { "00001", "00002", "00003" },
            jkMahasiswa = { "Perempuan", "Laki-laki", "Laki-laki" };
    static int[] jmlKasMhs = { 15, 6, 10 }, riwayatTotal = { 0, 0 }, hutangKas = new int[3];
    static boolean[] saveDenda = { true, false, true };
    static String[][] riwayatTransaksi = new String[3][100];
    static int totalKas = 100000, kasBulanFull = 20000, kasBulanReal = 10000, jumlahKas = 12, kesempatan = 3;
    static Scanner scan = new Scanner(System.in);

    static String formatWaktu() {
        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return waktu.format(format);
    }

    static boolean login() {
        String username, password;

        do {
            System.out.println("Silahkan Login");
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan Password: ");
            password = scan.nextLine();

            if (username.equals("mahasiswa") && password.equals("123")) {
                System.out.println("\nLogin berhasil");
                kesempatan = 3;
                return true;
            } else {
                kesempatan--;
                System.out.println("--------------------------");
                System.out.println("Login gagal.\nSisa percobaan: " + kesempatan);
                System.out.println("--------------------------");
            }

        } while (kesempatan > 0);

        return false;
    }

    static int inputNama(String[] namaMahasiswa) {
        String cekNama = ("");
        boolean cek = false;
        int index = -1;
        while (!cek && kesempatan >= 1) {
            System.out.println("Masukkan nama: ");
            cekNama = scan.nextLine();

            for (int i = 0; i < namaMahasiswa.length; i++) {
                if (namaMahasiswa[i].equals(cekNama)) {
                    System.out.println("Data mahasiswa ditemukan: " + namaMahasiswa[i]);
                    System.out.println("--------------------------");
                    cek = true;
                    index = i;
                    break;
                }
            }
            if (!cek) {
                kesempatan--;
                System.out.println("Data mahasiswa tidak ditemukan dalam database.");
                System.out.println("Sisa percobaan: " + kesempatan);
                System.out.println("--------------------------");
            }
        }
        if (kesempatan == 0) {
            System.out.println("Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
            return -1;
        } else {
            kesempatan = 3;
            return index;
        }
    }

    static int simpanRiwayat(String[] simpan) {
        int indexRiwayat = 0;
        for (String input : simpan) {
            if (input == null) {
                break;
            }
            indexRiwayat++;
        }
        return indexRiwayat;
    }

    static void penarikanKas() {
        int index = inputNama(namaMahasiswa);
        if (index == -1) {
            return;
        }

        while (kesempatan >= 1) {
            System.out.println("Masukkan jumlah yang ditarik: ");
            if (scan.hasNextInt()) {
                int kasKeluar = scan.nextInt();
                scan.nextLine();

                System.out.println("Masukkan Alasan: ");
                String alasanTarik = scan.nextLine();
                System.out.println("--------------------------");

                if (kasKeluar <= 0) {
                    System.out.println("Jumlah yang ditarik harus lebih dari 0");
                } else if (kasKeluar > totalKas) {
                    System.out.println("Maaf, jumlah yang ditarik melebihi total kas yang tersedia");
                } else {
                    totalKas -= kasKeluar;
                    System.out.println("Mahasiswa yang meminjam kas: " + namaMahasiswa[index]);
                    System.out.println("Kas yang ditarik: " + kasKeluar);
                    System.out.println("Alasan penarikan: " + alasanTarik);
                    System.out.println("Waktu penarikan: " + formatWaktu());
                    System.out.println("Jumlah total kas setelah penarikan: " + totalKas);

                    int indexRiwayat = simpanRiwayat(riwayatTransaksi[index]);
                    riwayatTransaksi[index][indexRiwayat] = "Penarikan kas = " + kasKeluar + " - "
                            + alasanTarik + " - " + formatWaktu();
                    riwayatTotal[0] += kasKeluar;
                    hutangKas[index] += kasKeluar;
                    break;
                }
            } else {
                System.out.println("Masukkan jumlah yang valid (angka bulat)");
                scan.nextLine();
            }

            kesempatan--;
            if (kesempatan == 0) {
                System.out.println("Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
            }
        }
    }

    static void pembayaranKas() {
        int index = inputNama(namaMahasiswa);
        boolean cek = false;
        if (index == -1) {
            return;
        }
        while (!cek && kesempatan >= 1) {
            System.out.println("Masukkan jumlah uang yang ditambahkan: ");
            int kasMasuk = scan.nextInt();

            if (kasMasuk % 5000 == 0) {
                cek = true;
                totalKas += kasMasuk;
                jmlKasMhs[index] += kasMasuk / 5000;
                kasBulanReal += kasMasuk;

                System.out.println("--------------------------");
                System.out.println("Mahasiswa yang membayar kas: " + namaMahasiswa[index]);
                System.out.println("Kas yang ditambah: " + kasMasuk);
                System.out.println("Waktu penambahan: " + formatWaktu());
                System.out.println("Pembayaran yang dilakukan mahasiswa keseluruhan: "
                        + jmlKasMhs[index]);
                System.out.println("--------------------------");

                if (jmlKasMhs[index] >= 8) {
                    if (jmlKasMhs[index] > jumlahKas) {
                        System.out.println("Lunas, tidak perlu membayar denda");
                    } else {
                        System.out.println("Belum Lunas, tetapi tidak perlu membayar denda");
                    }
                } else {
                    System.out.println("Belum Lunas, dan perlu membayar denda 5000");
                }

                System.out.println("--------------------------");
                System.out.println("Kas asli bulan ini setelah ditambah: " + kasBulanReal);
                System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                System.out.println("Jumlah kas setelah penambahan: " + totalKas);
            } else {
                System.out.println("Uang harus kelipatan 5000");
            }

            int indexRiwayat = simpanRiwayat(riwayatTransaksi[index]);
            riwayatTransaksi[index][indexRiwayat] = "Penambahan kas = " + kasMasuk + " - "
                    + formatWaktu();
            riwayatTotal[1] += kasMasuk;
            kesempatan--;
            if (kesempatan == 0) {
                System.out.println("Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
            }
        }
    }

    static void riwayatKas() {
        System.out.println("Pilih Menu:");
        System.out.println("1. Riwayat Mahasiswa");
        System.out.println("2. Riwayat Total");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu dalam (1/2/0): ");
        int pilihan = scan.nextInt();

        switch (pilihan) {
            case 1:
                scan.nextLine();
                int index = inputNama(namaMahasiswa);
                if (index == -1) {
                    return;
                }
                System.out.println("Cetak Riwayat Transaksi untuk setiap pengguna");
                System.out.println(namaMahasiswa[index]);
                for (int i = 0; i < riwayatTransaksi[index].length; i++) {
                    if (riwayatTransaksi[index][i] != null) {
                        System.out.println(riwayatTransaksi[index][i]);
                    }
                }
                System.out.println();
                break;

            case 2:
                System.out.println("Cetak Semua Riwayat Transaksi");
                System.out.println("Total kas yang telah ditarik hari ini: " + riwayatTotal[0]);
                System.out.println("Total kas yang telah ditambahkan hari ini: " + riwayatTotal[1]);
                break;

            case 0:
                break;

            default:
                System.out.println("Pilihan tidak valid");
                break;
        }
    }

    static void pembayaranDenda() {
        int index = inputNama(namaMahasiswa);
        boolean cek = false;
        if (index == -1) {
            return;
        }
        if (saveDenda[index] == true) {
            System.out.println("Anda tidak memiliki tanggungan denda\nKeluar fitur");
        } else if (saveDenda[index] == false && jmlKasMhs[index] <= 4) {
            System.out.println("Anda memiliki tanggungan denda");
            System.out.println("--------------------------");
            System.out.println("Lunasi dahulu minimal 2 bulan untuk membayar denda");
        } else {
            System.out.println("Anda memiliki tanggungan denda");
            System.out.println("--------------------------");
            System.out.println("Denda bisa dibayarkan");
            System.out.println("Apakah anda ingin membayar denda? y/n");
            String pilih = scan.nextLine();

            if (pilih.equalsIgnoreCase("y")) {
                while (!cek) {
                    System.out.println("Masukkan nominal denda Rp. 5000");
                    int bayarDenda = scan.nextInt();
                    if (bayarDenda == 5000) {
                        saveDenda[index] = true;
                        System.out.println("Denda telah dibayarkan, Terima Kasih");

                        int indexRiwayat = simpanRiwayat(riwayatTransaksi[index]);
                        riwayatTransaksi[index][indexRiwayat] = "Pembayaran Denda = " + bayarDenda + " - "
                                + formatWaktu();
                        totalKas += bayarDenda;
                        kasBulanReal += bayarDenda;
                        cek = true;
                    } else {
                        System.out.println("Nominal pembayaran harus Rp. 5000");
                        System.out.println("Apakah anda ingin mencoba lagi? y/n");
                        scan.nextLine();
                        String cobaLagi = scan.nextLine();
                        if (cobaLagi.equalsIgnoreCase("n")) {
                            cek = true;
                        } else if (cobaLagi.equalsIgnoreCase("y")) {
                            continue;
                        } else {
                            System.out.println("Input tidak sesuai\nKembali ke menu awal");
                            cek = true;
                        }
                    }
                }
            } else if (pilih.equalsIgnoreCase("n")) {
                System.out.println("Baiklah, kembali ke menu");
            }
        }
    }

    static void dataMahasiswa() {
        int index = inputNama(namaMahasiswa);
        if (index == -1) {
            return;
        }
        System.out.println("Nama mahasiswa\t\t: " + namaLengkap[index]);
        System.out.println("NIM\t\t\t: " + nimMahasiswa[index]);
        System.out.println("Jenis kelamin\t\t: " + jkMahasiswa[index]);
        System.out.println("Kas yang telah dibayar\t: " + jmlKasMhs[index] + "/" + jumlahKas);

        if (saveDenda[index]) {
            System.out.println("Tidak ada tanggungan denda");
        } else {
            System.out.println("Ada tanggungan denda");
        }
        System.out.println("--------------------------");
    }

    static void pembayaranHutang() {
        int index = inputNama(namaMahasiswa);
        boolean cek = false;
        if (index == -1) {
            return;
        }
        if (hutangKas[index] == 0) {
            System.out.println("Anda tidak memiliki hutang kas");
        } else {
            System.out.println("Hutang kas anda: " + hutangKas[index]);
            System.out.println("Apakah anda ingin membayar hutang? y/n");
            String pilih = scan.nextLine();

            if (pilih.equalsIgnoreCase("y")) {
                while (!cek) {
                    System.out.println("Masukkan nominal pembayaran: ");
                    int bayarHutang = scan.nextInt();

                    if (bayarHutang <= 0 || bayarHutang > hutangKas[index]) {
                        System.out.println(
                                "Nominal yang anda masukkan tidak sesuai.\nMasukkan nominal yang sesuai");
                        System.out.println("Hutang kas anda: " + hutangKas[index]);
                    } else if (bayarHutang == hutangKas[index] || bayarHutang <= hutangKas[index]) {
                        int indexRiwayat = simpanRiwayat(riwayatTransaksi[index]);
                        riwayatTransaksi[index][indexRiwayat] = "Pembayaran Hutang = " + bayarHutang + " - "
                                + formatWaktu();
                        hutangKas[index] -= bayarHutang;
                        totalKas += bayarHutang;
                        System.out.println("Pembayaran berhasil");
                        System.out.println("Sisa hutang anda: " + hutangKas[index]);
                        if (hutangKas[index] > 0) {
                            System.out.println("Apakah anda ingin membayar lagi? y/n");
                            scan.nextLine();
                            String cobaLagi = scan.nextLine();
                            if (cobaLagi.equalsIgnoreCase("n")) {
                                cek = true;
                            } else if (cobaLagi.equalsIgnoreCase("y")) {
                                continue;
                            } else {
                                System.out.println("Input tidak sesuai\nKembali ke menu awal");
                                cek = true;
                            }
                        }
                        cek = true;
                    }
                }
            } else if (pilih.equalsIgnoreCase("n")) {
                System.out.println("Baiklah, kembali ke menu");
            }
        }
    }

    public static void main(String[] args) {
        if (login()) {
            boolean run = true;
            do {
                System.out.println("\nSelamat Datang di Program Kas Online!");
                System.out.println("--------------------------");
                System.out.println("Total kas: " + totalKas);
                System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                System.out.println("Kas asli bulan ini: " + kasBulanReal);
                System.out.println("--------------------------");
                System.out.println("Pilih Menu:");
                System.out.println("1. Penarikan Kas");
                System.out.println("2. Pembayaran Kas");
                System.out.println("3. Riwayat Kas");
                System.out.println("4. Pembayaran Denda");
                System.out.println("5. Data Mahasiswa");
                System.out.println("6. Bayar Hutang");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu dalam (1/2/3/4/5/6/0): ");
                int pilihan = scan.nextInt();

                switch (pilihan) {
                    case 1:
                        System.out.println("\nSelamat datang di Fitur Penarikan Kas");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan mahasiswa untuk menarik kas\nSetiap kas yang ditarik akan masuk ke dalam hutang mahasiswa tersebut");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        penarikanKas();
                        break;

                    case 2:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Kas");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan mahasiswa untuk membayar kas\nSetiap kas yang dibayar akan masuk ke dalam riwayat kas mahasiswa tersebut");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        pembayaranKas();
                        break;

                    case 3:
                        System.out.println("\nSelamat Datang di Fitur Riwayat Kas!");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk melihat riwayat kas\nTerdapat 2 fitur riwayat yaitu riwayat mahasiswa dan riwayat total");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        riwayatKas();
                        break;

                    case 4:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Denda");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk membayar denda jika tidak melakukan pembayaran kas selama 1 bulan\nDenda yang harus dibayarkan sejumlah Rp 5.000");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        pembayaranDenda();
                        break;

                    case 5:
                        System.out.println("\nSelamat datang di Fitur Data Mahasiswa");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk melihat data dari masing-masing mahasiswa\nInformasi berupa seperti nama, NIM, jenis kelamin, total kas bayar selama 1 tahun, dan status tanggungan denda.");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        dataMahasiswa();
                        break;

                    case 6:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Hutang");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk pembayaran hutang\nHanya berlaku untuk mahasiswa yang memiliki hutang");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        pembayaranHutang();
                        break;

                    default:
                        System.out.println("Pilihan tidak valid");
                        break;

                    case 0:
                        System.out.println("Terima Kasih telah menggunakan Kas Online!");
                        run = false;
                        break;

                }
            } while (run);
        } else {
            System.out.println("Jumlah Percobaan Anda Telah Habis\nKeluar");
        }
        scan.close();
    }
}