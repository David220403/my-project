/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class Data_Supplier extends javax.swing.JPanel {

	public void tabel() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Kode Supplier");
		model.addColumn("Nama Supplier");
		model.addColumn("Nama Toko");
		model.addColumn("Alamat");
		model.addColumn("Produk");

		try {

			String sql = "select dt.id, s.nama, s.toko, s.alamat, p.nama from tb_detail_supplier as dt join tb_supplier as s on dt.id_supplier = s.id join tb_produk as p on p.id = dt.id_produk";
			Connection conn = com.Koneksi.Koneksi.configDB();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				model.addRow(new Object[]{
					rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
				});
			}
			table1.setModel(model);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	Tambah_Detail_Supplier edit = new Tambah_Detail_Supplier(null, true);

	public Data_Supplier() {
		initComponents();
		table1.fixTable(jScrollPane1);
		tabel();
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
        btn_supplier = new javax.swing.JButton();
        btn_produk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.swing.table.Table();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button supplier.png"))); // NOI18N
        btn_supplier.setContentAreaFilled(false);
        btn_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supplierActionPerformed(evt);
            }
        });
        jPanel1.add(btn_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(881, 660, 125, 30));

        btn_produk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/button produk.png"))); // NOI18N
        btn_produk.setContentAreaFilled(false);
        btn_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_produkActionPerformed(evt);
            }
        });
        jPanel1.add(btn_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1033, 660, 125, 30));

        table1.setBackground(new java.awt.Color(255, 158, 187));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Supplier", "Nama Supplier", "Nama Toko", "Alamat", "Produk"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 1020, 495));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Background Table.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 83, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1179, 660, 125, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tampilan supplier baru.png"))); // NOI18N
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:

		Tambah_Detail_Supplier supplier = new Tambah_Detail_Supplier(null, true);
		edit.select1(0);
		edit.setVisible(true);
		tabel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
		// TODO add your handling code here:
		int index = table1.rowAtPoint(evt.getPoint());
		String Kode_Supplier = table1.getValueAt(index, 0).toString();
		String Nama_Supplier = table1.getValueAt(index, 1).toString();
		String Nama_Toko = table1.getValueAt(index, 2).toString();
		String Alamat = table1.getValueAt(index, 3).toString();
		String Produk = table1.getValueAt(index, 4).toString();

		Tambah_Detail_Supplier edit = new Tambah_Detail_Supplier(null, true);
		edit.show1(Kode_Supplier, Nama_Supplier, Nama_Toko, Alamat, Produk);
		edit.select1(1);
		edit.setVisible(true);

		tabel();
    }//GEN-LAST:event_table1MouseClicked

    private void btn_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_produkActionPerformed
		// TODO add your handling code here:
		Tambah_Produk b = new Tambah_Produk((Frame) SwingUtilities.getWindowAncestor(this), true);
		b.show();
    }//GEN-LAST:event_btn_produkActionPerformed

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
		// TODO add your handling code here:
		Tambah_Supplier b = new Tambah_Supplier((Frame) SwingUtilities.getWindowAncestor(this), true);
		b.show();
    }//GEN-LAST:event_btn_supplierActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_supplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
