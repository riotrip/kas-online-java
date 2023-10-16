import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasOnline {
    public static void main(String[] args) {
        String username, password, namaMahasiswa, alasanTarik;
        int jumlahPercobaan, pilihan, kasMasuk, kasKeluar, totKasAwal, totKasAkhir, kasBulanFull, kasBulanReal,
                jmlKasFull, jmlKasAdd,
                jmlKasDone, kasYangBelumDiBayarkan;

        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Scanner scan = new Scanner(System.in);

        String formatWaktu = waktu.format(format);

        totKasAwal = 100000;
        kasBulanFull = 20000;
        kasBulanReal = 10000;
        jmlKasFull = 4;

        jumlahPercobaan = 3;

        do {
            System.out.println("Silahkan Login");
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan Password: ");
            password = scan.nextLine();

            if (username.equals("mahasiswa") && password.equals("123")) {
                System.out.println("Login berhasil\n");

                do {
                    System.out.println("Selamat Datang di Program Kas Online!");
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
                            System.out.println("Masukkan nama siswa yang menarik Kas: ");
                            scan.nextLine();
                            namaMahasiswa = scan.nextLine();
                            System.out.println("Masukkan jumlah yang ditarik: ");
                            kasKeluar = scan.nextInt();
                            System.out.println("Masukkan Alasan: ");
                            scan.nextLine();
                            alasanTarik = scan.nextLine();
                            System.out.println("--------------------------");
                            totKasAkhir = totKasAwal - kasKeluar;

                            if (kasKeluar <= 0) {
                                System.out.println("Jumlah yang ditarik harus lebih dari 0");
                            } else if (kasKeluar > totKasAwal) {
                                System.out.println("Maaf, jumlah yang ditarik melebihi total kas yang tersedia");
                            } else {
                                System.out.println("Mahasiswa yang meminjam kas: " + namaMahasiswa);
                                System.out.println("Kas yang ditarik: " + kasKeluar);
                                System.out.println("Alasan penarikan: " + alasanTarik);
                                System.out.println("Waktu penarikan: " + formatWaktu);
                                System.out.println("Jumlah total kas setelah penarikan: " + totKasAkhir);
                            }
                            break;
                        case 2:
                            System.out.println("Selamat datang di Program Penambahan Kas");
                            System.out.println("--------------------------");
                            System.out.println("Total kas: " + totKasAwal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Kas asli bulan ini: " + kasBulanReal);
                            System.out.println("--------------------------");
                            System.out.println("Masukkan nama siswa yang menarik Kas: ");
                            scan.nextLine();
                            namaMahasiswa = scan.nextLine();
                            System.out.println("Masukkan berapa kali pembayaran sudah dilakukan: ");
                            jmlKasDone = scan.nextInt();
                            System.out.println("Masukkan jumlah uang yang ditambahkan: ");
                            kasMasuk = scan.nextInt();
                            System.out.println("Masukkan berapa kali pembayaran dilakukan sekarang: ");
                            jmlKasAdd = scan.nextInt();
                            System.out.println("--------------------------");

                            totKasAkhir = totKasAwal + kasMasuk;
                            jmlKasFull = jmlKasAdd + jmlKasDone;
                            kasBulanReal = kasMasuk + kasBulanReal;

                            System.out.println("Mahasiswa yang membayar kas: " + namaMahasiswa);
                            System.out.println("Kas yang ditambah: " + kasMasuk);
                            System.out.println("Waktu penambahan: " + formatWaktu);
                            System.out.println("Pembayaran yang dilakukan mahasiswa pada bulan ini: " + jmlKasFull);
                            System.out.println("Kas asli bulan ini setelah ditambah: " + kasBulanReal);
                            System.out.println("Kas bulan ini jika penuh: " + kasBulanFull);
                            System.out.println("Jumlah kas setelah penambahan: " + totKasAkhir);
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