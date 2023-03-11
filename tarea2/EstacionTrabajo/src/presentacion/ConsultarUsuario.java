package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import excepciones.ActividadNoExisteException;
import excepciones.SalidaNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorSalidas;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings({ "serial", "deprecation" })
public class ConsultarUsuario extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorUsuario controlUsr;
	private JTextField textFieldNombre;
	private JLabel lblIngresoCI;
	private JButton btnBuscarTurista;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JLabel lblInfoUsuario;
	private JComboBox comboBoxTurista;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JTextField textFieldNick;
	private JLabel lblTipo;
	private JLabel lblNacionalidad;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField textFieldTipo;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblNacimiento;
	private JLabel lblNickname;
	private JComboBox comboBoxSalidas;
	private JComboBox comboBoxActividades;
	private JButton btnVerActividades;
	private JButton btnVerSalidas;
	private JLabel lblActividades;
	private JLabel lblSalidas;
	private JTextField textFieldNacimiento;
	private JLabel lblSeleccioneUnProveedor;
	private JComboBox comboBoxProveedor;
	private JButton btnBuscarProveedor;
	private JLabel lblTipoDeUsuario;
	private JComboBox comboBoxTipoUsuario;
	private DataTurista[] turistas;
	private DataTurista turista;
	private DataProveedor[] proveedores;
	private DataProveedor proveedor;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ConsultarUsuario() {

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();

		// Inicializando colecciones de turistas y de proveedores
		try {
			turistas = controlUsr.getTuristas();
		} catch (UsuarioNoExisteException e) {
			turistas = new DataTurista[0];
		}
		try {
			proveedores = controlUsr.getProveedores();
		} catch (UsuarioNoExisteException e) {
			proveedores = new DataProveedor[0];
		}

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
			}

			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
		});

		setClosable(true);
		setTitle("Consultar un Usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 178, 190, 0, 190, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 31, 0, 34, 30, 0, 0, 0, 0, 0, 0, 0, 0, 23, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipoDeUsuario = new GridBagConstraints();
		gbclblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbclblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblTipoDeUsuario.gridx = 0;
		gbclblTipoDeUsuario.gridy = 1;
		getContentPane().add(lblTipoDeUsuario, gbclblTipoDeUsuario);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.addItem(new ItemTexto("Seleccione..."));
		comboBoxTipoUsuario.addItem(new ItemTexto("Proveedor"));
		comboBoxTipoUsuario.addItem(new ItemTexto("Turista"));
		comboBoxTipoUsuario.setMaximumRowCount(3);
		GridBagConstraints gbccomboBoxTipoUsuario = new GridBagConstraints();
		gbccomboBoxTipoUsuario.gridwidth = 2;
		gbccomboBoxTipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTipoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxTipoUsuario.gridx = 1;
		gbccomboBoxTipoUsuario.gridy = 1;
		comboBoxTipoUsuario.setSelectedIndex(0);
		getContentPane().add(comboBoxTipoUsuario, gbccomboBoxTipoUsuario);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la cédula del usuario.
		lblIngresoCI = new JLabel("Seleccione un turista:");
		GridBagConstraints gbclblIngresoCI = new GridBagConstraints();
		gbclblIngresoCI.anchor = GridBagConstraints.EAST;
		gbclblIngresoCI.insets = new Insets(0, 0, 5, 5);
		gbclblIngresoCI.gridx = 0;
		gbclblIngresoCI.gridy = 2;
		lblIngresoCI.setVisible(false);
		getContentPane().add(lblIngresoCI, gbclblIngresoCI);

		// Un botón (JButton) con un evento asociado que permite buscar un usuario.
		// Dado que el código de registro tiene cierta complejidad, conviene delegarlo
		// a otro método en lugar de incluirlo directamente de el método actionPerformed
		btnBuscarTurista = new JButton("Buscar");
		btnBuscarTurista.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscarTurista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdBuscarTuristaActionPerformed(event);
			}
		});
		comboBoxTurista = new JComboBox();
		GridBagConstraints gbccomboBoxTurista = new GridBagConstraints();
		gbccomboBoxTurista.gridwidth = 2;
		gbccomboBoxTurista.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTurista.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxTurista.gridx = 1;
		gbccomboBoxTurista.gridy = 2;
		getContentPane().add(comboBoxTurista, gbccomboBoxTurista);
		GridBagConstraints gbcbtnBuscarTurista = new GridBagConstraints();
		gbcbtnBuscarTurista.fill = GridBagConstraints.VERTICAL;
		gbcbtnBuscarTurista.anchor = GridBagConstraints.WEST;
		gbcbtnBuscarTurista.insets = new Insets(0, 0, 5, 5);
		gbcbtnBuscarTurista.gridx = 3;
		gbcbtnBuscarTurista.gridy = 2;
		comboBoxTurista.setVisible(false);
		getContentPane().add(btnBuscarTurista, gbcbtnBuscarTurista);

		lblSeleccioneUnProveedor = new JLabel("Seleccione un proveedor:");
		lblSeleccioneUnProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblSeleccioneUnProveedor = new GridBagConstraints();
		gbclblSeleccioneUnProveedor.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnProveedor.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnProveedor.gridx = 0;
		gbclblSeleccioneUnProveedor.gridy = 3;
		lblSeleccioneUnProveedor.setVisible(false);
		getContentPane().add(lblSeleccioneUnProveedor, gbclblSeleccioneUnProveedor);

		comboBoxProveedor = new JComboBox();
		GridBagConstraints gbccomboBoxProveedor = new GridBagConstraints();
		gbccomboBoxProveedor.gridwidth = 2;
		gbccomboBoxProveedor.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxProveedor.gridx = 1;
		gbccomboBoxProveedor.gridy = 3;
		comboBoxProveedor.setVisible(false);
		getContentPane().add(comboBoxProveedor, gbccomboBoxProveedor);

		btnBuscarProveedor = new JButton("Buscar");
		btnBuscarProveedor.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdBuscarProveedorActionPerformed(event);
			}
		});
		GridBagConstraints gbcbtnBuscarProveedor = new GridBagConstraints();
		gbcbtnBuscarProveedor.fill = GridBagConstraints.VERTICAL;
		gbcbtnBuscarProveedor.anchor = GridBagConstraints.WEST;
		gbcbtnBuscarProveedor.insets = new Insets(0, 0, 5, 5);
		gbcbtnBuscarProveedor.gridx = 3;
		gbcbtnBuscarProveedor.gridy = 3;
		getContentPane().add(btnBuscarProveedor, gbcbtnBuscarProveedor);

		// Una etiqueta (JLabel) indicando que a continuación se verá la
		// información del usuarios buscado.
		lblInfoUsuario = new JLabel("Información del  usuario");
		GridBagConstraints gbclblInfoUsuario = new GridBagConstraints();
		gbclblInfoUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblInfoUsuario.gridwidth = 2;
		gbclblInfoUsuario.gridx = 1;
		gbclblInfoUsuario.gridy = 4;
		getContentPane().add(lblInfoUsuario, gbclblInfoUsuario);

		// Una etiqueta (JLabel) indicando que en el siguiente campo se verá
		// el nombre del usuario encontrado.
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbclblNombre);

		// Una campo de texto (JTextField) para mostrar el nombre del usuario.
		// El campo se hace no editable para impedir que se modifique.
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblApellido = new GridBagConstraints();
		gbclblApellido.anchor = GridBagConstraints.EAST;
		gbclblApellido.insets = new Insets(0, 0, 5, 5);
		gbclblApellido.gridx = 2;
		gbclblApellido.gridy = 5;
		getContentPane().add(lblApellido, gbclblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldApellido.gridx = 3;
		gbctextFieldApellido.gridy = 5;
		getContentPane().add(textFieldApellido, gbctextFieldApellido);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblEmail = new GridBagConstraints();
		gbclblEmail.anchor = GridBagConstraints.EAST;
		gbclblEmail.insets = new Insets(0, 0, 5, 5);
		gbclblEmail.gridx = 0;
		gbclblEmail.gridy = 6;
		getContentPane().add(lblEmail, gbclblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 3;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 6;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);
		textFieldEmail.setColumns(10);

		lblNickname = new JLabel("Nickname");
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 7;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNick = new JTextField();
		textFieldNick.setEditable(false);
		GridBagConstraints gbctextFieldNick = new GridBagConstraints();
		gbctextFieldNick.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNick.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNick.gridx = 1;
		gbctextFieldNick.gridy = 7;
		getContentPane().add(textFieldNick, gbctextFieldNick);
		textFieldNick.setColumns(10);

		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.anchor = GridBagConstraints.EAST;
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 2;
		gbclblNacimiento.gridy = 7;
		getContentPane().add(lblNacimiento, gbclblNacimiento);

		textFieldNacimiento = new JTextField();
		textFieldNacimiento.setEditable(false);
		GridBagConstraints gbctextFieldNacimiento = new GridBagConstraints();
		gbctextFieldNacimiento.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNacimiento.gridx = 3;
		gbctextFieldNacimiento.gridy = 7;
		getContentPane().add(textFieldNacimiento, gbctextFieldNacimiento);
		textFieldNacimiento.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 8;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbctextFieldTipo = new GridBagConstraints();
		gbctextFieldTipo.gridwidth = 3;
		gbctextFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbctextFieldTipo.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldTipo.gridx = 1;
		gbctextFieldTipo.gridy = 8;
		getContentPane().add(textFieldTipo, gbctextFieldTipo);
		textFieldTipo.setColumns(10);

		lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacionalidad = new GridBagConstraints();
		gbclblNacionalidad.anchor = GridBagConstraints.EAST;
		gbclblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbclblNacionalidad.gridx = 0;
		gbclblNacionalidad.gridy = 9;
		lblNacionalidad.setVisible(false);
		getContentPane().add(lblNacionalidad, gbclblNacionalidad);

		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setEditable(false);
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 3;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNacionalidad.gridx = 1;
		gbctextFieldNacionalidad.gridy = 9;
		textFieldNacionalidad.setVisible(false);
		getContentPane().add(textFieldNacionalidad, gbctextFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 10;
		lblDescripcion.setVisible(false);
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		GridBagConstraints gbctextFieldDescripcion = new GridBagConstraints();
		gbctextFieldDescripcion.gridwidth = 3;
		gbctextFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldDescripcion.gridx = 1;
		gbctextFieldDescripcion.gridy = 10;
		getContentPane().add(textFieldDescripcion, gbctextFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setVisible(false);

		lblLink = new JLabel("Link:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 11;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		textFieldLink.setEditable(false);
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 3;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 11;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);

		lblActividades = new JLabel("Actividades");
		GridBagConstraints gbclblActividades = new GridBagConstraints();
		gbclblActividades.insets = new Insets(0, 0, 5, 5);
		gbclblActividades.anchor = GridBagConstraints.EAST;
		gbclblActividades.gridx = 0;
		gbclblActividades.gridy = 12;
		lblActividades.setVisible(false);
		getContentPane().add(lblActividades, gbclblActividades);

		comboBoxActividades = new JComboBox();
		GridBagConstraints gbccomboBoxActividades = new GridBagConstraints();
		gbccomboBoxActividades.gridwidth = 2;
		gbccomboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividades.gridx = 1;
		gbccomboBoxActividades.gridy = 12;
		comboBoxActividades.setVisible(false);
		getContentPane().add(comboBoxActividades, gbccomboBoxActividades);

		lblSalidas = new JLabel("Salidas");
		GridBagConstraints gbclblSalidas = new GridBagConstraints();
		gbclblSalidas.insets = new Insets(0, 0, 5, 5);
		gbclblSalidas.anchor = GridBagConstraints.EAST;
		gbclblSalidas.gridx = 0;
		gbclblSalidas.gridy = 13;
		lblSalidas.setVisible(false);

		btnVerActividades = new JButton("Ver");
		btnVerActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxActividades.getSelectedItem() != null) {

					try {
						String dact = comboBoxActividades.getSelectedItem().toString();
						Fabrica fabrica = Fabrica.getInstance();
						IControladorActividades controladorActividades = fabrica.getIControladorActividades();
						DataActividadTuristica act = controladorActividades.consultarActividad(dact);

						FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
						ConsultarActividad ifrcActividad = fab.getConsultarActividad();
						ifrcActividad.recargar();
						ifrcActividad.setDesdeConsultarUsuarios(true);
						ifrcActividad.fromOtraVentana(act);
						ifrcActividad.setVisible(true);
						setVisible(false);
					} catch (ActividadNoExisteException actividadException) {
						// AGREGAR ALGUN MENSAJE DE ERROR
					}
				} else {
					JOptionPane.showMessageDialog(comboBoxActividades, "No hay ninguna actividad seleccionada",
							"Sistema", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		GridBagConstraints gbcbtnVerActividades = new GridBagConstraints();
		gbcbtnVerActividades.anchor = GridBagConstraints.WEST;
		gbcbtnVerActividades.fill = GridBagConstraints.VERTICAL;
		gbcbtnVerActividades.insets = new Insets(0, 0, 5, 5);
		gbcbtnVerActividades.gridx = 3;
		gbcbtnVerActividades.gridy = 12;
		btnVerActividades.setVisible(false);
		getContentPane().add(btnVerActividades, gbcbtnVerActividades);
		getContentPane().add(lblSalidas, gbclblSalidas);

		comboBoxSalidas = new JComboBox();
		GridBagConstraints gbccomboBoxSalidas = new GridBagConstraints();
		gbccomboBoxSalidas.gridwidth = 2;
		gbccomboBoxSalidas.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxSalidas.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxSalidas.gridx = 1;
		gbccomboBoxSalidas.gridy = 13;
		comboBoxSalidas.setVisible(false);
		getContentPane().add(comboBoxSalidas, gbccomboBoxSalidas);

		btnVerSalidas = new JButton("Ver");
		btnVerSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (comboBoxSalidas.getSelectedItem() != null) {
					try {
						String sal = comboBoxSalidas.getSelectedItem().toString();
						Fabrica fabrica = Fabrica.getInstance();
						IControladorSalidas controladorSalidas = fabrica.getIControladorSalidas();
						DataSalidaTuristica dsal = controladorSalidas.consultarSalida(sal);
						FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
						ConsultarSalidas ifrcSalidas = fab.getConsultarSalidas();
						ifrcSalidas.recargar();
						ifrcSalidas.setDesdeConsultarUsuario(true);
						ifrcSalidas.fromOtraVentana(dsal);
						ifrcSalidas.setVisible(true);
						setVisible(false);
					} catch (SalidaNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxSalidas, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxSalidas, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(comboBoxSalidas, "No hay ninguna salida seleccionada", "Sistema",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbcbtnVerSalidas = new GridBagConstraints();
		gbcbtnVerSalidas.anchor = GridBagConstraints.WEST;
		gbcbtnVerSalidas.fill = GridBagConstraints.VERTICAL;
		gbcbtnVerSalidas.insets = new Insets(0, 0, 5, 5);
		gbcbtnVerSalidas.gridx = 3;
		gbcbtnVerSalidas.gridy = 13;
		btnVerSalidas.setVisible(false);
		getContentPane().add(btnVerSalidas, gbcbtnVerSalidas);

		GridBagConstraints gbcbtnCerrar = new GridBagConstraints();
		gbcbtnCerrar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCerrar.fill = GridBagConstraints.BOTH;
		gbcbtnCerrar.gridx = 2;
		gbcbtnCerrar.gridy = 14;
		getContentPane().add(btnCerrar, gbcbtnCerrar);

		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxTipoUsuario.getSelectedIndex() == 1) {
					comboBoxProveedor.removeAllItems();

					try {
						proveedores = controlUsr.getProveedores();
						for (int i = 0; i < proveedores.length; i++) {
							comboBoxProveedor.addItem(proveedores[i].toString());
						}
					} catch (UsuarioNoExisteException e2) {
						// empty block on purpose
					}
					comboBoxProveedor.setSelectedIndex(-1);
					comboBoxProveedor.setVisible(true);
					lblSeleccioneUnProveedor.setVisible(true);
					btnBuscarProveedor.setVisible(true);
					textFieldNacionalidad.setVisible(true);
					textFieldDescripcion.setVisible(true);
					lblLink.setVisible(true);
					textFieldLink.setVisible(true);
					lblActividades.setVisible(true);
					comboBoxActividades.setVisible(true);
					btnVerActividades.setVisible(true);
					lblSalidas.setVisible(false);
					comboBoxSalidas.setVisible(false);
					btnVerSalidas.setVisible(false);
					btnBuscarTurista.setVisible(false);
					comboBoxTurista.setVisible(false);
					lblIngresoCI.setVisible(false);
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
				} else if (comboBoxTipoUsuario.getSelectedIndex() == 2) {
					comboBoxSalidas.removeAllItems();
					try {
						turistas = controlUsr.getTuristas();
						for (int i = 0; i < turistas.length; i++) {
							comboBoxTurista.addItem(turistas[i].toString());
						}
					} catch (UsuarioNoExisteException e1) {
						// empty block on purpose
					}
					comboBoxTurista.setSelectedIndex(-1);
					comboBoxProveedor.setVisible(false);
					lblSeleccioneUnProveedor.setVisible(false);
					btnBuscarProveedor.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
					lblActividades.setVisible(false);
					comboBoxActividades.setVisible(false);
					btnVerActividades.setVisible(false);
					lblSalidas.setVisible(true);
					comboBoxSalidas.setVisible(true);
					btnVerSalidas.setVisible(true);
					btnBuscarTurista.setVisible(true);
					comboBoxTurista.setVisible(true);
					lblIngresoCI.setVisible(true);
					lblNacionalidad.setVisible(true);
					textFieldNacionalidad.setVisible(true);
				} else {
					comboBoxProveedor.setVisible(false);
					lblSeleccioneUnProveedor.setVisible(false);
					btnBuscarProveedor.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
					lblActividades.setVisible(false);
					comboBoxActividades.setVisible(false);
					btnVerActividades.setVisible(false);
					lblSalidas.setVisible(false);
					comboBoxSalidas.setVisible(false);
					btnVerSalidas.setVisible(false);
					btnBuscarTurista.setVisible(false);
					comboBoxTurista.setVisible(false);
					lblIngresoCI.setVisible(false);
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
				}
			}
		});
	}

	// Este método es invocado al querer buscar un usuario, funcionalidad
	// provista por la operación del sistema verInfoUsuario().
	// En caso de que haya un error de búsqueda se despliega
	// un mensaje utilizando un panel de mensaje (JOptionPane).
	// No es necesario verificar que el campo con la cédula sea un número ya
	// que internamente el sistema almacena la cédula como un string.

	// cmdBuscarUsuarioActionPerformed
	protected void cmdBuscarTuristaActionPerformed(ActionEvent event) {
		if (comboBoxTurista.getSelectedItem() != null) {
			try {
				String tur = this.comboBoxTurista.getSelectedItem().toString();
				tur = tur.split(" ", 2)[0]; // me quedo solo con el nickname
				turista = controlUsr.verInfoTurista(tur);
				textFieldNombre.setText(turista.getNombre());
				textFieldApellido.setText(turista.getApellido());
				textFieldEmail.setText(turista.getEmail());
				textFieldNick.setText(turista.getNickname());
				textFieldTipo.setText("Turista");
				textFieldNacionalidad.setText(turista.getNacionalidad());
				textFieldNacimiento.setText(turista.getNacimiento().toString());
				comboBoxSalidas.removeAllItems();
				List<DataInscripcion> inscripcions = turista.getInscripciones();
				for (DataInscripcion dataInsc : inscripcions) {
					comboBoxSalidas.addItem(dataInsc.getSalida().getNombre());
				}
			} catch (UsuarioNoExisteException e1) {
				// Si el usuario no existe, se muestra mensaje de error y se limpia el
				// formulario.
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar usuario", JOptionPane.ERROR_MESSAGE);
				limpiarFormulario();
			}

		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun usuario seleccionado", "Buscar usuario",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void cmdBuscarProveedorActionPerformed(ActionEvent event) {
		if (comboBoxProveedor.getSelectedItem() != null) {
			try {
				String prov = this.comboBoxProveedor.getSelectedItem().toString();
				prov = prov.split(" ", 2)[0]; // me quedo solo con el nickname
				proveedor = controlUsr.verInfoProveedor(prov);
				textFieldNombre.setText(proveedor.getNombre());
				textFieldApellido.setText(proveedor.getApellido());
				textFieldEmail.setText(proveedor.getEmail());
				textFieldNick.setText(proveedor.getNickname());
				textFieldTipo.setText("Proveedor");
				textFieldDescripcion.setText(proveedor.getDescripcion());
				textFieldLink.setText(proveedor.getLink());
				textFieldNacimiento.setText(proveedor.getNacimiento().toString());
				comboBoxActividades.removeAllItems();
				DataActividadTuristica[] actividades = controlUsr.obtenerActividadesProveedor(proveedor.getNickname());
				int cont = 0;
				for (int i = 0; i < actividades.length; i++) {
					DataActividadTuristica aux = actividades[cont];
					comboBoxActividades.addItem(aux.getNombre());
					cont++;
				}
			} catch (UsuarioNoExisteException e1) {
				// Si el usuario no existe, se muestra mensaje de error y se limpia el
				// formulario.
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar usuario", JOptionPane.ERROR_MESSAGE);
				limpiarFormulario();
			}

		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun usuario seleccionado", "Buscar usuario",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void limpiarFormulario() {
		comboBoxTipoUsuario.setSelectedIndex(0);
		comboBoxActividades.setSelectedIndex(-1);
		comboBoxSalidas.setSelectedIndex(-1);
		textFieldNacimiento.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		textFieldNick.setText("");
		textFieldTipo.setText("");
		textFieldNacionalidad.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");
		textFieldEmail.setText("");
	}
}
