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
public class Tambah_Produk extends javax.swing.JDialog {

	/**
	 * Creates new form Tambah_Produk
	 */
    public void Tambah_Produk() {
		initComponents();
		table1.fixTable(jScrollPane1);
                tabelProduk();
    }
                
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Tambah_Produk(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		try {
			this.con = Koneksi.configDB();
		} catch (SQLException ex) {
			Logger.getLogger(Tambah_Produk.class.getName()).log(Level.SEVERE, null, ex);
		}
		initComponents();
                this.setLocationRelativeTo(null);
		tabelProduk();
	}

	private void tabelProduk() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nama Produk");
		try {

			String sql = "select * from tb_produk";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}

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
        btn_hapus1 = new javax.swing.JButton();
        txt_namasupplier = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.swing.table.Table();
        btn_tambah = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(429, 365));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Vector.png"))); // NOI18N
        btn_hapus1.setContentAreaFilled(false);
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        txt_namasupplier.setBackground(new java.awt.Color(255, 224, 233));
        txt_namasupplier.setBorder(null);
        txt_namasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namasupplierActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 84, 150, -1));

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 340));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produk"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 130, 305, 200));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 125, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/input tambah produk.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tampilan tambah produk2.png"))); // NOI18N
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
			String sql = "INSERT INTO `tb_produk`(nama) VALUES ('"+txt_namasupplier.getText()+"')";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
                 tabelProduk();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void txt_namasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namasupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namasupplierActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
this.setVisible(false);
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        tabelProduk();
    }//GEN-LAST:event_formWindowActivated

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int index = table1.rowAtPoint(evt.getPoint());
        String ID = table1.getValueAt(index, 0).toString();
        String nama_supplier = table1.getValueAt(index, 1).toString();

        Edit_Produk edit = new Edit_Produk(null, true);
        edit.show(ID, nama_supplier);
        edit.setVisible(true);

        tabelProduk();
        //        Edit_Produk b = new Edit_Produk((Frame) SwingUtilities.getWindowAncestor(this), true);
        //		b.show();
    }//GEN-LAST:event_table1MouseClicked

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
			java.util.logging.Logger.getLogger(Tambah_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Tambah_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Tambah_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Tambah_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Tambah_Produk dialog = new Tambah_Produk(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.swing.table.Table table1;
    private javax.swing.JTextField txt_namasupplier;
    // End of variables declaration//GEN-END:variables
}
