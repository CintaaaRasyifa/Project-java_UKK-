import javax.swing.JOptionPane;
import java.util.ArrayList;

public class IceCreamMixue {
    public static void main(String[] args) {
        ArrayList<String> pesanan = new ArrayList<>();
        ArrayList<Integer> jumlahPesanan = new ArrayList<>();
        ArrayList<String> namaPemesan = new ArrayList<>();
        ArrayList<Integer> hargaPesanan = new ArrayList<>();

        boolean stopProgram = false;
        while (!stopProgram) {
            String menu = JOptionPane.showInputDialog(
                    "Menu Program:\n" +
                            "1. Tambah Pesanan Mixue\n" +
                            "2. Lihat Daftar Pesanan Mixue\n" +
                            "3. Proses Pembayaran\n" +
                            "4. Hapus Pesanan\n" +
                            "5. Keluar\n" +
                            "Pilihan Anda:");

            int choice = Integer.parseInt(menu);

            switch (choice) {
                case 1:
                    String kategori = JOptionPane.showInputDialog(
                            "Kategori Mixue:\n" +
                                    "1. Ice Cream\n" +
                                    "2. Tea\n" +
                                    "3. Milkshake\n" +
                                    "Pilih kategori (masukkan angka 1-3):");

                    String[] kategoriMixue = { "Ice Cream", "Tea", "Milkshake" };
                    int kategoriIndex = Integer.parseInt(kategori) - 1;

                    if (kategoriIndex >= 0 && kategoriIndex < kategoriMixue.length) {
                        String menuMixue = JOptionPane.showInputDialog(
                                "Menu Mixue " + kategoriMixue[kategoriIndex] + ":\n" +
                                        getMenuOptions(kategoriMixue[kategoriIndex]) +
                                        "Pilih menu (masukkan angka 1-3):");

                        String[] menuMixueOptions = getMenu(kategoriMixue[kategoriIndex]);
                        int menuIndex = Integer.parseInt(menuMixue) - 1;

                        if (menuIndex >= 0 && menuIndex < menuMixueOptions.length) {
                            String nama = JOptionPane.showInputDialog("Masukkan nama pemesan:");
                            namaPemesan.add(nama);

                            int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Masukkan jumlah Mixue yang dipesan:"));
                            String pesananMixue = kategoriMixue[kategoriIndex] + " - " + menuMixueOptions[menuIndex];
                            int index = pesanan.indexOf(pesananMixue);

                            if (index != -1) {
                                int jumlahLama = jumlahPesanan.get(index);
                                jumlahPesanan.set(index, jumlahLama + jumlah);
                            } else {
                                pesanan.add(pesananMixue);
                                jumlahPesanan.add(jumlah);
                            }
                            
                            int harga = getHarga(kategoriMixue[kategoriIndex], menuMixueOptions[menuIndex]);
                            hargaPesanan.add(harga);

                            JOptionPane.showMessageDialog(null, "Pesanan Mixue berhasil ditambahkan.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Menu tidak valid.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Kategori tidak valid.");
                    }
                    break;
                case 2:
                    if (pesanan.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Belum ada pesanan Mixue.");
                    } else {
                        String daftarPesanan = "Daftar Pesanan Mixue:\n";
                        for (int i = 0; i < pesanan.size(); i++) {
                            String item = pesanan.get(i);
                            int jumlah = jumlahPesanan.get(i);
                            int harga = hargaPesanan.get(i);

                            daftarPesanan += item + " - Harga: " + harga + " - Jumlah: " + jumlah + "\n";
                        }
                        JOptionPane.showMessageDialog(null, daftarPesanan);
                    }
                    break;
                case 3:
                    if (pesanan.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Belum ada pesanan Mixue.");
                    } else {
                        String strukPesanan = "Struk Pesanan Mixue:\n";
                        int totalHarga = 0;
                        for (int i = 0; i < pesanan.size(); i++) {
                            String item = pesanan.get(i);
                            int jumlah = jumlahPesanan.get(i);
                            int harga = hargaPesanan.get(i);

                            int subtotal = harga * jumlah;
                            totalHarga += subtotal;

                            strukPesanan += item + " (" + jumlah + ") - Subtotal: " + subtotal + "\n";
                        }

                        strukPesanan += "\nTotal Harga: " + totalHarga;
                        String namaPesan = namaPemesan.get(0);
                        strukPesanan += "\nNama Pemesan: " + namaPesan;

                        int pembayaran = Integer.parseInt(JOptionPane.showInputDialog(strukPesanan + "\n\nMasukkan jumlah pembayaran:"));
                        if (pembayaran >= totalHarga) {
                            int kembalian = pembayaran - totalHarga;
                            JOptionPane.showMessageDialog(null, "Pembayaran berhasil.\nKembalian: " + kembalian);
                            pesanan.clear();
                            jumlahPesanan.clear();
                            namaPemesan.clear();
                            hargaPesanan.clear();
                        } else {
                            JOptionPane.showMessageDialog(null, "Jumlah pembayaran tidak mencukupi.");
                        }
                    }
                    break;
                case 4:
                    if (pesanan.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Belum ada pesanan Mixue.");
                    } else {
                        String pesananHapus = JOptionPane.showInputDialog("Masukkan nomor pesanan yang ingin dihapus:");
                        int indexHapus = Integer.parseInt(pesananHapus) - 1;

                        if (indexHapus >= 0 && indexHapus < pesanan.size()) {
                            pesanan.remove(indexHapus);
                            jumlahPesanan.remove(indexHapus);
                            namaPemesan.remove(indexHapus);
                            hargaPesanan.remove(indexHapus);
                            JOptionPane.showMessageDialog(null, "Pesanan berhasil dihapus.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nomor pesanan tidak valid.");
                        }
                    }
                    break;
                case 5:
                    stopProgram = true;
                    JOptionPane.showMessageDialog(null, "Terima kasih. Program selesai.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid. Silakan pilih angka 1-5.");
                    break;
            }
        }
    }

    private static String getMenuOptions(String kategori) {
        String options = "";
        if (kategori.equals("Ice Cream")) {
            options += "1. Boba Sundae\n";
            options += "2. Ice Cream Strawberry Cone\n";
            options += "3. Mango Sundae\n";
        } else if (kategori.equals("Tea")) {
            options += "1. Lemonade\n";
            options += "2. Lemon Tea\n";
            options += "3. Leci Tea\n";
        } else if (kategori.equals("Milkshake")) {
            options += "1. Strawberry Mi-Shake\n";
            options += "2. Mocca Mi-Shake\n";
            options += "3. Chocolate Mi-Shake\n";
        }
        return options;
    }

    private static String[] getMenu(String kategori) {
        if (kategori.equals("Ice Cream")) {
            return new String[] { "Boba Sundae", "Ice Cream Strawberry Cone", "Mango Sundae" };
        } else if (kategori.equals("Tea")) {
            return new String[] { "Lemonade", "Lemon Tea", "Leci Tea" };
        } else if (kategori.equals("Milkshake")) {
            return new String[] { "Strawberry Mi-Shake", "Mocca Mi-Shake", "Chocolate Mi-Shake" };
        }
        return new String[] {};
    }

    private static int getHarga(String kategori, String item) {
        if (kategori.equals("Ice Cream")) {
            if (item.equals("Boba Sundae")) {
                return 16000;
            } else if (item.equals("Ice Cream Strawberry Cone")) {
                return 18000;
            } else if (item.equals("Mango Sundae")) {
                return 15000;
            }
        } else if (kategori.equals("Tea")) {
            if (item.equals("Lemonade")) {
                return 10000;
            } else if (item.equals("Lemon Tea")) {
                return 12000;
            } else if (item.equals("Leci Tea")) {
                return 15000;
            }
        } else if (kategori.equals("Milkshake")) {
            if (item.equals("Strawberry Mi-Shake")) {
                return 18000;
            } else if (item.equals("Mocca Mi-Shake")) {
                return 16000;
            } else if (item.equals("Chocolate Mi-Shake")) {
                return 17000;
            }
        }
        return 0;
    }
}
