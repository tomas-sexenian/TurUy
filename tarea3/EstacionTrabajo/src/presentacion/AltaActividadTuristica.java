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
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.ActividadRepetidaException;
import excepciones.DepartamentoNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataProveedor;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorUsuario;

public class AltaActividadTuristica extends JInternalFrame {
	private IControladorActividades ica;
	private JTextField textFieldNombre;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextField textFieldCiudad;
	private JDateChooser dateChooser;

	private JButton btnAceptar;
	private JLabel lblProveedor;
	private JComboBox comboBoxProveedor;
	private JComboBox comboBoxDepartamento;
	private JTextArea textAreaDescripcion;
	private JLabel lblCategoria;
	private JScrollPane scrollPane;
	private JList listCategorias;
	private DefaultListModel demoList;

	private void formInternalFrameClosing(InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		textFieldCiudad.setText("");
		dateChooser.setDate(null);
		comboBoxDepartamento.setSelectedIndex(-1);
		comboBoxProveedor.setSelectedIndex(-1);
	}

	/**
	 * Create the frame.
	 */
	public AltaActividadTuristica() {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameClosed(InternalFrameEvent evt) {
			}

			public void internalFrameClosing(InternalFrameEvent evt) {
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
		setTitle("Alta de actividad turistica");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 3, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 170, 190, 190, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 28, 0, 30, 30, 50, 0, 0, 0, 80, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		Fabrica fabrica = Fabrica.getInstance();
		ica = fabrica.getIControladorActividades();

		lblProveedor = new JLabel("Proveedor");
		GridBagConstraints gbclblProveedor = new GridBagConstraints();
		gbclblProveedor.anchor = GridBagConstraints.EAST;
		gbclblProveedor.insets = new Insets(0, 0, 5, 5);
		gbclblProveedor.gridx = 0;
		gbclblProveedor.gridy = 1;
		getContentPane().add(lblProveedor, gbclblProveedor);

		comboBoxProveedor = new JComboBox();
		IControladorUsuario ctrlUsr = fabrica.getIControladorUsuario();
		try {
			DataProveedor[] proveedores = ctrlUsr.getProveedores();
			for (int i = 0; i < proveedores.length; i++) {
				comboBoxProveedor.addItem(proveedores[i].toString());
			}
		} catch (UsuarioNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
		}
		comboBoxProveedor.setSelectedIndex(-1);

		GridBagConstraints gbccomboBoxProveedor = new GridBagConstraints();
		gbccomboBoxProveedor.gridwidth = 2;
		gbccomboBoxProveedor.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxProveedor.gridx = 1;
		gbccomboBoxProveedor.gridy = 1;
		getContentPane().add(comboBoxProveedor, gbccomboBoxProveedor);

		JLabel lblA = new JLabel("Departamento");
		GridBagConstraints gbclblA = new GridBagConstraints();
		gbclblA.anchor = GridBagConstraints.EAST;
		gbclblA.insets = new Insets(0, 0, 5, 5);
		gbclblA.gridx = 0;
		gbclblA.gridy = 2;
		getContentPane().add(lblA, gbclblA);

		comboBoxDepartamento = new JComboBox();
		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamento.addItem(dptos[i].getNombre());
			}
		} catch (DepartamentoNoExisteException e1) {
			// empty block on purpose
		}
		comboBoxDepartamento.setSelectedIndex(-1);

		GridBagConstraints gbccomboBoxDepartamento = new GridBagConstraints();
		gbccomboBoxDepartamento.gridwidth = 2;
		gbccomboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxDepartamento.gridx = 1;
		gbccomboBoxDepartamento.gridy = 2;
		getContentPane().add(comboBoxDepartamento, gbccomboBoxDepartamento);

		JLabel lblB = new JLabel("Nombre");
		GridBagConstraints gbclblB = new GridBagConstraints();
		gbclblB.anchor = GridBagConstraints.EAST;
		gbclblB.insets = new Insets(0, 0, 5, 5);
		gbclblB.gridx = 0;
		gbclblB.gridy = 3;
		getContentPane().add(lblB, gbclblB);

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldN = new GridBagConstraints();
		gbctextFieldN.gridwidth = 2;
		gbctextFieldN.insets = new Insets(0, 0, 5, 5);
		gbctextFieldN.fill = GridBagConstraints.BOTH;
		gbctextFieldN.gridx = 1;
		gbctextFieldN.gridy = 3;
		getContentPane().add(textFieldNombre, gbctextFieldN);
		textFieldNombre.setColumns(10);

		JLabel lblC = new JLabel("Descripcion");
		GridBagConstraints gbclblC = new GridBagConstraints();
		gbclblC.anchor = GridBagConstraints.EAST;
		gbclblC.insets = new Insets(0, 0, 5, 5);
		gbclblC.gridx = 0;
		gbclblC.gridy = 4;
		getContentPane().add(lblC, gbclblC);

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
		gbctextAreaDescripcion.gridy = 4;
		getContentPane().add(textAreaDescripcion, gbctextAreaDescripcion);

		JLabel lblD = new JLabel("Duracion");
		GridBagConstraints gbclblD = new GridBagConstraints();
		gbclblD.anchor = GridBagConstraints.EAST;
		gbclblD.insets = new Insets(0, 0, 5, 5);
		gbclblD.gridx = 0;
		gbclblD.gridy = 5;
		getContentPane().add(lblD, gbclblD);

		textFieldDuracion = new JTextField();
		GridBagConstraints gbctextFieldDu = new GridBagConstraints();
		gbctextFieldDu.gridwidth = 2;
		gbctextFieldDu.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDu.fill = GridBagConstraints.BOTH;
		gbctextFieldDu.gridx = 1;
		gbctextFieldDu.gridy = 5;
		getContentPane().add(textFieldDuracion, gbctextFieldDu);
		textFieldDuracion.setColumns(10);
		JLabel lblE = new JLabel("Costo");
		GridBagConstraints gbclblE = new GridBagConstraints();
		gbclblE.anchor = GridBagConstraints.EAST;
		gbclblE.insets = new Insets(0, 0, 5, 5);
		gbclblE.gridx = 0;
		gbclblE.gridy = 6;
		getContentPane().add(lblE, gbclblE);

		textFieldCosto = new JTextField();
		GridBagConstraints gbctextFieldC = new GridBagConstraints();
		gbctextFieldC.gridwidth = 2;
		gbctextFieldC.insets = new Insets(0, 0, 5, 5);
		gbctextFieldC.fill = GridBagConstraints.BOTH;
		gbctextFieldC.gridx = 1;
		gbctextFieldC.gridy = 6;
		getContentPane().add(textFieldCosto, gbctextFieldC);
		textFieldCosto.setColumns(10);

		JLabel lblF = new JLabel("Ciudad");
		GridBagConstraints gbclblF = new GridBagConstraints();
		gbclblF.anchor = GridBagConstraints.EAST;
		gbclblF.insets = new Insets(0, 0, 5, 5);
		gbclblF.gridx = 0;
		gbclblF.gridy = 7;
		getContentPane().add(lblF, gbclblF);

		textFieldCiudad = new JTextField();
		GridBagConstraints gbctextFieldCi = new GridBagConstraints();
		gbctextFieldCi.gridwidth = 2;
		gbctextFieldCi.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCi.fill = GridBagConstraints.BOTH;
		gbctextFieldCi.gridx = 1;
		gbctextFieldCi.gridy = 7;
		getContentPane().add(textFieldCiudad, gbctextFieldCi);
		textFieldCiudad.setColumns(10);

		lblCategoria = new JLabel("Categoria");
		GridBagConstraints gbclblCategoria = new GridBagConstraints();
		gbclblCategoria.anchor = GridBagConstraints.EAST;
		gbclblCategoria.insets = new Insets(0, 0, 5, 5);
		gbclblCategoria.gridx = 0;
		gbclblCategoria.gridy = 8;
		getContentPane().add(lblCategoria, gbclblCategoria);

		scrollPane = new JScrollPane();
		GridBagConstraints gbcscrollPane = new GridBagConstraints();
		gbcscrollPane.insets = new Insets(0, 0, 5, 5);
		gbcscrollPane.fill = GridBagConstraints.BOTH;
		gbcscrollPane.gridx = 1;
		gbcscrollPane.gridy = 8;
		getContentPane().add(scrollPane, gbcscrollPane);

		demoList = new DefaultListModel();
		listCategorias = new JList(demoList);
		scrollPane.setViewportView(listCategorias);
		listCategorias.setVisibleRowCount(3);

		JLabel lblG = new JLabel("Fecha de alta");
		GridBagConstraints gbclblG = new GridBagConstraints();
		gbclblG.anchor = GridBagConstraints.EAST;
		gbclblG.insets = new Insets(0, 0, 5, 5);
		gbclblG.gridx = 0;
		gbclblG.gridy = 9;
		getContentPane().add(lblG, gbclblG);

		dateChooser = new JDateChooser();
		GridBagConstraints gbcdateChooser = new GridBagConstraints();
		gbcdateChooser.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser.fill = GridBagConstraints.BOTH;
		gbcdateChooser.gridx = 1;
		gbcdateChooser.gridy = 9;
		getContentPane().add(dateChooser, gbcdateChooser);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaActividadActionPerformed(arg0);
			}
		});

		GridBagConstraints gbcbtnAceptar = new GridBagConstraints();
		gbcbtnAceptar.insets = new Insets(0, 0, 0, 5);
		gbcbtnAceptar.gridx = 1;
		gbcbtnAceptar.gridy = 10;
		getContentPane().add(btnAceptar, gbcbtnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCancelar.gridx = 2;
		gbcbtnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbcbtnCancelar);
	}

	protected void refrescar() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario ctrlUsr = fabrica.getIControladorUsuario();
		comboBoxProveedor.removeAllItems();
		comboBoxDepartamento.removeAllItems();
		demoList.removeAllElements();
		try {
			DataProveedor[] proveedores = ctrlUsr.getProveedores();
			for (int i = 0; i < proveedores.length; i++) {
				comboBoxProveedor.addItem(proveedores[i].toString());
			}
		} catch (UsuarioNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
		}
		comboBoxProveedor.setSelectedIndex(-1);
		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamento.addItem(dptos[i].getNombre());
			}
		} catch (DepartamentoNoExisteException e1) {
			// empty block on purpose
		}
		comboBoxDepartamento.setSelectedIndex(-1);
		IControladorActividades ica = fabrica.getIControladorActividades();
		List<String> categorias = ica.obtenerCategorias();
		for (String nomString : categorias) {
			demoList.addElement(nomString);
		}
	}

	protected void altaActividadActionPerformed(ActionEvent arg0) {

		// Obtengo datos de los controles Swing
		String nombre = this.textFieldNombre.getText().trim();
		String descripcion = this.textAreaDescripcion.getText().trim();
		String duracion = this.textFieldDuracion.getText().trim();
		String costo = this.textFieldCosto.getText().trim();
		String ciudad = this.textFieldCiudad.getText().trim();
		java.util.Date fecha = this.dateChooser.getDate();

		if (comboBoxProveedor.getSelectedItem() == null || comboBoxDepartamento.getSelectedItem() == null
				|| nombre.isEmpty() || descripcion.isEmpty() || duracion.isEmpty() || costo.isEmpty()
				|| ciudad.isEmpty() || fecha == null || listCategorias.getSelectedValuesList().size() == 0) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de actividad turistica",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String proveedor = this.comboBoxProveedor.getSelectedItem().toString();
				List<String> categorias = listCategorias.getSelectedValuesList();
				proveedor = proveedor.split(" ", 2)[0]; // me quedo solo con el nickname
				String departamento = this.comboBoxDepartamento.getSelectedItem().toString();
				double cost;
				cost = Double.parseDouble(costo);
				try {
					ArrayList<String> arraycat = new ArrayList<String>(categorias);
					int dur = Integer.parseInt(duracion);
					if (cost < 0) {
						JOptionPane.showMessageDialog(this, "El costo debe ser un numero positivo",
								"Registrar Actividad", JOptionPane.ERROR_MESSAGE);
						textFieldCosto.setText("");
					} else if (dur < 0) {
						JOptionPane.showMessageDialog(this, "La duracion debe ser un numero positivo",
								"Registrar Actividad", JOptionPane.ERROR_MESSAGE);
						textFieldDuracion.setText("");
					} else {
						LocalDate fechaAlta = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						ica.crearActividad(departamento, descripcion, nombre, dur, cost, ciudad, fechaAlta, proveedor,
								arraycat);

						JOptionPane.showMessageDialog(this, "Se ha registrado correctamente la actividad",
								"Alta actividad turistica", JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();

						setVisible(false);

					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "La duracion debe ser un numero entero", "Registrar Actividad",
							JOptionPane.ERROR_MESSAGE);
					textFieldDuracion.setText("");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "El costo debe ser un numero real", "Registrar Actividad",
						JOptionPane.ERROR_MESSAGE);
				textFieldCosto.setText("");
			} catch (ActividadRepetidaException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
