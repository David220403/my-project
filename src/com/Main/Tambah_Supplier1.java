/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.Koneksi.Koneksi;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class Tambah_Supplier1 extends javax.swing.JDialog {

    /**
     * Creates new form Tambah_Supplier1
     */
    Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Tambah_Supplier1(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		try {
			this.con = Koneksi.configDB();
		} catch (SQLException ex) {
			Logger.getLogger(Tambah_Produk.class.getName()).log(Level.SEVERE, null, ex);
		}
		initComponents();
		tabelProduk();
	}
    private void tabelProduk() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nama supplier");
                model.addColumn("Nama Toko");
                model.addColumn("Alamat");
		try {

			String sql = "select * from tb_supplier";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}

	}
    public void kosongkan(){
        txt_id.setText("");
        txt_namasupplier.setText("");
        txt_namatoko.setText("");
        txt_alamat.setText("");
        tabelProduk();
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
        txt_namatoko = new javax.swing.JTextField();
        txt_namasupplier = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.swing.table.Table();
        btn_hapus1 = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(645, 365));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_namatoko.setBackground(new java.awt.Color(255, 224, 233));
        txt_namatoko.setBorder(null);
        txt_namatoko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namatokoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namatoko, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 132, 150, -1));

        txt_namasupplier.setBackground(new java.awt.Color(255, 224, 233));
        txt_namasupplier.setBorder(null);
        txt_namasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namasupplierActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 88, 150, -1));

        txt_alamat.setBackground(new java.awt.Color(255, 224, 233));
        txt_alamat.setBorder(null);
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 174, 150, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Supplier", "Nama Toko", "Alamat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 370, 250));

        btn_hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Vector.png"))); // NOI18N
        btn_hapus1.setContentAreaFilled(false);
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 30, 30));

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button hapus.png"))); // NOI18N
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 125, 30));

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button Simpan.png"))); // NOI18N
        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 125, 30));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 125, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/input tambah produk.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/input tambah produk.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 128, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/input tambah produk.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 84, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/tampilan supplier.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txt_id.setBackground(new java.awt.Color(255, 224, 233));
        txt_id.setBorder(null);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 150, -1));

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
			String sql = "INSERT INTO `tb_supplier`(`id`, `nama`, `toko`, `alamat`) VALUES ('','"+txt_namasupplier.getText()+"','"+txt_namatoko.getText()+"','"+txt_alamat.getText()+"')";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
         try {
			String sql = "UPDATE `tb_supplier` SET `id`='',`nama`='"+txt_namasupplier.getText()+"',`toko`='"+txt_namatoko.getText()+"',`alamat`='"+txt_alamat.getText()+"' WHERE id='"+txt_id.getText()+"'";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
         kosongkan();
         tabelProduk();
                
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try {
			String sql = "DELETE FROM tb_supplier WHERE id = '" + txt_id.getText() + "'";
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
       this.setVisible(false);
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_namasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namasupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namasupplierActionPerformed

    private void txt_namatokoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namatokoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namatokoActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
         int baris = table1.rowAtPoint(evt.getPoint());
         txt_id.setText(table1.getValueAt(baris, 0).toString());
         txt_id.disable();
        txt_namasupplier.setText(table1.getValueAt(baris, 1).toString());
        txt_namatoko.setText(table1.getValueAt(baris, 2).toString());
        txt_alamat.setText(table1.getValueAt(baris, 3).toString());
    }//GEN-LAST:event_table1MouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        tabelProduk();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(Tambah_Supplier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Supplier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Supplier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Supplier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tambah_Supplier1 dialog = new Tambah_Supplier1(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.swing.table.Table table1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_namasupplier;
    private javax.swing.JTextField txt_namatoko;
    // End of variables declaration//GEN-END:variables
}
