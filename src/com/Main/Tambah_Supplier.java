/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class Tambah_Supplier extends javax.swing.JDialog {

	/**
	 * Creates new form Tambah_Supplier
	 */
	public Tambah_Supplier(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
        txt_kodesupplier.disable();
	}
        public void select1(int konfirm){
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
        public void show1(String Kode_Supplier, String Nama_Supplier, String Nama_Toko, String Alamat, String Produk) {
		this.txt_kodesupplier.setText(Kode_Supplier);
		this.txt_namasupplier.setText(Nama_Supplier);
		this.txt_namatoko.setText(Nama_Toko);
		this.txt_alamat.setText(Alamat);
		this.txt_produk.setText(Produk);
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
        txt_produk = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_namatoko = new javax.swing.JTextField();
        txt_namasupplier = new javax.swing.JTextField();
        txt_kodesupplier = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(430, 365));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_produk.setBackground(new java.awt.Color(255, 194, 212));
        txt_produk.setBorder(null);
        txt_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_produkActionPerformed(evt);
            }
        });
        jPanel1.add(txt_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 292, 150, -1));

        txt_alamat.setBackground(new java.awt.Color(255, 194, 212));
        txt_alamat.setBorder(null);
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 249, 150, -1));

        txt_namatoko.setBackground(new java.awt.Color(255, 194, 212));
        txt_namatoko.setBorder(null);
        txt_namatoko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namatokoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namatoko, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 204, 150, -1));

        txt_namasupplier.setBackground(new java.awt.Color(255, 194, 212));
        txt_namasupplier.setBorder(null);
        txt_namasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namasupplierActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 159, 150, -1));

        txt_kodesupplier.setBackground(new java.awt.Color(255, 194, 212));
        txt_kodesupplier.setBorder(null);
        txt_kodesupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodesupplierActionPerformed(evt);
            }
        });
        jPanel1.add(txt_kodesupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 109, 150, -1));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 249, 125, 30));

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Simpan.png"))); // NOI18N
        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 249, 125, 30));

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button hapus.png"))); // NOI18N
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 292, 125, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 288, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 245, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 155, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tambah Supplier.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -15, 430, 400));

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

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
		// TODO add your handling code here:
//                try {
//			String sql = "UPDATE `tb_detail_supplier ` SET `id`='"+txt_kodesupplier.getText()+"',`nama`='"+txt_hargabeli.getText()+"',`harga_jual`='"+txt_hargajual.getText()+"',`stock`='"+txt_stock.getText()+"',`id_detail_supplier`='"+txt_supplier.getSelectedItem()+"',`id_kategori`='"+txt_kategori.getSelectedItem()+"' WHERE id = '"+txt_id.getText()+"'";
//			System.out.println(sql);
//			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
//			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//			pst.execute();
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//		}
//		this.setVisible(false);
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_kodesupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodesupplierActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_kodesupplierActionPerformed

    private void txt_namasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namasupplierActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_namasupplierActionPerformed

    private void txt_namatokoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namatokoActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_namatokoActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_produkActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_produkActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "DELETE FROM tb_detail_supplier WHERE id = '"+txt_kodesupplier.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn= (Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.setVisible(false);
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        try {
            String sql1 = "INSERT INTO `tb_supplier`(`id`, `nama`, `toko`, 'alamat') VALUES ('"+txt_kodesupplier.getText()+"','"+txt_namasupplier.getText()+"','"+txt_namatoko.getText()+"','"
            +txt_alamat.getText()+"')";
            System.out.println(sql1);
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
            pst.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.setVisible(false);
    }//GEN-LAST:event_btn_tambahActionPerformed
// public void id_barang(){
//        try {
//            String sql = "SELECT id FROM tb_detail_supplier ORDER BY id DESC";
//            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
//            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
//            java.sql.ResultSet rs = ps.executeQuery(sql);
//            if ( rs.next()){
//                String idBarang = rs.getString("id").substring(2);
//                String br = "BR" +(Integer.parseInt(idBarang)+1);
//                txt_kodesupplier.setText(br);
//            } else {
//                txt_kodesupplier.setText("BR100");
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
			java.util.logging.Logger.getLogger(Tambah_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Tambah_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Tambah_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Tambah_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Tambah_Supplier dialog = new Tambah_Supplier(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_kodesupplier;
    private javax.swing.JTextField txt_namasupplier;
    private javax.swing.JTextField txt_namatoko;
    private javax.swing.JTextField txt_produk;
    // End of variables declaration//GEN-END:variables
}
