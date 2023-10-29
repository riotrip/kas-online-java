import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasOnline {
    public static void main(String[] args) {
        String username, password, alasanTarik, cekNama;
        int jumlahPercobaan, pilihan, kasMasuk, kasKeluar, totKasAwal, kasBulanFull, kasBulanReal,
                jmlKasFull, jmlKasAdd, kesempatan, index;
        boolean sesuai, cekKelipatan;
        String[] namaMahasiswa = { "Tony", "Rey", "Dani" };
        int[] jmlKasDone = { 15, 6, 10 };

        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Scanner scan = new Scanner(System.in);

        String formatWaktu = waktu.format(format);

        totKasAwal = 100000;
        kasBulanFull = 20000;
        kasBulanReal = 10000;
        jmlKasFull = 12;

        jumlahPercobaan = 3;

        do {
            System.out.println("Silahkan Login");
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan Password: ");
            password = scan.nextLine();

            if (username.equals("mahasiswa") && password.equals("123")) {
                System.out.println("\nLogin berhasil");

                do {
                    System.out.println("\nSelamat Datang di Program Kas Online!");
                    System.out.println("Pilih Menu:");
                    System.out.println("1. Penarikan Kas");
                    System.out.println("2. Penambahan Kas");
                    System.out.println("0. keluar");
                    System.out.print("Pilih menu dalam (1/2/0): ");
                    pilihan = scan.nextInt();

                    switch (pilihan) {
                        case 1:
                            System.out.println("Selamat datang di Program Penarikan Kas");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");

                            scan.nextLine();
                            cekNama = " ";
                            kesempatan = 3;
                            sesuai = false;
                            index = -1;
                            while (!sesuai && kesempatan >= 1) {
                                System.out.println("Masukkan nama yang menarik kas: ");
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
                                    System.out.println("Mahasiswa yang meminjam kas: " + cekNama);
                                    System.out.println("Kas yang ditarik: " + kasKeluar);
                                    System.out.println("Alasan penarikan: " + alasanTarik);
                                    System.out.println("Waktu penarikan: " + formatWaktu);
                                    System.out.println("Jumlah total kas setelah penarikan: " + totKasAwal);

                                }
                            }

                            break;
                        case 2:
                            System.out.println("Selamat datang di Program Penambahan Kas");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");
                            scan.nextLine();

                            cekNama = " ";
                            kesempatan = 3;
                            sesuai = false;
                            index = -1;
                            while (!sesuai && kesempatan >= 1) {
                                System.out.println("Masukkan nama yang menambah kas: ");
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
                                System.out.println(
                                        "Anda telah melebihi batas percobaan, coba lagi setelah kembali ke menu");
                            } else {
                                cekKelipatan = false;
                                while (!cekKelipatan) {
                                    System.out.println("Masukkan jumlah uang yang ditambahkan: ");
                                    kasMasuk = scan.nextInt();
                                    if (kasMasuk % 5000 == 0) {
                                        cekKelipatan = true;
                                        totKasAwal = totKasAwal + kasMasuk;
                                        jmlKasAdd = kasMasuk / 5000;
                                        jmlKasDone[index] += jmlKasAdd;
                                        kasBulanReal = kasMasuk + kasBulanReal;

                                        System.out.println("--------------------------");
                                        System.out.println("Mahasiswa yang membayar kas: " + cekNama);
                                        System.out.println("Kas yang ditambah: " + kasMasuk);
                                        System.out.println("Waktu penambahan: " + formatWaktu);
                                        System.out.println(
                                                "Pembayaran yang dilakukan mahasiswa keseluruhan: "
                                                        + jmlKasDone[index]);
                                        System.out.println("--------------------------");

                                        if (jmlKasDone[index] >= 8) {
                                            if (jmlKasDone[index] > 12) {
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
                jumlahPercobaan--;
                System.out.println("Login gagal. Sisa percobaan: " + (jumlahPercobaan));
            }
            if (jumlahPercobaan == 0)
                return;

        } while (jumlahPercobaan > 0);

        scan.close();

    }
}