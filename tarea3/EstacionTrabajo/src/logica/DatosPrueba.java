package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.DepartamentoRepetidoException;
import excepciones.EmailRepetidoException;
import excepciones.EntidadRepetidaException;
import excepciones.InscripcionRepetidaException;
import excepciones.NickameRepetidoException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import excepciones.NoHayCapacidadException;
import excepciones.PaqueteCompradoException;
import excepciones.PaqueteVencidoException;
import excepciones.SalidaRepetidaException;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import logica.interfaces.IControladorUsuario;

public class DatosPrueba {

	public static void cargarDatos() {
		IControladorUsuario controlUSR = Fabrica.getInstance().getIControladorUsuario();
		IControladorSalidas controlSAL = Fabrica.getInstance().getIControladorSalidas();
		IControladorActividades controlACT = Fabrica.getInstance().getIControladorActividades();
		IControladorDepartamentos controlDEP = Fabrica.getInstance().getIControladorDepartamentos();

		// CARGA DE TURISTAS Y PROVEEDORES
		try {
			controlUSR.crearTurista("lachiqui", "Rosa Maria", "Martinez", "mirtha.legrand.ok@hotmail.com.ar",
					LocalDate.of(1972, 2, 23), "argentina", "awdrg543", "2e3s66tw");
			controlUSR.crearTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk",
					LocalDate.of(1926, 4, 21), "inglesa", "r5t6y7u8", "ycy8mbrn");
			controlUSR.crearTurista("anibal", "Anibal", "Lecter", "anibal@fing.edu.uy", LocalDate.of(1937, 12, 31),
					"lituana", "edrft543", "y2u3tybh");
			controlUSR.crearTurista("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.of(1990, 4, 15),
					"inglesa", "poiuy987", "2p9ed8et");
			controlUSR.crearTurista("elelvis", "Elivis", "Lacio", "suavemente@hotmail.com", LocalDate.of(1971, 7, 30),
					"estadounidense", "45idgaf67", "mtwppxxz");
			controlUSR.crearTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.of(2004, 2, 19),
					"espaniola", "xdrgb657", "3ztpasya");
			controlUSR.crearTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com",
					LocalDate.of(1999, 5, 1), "japonesa", "sbsplol1", "43zymcch");
			controlUSR.crearTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.of(1976, 4, 11),
					"uruguaya", "okmnji98", "mr3a38w4");
			controlUSR.crearTurista("chino", "Alvaro", "Recoba", "chino@trico.org.uy", LocalDate.of(1976, 3, 17),
					"uruguaya", "qsxcdw43", "2b556k7t");
			controlUSR.crearTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com",
					LocalDate.of(1922, 2, 7), "austriaca", "qpwoei586", "3mbeyawm");
			controlUSR.crearProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy",
					LocalDate.of(1970, 9, 14),
					"Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay",
					"http://turismorocha.gub.uy/", "asdfg654", "3whe8372");
			controlUSR.crearProveedor("eldiez", "Pablo", "Bengochea", "eldiez@socfomturriv.gob.uy",
					LocalDate.of(1965, 6, 27),
					"Pablo es el presidente de la Sociedad de Fomento Turistico de Rivera (conocida como Socfomturriv)",
					"http://socfomturriv.org.uy", "ytrewq10", "mu4jeas3");
			controlUSR.crearProveedor("meche", "Mercedes", "Venn", "meche@colonia.org.uy", LocalDate.of(1990, 12, 31),
					"Departamento de Turismo del Departamento de Colonia", "http://colonia.gub.uy/turismo/", "mnjkiu89",
					"4hs4v9c5");
		} catch (EmailRepetidoException | NickameRepetidoException e) {
			e.printStackTrace();
		}

		// CARGA DE DEPARTAMENTOS
		try {
			controlDEP.crearDepartamento("Canelones", "Division Turismo de la Intendencia",
					"https://www.imcanelones.gub.uy/es");
			controlDEP.crearDepartamento("Maldonado", "Division Turismo de la Intendencia",
					"https://www.maldonado.gub.uy/");
			controlDEP.crearDepartamento("Rocha",
					"La Organizacion de Gestion del Destino (OGD) Rocha es un ambito de articulacion publico / privada "
							+ "en el sector turistico que integran la Coporacion Rochense de Turismo y la Intendencia de Rocha a traves de su Direccion de Turismo",
					"http://www.turismorocha.gub.uy/");
			controlDEP.crearDepartamento("Treinta y Tres", "Division Turismo de la Intendencia",
					"https://treintaytres.gub.uy/");
			controlDEP.crearDepartamento("Cerro Largo", "División Turismo de la Intendencia",
					" https://www.gub.uy/intendencia-cerro-largo/");
			controlDEP.crearDepartamento("Rivera",
					"Promociona e implementa proyectos e iniciativas sostenibles de interes turistico con la"
							+ " participacion institucional publico - privada en bien del desarrollo socioeconomico de la comunidad",
					"http://www.rivera.gub.uy/social/turismo/");
			controlDEP.crearDepartamento("Artigas", "Division Turismo de la Intendencia", "http://www.artigas.gub.uy/");
			controlDEP.crearDepartamento("Salto", "Division Turismo de la Intendencia", "https://www.salto.gub.uy/");
			controlDEP.crearDepartamento("Paysandu", "Division Turismo de la Intendencia",
					"https://www.paysandu.gub.uy/");
			controlDEP.crearDepartamento("Rio Negro", "Division Turismo de la Intendencia",
					"https://www.rionegro.gub.uy/");
			controlDEP.crearDepartamento("Soriano", "Division Turismo de la Intendencia",
					"https://www.soriano.gub.uy/");
			controlDEP.crearDepartamento("Colonia",
					"La propuesta del Departamento de Colonia divide en cuatro actos su espectaculo anual. Cada "
							+ "acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el anio se disfruta",
					"https://colonia.gub.uy/turismo/");
			controlDEP.crearDepartamento("San Jose", "Division Turismo de la Intendencia", "https://sanjose.gub.uy/");
			controlDEP.crearDepartamento("Flores", "Division Turismo de la Intendencia", "https://flores.gub.uy/");
			controlDEP.crearDepartamento("Florida", "Division Turismo de la Intendencia", "http://www.florida.gub.uy/");
			controlDEP.crearDepartamento("Lavalleja", "Division Turismo de la Intendencia",
					"http://www.lavalleja.gub.uy/");
			controlDEP.crearDepartamento("Durazno", "Division Turismo de la Intendencia", "https://durazno.uy/");
			controlDEP.crearDepartamento("Tacuarembo", "Division Turismo de la Intendencia",
					"https://tacuarembo.gub.uy/");
			controlDEP.crearDepartamento("Montevideo", "Division Turismo de la Intendencia",
					"https://montevideo.gub.uy/areas-tematicas/turismo");

		} catch (DepartamentoRepetidoException e) {
			e.printStackTrace();
		}

		// CARGA DE USUARIO SIGUE A USUARIO
		controlUSR.seguirUsr("lachiqui", "isabelita");
		controlUSR.seguirUsr("lachiqui", "mastropiero");
		controlUSR.seguirUsr("lachiqui", "washington");
		controlUSR.seguirUsr("lachiqui", "eldiez");
		controlUSR.seguirUsr("lachiqui", "meche");
		controlUSR.seguirUsr("isabelita", "lachiqui");
		controlUSR.seguirUsr("anibal", "waston");
		controlUSR.seguirUsr("anibal", "eleven11");
		controlUSR.seguirUsr("anibal", "bobesponja");
		controlUSR.seguirUsr("anibal", "meche");
		controlUSR.seguirUsr("waston", "isabelita");
		controlUSR.seguirUsr("waston", "washington");
		controlUSR.seguirUsr("elelvis", "bobesponja");
		controlUSR.seguirUsr("elelvis", "tony");
		controlUSR.seguirUsr("elelvis", "eldiez");
		controlUSR.seguirUsr("eleven11", "lachiqui");
		controlUSR.seguirUsr("eleven11", "waston");
		controlUSR.seguirUsr("eleven11", "mastropiero");
		controlUSR.seguirUsr("bobesponja", "anibal");
		controlUSR.seguirUsr("bobesponja", "eleven11");
		controlUSR.seguirUsr("tony", "chino");
		controlUSR.seguirUsr("tony", "eldiez");
		controlUSR.seguirUsr("chino", "elelvis");
		controlUSR.seguirUsr("chino", "mastropiero");
		controlUSR.seguirUsr("chino", "washington");
		controlUSR.seguirUsr("chino", "meche");
		controlUSR.seguirUsr("washington", "mastropiero");
		controlUSR.seguirUsr("washington", "waston");
		controlUSR.seguirUsr("eldiez", "tony");
		controlUSR.seguirUsr("meche", "lachiqui");
		controlUSR.seguirUsr("meche", "isabelita");
		controlUSR.seguirUsr("meche", "waston");
		controlUSR.seguirUsr("meche", "eleven11");

		// CARGA CATEGORIAS
		try {
			controlACT.agregarCategoria("Aventura y Deporte");
			controlACT.agregarCategoria("Campo y Naturaleza");
			controlACT.agregarCategoria("Cultura y Patrimonio");
			controlACT.agregarCategoria("Gastronomia");
			controlACT.agregarCategoria("Turismo Playas");
		} catch (EntidadRepetidaException e) {
			e.printStackTrace();
		}

		// CARGA DE ACTIVIDADES
		try {
			controlACT.crearActividad("Rocha", "Festival gastronomico de productos locales en Rocha", "Degusta", 3, 800,
					"Rocha", LocalDate.of(2022, 7, 20), "washington",
					new ArrayList<String>(Arrays.asList("Gastronomia")), "bdehz9bb",
					"https://www.youtube.com/embed/zQjSMQ6uV1g", 0);
			controlACT.crearActividad("Rocha",
					"En el mes aniversario del Club Deportivo Union de Rocha te invitamos a una merienda deliciosa.",
					"Teatro con Sabores", 3, 500, "Rocha", LocalDate.of(2022, 7, 21), "washington",
					new ArrayList<String>(Arrays.asList("Gastronomia", "Cultura y Patrimonio")), "58fnr5j7",
					"https://www.youtube.com/embed/Lxit3xvKShc", 0);
			controlACT.crearActividad("Colonia",
					"Con guia especializado y en varios idiomas. Varios circuitos posibles.",
					"Tour por Colonia del Sacramento", 2, 400, "Colonia del Sacramento", LocalDate.of(2022, 8, 1),
					"meche", new ArrayList<String>(Arrays.asList("Cultura y Patrimonio")), "3rp2vvjf",
					"https://www.youtube.com/embed/zVDGjURCBz8", 0);
			controlACT.crearActividad("Colonia", "Almuerzo en la renovada Plaza de Toros con menu internacional",
					"Almuerzo en el Real de San Carlos", 2, 800, "Colonia del Sacramento", LocalDate.of(2022, 8, 1),
					"meche", new ArrayList<String>(Arrays.asList("Gastronomia")), "2yeu66vb",
					"https://www.youtube.com/embed/wfyDxicM1PQ", 0);
			controlACT.crearActividad("Rivera",
					"Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero",
					"Almuerzo en Valle del Lunarejo", 2, 300, "Tranqueras", LocalDate.of(2022, 8, 1), "eldiez",
					new ArrayList<String>(Arrays.asList("Campo y Naturaleza", "Gastronomia")), "4yrs8y2c",
					"https://www.youtube.com/embed/5uaEdiQVEEE", 0);
			controlACT.crearActividad("Rivera", "Cabalgata por el area protegida. Varios recorridos para elegir.",
					"Cabalgata en Valle del Lunarejo", 2, 150, "Tranqueras", LocalDate.of(2022, 8, 1), "eldiez",
					new ArrayList<String>(Arrays.asList("Campo y Naturaleza")), "2vjd382t",
					"https://www.youtube.com/embed/dlUb22YfXDg", 0);
			controlACT.crearActividad("Colonia", "Recorrida por los principales atractivos de la ciudad",
					"Bus turistico Colonia", 3, 600, "Colonia del Sacramento", LocalDate.of(2022, 9, 1), "meche",
					new ArrayList<String>(Arrays.asList("Cultura y Patrimonio")), "bdzyrm93",
					"https://www.youtube.com/embed/FCFoe4ibkk8", 0);
			controlACT.crearActividad("Colonia", "Visita lugares exclusivos y relevantes", "Colonia Premium Tour", 4,
					2600, "Colonia del Sacramento", LocalDate.of(2022, 9, 3), "meche",
					new ArrayList<String>(Arrays.asList("Cultura y Patrimonio")), "284kr973", "", 0);
			controlACT.crearActividad("Rocha", "kitsurf - windsurf - kayakismo - canotaje en Rocha",
					"Deportes nauticos sin uso de motor", 3, 1200, "Rocha", LocalDate.of(2022, 9, 5), "washington",
					new ArrayList<String>(Arrays.asList("Aventura y Deporte", "Turismo Playas")), "yck2a92h",
					"https://www.youtube.com/embed/a7Lfx4Flb28", 0);
			controlACT.crearActividad("Rivera",
					"Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicacion geografica privilegiada",
					"Descubre Rivera", 2, 650, "Rivera", LocalDate.of(2022, 9, 16), "eldiez",
					new ArrayList<String>(Arrays.asList("Cultura y Patrimonio")), "y4vbc4xc", "", 0);

			controlACT.aceptarActividad("Degusta");
			controlACT.aceptarActividad("Teatro con Sabores");
			controlACT.aceptarActividad("Tour por Colonia del Sacramento");
			controlACT.aceptarActividad("Almuerzo en el Real de San Carlos");
			controlACT.aceptarActividad("Almuerzo en Valle del Lunarejo");
			controlACT.aceptarActividad("Cabalgata en Valle del Lunarejo");
			controlACT.rechazarActividad("Colonia Premium Tour");
			controlACT.rechazarActividad("Descubre Rivera");

		} catch (ActividadRepetidaException | ActividadNoExisteException e) {
			e.printStackTrace();
		}

		// CARGA DE SALIDAS
		try {
			controlSAL.crearSalida("Degusta Agosto", 20, LocalDate.of(2022, 7, 21), "Sociedad Agropecuaria de Rocha",
					LocalDate.of(2022, 8, 20), "17:00", "Degusta", "4jwed4jx");
			controlSAL.crearSalida("Degusta setiembre", 20, LocalDate.of(2022, 7, 22), "Sociedad Agropecuaria de Rocha",
					LocalDate.of(2022, 9, 3), "17:00", "Degusta", "2maxmx6c");
			controlSAL.crearSalida("Teatro con Sabores 1", 30, LocalDate.of(2022, 7, 23), "Club Deportivo Union",
					LocalDate.of(2022, 9, 4), "18:00", "Teatro con Sabores", "2zturssk");
			controlSAL.crearSalida("Teatro con Sabores 2", 30, LocalDate.of(2022, 7, 23), "Club Deportivo Union",
					LocalDate.of(2022, 9, 11), "18:00", "Teatro con Sabores", "5d5vm953");
			controlSAL.crearSalida("Tour Colonia del Sacramento 11-09", 5, LocalDate.of(2022, 8, 5),
					"Encuentro en la base del Faro", LocalDate.of(2022, 9, 11), "10:00",
					"Tour por Colonia del Sacramento", "5n7ud8e7");
			controlSAL.crearSalida("Tour Colonia del Sacramento 18-09", 5, LocalDate.of(2022, 8, 5),
					"Encuentro en la base del Faro", LocalDate.of(2022, 9, 18), "10:00",
					"Tour por Colonia del Sacramento", "583b3mbs");
			controlSAL.crearSalida("Almuerzo 1", 5, LocalDate.of(2022, 8, 4), "Restaurante de la Plaza de Toros",
					LocalDate.of(2022, 9, 18), "12:00", "Almuerzo en el Real de San Carlos");
			controlSAL.crearSalida("Almuerzo 2", 5, LocalDate.of(2022, 8, 4), "Restaurante de la Plaza de Toros",
					LocalDate.of(2022, 9, 25), "12:00", "Almuerzo en el Real de San Carlos");
			controlSAL.crearSalida("Almuerzo 3", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 10), "12:00", "Almuerzo en Valle del Lunarejo");
			controlSAL.crearSalida("Almuerzo 4", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 11), "12:00", "Almuerzo en Valle del Lunarejo");
			controlSAL.crearSalida("Cabalgata 1", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 10), "16:00", "Cabalgata en Valle del Lunarejo", "2p9he77w");
			controlSAL.crearSalida("Cabalgata 2", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 11), "16:00", "Cabalgata en Valle del Lunarejo");
			controlSAL.crearSalida("Degusta Octubre", 20, LocalDate.of(2022, 9, 22), "Sociedad Agropecuaria de Rocha",
					LocalDate.of(2022, 10, 30), "17:00", "Degusta", "mryhyr5f");
			controlSAL.crearSalida("Degusta Noviembre", 20, LocalDate.of(2022, 10, 02),
					"Sociedad Agropecuaria de Rocha", LocalDate.of(2022, 11, 5), "17:00", "Degusta", "yzz6b7et");
			controlSAL.crearSalida("Teatro con Sabores 3", 30, LocalDate.of(2022, 8, 25), "Club Deportivo Union",
					LocalDate.of(2022, 11, 11), "18:00", "Teatro con Sabores");
			controlSAL.crearSalida("Tour Colonia del Sacramento 30-10", 10, LocalDate.of(2022, 9, 07),
					"Encuentro en la base del Faro", LocalDate.of(2022, 10, 30), "10:00",
					"Tour por Colonia del Sacramento", "mv7etjx2");
			controlSAL.crearSalida("Cabalgata Extrema", 4, LocalDate.of(2022, 9, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 10, 30), "16:00", "Cabalgata en Valle del Lunarejo", "3vwzthcr");
			controlSAL.crearSalida("Almuerzo en el Real 1", 10, LocalDate.of(2022, 10, 10),
					"Restaurante de la Plaza de Toros", LocalDate.of(2022, 10, 30), "12:00",
					"Almuerzo en el Real de San Carlos");
			controlSAL.crearSalida("Degusta Diciembre", 20, LocalDate.of(2022, 11, 07),
					"Sociedad Agropecuaria de Rocha", LocalDate.of(2022, 12, 02), "17:00", "Degusta", "yzz6b7et");
			controlSAL.crearSalida("Teatro con Sabores 4", 30, LocalDate.of(2022, 11, 07), "Club Deportivo Union",
					LocalDate.of(2022, 12, 03), "18:00", "Teatro con Sabores");
		} catch (SalidaRepetidaException | ActividadNoExisteException e) {
			e.printStackTrace();
		}
		// CARGA DE PAQUETES
		try {
			controlACT.crearPaquete("Disfrutar Rocha",
					"Actividades para hacer en familia y disfrutar arte y gastronomia", 60, 20,
					LocalDate.of(2022, 8, 10), "3ppwdca4");
			controlACT.crearPaquete("Un dia en Colonia",
					"Paseos por el casco historico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15,
					LocalDate.of(2022, 8, 1), "4yzrdt8b");
			controlACT.crearPaquete("Valle Del Lunarejo", "Visite un área protegida con un paisaje natural hermoso", 60,
					15, LocalDate.of(2022, 9, 15), "mvteyv6y");
			controlACT.crearPaquete("Rocha de Fiesta",
					"Para cerrar el año a lo grande en nuestro departamento mas oceanico", 45, 30,
					LocalDate.of(2022, 11, 07), "");
		} catch (EntidadRepetidaException e) {
			e.printStackTrace();
		}

		// CARGA DE ACTIVIDADES FAVORITAS
		controlUSR.marcarFavorita("lachiqui", "Degusta");
		controlUSR.marcarFavorita("lachiqui", "Tour por Colonia del Sacramento");
		controlUSR.marcarFavorita("isabelita", "Tour por Colonia del Sacramento");
		controlUSR.marcarFavorita("isabelita", "Almuerzo en el Real de San Carlos");
		controlUSR.marcarFavorita("anibal", "Almuerzo en el Real de San Carlos");
		controlUSR.marcarFavorita("anibal", "Almuerzo en Valle del Lunarejo");
		controlUSR.marcarFavorita("anibal", "Cabalgata en Valle del Lunarejo");
		controlUSR.marcarFavorita("waston", "Degusta");
		controlUSR.marcarFavorita("waston", "Teatro con Sabores");
		controlUSR.marcarFavorita("waston", "Tour por Colonia del Sacramento");
		controlUSR.marcarFavorita("waston", "Almuerzo en el Real de San Carlos");
		controlUSR.marcarFavorita("elelvis", "Cabalgata en Valle del Lunarejo");
		controlUSR.marcarFavorita("eleven11", "Degusta");
		controlUSR.marcarFavorita("eleven11", "Teatro con Sabores");
		controlUSR.marcarFavorita("bobesponja", "Tour por Colonia del Sacramento");
		controlUSR.marcarFavorita("bobesponja", "Almuerzo en el Real de San Carlos");
		controlUSR.marcarFavorita("tony", "Teatro con Sabores");

		// CARGA DE ACTIVIDADES EN PAQUETES
		try {
			controlACT.agregarActividadPaquete("Disfrutar Rocha", "Degusta");
			controlACT.agregarActividadPaquete("Disfrutar Rocha", "Teatro con Sabores");
			controlACT.agregarActividadPaquete("Un dia en Colonia", "Tour por Colonia del Sacramento");
			controlACT.agregarActividadPaquete("Valle Del Lunarejo", "Almuerzo en Valle del Lunarejo");
			controlACT.agregarActividadPaquete("Valle Del Lunarejo", "Cabalgata en Valle del Lunarejo");
			controlACT.agregarActividadPaquete("Rocha de Fiesta", "Degusta");
		} catch (NoExistePaqueteException | ActividadNoExisteException | ActividadRepetidaException
				| PaqueteCompradoException e) {
			e.printStackTrace();
		}

		// COMPRAS
		try {
			controlUSR.compraTuristaPaquete("lachiqui", "Disfrutar Rocha", LocalDate.of(2022, 8, 15), 2);
			controlUSR.compraTuristaPaquete("lachiqui", "Un dia en Colonia", LocalDate.of(2022, 8, 20), 5);
			controlUSR.compraTuristaPaquete("waston", "Un dia en Colonia", LocalDate.of(2022, 9, 15), 1);
			controlUSR.compraTuristaPaquete("elelvis", "Disfrutar Rocha", LocalDate.of(2022, 9, 1), 10);
			controlUSR.compraTuristaPaquete("elelvis", "Un dia en Colonia", LocalDate.of(2022, 9, 18), 2);
			controlUSR.compraTuristaPaquete("mastropiero", "Un dia en Colonia", LocalDate.of(2022, 9, 2), 6);

		} catch (PaqueteCompradoException e) {
			e.printStackTrace();
		}

		// CARGA DE INSCRIPCIONES
		try {
			controlSAL.inscribirTuristaSalida("lachiqui", "Degusta Agosto", 3, LocalDate.of(2022, 8, 15));
			controlSAL.inscribirTuristaSalida("elelvis", "Degusta Agosto", 5, LocalDate.of(2022, 8, 16));
			controlSAL.inscribirTuristaSalida("lachiqui", "Tour Colonia del Sacramento 18-09", 3,
					LocalDate.of(2022, 8, 18));
			controlSAL.inscribirTuristaSalida("isabelita", "Tour Colonia del Sacramento 18-09", 1,
					LocalDate.of(2022, 8, 19));
			controlSAL.inscribirTuristaSalida("mastropiero", "Almuerzo 2", 2, LocalDate.of(2022, 8, 19));
			controlSAL.inscribirTuristaSalida("chino", "Teatro con Sabores 1", 1, LocalDate.of(2022, 8, 19));
			controlSAL.inscribirTuristaSalida("chino", "Teatro con Sabores 2", 10, LocalDate.of(2022, 8, 20));
			controlSAL.inscribirTuristaSalida("bobesponja", "Teatro con Sabores 2", 2, LocalDate.of(2022, 8, 20));
			controlSAL.inscribirTuristaSalida("anibal", "Teatro con Sabores 2", 1, LocalDate.of(2022, 8, 21));
			controlSAL.inscribirTuristaSalida("tony", "Degusta setiembre", 11, LocalDate.of(2022, 8, 21));
			controlSAL.inscribirTuristaSalidaPaquete("lachiqui", "Degusta Noviembre", "Disfrutar Rocha", 2,
					LocalDate.of(2022, 10, 3));
			controlSAL.inscribirTuristaSalidaPaquete("lachiqui", "Teatro con Sabores 3", "Disfrutar Rocha", 2,
					LocalDate.of(2022, 10, 3));
			controlSAL.inscribirTuristaSalidaPaquete("elelvis", "Degusta setiembre", "Disfrutar Rocha", 5,
					LocalDate.of(2022, 9, 2));
			controlSAL.inscribirTuristaSalidaPaquete("elelvis", "Teatro con Sabores 1", "Disfrutar Rocha", 5,
					LocalDate.of(2022, 9, 2));
			controlSAL.inscribirTuristaSalidaPaquete("lachiqui", "Tour Colonia del Sacramento 11-09",
					"Un dia en Colonia", 5, LocalDate.of(2022, 9, 3));
			controlSAL.inscribirTuristaSalida("lachiqui", "Almuerzo 1", 5, LocalDate.of(2022, 9, 3));
			controlSAL.inscribirTuristaSalidaPaquete("waston", "Tour Colonia del Sacramento 18-09", "Un dia en Colonia",
					1, LocalDate.of(2022, 9, 5));
			controlSAL.inscribirTuristaSalida("waston", "Almuerzo 2", 1,
					LocalDate.of(2022, 9, 5));
			controlSAL.inscribirTuristaSalidaPaquete("elelvis", "Tour Colonia del Sacramento 30-10",
					"Un dia en Colonia", 2, LocalDate.of(2022, 10, 2));
			controlSAL.inscribirTuristaSalida("elelvis", "Almuerzo en el Real 1", 2,
					LocalDate.of(2022, 10, 11));
			controlSAL.inscribirTuristaSalidaPaquete("mastropiero", "Tour Colonia del Sacramento 30-10",
					"Un dia en Colonia", 4, LocalDate.of(2022, 10, 12));
			controlSAL.inscribirTuristaSalida("mastropiero", "Almuerzo en el Real 1", 4,
					LocalDate.of(2022, 10, 12));
		} catch (NoExisteEntidadException | InscripcionRepetidaException | NoHayCapacidadException
				| PaqueteVencidoException | ActividadNoExisteException e) {
			e.printStackTrace();
		}
		
	
	}

}
