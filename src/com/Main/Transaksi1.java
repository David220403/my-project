/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public final class Transaksi1 extends javax.swing.JPanel {

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

			String sql = "select db.id, db.nama, db.harga_beli, db.harga_jual, db.stock, k.kategori, s.nama, p.nama from tb_data_barang as db join tb_detail_supplier as ds on db.id_detail_supplier = ds.id join tb_kategori as k on db.id_kategori = k.id join tb_supplier as s on ds.id_supplier = s.id join tb_produk as p on ds.id_produk = p.id";
			Connection conn = com.Koneksi.Koneksi.configDB();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				model.addRow(new Object[]{
					rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
				});

			}
			table1.setModel(model);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void tabelTransaksi() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nama Barang");
		model.addColumn("Harga Jual");
		model.addColumn("Jumlah");

		try {

			String sql = "select dt.id, dt.id_transaksi, db.nama, db.harga_jual, count(dt.jumlah) as \"Jumlah\" from tb_detail_transaksi as dt join tb_transaksi as t on dt.id_transaksi = t.id join tb_data_barang as db on dt.id_barang = db.id where t.id = (select id from tb_transaksi order by id desc limit 1) group by db.id";
			Connection conn = com.Koneksi.Koneksi.configDB();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				model.addRow(new Object[]{
					rs.getString(1), rs.getString(3), rs.getString(4),
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
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
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

        txt_search.setBackground(new java.awt.Color(255, 224, 233));
        txt_search.setBorder(null);
        txt_search.setOpaque(false);
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        jPanel1.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 119, 150, -1));

        jTextField7.setBackground(new java.awt.Color(255, 224, 233));
        jTextField7.setBorder(null);
        jTextField7.setOpaque(false);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 626, 150, -1));

        jTextField6.setBackground(new java.awt.Color(255, 224, 233));
        jTextField6.setBorder(null);
        jTextField6.setOpaque(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 621, 150, -1));

        jTextField5.setBackground(new java.awt.Color(255, 224, 233));
        jTextField5.setBorder(null);
        jTextField5.setOpaque(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 625, 150, -1));

        jTextField4.setBackground(new java.awt.Color(255, 224, 233));
        jTextField4.setBorder(null);
        jTextField4.setOpaque(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 668, 150, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Bayar.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174, 397, 125, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button hapus.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Search (2).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 115, -1, -1));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produk", "Nama Barang", "Harga Barang", "Jumlah"
            }
        ));
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Kategori.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(135, 30));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 114, 155, -1));

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

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		Tambah_Barang edit = new Tambah_Barang(null, true);
		edit.select(0);
		edit.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
//		int index = table1.rowAtPoint(evt.getPoint());
//		String ID = table1.getValueAt(index, 0).toString();
//		String Produk = table1.getValueAt(index, 1).toString();
//		String Nama_Barang = table1.getValueAt(index, 2).toString();
//		String Harga_Barang = table1.getValueAt(index, 3).toString();
//		String Stock = table1.getValueAt(index, 4).toString();
//		String Nama_Supplier = table1.getValueAt(index, 5).toString();
//		String Kategori = table1.getValueAt(index, 6).toString();
//
//		Tambah_Barang edit = new Tambah_Barang(null, true);
//		edit.show(ID, Produk, Nama_Barang, Harga_Barang, Stock, Nama_Supplier, Kategori);
//		edit.select(1);
//		edit.setVisible(true);

//		tabel();
    }//GEN-LAST:event_table1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private com.swing.table.Table table1;
    private com.swing.table.Table table2;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
