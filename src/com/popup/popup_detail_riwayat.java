/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class popup_detail_riwayat extends javax.swing.JDialog {

    String id = null;

    public popup_detail_riwayat(java.awt.Frame parent, boolean modal, String id) {
        super(parent, modal);
        initComponents();
        this.id = id;
        tabelRiwayat();
        this.setLocationRelativeTo(null);
    }

    private void tabelRiwayat() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Barang");
        model.addColumn("ID Transaksi");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Jumlah");

        try {

            String sql = "select dt.id_barang, dt.id_transaksi, db.nama, db.harga_jual, count(dt.jumlah) as \"Jumlah\" from tb_detail_transaksi as dt join tb_transaksi as t on dt.id_transaksi = t.id join tb_data_barang as db on dt.id_barang = db.id where t.id = " + id + " group by db.id;";
            Connection conn = com.Koneksi.Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)
                });

            }

            table1.setModel(model);

        } catch (SQLException e) {
            System.out.println(e);
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
        btn_tambah1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.swing.table.Table();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1064, 533));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_tambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ok.png"))); // NOI18N
        btn_tambah1.setContentAreaFilled(false);
        btn_tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 125, 30));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Pembeli", "Alamat Pembeli", "No Telp Pembeli", "Nama Barang", "Harga Barang", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 980, 380));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/border_riwayat.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/popup_detal_riwayat.png"))); // NOI18N
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

    private void btn_tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_tambah1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tambah1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
