import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasOnline {
    static int inputNama(String[] namaMahasiswa) {
        Scanner scan = new Scanner(System.in);
        String cekNama = " ";
        int kesempatan = 3;
        boolean sesuai = false;
        int index = -1;
        while (!sesuai && kesempatan >= 1) {
            System.out.println("Masukkan nama: ");
            cekNama = scan.nextLine();

            for (int i = 0; i < namaMahasiswa.length; i++) {
                if (namaMahasiswa[i].equals(cekNama)) {
                    System.out.println("Data mahasiswa ditemukan: " + namaMahasiswa[i]);
                    sesuai = true;
                    index = i;
                    break;
                }
            }
            if (!sesuai) {
                kesempatan--;
                System.out.println("Data mahasiswa tidak ditemukan dalam database.");
            }
        }
        if (kesempatan == 0) {
            System.out.println("Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
            return -1;
        } else {
            return index;
        }
    }

    static boolean login() {
        Scanner scan = new Scanner(System.in);
        String username, password;
        int jumlahPercobaan = 3;

        do {
            System.out.println("Silahkan Login");
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan Password: ");
            password = scan.nextLine();

            if (username.equals("mahasiswa") && password.equals("123")) {
                System.out.println("\nLogin berhasil");
                return true;
            } else {
                jumlahPercobaan--;
                System.out.println("Login gagal. Sisa percobaan: " + (jumlahPercobaan));
            }

        } while (jumlahPercobaan > 0);

        return false;
    }

    static String formatWaktu() {
        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return waktu.format(format);
    }

    static void penarikanKas(String[] namaMahasiswa, int[] jmlKasDone, String[][] riwayatTransaksi, int[] hutangKas,
            LocalDateTime waktu, int totKasAwal, int kasBulanReal, int[] riwayatTotal) {
        int kesempatan = 3;
        int index = inputNama(namaMahasiswa);

        Scanner scan = new Scanner(System.in);

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
                } else if (kasKeluar > totKasAwal) {
                    System.out.println("Maaf, jumlah yang ditarik melebihi total kas yang tersedia");
                } else {
                    totKasAwal -= kasKeluar;
                    System.out.println("Mahasiswa yang meminjam kas: " + namaMahasiswa[index]);
                    System.out.println("Kas yang ditarik: " + kasKeluar);
                    System.out.println("Alasan penarikan: " + alasanTarik);
                    System.out.println("Waktu penarikan: " + formatWaktu());
                    System.out.println("Jumlah total kas setelah penarikan: " + totKasAwal);

                    riwayatTransaksi[index][jmlKasDone[index]] = "Penarikan kas = " + kasKeluar + " - "
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

    static void penambahanKas(String[] namaMahasiswa, int[] jmlKasDone, String[][] riwayatTransaksi,
            LocalDateTime waktu,
            int totKasAwal, int kasBulanReal, int kasBulanFull, int jmlKasFull, int[] riwayatTotal,
            boolean[] saveDenda) {
        boolean sesuai = false;
        int kesempatan = 3;
        int index = inputNama(namaMahasiswa);

        while (!sesuai && kesempatan >= 1) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan jumlah uang yang ditambahkan: ");
            int kasMasuk = scan.nextInt();

            if (kasMasuk % 5000 == 0) {
                sesuai = true;
                totKasAwal += kasMasuk;
                int jmlKasAdd = kasMasuk / 5000;
                jmlKasDone[index] += jmlKasAdd;
                kasBulanReal = kasMasuk + kasBulanReal;

                System.out.println("--------------------------");
                System.out.println("Mahasiswa yang membayar kas: " + namaMahasiswa[index]);
                System.out.println("Kas yang ditambah: " + kasMasuk);
                System.out.println("Waktu penambahan: " + formatWaktu());
                System.out.println("Pembayaran yang dilakukan mahasiswa keseluruhan: "
                        + jmlKasDone[index]);
                System.out.println("--------------------------");

                if (jmlKasDone[index] >= 8) {
                    if (jmlKasDone[index] > jmlKasFull) {
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
                System.out.println("Jumlah kas setelah penambahan: " + totKasAwal);
            } else {
                System.out.println("Uang harus kelipatan 5000");
            }

            riwayatTransaksi[index][jmlKasDone[index]] = "Penambahan kas = " + kasMasuk + " - "
                    + formatWaktu();
            riwayatTotal[1] += kasMasuk;
            kesempatan--;
            if (kesempatan == 0) {
                System.out.println("Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
            }
        }
    }

    static void riwayatKas(String[] namaMahasiswa, int[] jmlKasDone, String[][] riwayatTransaksi,
            String[] nimMahasiswa,
            String[] jkMahasiswa, boolean[] saveDenda, int[] hutangKas, int[] riwayatTotal) {
        Scanner scan = new Scanner(System.in);
        int index;

        System.out.println("\nSelamat Datang di Program Riwayat Kas");
        System.out.println("--------------------------");
        System.out.println("Pilih Menu:");
        System.out.println("1. Riwayat Mahasiswa");
        System.out.println("2. Riwayat Total");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu dalam (1/2/0): ");
        int pilihan = scan.nextInt();

        switch (pilihan) {
            case 1:
                scan.nextLine();
                index = inputNama(namaMahasiswa);
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

    static void pembayaranDenda(String[] namaMahasiswa, int[] jmlKasDone, String[][] riwayatTransaksi,
            boolean[] saveDenda, int[] hutangKas, int totKasAwal, int kasBulanReal, LocalDateTime waktu) {
        Scanner scan = new Scanner(System.in);
        int index = inputNama(namaMahasiswa);

        if (saveDenda[index] == true) {
            System.out.println("Anda tidak memiliki tanggungan denda\nKeluar fitur");
        } else if (saveDenda[index] == false && jmlKasDone[index] <= 4) {
            System.out.println("Anda memiliki tanggungan denda");
            System.out.println("--------------------------");
            System.out.println("Lunasi dahulu minimal 2 bulan untuk membayar denda");
        } else {
            System.out.println("Anda memiliki tanggungan denda");
            System.out.println("--------------------------");
            System.out.println("Denda bisa dibayarkan");
            System.out.println("Apakah anda ingin membayar denda? y/n");
            boolean cekDenda = false;
            String pilih = scan.nextLine();

            if (pilih.equalsIgnoreCase("y")) {
                while (!cekDenda) {
                    System.out.println("Masukkan nominal denda Rp. 5000");
                    int bayarDenda = scan.nextInt();
                    if (bayarDenda == 5000) {
                        saveDenda[index] = true;
                        System.out.println("Denda telah dibayarkan, Terima Kasih");

                        riwayatTransaksi[index][jmlKasDone[index]] = "Pembayaran Denda = " + bayarDenda + " - "
                                + formatWaktu();
                        totKasAwal += bayarDenda;
                        kasBulanReal += bayarDenda;
                    } else {
                        System.out.println("Nominal pembayaran harus Rp. 5000");
                    }
                    cekDenda = true;
                }
            } else if (pilih.equalsIgnoreCase("n")) {
                System.out.println("Baiklah, kembali ke menu");
            }
        }
    }

    static void dataMahasiswa(String[] namaMahasiswa, String[] nimMahasiswa, String[] jkMahasiswa,
            boolean[] saveDenda, int[] jmlKasDone, int[] hutangKas) {
        Scanner scan = new Scanner(System.in);
        int jmlKasFull = 12;
        int index = inputNama(namaMahasiswa);

        System.out.println("Nama mahasiswa: " + namaMahasiswa[index]);
        System.out.println("NIM: " + nimMahasiswa[index]);
        System.out.println("Jenis kelamin: " + jkMahasiswa[index]);
        System.out.println("Kas yang telah dibayar: " + jmlKasDone[index] + "/" + jmlKasFull);

        if (saveDenda[index]) {
            System.out.println("Tidak ada tanggungan denda");
        } else {
            System.out.println("Ada tanggungan denda");
        }
    }

    public static void main(String[] args) {
        String pilih;
        int pilihan, totKasAwal, kasBulanFull, kasBulanReal,
                jmlKasFull, index, bayarHutang;
        boolean cekHutang;

        String[] namaMahasiswa = { "Azza", "Angga", "Rio" };
        int[] jmlKasDone = { 15, 6, 10 };
        String[][] riwayatTransaksi = new String[3][100];
        int[] riwayatTotal = { 0, 0 };
        int[] hutangKas = new int[3];
        String[] nimMahasiswa = { "00001", "00002", "00003" };
        String[] jkMahasiswa = { "Perempuan", "Laki-laki", "Laki-laki" };
        boolean saveDenda[] = { true, false, true };

        formatWaktu();
        Scanner scan = new Scanner(System.in);

        totKasAwal = 100000;
        kasBulanFull = 20000;
        kasBulanReal = 10000;
        jmlKasFull = 12;

        if (login()) {
            do {
                System.out.println("\nSelamat Datang di Program Kas Online!");
                System.out.println("--------------------------");
                System.out.println("Total kas: " + totKasAwal);
                System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                System.out.println("Kas asli bulan ini: " + kasBulanReal);
                System.out.println("--------------------------");
                System.out.println("Pilih Menu:");
                System.out.println("1. Penarikan Kas");
                System.out.println("2. Penambahan Kas");
                System.out.println("3. Riwayat Kas");
                System.out.println("4. Pembayaran Denda");
                System.out.println("5. Data Mahasiswa");
                System.out.println("6. Bayar Hutang");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu dalam (1/2/3/4/5/6/0): ");
                pilihan = scan.nextInt();

                switch (pilihan) {
                    case 1:
                        System.out.println("\nSelamat datang di Fitur Penarikan Kas");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan mahasiswa untuk menarik kas\nSetiap kas yang ditarik akan masuk ke dalam hutang mahasiswa tersebut");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        penarikanKas(namaMahasiswa, jmlKasDone, riwayatTransaksi, hutangKas, null, totKasAwal,
                                kasBulanReal, riwayatTotal);
                        break;

                    case 2:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Kas");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan mahasiswa untuk membayar kas\nSetiap kas yang dibayar akan masuk ke dalam riwayat kas mahasiswa tersebut");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        penambahanKas(namaMahasiswa, jmlKasDone, riwayatTransaksi, null, totKasAwal, kasBulanReal,
                                kasBulanFull, jmlKasFull, riwayatTotal, saveDenda);
                        break;

                    case 3:
                        System.out.println("\nSelamat Datang di Fitur Riwayat Kas!");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk melihat riwayat kas\nTerdapat 2 fitur riwayat yaitu riwayat mahasiswa dan riwayat total");
                        System.out.println("--------------------------");
                        riwayatKas(namaMahasiswa, jmlKasDone, riwayatTransaksi, nimMahasiswa, jkMahasiswa,
                                saveDenda,
                                hutangKas, riwayatTotal);
                        break;

                    case 4:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Denda");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk membayar denda jika tidak melakukan pembayaran kas selama 1 bulan\nDenda yang harus dibayarkan sejumlah Rp.5.000");

                        System.out.println("--------------------------");
                        scan.nextLine();

                        pembayaranDenda(namaMahasiswa, jmlKasDone, riwayatTransaksi, saveDenda, hutangKas, totKasAwal,
                                kasBulanReal, null);
                        break;
                    case 5:
                        System.out.println("\nSelamat datang di Fitur Data Mahasiswa");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk melihat data dari masing-masing mahasiswa\nInformasi berupa seperti nama, NIM, jenis kelamin, total kas bayar selama 1 tahun, dan status tanggungan denda.");
                        System.out.println("--------------------------");
                        scan.nextLine();
                        dataMahasiswa(namaMahasiswa, nimMahasiswa, jkMahasiswa, saveDenda, hutangKas, jmlKasDone);
                        break;

                    case 6:
                        System.out.println("\nSelamat datang di Fitur Pembayaran Hutang");
                        System.out.println("--------------------------");
                        System.out.println(
                                "Fitur ini digunakan untuk pembayarn hutang\nHanya berlaku untuk mahasiswa yang memiliki hutang");
                        System.out.println("--------------------------");
                        scan.nextLine();

                        index = inputNama(namaMahasiswa);

                        if (hutangKas[index] == 0) {
                            System.out.println("Anda tidak memiliki hutang kas");
                        } else {
                            cekHutang = false;
                            System.out.println("Hutang kas anda: " + hutangKas[index]);
                            System.out.println("Apakah anda ingin membayar hutang? y/n");
                            pilih = scan.nextLine();
                            if (pilih.equalsIgnoreCase("y")) {
                                while (!cekHutang) {
                                    System.out.println("Masukkan nominal pembayaran: ");
                                    bayarHutang = scan.nextInt();
                                    if (bayarHutang > hutangKas[index]) {
                                        System.out.println(
                                                "Nominal melebihi hutang anda. Masukkan nominal yang sesuai");
                                    } else {
                                        hutangKas[index] -= bayarHutang;
                                        System.out.println("Pembayaran berhasil");
                                        System.out.println("Sisa hutang anda: " + hutangKas[index]);
                                        cekHutang = true;
                                    }
                                }
                            } else if (pilih.equalsIgnoreCase("n")) {
                                System.out.println("Baiklah, kembali ke menu");
                            }
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid");
                        break;

                    case 0:
                        System.out.println("Keluar");
                        return;

                }
            } while (pilihan >= 0);
        } else {
            System.out.println("--------------------------");
            System.out.println("Jumlah Percobaan Anda Telah Habis");
            System.out.println("Keluar");
        }
        scan.close();

    }
}
