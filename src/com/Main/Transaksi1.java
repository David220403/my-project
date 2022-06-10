/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.popup.popup_pembayaran;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author david
 */
public final class Transaksi1 extends javax.swing.JPanel {

    String barcode = "";
    String idBarang = null;
    int uangBayar = 0;
    int uangKembalian = 0;
    
    private String formatRupiah(int nilai){
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols simbol = format.getDecimalFormatSymbols();
        simbol.setCurrencySymbol("Rp. ");
        simbol.setMonetaryDecimalSeparator(',');
        simbol.setGroupingSeparator('.');
        format.setDecimalFormatSymbols(simbol);
        return format.format(nilai);
    }
    
    NumberFormat numformat = NumberFormat.getInstance(new Locale("ca", "CA"));
    
    NumberFormatter numformatter;
        
    private void setRupiah(){
        
        
        numformat.setMaximumFractionDigits(0);
        
        numformatter = new NumberFormatter(numformat);
        numformatter.setAllowsInvalid(false);

    }

    private String setBarcode(String barcode) {
        return this.barcode = barcode;
    }

    private String getBarcode() {
        return this.barcode;
    }

    private void afterEnter() {
        insertBarang(this.barcode);
        setBarcode("");
        search.setText("");
    }

    private int getTotalHarga() {
        int total = 0;
        try {
            String sql = "select db.harga_jual * count(dt.jumlah) as \"Jumlah\" from tb_detail_transaksi as dt join tb_transaksi as t on dt.id_transaksi = t.id join tb_data_barang as db on dt.id_barang = db.id where t.id = (select id from tb_transaksi order by id desc limit 1) group by db.id;";
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
//                            int diskon = Integer.parseInt(rs.getString(5));
//                            int total_diskon =diskon/100;
                total += rs.getInt(1);
            }
        } catch (SQLException ex) {

        }
        return total;
    }

    private int getDiscounted() {
        int total = 0;
        int discounted = 0;
        try {
            String sql = "select persen from tb_transaksi as t join tb_diskon as d on t.id_diskon = d.id where t.id = (select id from tb_transaksi order by id desc limit 1)";
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                total = getTotalHarga() - (getTotalHarga() * rs.getInt(1) / 100);
                discounted = getTotalHarga() * rs.getInt(1) / 100;
            }
        } catch (SQLException ex) {

        }
        return total;
    }

    private void getBayar() {
        uangBayar = Integer.parseInt(bayar.getText());
        getKembalian();
    }

    private int getKembalian() {
        uangKembalian = uangBayar - getTotalHarga();
        if (uangKembalian <= 0) {
            JOptionPane.showMessageDialog(this, "uang yang anda masukan tidak cukup");
            clear();
        } else {
            kembalian.setText(Integer.toString(uangKembalian));
        }
        return uangKembalian;
    }

    private void clear() {
        bayar.setText(null);
        kembalian.setText(null);
        tabelTransaksi();
    }

    public void insertBarang(String barcode) {
        try {
            String sql = "INSERT INTO tb_detail_transaksi VALUES (NULL, (select id from tb_transaksi order by id desc limit 1) , '" + barcode + "', '1');";
            System.out.println(sql);
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate(sql);
            tabelTransaksi();
        } catch (SQLException ex) {
            System.out.println("lol dek: " + ex.getMessage());
        }
    }

    private void checkout() {
        try {
            String sql = "UPDATE tb_transaksi SET total_harga = " + getTotalHarga() + ", dibayar = " + uangBayar + ", kembalian = " + getKembalian() + " where id = (select id from tb_transaksi order by id desc limit 1);";
            System.out.println(sql);
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate(sql);
            tabelTransaksi();
        } catch (SQLException ex) {
            System.out.println("lol dek: " + ex.getMessage());
        }
        insertNewTransaksi();
        clear();
    }

    private void insertNewTransaksi() {
        try {
            String sql = "INSERT INTO tb_transaksi VALUES (NULL, NULL, NULL, NULL, current_timestamp(), NULL, '1', (select id from tb_diskon where tanggal = current_date order by tanggal desc limit 1));";
            System.out.println(sql);
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate(sql);
            tabelTransaksi();
        } catch (SQLException ex) {
            System.out.println("lol dek: " + ex.getMessage());
        }
    }

    private void tabelBarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Beli");
        model.addColumn("Jual");
        model.addColumn("Stock");
        model.addColumn("Kategori");
        model.addColumn("Supplier");
        model.addColumn("Produk");

        try {

            String sql = "select db.id, db.nama, db.harga_beli, db.harga_jual, db.stock, k.kategori, s.nama, p.nama from tb_data_barang as db join tb_detail_supplier as ds on db.id_detail_supplier = ds.id join tb_kategori as k on db.id_kategori = k.id join tb_supplier as s on ds.id_supplier = s.id join tb_produk as p on ds.id_produk = p.id ";
            if (kategori.getSelectedIndex() != -1) {
                sql = sql + "where db.id_kategori = ( select id from tb_kategori where kategori = '" + kategori.getSelectedItem() + "')";
            }
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), this.formatRupiah(Integer.parseInt(rs.getString(3))).replace(",00", ""),
                    this.formatRupiah(Integer.parseInt(rs.getString(4))).replace(",00", ""), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
                });

            }
            table1.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void getKategori() {
        kategori.removeAllItems();

        String sql = "select kategori from tb_kategori";

        try {
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                kategori.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        kategori.setSelectedIndex(-1);
    }

    private void tabelTransaksi() {
        totalHarga.setText(Integer.toString(getTotalHarga()));
        totalDiskon.setText(Integer.toString(getDiscounted()));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Jumlah");

        try {
            String sql = "select dt.id_barang, dt.id_transaksi, db.nama, db.harga_jual, count(dt.jumlah) as \"Jumlah\" from tb_detail_transaksi as dt join tb_transaksi as t on dt.id_transaksi = t.id join tb_data_barang as db on dt.id_barang = db.id where t.id = (select id from tb_transaksi order by id desc limit 1) group by db.id";

            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString(1), rs.getString(3), this.formatRupiah(Integer.parseInt(rs.getString(4))).replace(",00", ""),
                    rs.getString(5)
                });

            }

            table2.setModel(model);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    Tambah_Barang edit = new Tambah_Barang(null, true);

    public Transaksi1() {
        initComponents();
        table1.fixTable(jScrollPane1);
        tabelBarang();
        table2.fixTable(jScrollPane2);
        tabelTransaksi();
        getKategori();      
    }

    public void getApasih() {
        try {
            String namaFile = "/com/Main/Nota.jasper";
            InputStream Report;
            Connection conn = com.Koneksi.Koneksi.configDB();
            Report = getClass().getResourceAsStream(namaFile);
            HashMap param = new HashMap();
            param.put("dibayar", uangBayar);
            param.put("kembalian", getKembalian());
            param.put("total", getDiscounted());
            JasperPrint JPrint = JasperFillManager.fillReport(Report, param, conn);
            JasperViewer.viewReport(JPrint, false);
        } catch (SQLException | JRException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        search1 = new javax.swing.JTextField();
        search = new javax.swing.JTextField();
        txt_nopembeli = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        txt_namapembeli = new javax.swing.JTextField();
        txt_alamatpembali = new javax.swing.JTextField();
        totalHarga = new javax.swing.JTextField();
        bayar = new javax.swing.JTextField();
        totalDiskon = new javax.swing.JTextField();
        kategori = new com.swing.Combobox();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.swing.table.Table();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.swing.table.Table();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search1.setBackground(new java.awt.Color(255, 194, 212));
        search1.setBorder(null);
        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search1KeyReleased(evt);
            }
        });
        jPanel1.add(search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 122, 210, -1));

        search.setBackground(new java.awt.Color(255, 194, 212));
        search.setBorder(null);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 122, 150, -1));

        txt_nopembeli.setBackground(new java.awt.Color(255, 194, 212));
        txt_nopembeli.setBorder(null);
        txt_nopembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nopembeliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_nopembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 626, 150, -1));

        kembalian.setBackground(new java.awt.Color(255, 194, 212));
        kembalian.setBorder(null);
        kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianActionPerformed(evt);
            }
        });
        jPanel1.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 670, 150, -1));

        txt_namapembeli.setBackground(new java.awt.Color(255, 194, 212));
        txt_namapembeli.setBorder(null);
        txt_namapembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namapembeliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namapembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 625, 150, -1));

        txt_alamatpembali.setBackground(new java.awt.Color(255, 194, 212));
        txt_alamatpembali.setBorder(null);
        txt_alamatpembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatpembaliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamatpembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 668, 150, -1));

        totalHarga.setBackground(new java.awt.Color(255, 194, 212));
        totalHarga.setBorder(null);
        totalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalHargaActionPerformed(evt);
            }
        });
        jPanel1.add(totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 621, 150, -1));

        bayar.setBackground(new java.awt.Color(255, 194, 212));
        bayar.setBorder(null);
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarKeyReleased(evt);
            }
        });
        jPanel1.add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 621, 150, -1));

        totalDiskon.setBackground(new java.awt.Color(255, 194, 212));
        totalDiskon.setBorder(null);
        totalDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalDiskonActionPerformed(evt);
            }
        });
        jPanel1.add(totalDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 670, 150, -1));

        kategori.setBackground(new java.awt.Color(255, 122, 162));
        kategori.setLabeText("Kategori");
        kategori.setOpaque(false);
        kategori.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                kategoriPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 106, 170, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Bayar.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174, 397, 125, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button hapus.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1034, 397, 125, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1187, 114, 125, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1067, 666, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1067, 617, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 666, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 617, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 622, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 664, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 622, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 118, -1, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produk", "Nama Barang", "Harga Barang", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 1015, 128));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/border transaksi.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 433, -1, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Kategori", "Produk", "Nama Barang", "Harga Barang", "Stock", "Nama Supplier", "Tanggal"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 1015, 150));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/border data barang.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 153, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Search.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 118, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Transaksi.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_alamatpembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatpembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatpembaliActionPerformed

    private void txt_namapembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namapembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namapembeliActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void txt_nopembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nopembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nopembeliActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Tambah_Barang edit = new Tambah_Barang(null, true);
        edit.select(0);
        edit.setVisible(true);
        tabelBarang();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int index = table1.rowAtPoint(evt.getPoint());
        String ID = table1.getValueAt(index, 0).toString();
        String Nama_Barang = table1.getValueAt(index, 1).toString();
        String Harga_Beli = table1.getValueAt(index, 2).toString().replace("Rp", "").replaceAll("\\s+", "").replaceAll("\\.", "");
        String Harga_Jual = table1.getValueAt(index, 3).toString().replace("Rp", "").replaceAll("\\s+", "").replaceAll("\\.", "");
        String Stock = table1.getValueAt(index, 4).toString();
        String Nama_Supplier = table1.getValueAt(index, 5).toString();
        String Kategori = table1.getValueAt(index, 6).toString();

        Tambah_Barang edit = new Tambah_Barang(null, true);
        edit.show(ID, Nama_Barang, Harga_Beli, Harga_Jual, Stock, Nama_Supplier, Kategori);
        edit.select(1);
        edit.setVisible(true);

        tabelBarang();
    }//GEN-LAST:event_table1MouseClicked

        private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
            // TODO add your handling code here:
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                setBarcode(getBarcode());
                afterEnter();
            } else {
                // some character has been read, append it to your "barcode cache"
                setBarcode(getBarcode() + evt.getKeyChar());
            }
        }//GEN-LAST:event_searchKeyReleased

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bayarActionPerformed

    private void totalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalHargaActionPerformed

    private void totalDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalDiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalDiskonActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriActionPerformed

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            // TODO add your handling code here:
            getApasih();
            checkout();

//                popup_pembayaran  pembayaran = new popup_pembayaran(null, true);
//                pembayaran.setVisible(true);
        }//GEN-LAST:event_jButton3ActionPerformed

        private void bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyReleased
            // TODO add your handling code here:
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    Integer.parseInt(bayar.getText());
                    getBayar();
                } catch (NumberFormatException ex) {
                    bayar.setText("");
                    kembalian.setText("");
                }
            }
        }//GEN-LAST:event_bayarKeyReleased

        private void kategoriPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kategoriPopupMenuWillBecomeInvisible
            // TODO add your handling code here:
            tabelBarang();
        }//GEN-LAST:event_kategoriPopupMenuWillBecomeInvisible

        private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
            // TODO add your handling code here:
            int index = table2.rowAtPoint(evt.getPoint());
            idBarang = table2.getValueAt(index, 0).toString();
        }//GEN-LAST:event_table2MouseClicked

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            // TODO add your handling code here:
            try {
                String sql = "delete from tb_detail_transaksi where id = (select id from tb_detail_transaksi where id_transaksi = (select id from tb_transaksi order by id desc limit 1) && id_barang = " + idBarang + " limit 1)";
                Connection conn = com.Koneksi.Koneksi.configDB();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("wkwkwkwk");
            }
            tabelTransaksi();
        }//GEN-LAST:event_jButton2ActionPerformed

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search1ActionPerformed

    private void search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_search1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.swing.Combobox kategori;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search1;
    private com.swing.table.Table table1;
    private com.swing.table.Table table2;
    private javax.swing.JTextField totalDiskon;
    private javax.swing.JTextField totalHarga;
    private javax.swing.JTextField txt_alamatpembali;
    private javax.swing.JTextField txt_namapembeli;
    private javax.swing.JTextField txt_nopembeli;
    // End of variables declaration//GEN-END:variables
}
