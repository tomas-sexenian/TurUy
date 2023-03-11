package presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings({ "serial" })
public class CrearUsuario extends JInternalFrame {

	// Controladorde usuarios que se utilizará para las acciones del JFrame
	private IControladorUsuario controlUsr;

	// Los componentes gráficos se agregan como atributos de la clase
	// para facilitar su acceso desde diferentes métodos de la misma.
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JLabel lblIngreseNombre;
	private JLabel lblIngreseApellido;
	private JLabel lblIngreseCi;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNickname;
	private JTextField textFieldNickname;
	private JLabel lblNacimiento;
	private JDateChooser dateChooser;
	private JLabel lblTipo;
	private JComboBox comboBox;
	private JLabel lblNacionalidad;
	private JTextField textFieldNacionalidad;
	private JLabel lblDescripcion;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldLink;
	private JLabel lblLink;
	private JLabel lblPassword;
	private JLabel lblConfirmacionPassword;
	private JTextField textFieldPassword;
	private JTextField textFieldConfirmPassword;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public CrearUsuario() {

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
		setTitle("Registrar un Usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 3, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();
		// En este caso utilizaremos el GridBagLayout que permite armar una grilla
		// en donde las filas y columnas no son uniformes.
		// Conviene trabajar este componente desde la vista de diseño gráfico y sólo
		// manipular los valores para ajustar alguna cosa.
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 215, 215, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 1;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNickname = new JTextField();
		GridBagConstraints gbctextFieldNickname = new GridBagConstraints();
		gbctextFieldNickname.gridwidth = 2;
		gbctextFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNickname.fill = GridBagConstraints.BOTH;
		gbctextFieldNickname.gridx = 1;
		gbctextFieldNickname.gridy = 1;
		getContentPane().add(textFieldNickname, gbctextFieldNickname);
		textFieldNickname.setColumns(10);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse
		// el nombre del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblIngreseNombre = new GridBagConstraints();
		gbclblIngreseNombre.fill = GridBagConstraints.BOTH;
		gbclblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbclblIngreseNombre.gridx = 0;
		gbclblIngreseNombre.gridy = 2;
		getContentPane().add(lblIngreseNombre, gbclblIngreseNombre);

		// Una campo de texto (JTextField) para ingresar el nombre del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		textFieldNombre.setColumns(10);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse
		// el apellido del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseApellido = new JLabel("Apellido:");
		lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblIngreseApellido = new GridBagConstraints();
		gbclblIngreseApellido.fill = GridBagConstraints.BOTH;
		gbclblIngreseApellido.insets = new Insets(0, 0, 5, 5);
		gbclblIngreseApellido.gridx = 0;
		gbclblIngreseApellido.gridy = 3;
		getContentPane().add(lblIngreseApellido, gbclblIngreseApellido);

		// Una campo de texto (JTextField) para ingresar el apellido del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldApellido = new JTextField();
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.gridwidth = 2;
		gbctextFieldApellido.fill = GridBagConstraints.BOTH;
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.gridx = 1;
		gbctextFieldApellido.gridy = 3;
		getContentPane().add(textFieldApellido, gbctextFieldApellido);
		textFieldApellido.setColumns(10);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la cédula del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseCi = new JLabel("Email:");
		lblIngreseCi.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblIngreseCi = new GridBagConstraints();
		gbclblIngreseCi.fill = GridBagConstraints.BOTH;
		gbclblIngreseCi.insets = new Insets(0, 0, 5, 5);
		gbclblIngreseCi.gridx = 0;
		gbclblIngreseCi.gridy = 4;
		getContentPane().add(lblIngreseCi, gbclblIngreseCi);

		// Un botón (JButton) con un evento asociado que permite registrar el usuario.
		// Dado que el código de registro tiene cierta complejidad, conviene delegarlo
		// a otro método en lugar de incluirlo directamente de el método actionPerformed
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdRegistroUsuarioActionPerformed(arg0);
			}
		});

		// Una campo de texto (JTextField) para ingresar la cédula del usuario.
		// Por defecto es posible ingresar cualquier string.
		// Al campo se le incluye un Tooltip que, al pasar el mouse por encima,
		// despliega un mensaje.
		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("Ingrese un número sin puntos ni guiones");
		textFieldEmail.setColumns(10);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 2;
		gbctextFieldEmail.fill = GridBagConstraints.BOTH;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 4;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);

		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.anchor = GridBagConstraints.EAST;
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 0;
		gbclblNacimiento.gridy = 5;
		getContentPane().add(lblNacimiento, gbclblNacimiento);

		dateChooser = new JDateChooser();
		GridBagConstraints gbcdateChooser = new GridBagConstraints();
		gbcdateChooser.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser.fill = GridBagConstraints.BOTH;
		gbcdateChooser.gridx = 1;
		gbcdateChooser.gridy = 5;
		getContentPane().add(dateChooser, gbcdateChooser);

		lblPassword = new JLabel("Password:");
		GridBagConstraints gbclblPassword = new GridBagConstraints();
		gbclblPassword.anchor = GridBagConstraints.EAST;
		gbclblPassword.insets = new Insets(0, 0, 5, 5);
		gbclblPassword.gridx = 0;
		gbclblPassword.gridy = 6;
		getContentPane().add(lblPassword, gbclblPassword);

		textFieldPassword = new JPasswordField();
		GridBagConstraints gbctextFieldPassword = new GridBagConstraints();
		gbctextFieldPassword.gridwidth = 2;
		gbctextFieldPassword.insets = new Insets(0, 0, 5, 5);
		gbctextFieldPassword.fill = GridBagConstraints.BOTH;
		gbctextFieldPassword.gridx = 1;
		gbctextFieldPassword.gridy = 6;
		getContentPane().add(textFieldPassword, gbctextFieldPassword);
		textFieldPassword.setColumns(10);

		lblConfirmacionPassword = new JLabel("Confirme password:");
		GridBagConstraints gbclblConfirmacionPassword = new GridBagConstraints();
		gbclblConfirmacionPassword.anchor = GridBagConstraints.EAST;
		gbclblConfirmacionPassword.insets = new Insets(0, 0, 5, 5);
		gbclblConfirmacionPassword.gridx = 0;
		gbclblConfirmacionPassword.gridy = 7;
		getContentPane().add(lblConfirmacionPassword, gbclblConfirmacionPassword);

		textFieldConfirmPassword = new JPasswordField();
		GridBagConstraints gbctextFieldConfirmPassword = new GridBagConstraints();
		gbctextFieldConfirmPassword.gridwidth = 2;
		gbctextFieldConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbctextFieldConfirmPassword.fill = GridBagConstraints.BOTH;
		gbctextFieldConfirmPassword.gridx = 1;
		gbctextFieldConfirmPassword.gridy = 7;
		getContentPane().add(textFieldConfirmPassword, gbctextFieldConfirmPassword);
		textFieldConfirmPassword.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 8;
		getContentPane().add(lblTipo, gbclblTipo);

		comboBox = new JComboBox();
		comboBox.addItem(new ItemTexto("Seleccione..."));
		comboBox.addItem(new ItemTexto("Proveedor"));
		comboBox.addItem(new ItemTexto("Turista"));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(3);
		GridBagConstraints gbccomboBox = new GridBagConstraints();
		gbccomboBox.gridwidth = 2;
		gbccomboBox.insets = new Insets(0, 0, 5, 5);
		gbccomboBox.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBox.gridx = 1;
		gbccomboBox.gridy = 8;
		getContentPane().add(comboBox, gbccomboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == 1) {
					// Es un proveedor
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(true);
					textAreaDescripcion.setVisible(true);
					lblLink.setVisible(true);
					textFieldLink.setVisible(true);
				} else if (comboBox.getSelectedIndex() == 2) {
					// Es un turista
					lblNacionalidad.setVisible(true);
					textFieldNacionalidad.setVisible(true);
					lblDescripcion.setVisible(false);
					textAreaDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
				} else {
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(false);
					textAreaDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
				}
			}
		});

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
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 2;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.BOTH;
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

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion
				.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gbctextAreaDescripcion = new GridBagConstraints();
		gbctextAreaDescripcion.gridwidth = 2;
		gbctextAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbctextAreaDescripcion.gridx = 1;
		gbctextAreaDescripcion.gridy = 10;
		textAreaDescripcion.setVisible(false);
		getContentPane().add(textAreaDescripcion, gbctextAreaDescripcion);

		lblLink = new JLabel("Link:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 11;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 2;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.BOTH;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 11;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);

		GridBagConstraints gbcbtnAceptar = new GridBagConstraints();
		gbcbtnAceptar.fill = GridBagConstraints.BOTH;
		gbcbtnAceptar.insets = new Insets(0, 0, 0, 5);
		gbcbtnAceptar.gridx = 1;
		gbcbtnAceptar.gridy = 12;
		getContentPane().add(btnAceptar, gbcbtnAceptar);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCancelar.fill = GridBagConstraints.BOTH;
		gbcbtnCancelar.gridx = 2;
		gbcbtnCancelar.gridy = 12;
		getContentPane().add(btnCancelar, gbcbtnCancelar);
	}

	// Este método es invocado al querer registrar un usuario, funcionalidad
	// provista por la operación del sistem registrarUsuario().
	// Previamente se hace una verificación de los campos, particularmente que no
	// sean vacíos
	// y que la cédula sea un número.
	// Tanto en caso de que haya un error (de verificación o de registro) o no, se
	// despliega
	// un mensaje utilizando un panel de mensaje (JOptionPane).
	protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
		// Obtengo datos de los controles Swing
		String nicknameU = this.textFieldNickname.getText().trim();
		String nombreU = this.textFieldNombre.getText().trim();
		String apellidoU = this.textFieldApellido.getText().trim();
		String emailU = this.textFieldEmail.getText().trim();
		java.util.Date fecha = this.dateChooser.getDate();
		String password = textFieldPassword.getText().trim();
		String passwordCnf = textFieldConfirmPassword.getText().trim();
		if (nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || emailU.isEmpty() || fecha == null
				|| password.isEmpty() || passwordCnf.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
					JOptionPane.ERROR_MESSAGE);
		} else {

			LocalDate nacimientoU = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (!password.equals(passwordCnf)) {
				JOptionPane.showMessageDialog(this, "Error al confirmar password", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				textFieldConfirmPassword.setText("");
				textFieldPassword.setText("");
			}

			else if (comboBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Debe indicar que tipo de usuario desea registrar",
						"Registrar Usuario", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (comboBox.getSelectedIndex() == 2) {
						String nacionalidadT = this.textFieldNacionalidad.getText();
						if (nacionalidadT.isEmpty()) {
							JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
									JOptionPane.ERROR_MESSAGE);
						} else {
							controlUsr.crearTurista(nicknameU, nombreU, apellidoU, emailU, nacimientoU, nacionalidadT,
									password, "");
							JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
									"Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
							// Limpio el internal frame antes de cerrar la ventana
							limpiarFormulario();
							setVisible(false);
						}
					} else if (comboBox.getSelectedIndex() == 1) {
						String descripcionP = this.textAreaDescripcion.getText();
						String linkP = this.textFieldLink.getText();
						if (descripcionP.isEmpty() || linkP.isEmpty()) {
							JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
									JOptionPane.ERROR_MESSAGE);
						} else {
							controlUsr.crearProveedor(nicknameU, nombreU, apellidoU, emailU, nacimientoU, descripcionP,
									linkP, password, "");
							JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
									"Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
							// Limpio el internal frame antes de cerrar la ventana
							limpiarFormulario();
							setVisible(false);
						}
					}

				} catch (EmailRepetidoException e) {
					// Muestro error de registro
					JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
					textFieldEmail.setText("");
				} catch (NickameRepetidoException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
					textFieldNickname.setText("");
				}
			}
		}
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void limpiarFormulario() {
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		dateChooser.setDate(null);
		comboBox.setSelectedIndex(-1);
		textFieldNacionalidad.setText("");
		textAreaDescripcion.setText("");
		textFieldLink.setText("");
		textFieldPassword.setText("");
		textFieldConfirmPassword.setText("");
	}

}
