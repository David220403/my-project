-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 23, 2022 at 06:43 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_skincare`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_akun`
--

CREATE TABLE `tb_akun` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_akun`
--

INSERT INTO `tb_akun` (`id`, `nama`, `username`, `password`, `role`) VALUES
(1, 'david', 'david', '123', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_data_barang`
--

CREATE TABLE `tb_data_barang` (
  `id` varchar(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `id_detail_supplier` varchar(11) NOT NULL,
  `id_kategori` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_data_barang`
--

INSERT INTO `tb_data_barang` (`id`, `nama`, `harga_beli`, `harga_jual`, `stock`, `id_detail_supplier`, `id_kategori`) VALUES
('2200001', 'loldek', 10000, 2000, 6, 'DS00002', 'K00001');

--
-- Triggers `tb_data_barang`
--
DELIMITER $$
CREATE TRIGGER `format_idBarang` BEFORE INSERT ON `tb_data_barang` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_data_barang);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_data_barang order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT(right(year(now()),2), RIGHT(CONCAT('0000', (@countColumn + 1)),5));
	ELSE
		SET NEW.id = CONCAT(right(year(now()),2), RIGHT(CONCAT('0000', @lastColumn + 1),5));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_supplier`
--

CREATE TABLE `tb_detail_supplier` (
  `id` varchar(11) NOT NULL,
  `id_supplier` varchar(11) NOT NULL,
  `id_produk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_detail_supplier`
--

INSERT INTO `tb_detail_supplier` (`id`, `id_supplier`, `id_produk`) VALUES
('DS00001', 'S00001', 2),
('DS00002', 'S00001', 1);

--
-- Triggers `tb_detail_supplier`
--
DELIMITER $$
CREATE TRIGGER `format_idDetailSupplier` BEFORE INSERT ON `tb_detail_supplier` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_detail_supplier);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_detail_supplier order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("DS", RIGHT(CONCAT('0000', (@countColumn + 1)),5));
	ELSE
		SET NEW.id = CONCAT("DS", RIGHT(CONCAT('0000', @lastColumn + 1),5));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_transaksi`
--

CREATE TABLE `tb_detail_transaksi` (
  `id` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_diskon`
--

CREATE TABLE `tb_diskon` (
  `id` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `tanggal` date NOT NULL,
  `persen` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_kategori`
--

CREATE TABLE `tb_kategori` (
  `id` varchar(11) NOT NULL,
  `kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kategori`
--

INSERT INTO `tb_kategori` (`id`, `kategori`) VALUES
('K00001', 'Sabun Ngocok'),
('K00002', 'Sabun Coli');

--
-- Triggers `tb_kategori`
--
DELIMITER $$
CREATE TRIGGER `format_idKategori` BEFORE INSERT ON `tb_kategori` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_kategori);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_kategori order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("K", RIGHT(CONCAT('0000', (@countColumn + 1)),5));
	ELSE
		SET NEW.id = CONCAT("K", RIGHT(CONCAT('0000', @lastColumn + 1),5));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_pelanggan`
--

CREATE TABLE `tb_pelanggan` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pelanggan`
--

INSERT INTO `tb_pelanggan` (`id`, `nama`, `alamat`, `no_telp`) VALUES
(1, 'Memeki Ladina', 'awikwok', '11324kepo');

-- --------------------------------------------------------

--
-- Table structure for table `tb_produk`
--

CREATE TABLE `tb_produk` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_produk`
--

INSERT INTO `tb_produk` (`id`, `nama`) VALUES
(1, 'SK II'),
(2, 'MS Glow');

-- --------------------------------------------------------

--
-- Table structure for table `tb_supplier`
--

CREATE TABLE `tb_supplier` (
  `id` varchar(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `toko` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_supplier`
--

INSERT INTO `tb_supplier` (`id`, `nama`, `toko`, `alamat`) VALUES
('S00001', 'raihan geming', 'awikwokStore', 'toko kontolodon'),
('S00002', 'DEVI CANTIKKK LUCU', 'love you', '<33333');

--
-- Triggers `tb_supplier`
--
DELIMITER $$
CREATE TRIGGER `format_idSupplier` BEFORE INSERT ON `tb_supplier` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_supplier);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_supplier order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("S", RIGHT(CONCAT('0000', (@countColumn + 1)),5));
	ELSE
		SET NEW.id = CONCAT("S", RIGHT(CONCAT('0000', @lastColumn + 1),5));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaksi`
--

CREATE TABLE `tb_transaksi` (
  `id` int(11) NOT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `dibayar` int(11) DEFAULT NULL,
  `kembalian` int(11) DEFAULT NULL,
  `tanggal` datetime DEFAULT current_timestamp(),
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_akun` int(11) NOT NULL,
  `id_diskon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_akun`
--
ALTER TABLE `tb_akun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_data_barang`
--
ALTER TABLE `tb_data_barang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_detail_supplier` (`id_detail_supplier`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `tb_detail_supplier`
--
ALTER TABLE `tb_detail_supplier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_supplier` (`id_supplier`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `tb_detail_transaksi`
--
ALTER TABLE `tb_detail_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_transaksi` (`id_transaksi`);

--
-- Indexes for table `tb_diskon`
--
ALTER TABLE `tb_diskon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_kategori`
--
ALTER TABLE `tb_kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_produk`
--
ALTER TABLE `tb_produk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_supplier`
--
ALTER TABLE `tb_supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_akun` (`id_akun`),
  ADD KEY `id_diskon` (`id_diskon`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_akun`
--
ALTER TABLE `tb_akun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_detail_transaksi`
--
ALTER TABLE `tb_detail_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_produk`
--
ALTER TABLE `tb_produk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_data_barang`
--
ALTER TABLE `tb_data_barang`
  ADD CONSTRAINT `tb_data_barang_ibfk_3` FOREIGN KEY (`id_detail_supplier`) REFERENCES `tb_detail_supplier` (`id`),
  ADD CONSTRAINT `tb_data_barang_ibfk_4` FOREIGN KEY (`id_kategori`) REFERENCES `tb_kategori` (`id`);

--
-- Constraints for table `tb_detail_supplier`
--
ALTER TABLE `tb_detail_supplier`
  ADD CONSTRAINT `tb_detail_supplier_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `tb_produk` (`id`),
  ADD CONSTRAINT `tb_detail_supplier_ibfk_3` FOREIGN KEY (`id_supplier`) REFERENCES `tb_supplier` (`id`);

--
-- Constraints for table `tb_detail_transaksi`
--
ALTER TABLE `tb_detail_transaksi`
  ADD CONSTRAINT `tb_detail_transaksi_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `tb_transaksi` (`id`),
  ADD CONSTRAINT `tb_detail_transaksi_ibfk_3` FOREIGN KEY (`id_barang`) REFERENCES `tb_data_barang` (`id`);

--
-- Constraints for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `tb_pelanggan` (`id`),
  ADD CONSTRAINT `tb_transaksi_ibfk_2` FOREIGN KEY (`id_akun`) REFERENCES `tb_akun` (`id`),
  ADD CONSTRAINT `tb_transaksi_ibfk_3` FOREIGN KEY (`id_diskon`) REFERENCES `tb_diskon` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
