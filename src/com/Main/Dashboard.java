
package com.Main;

import com.Koneksi.Koneksi;
import com.swing.chart.ModelChart;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author david
 */
public final class Dashboard extends javax.swing.JPanel {
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

	private int waktumulai = 0;
	private Component jLabel7;
        
        DecimalFormat kursindonesia;
        DecimalFormatSymbols formatrupiah;

	/**
	 * Creates new form Dashboard
	 */
	public Dashboard() {
		initComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation(
			(screenSize.width = frameSize.width) / 2,
			(screenSize.height = frameSize.height) / 2
		);

		barangterjual();
		supplier();
		transaksi();
		diskon();
		pemasukan();
		pengeluaran();
		Tampil_Jam();

		loadChartOne();
		loadChartTwo();
                
                kursindonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                formatrupiah = new DecimalFormatSymbols();
                
                formatrupiah.setCurrencySymbol("Rp. ");
                formatrupiah.setMonetaryDecimalSeparator(',');
                formatrupiah.setGroupingSeparator('.');
                kursindonesia.setDecimalFormatSymbols(formatrupiah);
                
	}

	private void loadChartOne() {
		// ini buat yg dibawah minggu 1 minggu 2, yang bawah nyesuaikan sama yang bawah.
		chart.addLegend("Transaksi", new Color(245, 189, 135)); // line 1 

		try {
			String sql = "select db.nama, count(jumlah) from tb_detail_transaksi dt join tb_data_barang db on dt.id_barang = db.id group by id_barang order by count(jumlah) desc limit 5";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
		chart.addData(new ModelChart(rs.getString(1), new double[]{rs.getDouble(2)}));  // 1, 3 itu 1nya line 1. kalo 3nya buat line 2
			}
		} catch (Exception e) {

		}
	}

	private void loadChartTwo() {
		// ini buat yg dibawah minggu 1 minggu 2, yang bawah nyesuaikan sama yang bawah.
		chart1.addLegend("Transaksi", new Color(245, 189, 135)); // line 1 

		try {
			String sql = "select nama, stock from tb_data_barang db where stock <= 10 limit 5";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
		chart1.addData(new ModelChart(rs.getString(1), new double[]{rs.getDouble(2)}));  // 1, 3 itu 1nya line 1. kalo 3nya buat line 2
			}
		} catch (Exception e) {

		}
	}

	private double perMonth(int bulan){
		double day = 0;
		try {
			String sql = "select count(*) from tb_transaksi where month(tanggal) = " + bulan;
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				day = rs.getDouble(1);
			}
		} catch (Exception e) {

		}
		return day;
	}

	public void Tampil_Jam() {
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				String nol_jam = "", nol_menit = "", nol_detik = "";

				java.util.Date dateTime = new java.util.Date();
				int nilai_jam = dateTime.getHours();
				int nilai_menit = dateTime.getMinutes();
				int nilai_detik = dateTime.getSeconds();

				if (nilai_jam <= 9) {
					nol_jam = "0";
				}
				if (nilai_menit <= 9) {
					nol_menit = "0";
				}
				if (nilai_detik <= 9) {
					nol_detik = "0";
				}

				String jam = nol_jam + Integer.toString(nilai_jam);
				String menit = nol_menit + Integer.toString(nilai_menit);
				String detik = nol_detik + Integer.toString(nilai_detik);

				labeljamrealtime.setText(jam + ":" + menit + ":" + detik + "");
			}
		};
		new Timer(1000, taskPerformer).start();
	}

	private void jamrealtime() {
		new Thread() {
			@Override
			public void run() {
				while (waktumulai == 0) {
					Calendar kalender = new GregorianCalendar();
					int jam = kalender.get(Calendar.HOUR);
					//int menit = kalender.get(Calendar.MINUTE):
					int detik = kalender.get(Calendar.SECOND);
					int AMPM = kalender.get(Calendar.AM_PM);
					String SiangMalam;
					if (AMPM == 1) {
						SiangMalam = "PM";
					} else {
						SiangMalam = "AM";
					}
					String jamrealtime = jam + "i" + "i" + detik + " " + SiangMalam;
					labeljamrealtime.setText("jam: " + jamrealtime);
				}
			}
		}.start();
	}

	/**
	 * Creates new form Dashboard
	 */

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chart1 = new com.swing.chart.Chart();
        chart = new com.swing.chart.Chart();
        labeljamrealtime = new javax.swing.JLabel();
        jumlah_pengeluaran = new javax.swing.JLabel();
        jumlah_pemasukan = new javax.swing.JLabel();
        jumlah_DataDiskon = new javax.swing.JLabel();
        jumlah_dataTransaksi = new javax.swing.JLabel();
        jumlah_dataSupplier = new javax.swing.JLabel();
        jumlah_dataBarang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chart1.setBackground(new java.awt.Color(255, 158, 187));
        jPanel1.add(chart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 370, 290));

        chart.setBackground(new java.awt.Color(255, 158, 187));
        jPanel1.add(chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 370, 290));

        labeljamrealtime.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jPanel1.add(labeljamrealtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 30, 190, 60));

        jumlah_pengeluaran.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(jumlah_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1152, 516, -1, -1));

        jumlah_pemasukan.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(jumlah_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1152, 331, -1, -1));

        jumlah_DataDiskon.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(jumlah_DataDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1154, 170, 20, 20));

        jumlah_dataTransaksi.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(jumlah_dataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(859, 170, -1, 20));

        jumlah_dataSupplier.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jumlah_dataSupplier.setIconTextGap(18);
        jPanel1.add(jumlah_dataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 170, 20, 20));

        jumlah_dataBarang.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(jumlah_dataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 167, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tampilan dashboard.png"))); // NOI18N
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.swing.chart.Chart chart;
    private com.swing.chart.Chart chart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jumlah_DataDiskon;
    private javax.swing.JLabel jumlah_dataBarang;
    private javax.swing.JLabel jumlah_dataSupplier;
    private javax.swing.JLabel jumlah_dataTransaksi;
    private javax.swing.JLabel jumlah_pemasukan;
    private javax.swing.JLabel jumlah_pengeluaran;
    private javax.swing.JLabel labeljamrealtime;
    // End of variables declaration//GEN-END:variables
public void barangterjual() {
		try {
			String sql = "SELECT COUNT(*) FROM tb_data_barang";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_dataBarang.setText(rs.getString(1));
			}
		} catch (Exception e) {

		}
	}

	public void supplier() {
		try {
			String sql = "SELECT COUNT(*) FROM tb_detail_supplier";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_dataSupplier.setText(rs.getString(1));
			}
		} catch (Exception e) {

		}
	}

	public void transaksi() {
		try {
			String sql = "SELECT COUNT(*) FROM tb_detail_transaksi";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_dataTransaksi.setText(rs.getString(1));
			}
		} catch (Exception e) {

		}
	}

	public void diskon() {
		try {
			String sql = "SELECT COUNT(*) FROM tb_diskon";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_DataDiskon.setText(rs.getString(1));
			}
		} catch (Exception e) {

		}
	}

	public void pemasukan() {
		try {
			String sql = "SELECT SUM(total_harga) FROM tb_transaksi";
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_pemasukan.setText (this.formatRupiah(Integer.parseInt(rs.getString(1))).replace(",00", ""));
			}
		} catch (Exception e) {

		}
	}

	public void pengeluaran() {
		try {
			String sql = "SELECT SUM(harga_beli) FROM tb_data_barang";
			System.out.println(sql);
			java.sql.Connection conn = (Connection) Koneksi.configDB();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				jumlah_pengeluaran.setText (this.formatRupiah(Integer.parseInt(rs.getString(1))).replace(",00", ""));
			}
		} catch (Exception e) {

		}
	}

	private static class jLabel3 {

		public jLabel3() {
		}
	}
}
