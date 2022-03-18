create database huella;
use huella;

CREATE TABLE IF NOT EXISTS `empleado` (
  `clave` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apPaterno` varchar(60) DEFAULT NULL,
  `apMaterno` varchar(60) DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `calle` varchar(60) DEFAULT NULL,
  `noExterior` varchar(20) DEFAULT NULL,
  `colonia` varchar(60) DEFAULT NULL,
  `Municipio` varchar(60) DEFAULT NULL,
  `Estado` varchar(60) DEFAULT NULL,
  `cp` varchar(20) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `cel` varchar(20) DEFAULT NULL,
  `eMail` varchar(25) DEFAULT NULL,
  `hueya` blob,
  `hueya1` blob,
  `foto` blob,
  `Carrera` int not null,
  `Cuatrimestre` int not null,
  `Grupo` int not null,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.empleado: 1 rows
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;


# Dumping structure for table huellas.entradas
DROP TABLE IF EXISTS `entradas`;
CREATE TABLE IF NOT EXISTS `entradas` (
  `clave` int(10) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `claveAlumno` int(10) DEFAULT '0',
  `horaEntrada` time DEFAULT NULL,
  `entrada` varchar(20) DEFAULT '0',
  `destino` varchar(100) DEFAULT NULL,
  `horaSalida` time DEFAULT NULL,
  `bandera` smallint(6) DEFAULT NULL,
  `totalHoras` time DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.entradas: 18 rows
/*!40000 ALTER TABLE `entradas` DISABLE KEYS */;
/*!40000 ALTER TABLE `entradas` ENABLE KEYS */;


# Dumping structure for table huellas.huella
DROP TABLE IF EXISTS `huella`;
CREATE TABLE IF NOT EXISTS `huella` (
  `identificacion_usuario` bigint(20) unsigned NOT NULL,
  `nombres_usuario` varchar(250) NOT NULL,
  `huella1_usuario` blob NOT NULL,
  `huella2_usuario` blob NOT NULL,
  PRIMARY KEY (`identificacion_usuario`),
  KEY `cedula` (`identificacion_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table huellas.huella: ~2 rows (approximately)
/*!40000 ALTER TABLE `huella` DISABLE KEYS */;
/*!40000 ALTER TABLE `huella` ENABLE KEYS */;


# Dumping structure for table huellas.salidas
DROP TABLE IF EXISTS `salidas`;
CREATE TABLE IF NOT EXISTS `salidas` (
  `clave` int(10) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `salida` varchar(30) DEFAULT NULL,
  `claveAlumno` int(10) DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.salidas: 0 rows
/*!40000 ALTER TABLE `salidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `salidas` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


CREATE TABLE IF NOT EXISTS `destinos` (
  `clave` int(12) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `claveEmpleado` int(11) DEFAULT '0',
  `descripcion` varchar(100) DEFAULT '0',
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `destinos` DISABLE KEYS */;
/*!40000 ALTER TABLE `destinos` ENABLE KEYS */;

create table Carrera(
IdCarrera int not null,
NombreCarrera varchar (50) not null,
primary key (IdCarrera));


create table Cuatrimestre(
IdCuatrimestre int not null,
NumeroCuatrimestre int not null,
primary key (IdCuatrimestre));


create table Grupo(
IdGrupo int not null,
NumeroGrupo int not null,
Modalidad varchar (30) not null,
primary key (IdGrupo));

DROP TABLE IF EXISTS estados;
CREATE TABLE estados(
  IdEstados int(10) NOT NULL,
  clave varchar(2) NOT NULL,
  nombre varchar(40) NOT NULL,
  PRIMARY KEY (IdEstados));

INSERT INTO estados (IdEstados, clave, nombre) VALUES
(30, '30', 'Veracruz de Ignacio de la Llave');

DROP TABLE IF EXISTS `municipios`;
CREATE TABLE `municipios` (
  `IdMunicipio` int(11) NOT NULL,
  `IdEstado` int(11) NOT NULL COMMENT 'Relación: estados -> id',
  `clave` varchar(3) NOT NULL COMMENT 'CVE_MUN – Clave del municipio',
  `nombre` varchar(100) NOT NULL COMMENT 'NOM_MUN – Nombre del municipio',
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`IdMunicipio`),
  FOREIGN KEY (`IdEstado`) references estados(`IdEstado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Municipios de la República Mexicana';

INSERT INTO `municipios` (`IdMunicipio`, `IdEstado`, `clave`, `nombre`, `activo`) VALUES

(2088, 30, '001', 'Acajete', 1),
(2089, 30, '002', 'Acatlán', 1),
(2090, 30, '003', 'Acayucan', 1),
(2091, 30, '004', 'Actopan', 1),
(2092, 30, '005', 'Acula', 1),
(2093, 30, '006', 'Acultzingo', 1),
(2094, 30, '007', 'Camarón de Tejeda', 1),
(2095, 30, '008', 'Alpatláhuac', 1),
(2096, 30, '009', 'Alto Lucero de Gutiérrez Barrios', 1),
(2097, 30, '010', 'Altotonga', 1),
(2098, 30, '011', 'Alvarado', 1),
(2099, 30, '012', 'Amatitlán', 1),
(2100, 30, '013', 'Naranjos Amatlán', 1),
(2101, 30, '014', 'Amatlán de los Reyes', 1),
(2102, 30, '015', 'Angel R. Cabada', 1),
(2103, 30, '016', 'La Antigua', 1),
(2104, 30, '017', 'Apazapan', 1),
(2105, 30, '018', 'Aquila', 1),
(2106, 30, '019', 'Astacinga', 1),
(2107, 30, '020', 'Atlahuilco', 1),
(2108, 30, '021', 'Atoyac', 1),
(2109, 30, '022', 'Atzacan', 1),
(2110, 30, '023', 'Atzalan', 1),
(2111, 30, '024', 'Tlaltetela', 1),
(2112, 30, '025', 'Ayahualulco', 1),
(2113, 30, '026', 'Banderilla', 1),
(2114, 30, '027', 'Benito Juárez', 1),
(2115, 30, '028', 'Boca del Río', 1),
(2116, 30, '029', 'Calcahualco', 1),
(2117, 30, '030', 'Camerino Z. Mendoza', 1),
(2118, 30, '031', 'Carrillo Puerto', 1),
(2119, 30, '032', 'Catemaco', 1),
(2120, 30, '033', 'Cazones de Herrera', 1),
(2121, 30, '034', 'Cerro Azul', 1),
(2122, 30, '035', 'Citlaltépetl', 1),
(2123, 30, '036', 'Coacoatzintla', 1),
(2124, 30, '037', 'Coahuitlán', 1),
(2125, 30, '038', 'Coatepec', 1),
(2126, 30, '039', 'Coatzacoalcos', 1),
(2127, 30, '040', 'Coatzintla', 1),
(2128, 30, '041', 'Coetzala', 1),
(2129, 30, '042', 'Colipa', 1),
(2130, 30, '043', 'Comapa', 1),
(2131, 30, '044', 'Córdoba', 1),
(2132, 30, '045', 'Cosamaloapan de Carpio', 1),
(2133, 30, '046', 'Cosautlán de Carvajal', 1),
(2134, 30, '047', 'Coscomatepec', 1),
(2135, 30, '048', 'Cosoleacaque', 1),
(2136, 30, '049', 'Cotaxtla', 1),
(2137, 30, '050', 'Coxquihui', 1),
(2138, 30, '051', 'Coyutla', 1),
(2139, 30, '052', 'Cuichapa', 1),
(2140, 30, '053', 'Cuitláhuac', 1),
(2141, 30, '054', 'Chacaltianguis', 1),
(2142, 30, '055', 'Chalma', 1),
(2143, 30, '056', 'Chiconamel', 1),
(2144, 30, '057', 'Chiconquiaco', 1),
(2145, 30, '058', 'Chicontepec', 1),
(2146, 30, '059', 'Chinameca', 1),
(2147, 30, '060', 'Chinampa de Gorostiza', 1),
(2148, 30, '061', 'Las Choapas', 1),
(2149, 30, '062', 'Chocamán', 1),
(2150, 30, '063', 'Chontla', 1),
(2151, 30, '064', 'Chumatlán', 1),
(2152, 30, '065', 'Emiliano Zapata', 1),
(2153, 30, '066', 'Espinal', 1),
(2154, 30, '067', 'Filomeno Mata', 1),
(2155, 30, '068', 'Fortín', 1),
(2156, 30, '069', 'Gutiérrez Zamora', 1),
(2157, 30, '070', 'Hidalgotitlán', 1),
(2158, 30, '071', 'Huatusco', 1),
(2159, 30, '072', 'Huayacocotla', 1),
(2160, 30, '073', 'Hueyapan de Ocampo', 1),
(2161, 30, '074', 'Huiloapan de Cuauhtémoc', 1),
(2162, 30, '075', 'Ignacio de la Llave', 1),
(2163, 30, '076', 'Ilamatlán', 1),
(2164, 30, '077', 'Isla', 1),
(2165, 30, '078', 'Ixcatepec', 1),
(2166, 30, '079', 'Ixhuacán de los Reyes', 1),
(2167, 30, '080', 'Ixhuatlán del Café', 1),
(2168, 30, '081', 'Ixhuatlancillo', 1),
(2169, 30, '082', 'Ixhuatlán del Sureste', 1),
(2170, 30, '083', 'Ixhuatlán de Madero', 1),
(2171, 30, '084', 'Ixmatlahuacan', 1),
(2172, 30, '085', 'Ixtaczoquitlán', 1),
(2173, 30, '086', 'Jalacingo', 1),
(2174, 30, '087', 'Xalapa', 1),
(2175, 30, '088', 'Jalcomulco', 1),
(2176, 30, '089', 'Jáltipan', 1),
(2177, 30, '090', 'Jamapa', 1),
(2178, 30, '091', 'Jesús Carranza', 1),
(2179, 30, '092', 'Xico', 1),
(2180, 30, '093', 'Jilotepec', 1),
(2181, 30, '094', 'Juan Rodríguez Clara', 1),
(2182, 30, '095', 'Juchique de Ferrer', 1),
(2183, 30, '096', 'Landero y Coss', 1),
(2184, 30, '097', 'Lerdo de Tejada', 1),
(2185, 30, '098', 'Magdalena', 1),
(2186, 30, '099', 'Maltrata', 1),
(2187, 30, '100', 'Manlio Fabio Altamirano', 1),
(2188, 30, '101', 'Mariano Escobedo', 1),
(2189, 30, '102', 'Martínez de la Torre', 1),
(2190, 30, '103', 'Mecatlán', 1),
(2191, 30, '104', 'Mecayapan', 1),
(2192, 30, '105', 'Medellín de Bravo', 1),
(2193, 30, '106', 'Miahuatlán', 1),
(2194, 30, '107', 'Las Minas', 1),
(2195, 30, '108', 'Minatitlán', 1),
(2196, 30, '109', 'Misantla', 1),
(2197, 30, '110', 'Mixtla de Altamirano', 1),
(2198, 30, '111', 'Moloacán', 1),
(2199, 30, '112', 'Naolinco', 1),
(2200, 30, '113', 'Naranjal', 1),
(2201, 30, '114', 'Nautla', 1),
(2202, 30, '115', 'Nogales', 1),
(2203, 30, '116', 'Oluta', 1),
(2204, 30, '117', 'Omealca', 1),
(2205, 30, '118', 'Orizaba', 1),
(2206, 30, '119', 'Otatitlán', 1),
(2207, 30, '120', 'Oteapan', 1),
(2208, 30, '121', 'Ozuluama de Mascareñas', 1),
(2209, 30, '122', 'Pajapan', 1),
(2210, 30, '123', 'Pánuco', 1),
(2211, 30, '124', 'Papantla', 1),
(2212, 30, '125', 'Paso del Macho', 1),
(2213, 30, '126', 'Paso de Ovejas', 1),
(2214, 30, '127', 'La Perla', 1),
(2215, 30, '128', 'Perote', 1),
(2216, 30, '129', 'Platón Sánchez', 1),
(2217, 30, '130', 'Playa Vicente', 1),
(2218, 30, '131', 'Poza Rica de Hidalgo', 1),
(2219, 30, '132', 'Las Vigas de Ramírez', 1),
(2220, 30, '133', 'Pueblo Viejo', 1),
(2221, 30, '134', 'Puente Nacional', 1),
(2222, 30, '135', 'Rafael Delgado', 1),
(2223, 30, '136', 'Rafael Lucio', 1),
(2224, 30, '137', 'Los Reyes', 1),
(2225, 30, '138', 'Río Blanco', 1),
(2226, 30, '139', 'Saltabarranca', 1),
(2227, 30, '140', 'San Andrés Tenejapan', 1),
(2228, 30, '141', 'San Andrés Tuxtla', 1),
(2229, 30, '142', 'San Juan Evangelista', 1),
(2230, 30, '143', 'Santiago Tuxtla', 1),
(2231, 30, '144', 'Sayula de Alemán', 1),
(2232, 30, '145', 'Soconusco', 1),
(2233, 30, '146', 'Sochiapa', 1),
(2234, 30, '147', 'Soledad Atzompa', 1),
(2235, 30, '148', 'Soledad de Doblado', 1),
(2236, 30, '149', 'Soteapan', 1),
(2237, 30, '150', 'Tamalín', 1),
(2238, 30, '151', 'Tamiahua', 1),
(2239, 30, '152', 'Tampico Alto', 1),
(2240, 30, '153', 'Tancoco', 1),
(2241, 30, '154', 'Tantima', 1),
(2242, 30, '155', 'Tantoyuca', 1),
(2243, 30, '156', 'Tatatila', 1),
(2244, 30, '157', 'Castillo de Teayo', 1),
(2245, 30, '158', 'Tecolutla', 1),
(2246, 30, '159', 'Tehuipango', 1),
(2247, 30, '160', 'Álamo Temapache', 1),
(2248, 30, '161', 'Tempoal', 1),
(2249, 30, '162', 'Tenampa', 1),
(2250, 30, '163', 'Tenochtitlán', 1),
(2251, 30, '164', 'Teocelo', 1),
(2252, 30, '165', 'Tepatlaxco', 1),
(2253, 30, '166', 'Tepetlán', 1),
(2254, 30, '167', 'Tepetzintla', 1),
(2255, 30, '168', 'Tequila', 1),
(2256, 30, '169', 'José Azueta', 1),
(2257, 30, '170', 'Texcatepec', 1),
(2258, 30, '171', 'Texhuacán', 1),
(2259, 30, '172', 'Texistepec', 1),
(2260, 30, '173', 'Tezonapa', 1),
(2261, 30, '174', 'Tierra Blanca', 1),
(2262, 30, '175', 'Tihuatlán', 1),
(2263, 30, '176', 'Tlacojalpan', 1),
(2264, 30, '177', 'Tlacolulan', 1),
(2265, 30, '178', 'Tlacotalpan', 1),
(2266, 30, '179', 'Tlacotepec de Mejía', 1),
(2267, 30, '180', 'Tlachichilco', 1),
(2268, 30, '181', 'Tlalixcoyan', 1),
(2269, 30, '182', 'Tlalnelhuayocan', 1),
(2270, 30, '183', 'Tlapacoyan', 1),
(2271, 30, '184', 'Tlaquilpa', 1),
(2272, 30, '185', 'Tlilapan', 1),
(2273, 30, '186', 'Tomatlán', 1),
(2274, 30, '187', 'Tonayán', 1),
(2275, 30, '188', 'Totutla', 1),
(2276, 30, '189', 'Tuxpan', 1),
(2277, 30, '190', 'Tuxtilla', 1),
(2278, 30, '191', 'Ursulo Galván', 1),
(2279, 30, '192', 'Vega de Alatorre', 1),
(2280, 30, '193', 'Veracruz', 1),
(2281, 30, '194', 'Villa Aldama', 1),
(2282, 30, '195', 'Xoxocotla', 1),
(2283, 30, '196', 'Yanga', 1),
(2284, 30, '197', 'Yecuatla', 1),
(2285, 30, '198', 'Zacualpan', 1),
(2286, 30, '199', 'Zaragoza', 1),
(2287, 30, '200', 'Zentla', 1),
(2288, 30, '201', 'Zongolica', 1),
(2289, 30, '202', 'Zontecomatlán de López y Fuentes', 1),
(2290, 30, '203', 'Zozocolco de Hidalgo', 1),
(2291, 30, '204', 'Agua Dulce', 1),
(2292, 30, '205', 'El Higo', 1),
(2293, 30, '206', 'Nanchital de Lázaro Cárdenas del Río', 1),
(2294, 30, '207', 'Tres Valles', 1),
(2295, 30, '208', 'Carlos A. Carrillo', 1),
(2296, 30, '209', 'Tatahuicapan de Juárez', 1),
(2297, 30, '210', 'Uxpanapa', 1),
(2298, 30, '211', 'San Rafael', 1),
(2299, 30, '212', 'Santiago Sochiapan', 1);

