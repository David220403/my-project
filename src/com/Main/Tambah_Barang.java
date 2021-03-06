package com.Main;

import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Tambah_Barang extends javax.swing.JDialog {
    
    
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

	public int getKategori() {
		int idKategori = 0;
		try {
			String sql = "SELECT id FROM tb_kategori WHERE kategori = '" + txt_kategori.getSelectedItem() + "'";
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			java.sql.ResultSet rs = ps.executeQuery(sql);
			if (rs.next()) {
				idKategori = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Koneksi gagal");
		}
		return idKategori;
	}
        
        public void getBarcode(){
            try {
			String namaFile = "/com/Main/Barcode.jasper";
			             InputStream Report;
			Report = getClass().getResourceAsStream(namaFile);
                        Connection con = com.Koneksi.Koneksi.configDB();
			             HashMap param = new HashMap();
			param.put("id_barang", txt_id.getText());
			             JasperPrint JPrint = JasperFillManager.fillReport(Report, param, con);
			             JasperViewer.viewReport(JPrint, false);
		} catch (SQLException | JRException ex) {
                    System.out.println(ex.getMessage());
		}
        }

	private void tampilCombo() {
		try {
			String sql1 = "SELECT * FROM tb_kategori";
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql1);
			java.sql.ResultSet rs = pst.executeQuery(sql1);
			while (rs.next()) {
				txt_kategori.addItem(rs.getString("id"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void tampilSupplier() {
		try {
			String sql1 = "SELECT id FROM tb_detail_supplier";
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql1);
			java.sql.ResultSet rs = pst.executeQuery(sql1);
			while (rs.next()) {
				txt_supplier.addItem(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public Tambah_Barang(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
                setRupiah();

		initComponents();
		tampilCombo();
		tampilSupplier();
//        id_barang();
//        setBackground(new Color(0, 0, 0));
//        txt_id.disable();

	}

	public void select(int konfirm) {
		switch (konfirm) {
			case 0:
				btn_tambah.setVisible(true);
				btn_simpan.setVisible(false);
				btn_hapus.setVisible(false);
				btn_barcode.setVisible(false);
				break;
			case 1:
				btn_tambah.setVisible(false);
				btn_simpan.setVisible(true);
				btn_hapus.setVisible(true);
				btn_barcode.setVisible(true);
				break;
			default:
				break;
		}
	}

	public void show(String ID, String Nama_Barang, String Harga_Beli, String Harga_Jual, String Stock, String Nama_Supplier, String Kategori) {
		this.txt_id.setText(ID);
		this.txt_namabarang.setText(Nama_Barang);
		this.txt_hargabeli.setText(Harga_Beli);
		this.txt_hargajual.setText(Harga_Jual);
		this.txt_stock.setText(Stock);
		this.txt_supplier.getSelectedItem();
		this.txt_kategori.getSelectedItem();
	}

	private void getInfo(String value, String type){
		try{
		String sql = null;
		switch(type){
			case "supplier":
				sql = "select s.nama,p.nama from tb_supplier as s join tb_detail_supplier as ds on s.id = ds.id_supplier join tb_produk as p on ds.id_produk = p.id where ds.id = '"+value+"'";
				break;
			case "kategori":
				sql = "select id, kategori from tb_kategori where id = '"+value+"'";
				break;
			default:
				break;
		}
			System.out.println(sql);
            java.sql.Connection conn=(Connection)com.Koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
	    java.sql.ResultSet rs = pst.executeQuery();
	    if(rs.next()){
            JOptionPane.showMessageDialog(this, rs.getString(1) + ": " + rs.getString(2));
	    }
		}catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
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
        txt_id = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        txt_hargajual = new javax.swing.JTextField();
        txt_hargabeli = new javax.swing.JTextField();
        txt_namabarang = new javax.swing.JTextField();
        txt_supplier = new com.swing.Combobox();
        txt_kategori = new com.swing.Combobox();
        btn_hapus1 = new javax.swing.JButton();
        btn_barcode = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(645, 365));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_id.setBackground(new java.awt.Color(255, 194, 212));
        txt_id.setBorder(null);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 88, 150, -1));

        txt_stock.setBackground(new java.awt.Color(255, 194, 212));
        txt_stock.setBorder(null);
        txt_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stockActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 150, -1));

        txt_hargajual.setBackground(new java.awt.Color(255, 194, 212));
        txt_hargajual.setBorder(null);
        txt_hargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargajualActionPerformed(evt);
            }
        });
        jPanel1.add(txt_hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 226, 150, -1));

        txt_hargabeli.setBackground(new java.awt.Color(255, 194, 212));
        txt_hargabeli.setBorder(null);
        txt_hargabeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargabeliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_hargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 150, -1));

        txt_namabarang.setBackground(new java.awt.Color(255, 194, 212));
        txt_namabarang.setBorder(null);
        txt_namabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namabarangActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 134, 150, -1));

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
        jPanel1.add(txt_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 84, 170, 40));

        txt_kategori.setBackground(new java.awt.Color(255, 194, 212));
        txt_kategori.setLabeText("");
        txt_kategori.setOpaque(false);
        txt_kategori.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                txt_kategoriPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        txt_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(txt_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 154, 170, 40));

        btn_hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Vector.png"))); // NOI18N
        btn_hapus1.setContentAreaFilled(false);
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 30, 30));

        btn_barcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button barcode.png"))); // NOI18N
        btn_barcode.setContentAreaFilled(false);
        btn_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barcodeActionPerformed(evt);
            }
        });
        jPanel1.add(btn_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 125, 30));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Button tambah.png"))); // NOI18N
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 125, 30));

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
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 266, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 222, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 176, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Rectangle 53 (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 84, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tambah barang baru lagi.png"))); // NOI18N
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
			String sql = "insert into tb_data_barang values ('', '" + txt_namabarang.getText() + "', " + txt_hargabeli.getText() + ", " + txt_hargajual.getText() + ", " + txt_stock.getText() + ", '" + txt_supplier.getSelectedItem() + "', '" + txt_kategori.getSelectedItem() + "')";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.setVisible(false);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
		// TODO add your handling code here:
		try {
			String sql = "UPDATE `tb_data_barang` SET `nama`='" + txt_namabarang.getText() + "',`harga_beli`='" + Integer.parseInt(txt_hargabeli.getText().replaceAll("\\.", "")) + "',`harga_jual`='" + Integer.parseInt(txt_hargajual.getText().replaceAll("\\.", "")) + "',`stock`='" + txt_stock.getText() + "',`id_detail_supplier`='" + txt_supplier.getSelectedItem() + "',`id_kategori`='" + txt_kategori.getSelectedItem() + "' WHERE id = '" + txt_id.getText() + "'";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.setVisible(false);
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
		// TODO add your handling code here:
		try {
			String sql = "DELETE FROM tb_data_barang WHERE id = '" + txt_id.getText() + "'";
			java.sql.Connection conn = (Connection) com.Koneksi.Koneksi.configDB();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		this.setVisible(false);
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_supplierActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_supplierActionPerformed

    private void txt_namabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namabarangActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_namabarangActionPerformed

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
		txt_id.setBackground(new Color(255, 194, 212));
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kategoriActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txt_kategoriActionPerformed

    private void txt_supplierPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_txt_supplierPopupMenuWillBecomeInvisible
		// TODO add your handling code here:
		getInfo((String)txt_supplier.getSelectedItem(), "supplier");
    }//GEN-LAST:event_txt_supplierPopupMenuWillBecomeInvisible

    private void btn_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barcodeActionPerformed
    // TODO add your handling code here:
    getBarcode();
    }//GEN-LAST:event_btn_barcodeActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_hapus1ActionPerformed

        private void txt_kategoriPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_txt_kategoriPopupMenuWillBecomeInvisible
                // TODO add your handling code here:
		getInfo((String)txt_kategori.getSelectedItem(), "kategori");
        }//GEN-LAST:event_txt_kategoriPopupMenuWillBecomeInvisible

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
    private javax.swing.JButton btn_barcode;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_hargabeli;
    private javax.swing.JTextField txt_hargajual;
    private javax.swing.JTextField txt_id;
    private com.swing.Combobox txt_kategori;
    private javax.swing.JTextField txt_namabarang;
    private javax.swing.JTextField txt_stock;
    private com.swing.Combobox txt_supplier;
    // End of variables declaration//GEN-END:variables
}
