-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.37-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for book_lib_mgmt
CREATE DATABASE IF NOT EXISTS `book_lib_mgmt` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `book_lib_mgmt`;

-- Dumping structure for table book_lib_mgmt.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` varchar(255) NOT NULL,
  `author` varchar(30) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `is_shelved` tinyint(1) NOT NULL DEFAULT '0',
  `isbn` varchar(25) NOT NULL,
  `title` varchar(50) NOT NULL,
  `shelf_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7vf5b2vkphwbbfntbcoo7aby` (`shelf_id`),
  CONSTRAINT `FKp7vf5b2vkphwbbfntbcoo7aby` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table book_lib_mgmt.book: ~2 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `author`, `is_deleted`, `is_shelved`, `isbn`, `title`, `shelf_id`) VALUES
	('2c93906a68e5fa3a0168e5fd26b00000', 'author', 0, 1, 'isbn', 'title', NULL),
	('2c93906a68e951310168e95178bb0000', 'author', 0, 0, 'isbn', 'title', NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table book_lib_mgmt.shelf
CREATE TABLE IF NOT EXISTS `shelf` (
  `id` varchar(255) NOT NULL,
  `current_capacity` bigint(20) DEFAULT NULL,
  `max_capacity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table book_lib_mgmt.shelf: ~6 rows (approximately)
/*!40000 ALTER TABLE `shelf` DISABLE KEYS */;
INSERT INTO `shelf` (`id`, `current_capacity`, `max_capacity`) VALUES
	('2c93906a68dba0e60168dba1160f0000', NULL, NULL),
	('2c93906a68dba0e60168dba294260002', NULL, NULL),
	('2c93906a68e573ed0168e574d1700000', 10, 10),
	('2c93906a68e573ed0168e576764a0002', 10, 10),
	('2c93906a68e573ed0168e577766d0004', 10, 10),
	('2c93906a68e57b6c0168e57c15d10000', 0, 10);
/*!40000 ALTER TABLE `shelf` ENABLE KEYS */;

-- Dumping structure for table book_lib_mgmt.ws_audit_logs
CREATE TABLE IF NOT EXISTS `ws_audit_logs` (
  `id` varchar(255) NOT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `end_point` varchar(255) DEFAULT NULL,
  `operation_name` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `request_payload` text,
  `response_exception` text,
  `response_payload` text,
  `service_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table book_lib_mgmt.ws_audit_logs: ~44 rows (approximately)
/*!40000 ALTER TABLE `ws_audit_logs` DISABLE KEYS */;
INSERT INTO `ws_audit_logs` (`id`, `channel`, `created_date`, `end_point`, `operation_name`, `request_id`, `request_payload`, `response_exception`, `response_payload`, `service_name`) VALUES
	('2c93906a68db7ebf0168db7f1e160000', 'LIBMGMT_WEB', '2019-02-11 14:39:27', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '', '/api/shelf/add'),
	('2c93906a68db815d0168db8180a30000', 'LIBMGMT_WEB', '2019-02-11 14:42:03', '', '::searchShelf', NULL, '{\n}', NULL, '[]', '/api/shelf/search'),
	('2c93906a68db815d0168db81a00a0001', 'LIBMGMT_WEB', '2019-02-11 14:42:11', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '', '/api/shelf/add'),
	('2c93906a68db8e940168db8ecb950000', 'LIBMGMT_WEB', '2019-02-11 14:56:34', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '', '/api/shelf/add'),
	('2c93906a68db9b250168db9b4d830000', 'LIBMGMT_WEB', '2019-02-11 15:10:14', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '"com.fasterxml.jackson.core.JsonParseException: Duplicate field \'maxCapacity\'\\n at [Source: (String)\\"{\\n\\t\\"maxCapacity\\": null,\\n\\t\\"maxCapacity\\": null\\n}\\"; line: 3, column: 15]"', '/api/shelf/add'),
	('2c93906a68dba0e60168dba1165b0001', 'LIBMGMT_WEB', '2019-02-11 15:16:33', '', '::addShelf', NULL, '{\n}', NULL, '{"id":"2c93906a68dba0e60168dba1160f0000","maxCapacity":null,"currentCapacity":null,"books":[]}', '/api/shelf/add'),
	('2c93906a68dba0e60168dba294330003', 'LIBMGMT_WEB', '2019-02-11 15:18:11', '', '::addShelf', NULL, '{\n}', NULL, '{"id":"2c93906a68dba0e60168dba294260002","maxCapacity":null,"currentCapacity":null,"books":[]}', '/api/shelf/add'),
	('2c93906a68dba0e60168dba36b5a0004', 'LIBMGMT_WEB', '2019-02-11 15:19:06', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '"com.fasterxml.jackson.core.JsonParseException: Duplicate field \'maxCapacity\'\\n at [Source: (String)\\"{\\n\\t\\"maxCapacity\\": null,\\n\\t\\"maxCapacity\\": null\\n}\\"; line: 3, column: 15]"', '/api/shelf/add'),
	('2c93906a68dba76e0168dba7aa2d0000', 'LIBMGMT_WEB', '2019-02-11 15:23:44', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '{"statusCode":"500","response":"com.fasterxml.jackson.core.JsonParseException: Duplicate field \'maxCapacity\'\\n at [Source: (String)\\"{\\n\\t\\"maxCapacity\\": null,\\n\\t\\"maxCapacity\\": null\\n}\\"; line: 3, column: 15]"}', '/api/shelf/add'),
	('2c93906a68dba76e0168dba7f4290001', 'LIBMGMT_WEB', '2019-02-11 15:24:03', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '{"statusCode":"500","response":"com.fasterxml.jackson.core.JsonParseException: Duplicate field \'maxCapacity\'\\n at [Source: (String)\\"{\\n\\t\\"maxCapacity\\": null,\\n\\t\\"maxCapacity\\": null\\n}\\"; line: 3, column: 15]"}', '/api/shelf/add'),
	('2c93906a68dba76e0168dba891940002', 'LIBMGMT_WEB', '2019-02-11 15:24:44', '', '::addShelf', NULL, '{\n	"maxCapacity": null,\n	"maxCapacity": null\n}', NULL, '{"statusCode":"500","response":"com.fasterxml.jackson.core.JsonParseException: Duplicate field \'maxCapacity\'\\n at [Source: (String)\\"{\\n\\t\\"maxCapacity\\": null,\\n\\t\\"maxCapacity\\": null\\n}\\"; line: 3, column: 15]"}', '/api/shelf/add'),
	('2c93906a68e573ed0168e574d1b80001', 'LIBMGMT_WEB', '2019-02-13 13:04:24', '', '::createShelf', NULL, '{"id":null,"maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{},"body":{"id":"2c93906a68e573ed0168e574d1700000","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/api/shelf/create'),
	('2c93906a68e573ed0168e57676560003', 'LIBMGMT_WEB', '2019-02-13 13:06:12', '', '::createShelf', NULL, '{"id":"dsf","maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{},"body":{"id":"2c93906a68e573ed0168e576764a0002","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/api/shelf/create'),
	('2c93906a68e573ed0168e5777a580005', 'LIBMGMT_WEB', '2019-02-13 13:07:19', '', '::createShelf', NULL, '{"id":"dsf","maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{},"body":{"id":"2c93906a68e573ed0168e577766d0004","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/api/shelf/create'),
	('2c93906a68e57b6c0168e57c161f0001', 'LIBMGMT_WEB', '2019-02-13 13:12:21', '', '::createShelf', NULL, '{"id":null,"maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/api/shelf/create'),
	('2c93906a68e5823b0168e582b7210000', 'LIBMGMT_WEB', '2019-02-13 13:19:35', '', '::createShelf', NULL, '{"id":"sdfafa","maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{"X-LibMgmtApp-params":["shelf"],"X-LibMgmtApp-error":["A new Shelf cannot already have an ID"]},"body":null,"statusCode":"BAD_REQUEST","statusCodeValue":400}', '/api/shelf/create'),
	('2c93906a68e5cd3f0168e5cf7bd80000', 'LIBMGMT_WEB', '2019-02-13 14:43:26', '', '::createShelf', NULL, '{"id":null,"isbn":"fsafa","title":"title","author":"author","shelfId":null}', NULL, 'null', '/api/book/create'),
	('2c93906a68e5d03a0168e5d057ff0000', 'LIBMGMT_WEB', '2019-02-13 14:44:23', '', '::createShelf', NULL, '{"id":null,"isbn":"fsafa","title":"title","author":"author","shelfId":null}', NULL, 'null', '/api/book/create'),
	('2c93906a68e5d03a0168e5dc0f8b0001', 'LIBMGMT_WEB', '2019-02-13 14:57:10', '', '::createShelf', NULL, '{"id":null,"isbn":"fsafa","title":"title","author":"author","shelfId":null}', NULL, 'null', '/api/book/create'),
	('2c93906a68e5d03a0168e5dcd0690002', 'LIBMGMT_WEB', '2019-02-13 14:58:00', '', '::createShelf', NULL, '{"id":"sdfafa","maxCapacity":10,"currentCapacity":10}', NULL, '{"headers":{"X-LibMgmtApp-params":["shelf"],"X-LibMgmtApp-error":["A new Shelf cannot already have an ID"]},"body":null,"statusCode":"BAD_REQUEST","statusCodeValue":400}', '/api/shelf/create'),
	('2c93906a68e5de1c0168e5de6e340000', 'LIBMGMT_WEB', '2019-02-13 14:59:46', '', '::createShelf', NULL, '{"id":null,"isbn":"fsafa","title":"title","author":"author","shelfId":null}', NULL, 'null', '/api/book/create'),
	('2c93906a68e5e07e0168e5e09f440000', 'LIBMGMT_WEB', '2019-02-13 15:02:09', '', '::createShelf', NULL, '{"id":null,"isbn":"fsafa","title":"title","author":"author","shelfId":null}', NULL, 'null', '/api/book/create'),
	('2c93906a68e5fa3a0168e5fd26e40001', 'LIBMGMT_WEB', '2019-02-13 15:33:19', '', '::createShelf', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":null}', NULL, '{"headers":{},"body":{"id":"2c93906a68e5fa3a0168e5fd26b00000","isbn":"isbn","title":"title","author":"author","shelfId":null},"statusCode":"OK","statusCodeValue":200}', '/api/book/create'),
	('2c93906a68e951310168e95179030001', 'LIBMGMT_WEB', '2019-02-14 07:04:17', '', '::createShelf', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"shelfId"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":null},"statusCode":"OK","statusCodeValue":200}', '/api/book/create'),
	('2c93906a68e9ab590168e9acaf9f0000', 'LIBMGMT_WEB', '2019-02-14 08:43:55', '', '::createShelf', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"}', NULL, '{"headers":{},"body":{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"},"statusCode":"OK","statusCodeValue":200}', '/api/book/create'),
	('2c93906a68e9ab590168e9ad67300001', 'LIBMGMT_WEB', '2019-02-14 08:44:42', '', '::createShelf', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"}', NULL, '{"headers":{},"body":{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"},"statusCode":"OK","statusCodeValue":200}', '/api/book/create'),
	('2c93906a68e9efe50168e9f01c920000', 'LIBMGMT_WEB', '2019-02-14 09:57:33', '', '::findBooks', NULL, '"{}"', NULL, '{"headers":{},"body":[],"statusCode":"OK","statusCodeValue":200}', '/api/book/find'),
	('2c93906a68e9f1240168e9f1eb780000', 'LIBMGMT_WEB', '2019-02-14 09:59:32', '', '::findBooks', NULL, '"{}"', NULL, '{"headers":{},"body":[],"statusCodeValue":200,"statusCode":"OK"}', '/api/book/find'),
	('2c93906a68e9f3440168e9f402fa0000', 'LIBMGMT_WEB', '2019-02-14 10:01:49', '', '::findBooks', NULL, '"{}"', NULL, '{"headers":{},"body":[{"id":"2c93906a68e5fa3a0168e5fd26b00000","isbn":"isbn","title":"title","author":"author","shelfId":null},{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":null}],"statusCodeValue":200,"statusCode":"OK"}', '/api/book/find'),
	('2c93906a68e9f3440168e9f4d6640001', 'LIBMGMT_WEB', '2019-02-14 10:02:43', '', '::findBooks', NULL, '"{}"', NULL, '{"headers":{},"body":[{"id":"2c93906a68e5fa3a0168e5fd26b00000","isbn":"isbn","title":"title","author":"author","shelfId":null}],"statusCodeValue":200,"statusCode":"OK"}', '/api/book/find'),
	('2c93906a68ea10f50168ea11d6210000', 'LIBMGMT_WEB', '2019-02-14 10:34:24', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/shelf/find'),
	('2c93906a68ea163d0168ea1663450000', 'LIBMGMT_WEB', '2019-02-14 10:39:22', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea303f0168ea30e4940000', 'LIBMGMT_WEB', '2019-02-14 11:08:19', '', '::addBookIntoShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":"2c93906a68e57b6c0168e57c15d10000"},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/create?null'),
	('2c93906a68ea303f0168ea54fd2f0001', 'LIBMGMT_WEB', '2019-02-14 11:47:44', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea303f0168ea5500740002', 'LIBMGMT_WEB', '2019-02-14 11:47:45', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea303f0168ea5502e50003', 'LIBMGMT_WEB', '2019-02-14 11:47:46', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea303f0168ea5572180004', 'LIBMGMT_WEB', '2019-02-14 11:48:14', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea303f0168ea5572770005', 'LIBMGMT_WEB', '2019-02-14 11:48:14', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea560e0168ea5632eb0000', 'LIBMGMT_WEB', '2019-02-14 11:49:04', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea56e60168ea570cde0000', 'LIBMGMT_WEB', '2019-02-14 11:49:59', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea56e60168ea5713fc0001', 'LIBMGMT_WEB', '2019-02-14 11:50:01', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea56e60168ea5717b10002', 'LIBMGMT_WEB', '2019-02-14 11:50:02', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea56e60168ea5849d90003', 'LIBMGMT_WEB', '2019-02-14 11:51:21', '', '::findShelfById', NULL, '"{}"', NULL, '{"headers":{},"body":{"id":"2c93906a68e57b6c0168e57c15d10000","maxCapacity":10,"currentCapacity":10},"statusCodeValue":200,"statusCode":"OK"}', '/lib-mgmt/api/library/find?id=2c93906a68e57b6c0168e57c15d10000'),
	('2c93906a68ea61df0168ea627f3e0000', 'LIBMGMT_WEB', '2019-02-14 12:02:30', '', '::addBookIntoShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":"2c93906a68e57b6c0168e57c15d10000"},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/add?null'),
	('2c93906a68ea647b0168ea64a5a10000', 'LIBMGMT_WEB', '2019-02-14 12:04:51', '', '::addBookIntoShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":"2c93906a68e57b6c0168e57c15d10000"},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/add?null'),
	('2c93906a68ea67b60168ea682a480000', 'LIBMGMT_WEB', '2019-02-14 12:08:41', '', '::addBookIntoShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":null,"statusCodeValue":404,"statusCode":"NOT_FOUND"}', '/lib-mgmt/api/library/add?null'),
	('2c93906a68ea72aa0168ea73aa470000', 'LIBMGMT_WEB', '2019-02-14 12:21:15', '', '::removeBookFromShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":"2c93906a68e57b6c0168e57c15d10000"},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/remove?null'),
	('2c93906a68ea92f80168ea94f8d90000', 'LIBMGMT_WEB', '2019-02-14 12:57:38', '', '::removeBookFromShelf', NULL, '{"bookId":"2c93906a68e951310168e95178bb0000","shelfId":"2c93906a68e57b6c0168e57c15d10000"}', NULL, '{"headers":{},"body":{"id":"2c93906a68e951310168e95178bb0000","isbn":"isbn","title":"title","author":"author","shelfId":null},"statusCode":"OK","statusCodeValue":200}', '/lib-mgmt/api/library/remove?null'),
	('2c93906a68f01f040168f01f78100001', 'LIBMGMT_WEB', '2019-02-15 14:47:00', '', '::createBook', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"}', NULL, '{"headers":{"X-LibMgmtApp-params":["book"],"X-LibMgmtApp-error":["could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"]},"body":null,"statusCode":"BAD_REQUEST","statusCodeValue":400}', '/lib-mgmt/api/book/create?null'),
	('2c93906a68f029890168f02b4e2b0001', 'LIBMGMT_WEB', '2019-02-15 14:59:56', '', '::createBook', NULL, '{"id":null,"isbn":"isbn","title":"title","author":"author","shelfId":"test"}', NULL, '{"headers":{"X-LibMgmtApp-params":["book"],"X-LibMgmtApp-error":["could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"]},"body":null,"statusCode":"BAD_REQUEST","statusCodeValue":400}', '/lib-mgmt/api/book/create?null');
/*!40000 ALTER TABLE `ws_audit_logs` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
