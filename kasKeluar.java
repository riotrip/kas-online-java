import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class kasKeluar {
    public static void main(String[] args) {
        String namaMahasiswa, alasanTarik;
        int kasMasuk, kasKeluar, kasMahasiswa, totKasAwal, totKasAkhir, tunggakKas, kasBulan;
        Scanner scan = new Scanner(System.in);
        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formatWaktu = waktu.format(format);

        totKasAwal = 100000;
        kasBulan = 20000;

        namaMahasiswa = "Rei";
        kasMahasiswa = 15000;
        tunggakKas = kasBulan-kasMahasiswa;

        System.out.println("Selamat datang di Program Penarikan Kas");
        System.out.println("--------------------------");
        System.out.println("Total kas: " + totKasAwal);
        System.out.println("Kas penuh bulan ini: " + kasBulan);
        System.out.println("--------------------------");
        System.out.println("Nama mahasiswa: " + namaMahasiswa);
        System.out.println("Kas mahasiswa: " + kasMahasiswa);
        System.out.println("Tunggakan kas: " + tunggakKas);
        System.out.println("--------------------------");
        System.out.println("Masukkan jumlah yang ditarik: ");
        kasKeluar = scan.nextInt();
        System.out.println("Masukkan Alasan: ");
        scan.nextLine();
        alasanTarik = scan.nextLine();
        System.out.println("--------------------------");
        totKasAkhir = totKasAwal-kasKeluar;
        System.out.println("Kas yang ditarik: " + kasKeluar);
        System.out.println("Alasan penarikan: " + alasanTarik);
        System.out.println("Waktu penambahan: " + formatWaktu);
        System.out.println("Jumlah kas setelah penarikan: " + totKasAkhir);
    }
}