/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class Tambah_Barang extends javax.swing.JDialog {
//    public int getnamasupplier(){
//        int namasupplier = 0;
//        try{
//            String sql = "SELECT id FROM tb_supplier = '"+txt_produk.getSelectedItem()+"'";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//            java.sql.ResultSet rs = ps.executeQuery(sql);
//            if (rs.next()){
//                produk = rs.getInt(1);
//            }
//        }
//    }
//    public int getproduk(){
//        int produk = 0;
//        try{
//            String sql = "SELECT id FROM tb_produk WHERE nama = '"+txt_produk.getSelectedItem()+"'";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//            java.sql.ResultSet rs = ps.executeQuery(sql);
//            if (rs.next()){
//                produk = rs.getInt(1);
//            }
//        } catch (Exception e){
//            System.out.println("Koneksi gagal");
//        }
//        return produk;
//    }
//    private void tampilcombo1(){
//        try{
//            String sql1 = "SELECT * FROM tb_produk";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
//            java.sql.ResultSet rs=pst.executeQuery(sql1);
//            while (rs.next()){
//                txt_produk.addItem(rs.getString("nama"));
//            }
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(this , e.getMessage());
//        }
//    }
//    public void id_barang(){
//        try{
//            String sql = "SELECT id FROM tb_kategori WHERE kategori = '"+txt_kategori.getSelectedItem()+"'";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//            java.sql.ResultSet rs = ps.executeQuery(sql);
//            if (rs.next()){
//                String kode = rs.getString("id").substring(1);
//                String AN = "" +(Integer.parseInt(kode) +1);
//                String Nol = "";
//                
//                if(AN.length()==1)
//                    (Nol = "00";)
//                    else if(AN.length()==2)
//                        (Nol = "0";)
//                        else if (AN.length()==3)
//                            (Nol )
//            }
//        }
//    }
    public int getKategori(){
        int idKategori = 0;
        try {
            String sql = "SELECT id FROM tb_kategori WHERE kategori = '"+txt_kategori.getSelectedItem()+"'";
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery(sql);
            if (rs.next()){
                idKategori = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Koneksi gagal");
        }
        return idKategori;
    }
    private void tampilCombo(){
         try {
            String sql1 = "SELECT * FROM tb_kategori";
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
            java.sql.ResultSet rs=pst.executeQuery(sql1);
            while(rs.next()){
                txt_kategori.addItem(rs.getString("kategori"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void tampilSupplier(){
         try {
            String sql1 = "SELECT * FROM tb_supplier";
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
            java.sql.ResultSet rs=pst.executeQuery(sql1);
            while(rs.next()){
                txt_supplier.addItem(rs.getString("nama"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }
    
    private void tampilProduk(){
         try {
            String sql1 = "select p.nama from tb_detail_supplier as ds join tb_produk as p on ds.id_produk = p.id join tb_supplier as s on ds.id_supplier = s.id where s.nama = '" + txt_supplier.getSelectedItem() + "'";
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
            java.sql.ResultSet rs=pst.executeQuery(sql1);
            while(rs.next()){
                txt_produk.addItem(rs.getString("nama"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public Tambah_Barang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tampilCombo();
        tampilSupplier();
//        id_barang();
//        setBackground(new Color(0, 0, 0));
//        txt_id.disable();
        
    }
    public void select(int konfirm){
        if ( konfirm == 0 ){
         
            btn_tambah.setVisible(true);          
            btn_simpan.setVisible(false);
            btn_hapus.setVisible(false);
        } else if ( konfirm == 1 ){
            btn_tambah.setVisible(false);
            btn_simpan.setVisible(true);
            btn_hapus.setVisible(true);
       
        } else {
            
        }
    }
     public void show(String Kode_Barcode, String ID, String Produk, String Nama_Barang, String Harga_Beli, String Harga_Jual, String Stock, String Nama_Supplier, String Kategori){
        this.txt_barcode.setText(Kode_Barcode);
        this.txt_id.setText(ID);
        this.txt_produk.getSelectedItem();
        this.txt_namabarang.setText(Nama_Barang);
        this.txt_hargabeli.setText(Harga_Beli);
        this.txt_hargajual.setText(Harga_Jual);
        this.txt_stock.setText(Stock);
        this.txt_supplier.getSelectedItem();
        this.txt_kategori.getSelectedItem();
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
        txt_id = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        txt_hargajual = new javax.swing.JTextField();
        txt_hargabeli = new javax.swing.JTextField();
        txt_namabarang = new javax.swing.JTextField();
        txt_barcode = new javax.swing.JTextField();
        txt_supplier = new com.swing.Combobox();
        txt_kategori = new com.swing.Combobox();
        txt_produk = new com.swing.Combobox();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(645, 365));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_id.setBackground(new java.awt.Color(255, 194, 212));
        txt_id.setBorder(null);
        txt_id.setOpaque(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 124, 150, -1));

        txt_stock.setBackground(new java.awt.Color(255, 194, 212));
        txt_stock.setBorder(null);
        txt_stock.setOpaque(false);
        txt_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stockActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 306, 150, -1));

        txt_hargajual.setBackground(new java.awt.Color(255, 194, 212));
        txt_hargajual.setBorder(null);
        txt_hargajual.setOpaque(false);
        txt_hargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargajualActionPerformed(evt);
            }
        });
        jPanel1.add(txt_hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 264, 150, -1));

        txt_hargabeli.setBackground(new java.awt.Color(255, 194, 212));
        txt_hargabeli.setBorder(null);
        txt_hargabeli.setOpaque(false);
        txt_hargabeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargabeliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_hargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 219, 150, -1));

        txt_namabarang.setBackground(new java.awt.Color(255, 194, 212));
        txt_namabarang.setBorder(null);
        txt_namabarang.setOpaque(false);
        txt_namabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namabarangActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 172, 150, -1));

        txt_barcode.setBackground(new java.awt.Color(255, 194, 212));
        txt_barcode.setBorder(null);
        txt_barcode.setOpaque(false);
        txt_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_barcodeActionPerformed(evt);
            }
        });
        jPanel1.add(txt_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 74, 340, -1));

        txt_supplier.setBackground(new java.awt.Color(255, 194, 212));
        txt_supplier.setLabeText("");
        txt_supplier.setOpaque(false);
        txt_supplier.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                txt_supplierPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        txt_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_supplierActionPerformed(evt);
            }
        });
        jPanel1.add(txt_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 118, 170, 40));

        txt_kategori.setBackground(new java.awt.Color(255, 194, 212));
        txt_kategori.setLabeText("");
        txt_kategori.setOpaque(false);
        txt_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(txt_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 170, 40));

        txt_produk.setBackground(new java.awt.Color(255, 194, 212));
        txt_produk.setToolTipText("");
        txt_produk.setLabeText("");
        txt_produk.setOpaque(false);
        txt_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_produkActionPerformed(evt);
            }
        });
        jPanel1.add(txt_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 170, 40));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 125, 30));

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Simpan.png"))); // NOI18N
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 125, 30));

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button hapus.png"))); // NOI18N
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 125, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 302, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 95.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 215, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 168, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/tambah barang terbaru.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        try {
//            String sql1 = "INSERT INTO `tb_data_barang`(`id`, `nama`, `harga_beli`, 'harga_jual', `stock`, `id_detail_supplier`, `id_kategori`) VALUES ('"+txt_kode_barcode.getText()+"','"+getproduk()+"','"+txt_namabarang.getText()+"','"
//                    +txt_harga.getText()+"','"+txt_stock.getText()+"','"+txt_supplier.getText()+"','"+getKategori()+"')";
            String sql1 = "INSERT INTO `tb_data_barang`(`id`, `nama`, `harga_beli`, `harga_jual`, `stock`, `id_detail_supplier`, `id_kategori`) VALUES ('"+txt_id.getText()+"','"+txt_namabarang.getText()+"','"+txt_hargabeli.getText()+"','"+txt_hargajual.getText()+"','"+txt_stock.getText()+"','"+txt_supplier.getSelectedItem()   +"','"+getKategori()+"')";
            System.out.println(sql1);
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
            pst.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
//       try{
////        String sql = "UPDATE tb_data_barang SET id = '"+txt_kode_barcode.getText()+"', produk = (select id from tb_produk where nama = '"+txt_produk.getSelectedItem()+"'), nama = '"+txt_namabarang.getText()
////                +"', harga_beli = '"+txt_harga.getText()+"', stock = '"+txt_stock.getText()+"', kode_supplier = (select kode_supplier from data_supplier where nama_supplier = '"+txt_supplier.getText()
////                +"'), id_kategori =  (select id_kategori from kategori where kategori = '"+txt_kategori.getSelectedItem()+"') WHERE data_barang.id_barang = '"+txt_kode_barcode.getText()+"'";
//          String sql = "UPDATE `tb_data_barang` SET `id`='"+txt_id.getText()+"',`nama`='"+txt_namabarang.getText()+"',`harga_beli`='"+txt_hargabeli.getText()+"',`harga_jual`='"+txt_hargajual.getText()+"',`stock`='"+txt_stock.getText()+"',`id_detail_supplier`='"+txt_supplier.getSelectedItem()+"',`id_kategori`='"+getKategori()+"' where 1";
//        System.out.println(sql);
//        java.sql.Connection conn= (Connection)com.Koneksi.Koneksi.configDB();
//        java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//  
//        ps.execute();
//        JOptionPane.showMessageDialog(null, "Data berhasil di Update");
//        Transaksi1 t = new Transaksi1();
//        t.tabel();
//    } catch (Exception e){
//        JOptionPane.showMessageDialog(null, "Update Data Gagal"+e.getMessage());
//   }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "DELETE FROM tb_data_barang WHERE id = '"+txt_id.getText()+"'";
            java.sql.Connection conn= (Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_produkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_produkActionPerformed

    private void txt_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_supplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_supplierActionPerformed

    private void txt_namabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namabarangActionPerformed

    private void txt_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_barcodeActionPerformed

    private void txt_hargabeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargabeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargabeliActionPerformed

    private void txt_hargajualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargajualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargajualActionPerformed

    private void txt_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stockActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        txt_id.setBackground(new Color(255,194,212));
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kategoriActionPerformed

    private void txt_supplierPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_txt_supplierPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        tampilProduk();
    }//GEN-LAST:event_txt_supplierPopupMenuWillBecomeInvisible

//     public void id_barang(){
//        try {
//            String sql = "SELECT id FROM tb_data_barang ORDER BY id DESC";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//            java.sql.ResultSet rs = ps.executeQuery(sql);
//            if ( rs.next()){
//                String idBarang = rs.getString("id").substring(2);
//                String br = "BR" +(Integer.toString(idBarang)+1);
//                txt_id.setText(br);
//            } else {
//                txt_id.setText("100");
//            }
//        } catch (Exception e) {
//            System.out.println("Koneksi gagal");
//        }
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tambah_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tambah_Barang dialog = new Tambah_Barang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_barcode;
    private javax.swing.JTextField txt_hargabeli;
    private javax.swing.JTextField txt_hargajual;
    private javax.swing.JTextField txt_id;
    private com.swing.Combobox txt_kategori;
    private javax.swing.JTextField txt_namabarang;
    private com.swing.Combobox txt_produk;
    private javax.swing.JTextField txt_stock;
    private com.swing.Combobox txt_supplier;
    // End of variables declaration//GEN-END:variables
}
