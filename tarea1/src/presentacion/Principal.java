package presentacion;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logica.DatosPrueba;

public class Principal {

	private JFrame frmGestionDeUsuarios;
	private CrearUsuario creUsrInternalFrame;
	private ConsultarUsuario conUsrInternalFrame;
	private ModificarDatosUsuario modUsrInternalFrame;
	private ConsultarActividad conActInternalFrame;
	private ConsultarSalidas conSalInternalFrame;
	private AltaActividadTuristica altActTur;
	private AltaSalidaTuristica altSalTur;
	private InscripcionSalidaTuristica inscripcionSalidaFrame;
	private AgregarActividadAPaquete agregarActividadFrame;
	private CrearPaqueteActividades crearPaqueteActividades;
	private ConsultarPaqueteActividades consultarPaqueteActividades;
	private AceptarRechazarActividad aceptarRechazarAct;
	private AltaCategoria altaCategoria;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmGestionDeUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();

		// Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
		// De esta forma, no es necesario crear y destruir objetos lo que enlentece la
		// ejecución.
		// Cada InternalFrame usa un layout diferente, simplemente para mostrar
		// distintas opciones.
		FabricaInternalFrames fabricaInternalFrames = FabricaInternalFrames.getInstance();
		creUsrInternalFrame = new CrearUsuario();
		creUsrInternalFrame.setVisible(false);

		conSalInternalFrame = fabricaInternalFrames.getConsultarSalidas();
		conSalInternalFrame.setVisible(false);

		conActInternalFrame = fabricaInternalFrames.getConsultarActividad();
		conActInternalFrame.setVisible(false);

		conUsrInternalFrame = fabricaInternalFrames.getConsultarUsuario();
		conUsrInternalFrame.setVisible(false);

		consultarPaqueteActividades = fabricaInternalFrames.getConsultarPaqueteActividades();
		consultarPaqueteActividades.setVisible(false);

		modUsrInternalFrame = new ModificarDatosUsuario();
		modUsrInternalFrame.setVisible(false);

		frmGestionDeUsuarios.getContentPane().setLayout(null);

		altActTur = new AltaActividadTuristica();
		altActTur.setVisible(false);

		altSalTur = new AltaSalidaTuristica();
		altSalTur.setVisible(false);

		inscripcionSalidaFrame = new InscripcionSalidaTuristica();
		inscripcionSalidaFrame.setVisible(false);

		agregarActividadFrame = new AgregarActividadAPaquete();
		agregarActividadFrame.setVisible(false);

		crearPaqueteActividades = new CrearPaqueteActividades();
		crearPaqueteActividades.setVisible(false);

		aceptarRechazarAct = new AceptarRechazarActividad();
		aceptarRechazarAct.setVisible(false);

		altaCategoria = new AltaCategoria();
		altaCategoria.setVisible(false);

		frmGestionDeUsuarios.getContentPane().add(conUsrInternalFrame);
		frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
		frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame);
		frmGestionDeUsuarios.getContentPane().add(conActInternalFrame);
		frmGestionDeUsuarios.getContentPane().add(conSalInternalFrame);
		frmGestionDeUsuarios.getContentPane().add(altActTur);
		frmGestionDeUsuarios.getContentPane().add(altSalTur);
		frmGestionDeUsuarios.getContentPane().add(inscripcionSalidaFrame);
		frmGestionDeUsuarios.getContentPane().add(agregarActividadFrame);
		frmGestionDeUsuarios.getContentPane().add(crearPaqueteActividades);
		frmGestionDeUsuarios.getContentPane().add(consultarPaqueteActividades);
		frmGestionDeUsuarios.getContentPane().add(aceptarRechazarAct);
		frmGestionDeUsuarios.getContentPane().add(altaCategoria);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Se crea el Frame con las dimensiones indicadas.
		frmGestionDeUsuarios = new JFrame();
		frmGestionDeUsuarios.setTitle("Administracion Turismo.uy");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		frmGestionDeUsuarios.setSize(myScreen.getScreenSize().width * 2 / 3, myScreen.getScreenSize().height * 2 / 3);
		frmGestionDeUsuarios.setLocation(myScreen.getScreenSize().width / 5, myScreen.getScreenSize().height / 5);
		frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
		// Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un
		// evento asociado que permite realizar una acción una vez se seleccionan.
		JMenuBar menuBar = new JMenuBar();
		frmGestionDeUsuarios.setJMenuBar(menuBar);

		JMenu menuSistema = new JMenu("Sistema");
		menuBar.add(menuSistema);

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Salgo de la aplicación
				frmGestionDeUsuarios.setVisible(false);
				frmGestionDeUsuarios.dispose();
			}
		});

		JMenuItem mntmCargarDatos = new JMenuItem("Cargar Datos");
		mntmCargarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				DatosPrueba.cargarDatos();
				JOptionPane.showMessageDialog(mntmCargarDatos, "Los datos fueron cargados con éxito");
			}
		});
		menuSistema.add(mntmCargarDatos);
		menuSistema.add(menuSalir);

		JMenu menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);

		JMenuItem menuItemRegistrar = new JMenuItem("Registrar usuario");
		menuItemRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Muestro el InternalFrame para registrar un usuario
				creUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemRegistrar);

		JMenuItem menuItemVerInfo = new JMenuItem("Consultar Usuario");
		menuItemVerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Muestro el InternalFrame para ver información de un usuario
				conUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemVerInfo);

		JMenuItem mntmListaUsuarios = new JMenuItem("Modificar usuario");
		mntmListaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(mntmListaUsuarios);

		JMenu mnActividades = new JMenu("Actividades");
		menuBar.add(mnActividades);

		JMenuItem mntmRegistrarActividad = new JMenuItem("Registrar actividad");

		mntmRegistrarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altActTur.refrescar();
				altActTur.setVisible(true);
			}
		});
		mnActividades.add(mntmRegistrarActividad);

		JMenuItem mntmVerInformacion = new JMenuItem("Consultar actividad");
		mntmVerInformacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				conActInternalFrame.recargar();
				conActInternalFrame.setVisible(true);

			}
		});
		mnActividades.add(mntmVerInformacion);

		JMenuItem mntmAceptarORechazar = new JMenuItem("Aceptar o rechazar actividad");
		mntmAceptarORechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aceptarRechazarAct.recargar();
				aceptarRechazarAct.setVisible(true);
			}
		});
		mnActividades.add(mntmAceptarORechazar);

		JMenuItem mntmCrearPaqueteDe = new JMenuItem("Crear paquete de actividades");
		mntmCrearPaqueteDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearPaqueteActividades.setVisible(true);
			}
		});
		mnActividades.add(mntmCrearPaqueteDe);

		JMenuItem mntmConsultarPaquetes = new JMenuItem("Consultar paquete");
		mntmConsultarPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarPaqueteActividades.recargar();
				consultarPaqueteActividades.setVisible(true);
			}
		});
		mnActividades.add(mntmConsultarPaquetes);

		JMenuItem mntmAgregarActividadA = new JMenuItem("Agregar actividad a paquete");
		mntmAgregarActividadA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarActividadFrame.recargar();
				agregarActividadFrame.setVisible(true);
			}
		});
		mnActividades.add(mntmAgregarActividadA);

		JMenuItem mntmAltaDeCategor = new JMenuItem("Alta de Categoría");
		mntmAltaDeCategor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altaCategoria.setVisible(true);
			}
		});
		mnActividades.add(mntmAltaDeCategor);

		JMenu mnSalidas = new JMenu("Salidas");
		menuBar.add(mnSalidas);

		JMenuItem mntmRegistrarSalida = new JMenuItem("Registrar salida");
		mntmRegistrarSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altSalTur.setVisible(true);
				altSalTur.refrescar();
			}
		});
		mnSalidas.add(mntmRegistrarSalida);

		JMenuItem mntmConsultarSalida = new JMenuItem("Consultar salida");
		mntmConsultarSalida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				conSalInternalFrame.recargar();
				conSalInternalFrame.setVisible(true);

			}
		});
		mnSalidas.add(mntmConsultarSalida);

		JMenuItem mntmInscripcionASalida = new JMenuItem("Inscripción a salida");
		mntmInscripcionASalida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				inscripcionSalidaFrame.hacerVisible();
				;

			}
		});
		mnSalidas.add(mntmInscripcionASalida);

	}
}
