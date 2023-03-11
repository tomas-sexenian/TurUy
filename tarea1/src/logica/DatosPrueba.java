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
			String password = "pwd";
			controlUSR.crearTurista("lachiqui", "Rosa Maria", "Martinez", "mirtha.legrand.ok@hotmail.com.ar",
					LocalDate.of(1972, 2, 23), "argentina", password);
			controlUSR.crearTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk",
					LocalDate.of(1926, 4, 21), "inglesa", password);
			controlUSR.crearTurista("anibal", "Anibal", "Lecter", "anibal@fing.edu.uy", LocalDate.of(1937, 12, 31),
					"lituana", password);
			controlUSR.crearTurista("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.of(1990, 4, 14),
					"inglesa", password);
			controlUSR.crearTurista("elelvis", "Elivis", "Lacio", "suavemente@hotmail.com", LocalDate.of(1971, 7, 30),
					"estadounidense", password);
			controlUSR.crearTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.of(2004, 2, 19),
					"espaniola", password);
			controlUSR.crearTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com",
					LocalDate.of(1999, 5, 1), "japonesa", password);
			controlUSR.crearTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.of(1976, 4, 11),
					"uruguaya", password);
			controlUSR.crearTurista("chino", "Alvaro", "Recoba", "chino@trico.or.uy", LocalDate.of(1976, 3, 17),
					"uruguaya", password);
			controlUSR.crearTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com",
					LocalDate.of(1922, 2, 7), "austriaca", password);
			controlUSR.crearProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy",
					LocalDate.of(1970, 9, 14),
					"Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay",
					"http://turismorocha.gub.uy/", password);
			controlUSR.crearProveedor("eldiez", "Pablo", "Bengochea", "eldiez@socfomturriv.gob.uy",
					LocalDate.of(1965, 6, 27),
					"Pablo es el presidente de la Sociedad de Fomento Turistico de Rivera (conocida como Socfomturriv)",
					"http://socfomturriv.org.uy", password);
			controlUSR.crearProveedor("meche", "Mercedes", "Venn", "meche@colonia.org.uy", LocalDate.of(1990, 12, 31),
					"Departamento de Turismo del Departamento de Colonia", "http://colonia.gub.uy/turismo/", password);
		} catch (EmailRepetidoException e) {
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			// TODO Auto-generated catch block
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

		} catch (DepartamentoRepetidoException ignored) {
		}

		// CARGA CATEGORIAS
		try {
			controlACT.agregarCategoria("Categoria de prueba");
		} catch (EntidadRepetidaException e) {
			// TODO: handle exception
		}

		// CARGA DE ACTIVIDADES
		try {
			controlACT.crearActividad("Rocha", "Festival gastronomico de productos locales en Rocha", "Degusta", 3, 800,
					"Rocha", LocalDate.of(2022, 7, 20), "washington",
					new ArrayList<String>(Arrays.asList("Categoria de prueba")));
			controlACT.crearActividad("Rocha",
					"En el mes aniversario del Club Deportivo Union de Rocha te invitamos a una merienda deliciosa.",
					"Teatro con Sabores", 3, 500, "Rocha", LocalDate.of(2022, 7, 21), "washington",
					new ArrayList<String>(Arrays.asList("Categoria de prueba")));
			controlACT.crearActividad("Colonia",
					"Con guia especializado y en varios idiomas. Varios circuitos posibles.",
					"Tour por Colonia del Sacramento", 2, 400, "Colonia del Sacramento", LocalDate.of(2022, 8, 1),
					"meche", new ArrayList<String>(Arrays.asList("Categoria de prueba")));
			controlACT.crearActividad("Colonia", "Almuerzo en la renovada Plaza de Toros con menu internacional",
					"Almuerzo en el Real de San Carlos", 2, 800, "Colonia del Sacramento", LocalDate.of(2022, 8, 1),
					"meche", new ArrayList<String>(Arrays.asList("Categoria de prueba")));
			controlACT.crearActividad("Rivera",
					"Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero",
					"Almuerzo en Valle del Lunarejo", 2, 300, "Tranqueras", LocalDate.of(2022, 8, 1), "eldiez",
					new ArrayList<String>(Arrays.asList("Categoria de prueba")));
			controlACT.crearActividad("Rivera", "Cabalgata por el area protegida. Varios recorridos para elegir.",
					"Cabalgata en el Valle del Lunarejo", 2, 150, "Tranqueras", LocalDate.of(2022, 8, 1), "eldiez",
					new ArrayList<String>(Arrays.asList("Categoria de prueba")));
		} catch (ActividadRepetidaException ignored) {
		}

		// CARGA DE SALIDAS
		try {
			controlSAL.crearSalida("Degusta Agosto", 20, LocalDate.of(2022, 7, 21), "Sociedad Agropecuaria de Rocha",
					LocalDate.of(2022, 8, 20), 17, "Degusta");
			controlSAL.crearSalida("Degusta setiembre", 20, LocalDate.of(2022, 7, 22), "Sociedad Agropecuaria de Rocha",
					LocalDate.of(2022, 9, 3), 17, "Degusta");
			controlSAL.crearSalida("Teatro con Sabores 1", 30, LocalDate.of(2022, 7, 23), "Club Deportivo Union",
					LocalDate.of(2022, 9, 4), 18, "Teatro con Sabores");
			controlSAL.crearSalida("Teatro con Sabores 2", 30, LocalDate.of(2022, 7, 23), "Club Deportivo Union",
					LocalDate.of(2022, 9, 11), 18, "Teatro con Sabores");
			controlSAL.crearSalida("Tour Colonia del Sacramento 11-09", 5, LocalDate.of(2022, 8, 5),
					"Encuentro en la base del Faro", LocalDate.of(2022, 9, 11), 10, "Tour por Colonia del Sacramento");
			controlSAL.crearSalida("Tour Colonia del Sacramento 18-09", 5, LocalDate.of(2022, 8, 5),
					"Encuentro en la base del Faro", LocalDate.of(2022, 9, 18), 10, "Tour por Colonia del Sacramento");
			controlSAL.crearSalida("Almuerzo 1", 5, LocalDate.of(2022, 8, 4), "Restaurante de la Plaza de Toros",
					LocalDate.of(2022, 9, 18), 12, "Almuerzo en el Real de San Carlos");
			controlSAL.crearSalida("Almuerzo 2", 5, LocalDate.of(2022, 8, 4), "Restaurante de la Plaza de Toros",
					LocalDate.of(2022, 9, 25), 12, "Almuerzo en el Real de San Carlos");
			controlSAL.crearSalida("Almuerzo 3", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 10), 12, "Almuerzo en Valle del Lunarejo");
			controlSAL.crearSalida("Almuerzo 4", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 11), 12, "Almuerzo en Valle del Lunarejo");
			controlSAL.crearSalida("Cabalgata 1", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 10), 16, "Cabalgata en el Valle del Lunarejo");
			controlSAL.crearSalida("Cabalgata 2", 4, LocalDate.of(2022, 8, 15), "Posada del Lunarejo",
					LocalDate.of(2022, 9, 11), 16, "Cabalgata en el Valle del Lunarejo");
		} catch (SalidaRepetidaException ignored) {
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
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

		} catch (NoExisteEntidadException e) {
			// TODO: handle exception
		} catch (InscripcionRepetidaException e) {
			// TODO: handle exception
		} catch (NoHayCapacidadException e) {
			// TODO: handle exception
		}

		// CARGA DE PAQUETES
		try {
			controlACT.crearPaquete("Disfrutar Rocha",
					"Actividades para hacer en familia y disfrutar arte y gastronomia", 60, 20,
					LocalDate.of(2022, 8, 10));
			controlACT.crearPaquete("Un dia en Colonia",
					"Paseos por el casco historico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15,
					LocalDate.of(2022, 8, 1));
		} catch (EntidadRepetidaException ignored) {
		}

		// CARGA DE ACTIVIDADES EN PAQUETES
		try {
			controlACT.agregarActividadPaquete("Disfrutar Rocha", "Degusta");
			controlACT.agregarActividadPaquete("Disfrutar Rocha", "Teatro con Sabores");
			controlACT.agregarActividadPaquete("Un dia en Colonia", "Tour por Colonia del Sacramento");
			controlACT.agregarActividadPaquete("Un dia en Colonia", "Almuerzo en el Real de San Carlos");
		} catch (NoExistePaqueteException ignored) {

		} catch (ActividadNoExisteException e) {
			// TODO: handle exception
		} catch (ActividadRepetidaException ignored) {

		} catch (PaqueteCompradoException e) {
			// TODO Auto-generated catch block
		}
	}

}
