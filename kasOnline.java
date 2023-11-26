import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasOnline {
    static String inputNama(String[] namaMahasiswa) {
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
            return null;
        } else {
            return cekNama;
        }
    }

    public static void main(String[] args) {
        String username, password, alasanTarik;
        int jumlahPercobaan, pilihan, kasMasuk, kasKeluar, totKasAwal, kasBulanFull, kasBulanReal,
                jmlKasFull, jmlKasAdd, kesempatan, index, bayarDenda, bayarHutang;
        boolean sesuai, cekKelipatan, cekDenda, cekHutang;

        String[] namaMahasiswa = { "Tony", "Rey", "Dani" };

        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Scanner scan = new Scanner(System.in);

        String formatWaktu = waktu.format(format);

        totKasAwal = 100000;
        kasBulanFull = 20000;
        kasBulanReal = 10000;
        jmlKasFull = 12;

        jumlahPercobaan = 3;

        cekKelipatan = false;
        kesempatan = 3;
        sesuai = false;
        index = -1;
        cekNama = " ";

        do {
            System.out.println("Silahkan Login");
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan Password: ");
            password = scan.nextLine();
            if (username.equals("mahasiswa") && password.equals("123")) {
                System.out.println("Login berhasil\n");

                do {
                    System.out.println("\nSelamat Datang di Program Kas Online!");
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
                            System.out.println("\nSelamat datang di Program Penarikan Kas");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");
                            scan.nextLine();
                            String cekNama = " ";
                            int kesempatan = 3;
                            boolean sesuai = false;
                            while (!sesuai && kesempatan >= 1) {
                                System.out.println("Masukkan nama yang menarik kas: ");
                                cekNama = scan.nextLine();
                                for (String cari : namaMahasiswa) {
                                    if (cari.equals(cekNama)) {
                                        System.out.println("Data ditemukan: " + cari);
                                        sesuai = true;
                                        break;
                                    }
                                }
                                if (!sesuai) {
                                    kesempatan--;
                                    System.out.println("Data tidak ditemukan dalam array.");
                                }
                            }
                            if (kesempatan == 0) {
                                System.out.println(
                                        "Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
                            } else {
                                System.out.println("Masukkan jumlah yang ditarik: ");
                                kasKeluar = scan.nextInt();
                                System.out.println("Masukkan Alasan: ");
                                scan.nextLine();
                                alasanTarik = scan.nextLine();
                                System.out.println("--------------------------");
                                totKasAwal = totKasAwal - kasKeluar;

                                if (kasKeluar <= 0) {
                                    System.out.println("Jumlah yang ditarik harus lebih dari 0");
                                } else if (kasKeluar > totKasAwal) {
                                    System.out
                                            .println("Maaf, jumlah yang ditarik melebihi total kas yang tersedia");
                                } else {
                                    System.out.println("Mahasiswa yang meminjam kas: " + inputNama(namaMahasiswa));
                                    System.out.println("Kas yang ditarik: " + kasKeluar);
                                    System.out.println("Alasan penarikan: " + alasanTarik);
                                    System.out.println("Waktu penarikan: " + formatWaktu);
                                    System.out.println("Jumlah total kas setelah penarikan: " + totKasAwal);
                                }
                            }
                            break;

                        case 2:
                            System.out.println("\nSelamat datang di Program Penambahan Kas");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");
                            scan.nextLine();

                            cekNama = " ";
                            kesempatan = 3;
                            sesuai = false;
                            while (!sesuai && kesempatan >= 1) {
                                System.out.println("Masukkan nama yang menambah kas: ");
                                cekNama = scan.nextLine();
                                for (String cari : namaMahasiswa) {
                                    if (cari.equals(cekNama)) {
                                        System.out.println("Data ditemukan: " + cari);
                                        sesuai = true;
                                        break;
                                    }
                                }
                                if (!sesuai) {
                                    kesempatan--;
                                    System.out.println("Data tidak ditemukan dalam array.");
                                }
                            }
                            if (kesempatan == 0) {
                                System.out.println(
                                        "Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
                            }

                            break;

                        case 3:
                            System.out.println("\nSelamat Datang di Program Riwayat Kas!");
                            System.out.println("--------------------------");
                            System.out.println("Pilih Menu:");
                            System.out.println("1. Riwayat Mahasiswa");
                            System.out.println("2. Riwayat Total");
                            System.out.println("0. keluar");
                            System.out.print("Pilih menu dalam (1/2/0): ");

                            pilihan = scan.nextInt();
                            switch (pilihan) {
                                case 1:
                                    scan.nextLine();

                                    inputNama(namaMahasiswa);

                                    System.out.println("Cetak Riwayat Transaksi untuk setiap pengguna");
                                    System.out.println(inputNama(namaMahasiswa));
                                    for (int i = 0; i < riwayatTransaksi[index].length; i++) {
                                        if (riwayatTransaksi[index][i] != null) {
                                            System.out.println(riwayatTransaksi[index][i]);
                                        }
                                    }
                                    System.out.println();

                                    break;
                                case 2:
                                    System.out.println("Cetak Semua Riwayat Transaksi");
                                    System.out.print("Total kas yang telah ditarik hari ini: ");
                                    System.out.println(riwayatTotal[0]);
                                    System.out.print("Total kas yang telah ditambahkan hari ini: ");
                                    System.out.println(riwayatTotal[1]);
                                    break;
                                case 0:
                                    break;
                                default:
                                    break;
                            }
                            break;

                        case 4:
                            System.out.println("\nSelamat datang di Program Pembayaran Denda");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");
                            scan.nextLine();

                            inputNama(namaMahasiswa);

                            if (jmlKasDone[index] >= 8) {
                                if (jmlKasDone[index] > 12) {
                                    System.out.println("Lunas, tidak perlu membayar denda");
                                } else {
                                    System.out.println("Belum Lunas, tetapi tidak perlu membayar denda");
                                }
                            } else if (jmlKasDone[index] <= 4) {
                                System.out.println("Belum Lunas, dan perlu membayar denda 5000");
                                System.out.println("--------------------------");
                                System.out.println("Lunasi dahulu minimal 2 bulan untuk membayar denda");
                            } else {
                                System.out.println("Masukkan berapa kali pembayaran sudah dilakukan: ");
                                jmlKasDone = scan.nextInt();
                                System.out.println("Masukkan jumlah uang yang ditambahkan: ");
                                kasMasuk = scan.nextInt();
                                System.out.println("Masukkan berapa kali pembayaran dilakukan sekarang: ");
                                jmlKasAdd = scan.nextInt();
                                System.out.println("--------------------------");

                                totKasAwal = totKasAwal + kasMasuk;
                                jmlKasFull = jmlKasAdd + jmlKasDone;
                                kasBulanReal = kasMasuk + kasBulanReal;

                                System.out.println("Mahasiswa yang membayar kas: " + cekNama);
                                System.out.println("Kas yang ditambah: " + kasMasuk);
                                System.out.println("Waktu penambahan: " + formatWaktu);
                                System.out.println("Pembayaran yang dilakukan mahasiswa pada bulan ini: " + jmlKasFull);
                                System.out.println("Kas asli bulan ini setelah ditambah: " + kasBulanReal);
                                System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                                System.out.println("Jumlah kas setelah penambahan: " + totKasAwal);
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
                jumlahPercobaan--;
                System.out.println("Login gagal. Sisa percobaan: " + (jumlahPercobaan));
            }
            if (jumlahPercobaan == 0)
                return;
        } while (jumlahPercobaan > 0);
        scan.close();
    }

}
