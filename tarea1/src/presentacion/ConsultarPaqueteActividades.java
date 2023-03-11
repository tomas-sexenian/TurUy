package presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import excepciones.ActividadNoExisteException;
import excepciones.NoExisteEntidadException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataPaqueteActividades;
import logica.interfaces.IControladorActividades;

public class ConsultarPaqueteActividades extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldValidez;
	private JTextField textFieldDescuento;
	private JTextField textFieldFechaAlta;
	private JComboBox comboBoxPaquete;
	private JComboBox comboBoxActividades;
	private JTextArea textAreaDescripcion;

	private JScrollPane scrollPane;
	private JList listCategorias;
	private DefaultListModel demoList;

	protected boolean desdeConsultarActividad = false;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		if (desdeConsultarActividad) {
			FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
			ConsultarActividad consultarActividad = fab.getConsultarActividad();
			consultarActividad.setVisible(true);
		}
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ConsultarPaqueteActividades() {
		setMaximizable(true);
		setClosable(true);
		setTitle("Consultar paquete de actividades turísticas");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

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

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 184, 300, 88, 46, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 25, 25, 50, 25, 25, 25, 25, 80, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblSeleccioneUnPaquete = new JLabel("Seleccione un paquete:");
		GridBagConstraints gbclblSeleccioneUnPaquete = new GridBagConstraints();
		gbclblSeleccioneUnPaquete.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnPaquete.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnPaquete.gridx = 0;
		gbclblSeleccioneUnPaquete.gridy = 1;
		getContentPane().add(lblSeleccioneUnPaquete, gbclblSeleccioneUnPaquete);

		comboBoxPaquete = new JComboBox();
		comboBoxPaquete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxPaquete.getSelectedItem() != null && comboBoxPaquete.getSelectedItem() != " ") {

					String paq = comboBoxPaquete.getSelectedItem().toString();
					Fabrica fabrica = Fabrica.getInstance();
					IControladorActividades controladorActividades = fabrica.getIControladorActividades();
					DataPaqueteActividades paquete;
					try {
						paquete = controladorActividades.consultarPaquete(paq);
						textFieldNombre.setText(paq);
						textFieldFechaAlta.setText(paquete.getFechaAlta().toString());
						textAreaDescripcion.setText(paquete.getDescripcion());
						textFieldDescuento.setText(Double.toString(paquete.getDescuento()));
						textFieldValidez.setText(Integer.toString(paquete.getValidez()));

						List<DataActividadTuristica> actividades = paquete.getActividades();
						comboBoxActividades.removeAllItems();
						demoList.removeAllElements();
						for (int i = 0; i < actividades.size(); i++) {
							comboBoxActividades.addItem(actividades.get(i).getNombre());
							for (String cat : actividades.get(i).getCategorias()) {
								if (!demoList.contains(cat)) {
									demoList.addElement(cat);
								}
							}
						}
					} catch (NoExisteEntidadException e1) {
						JOptionPane.showMessageDialog(comboBoxPaquete, e1.getMessage(), "Sistema",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		GridBagConstraints gbccomboBoxPaquete = new GridBagConstraints();
		gbccomboBoxPaquete.gridwidth = 2;
		gbccomboBoxPaquete.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxPaquete.fill = GridBagConstraints.BOTH;
		gbccomboBoxPaquete.gridx = 1;
		gbccomboBoxPaquete.gridy = 1;
		getContentPane().add(comboBoxPaquete, gbccomboBoxPaquete);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.EAST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbclblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripción");
		GridBagConstraints gbclblDescripcin = new GridBagConstraints();
		gbclblDescripcin.anchor = GridBagConstraints.EAST;
		gbclblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcin.gridx = 0;
		gbclblDescripcin.gridy = 3;
		getContentPane().add(lblDescripcin, gbclblDescripcin);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBackground(UIManager.getColor("Button.background"));
		textAreaDescripcion
				.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192)), new EmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gbctextAreaDescripcion = new GridBagConstraints();
		gbctextAreaDescripcion.gridwidth = 2;
		gbctextAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbctextAreaDescripcion.gridx = 1;
		gbctextAreaDescripcion.gridy = 3;
		getContentPane().add(textAreaDescripcion, gbctextAreaDescripcion);

		JLabel lblNewLabel = new JLabel("Período de validez");
		GridBagConstraints gbclblNewLabel = new GridBagConstraints();
		gbclblNewLabel.anchor = GridBagConstraints.EAST;
		gbclblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbclblNewLabel.gridx = 0;
		gbclblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbclblNewLabel);

		textFieldValidez = new JTextField();
		textFieldValidez.setEditable(false);
		textFieldValidez.setColumns(10);
		GridBagConstraints gbctextFieldValidez = new GridBagConstraints();
		gbctextFieldValidez.gridwidth = 2;
		gbctextFieldValidez.insets = new Insets(0, 0, 5, 5);
		gbctextFieldValidez.fill = GridBagConstraints.BOTH;
		gbctextFieldValidez.gridx = 1;
		gbctextFieldValidez.gridy = 4;
		getContentPane().add(textFieldValidez, gbctextFieldValidez);

		JLabel lblDescuento = new JLabel("Descuento");
		GridBagConstraints gbclblDescuento = new GridBagConstraints();
		gbclblDescuento.anchor = GridBagConstraints.EAST;
		gbclblDescuento.insets = new Insets(0, 0, 5, 5);
		gbclblDescuento.gridx = 0;
		gbclblDescuento.gridy = 5;
		getContentPane().add(lblDescuento, gbclblDescuento);

		textFieldDescuento = new JTextField();
		textFieldDescuento.setEditable(false);
		textFieldDescuento.setColumns(10);
		GridBagConstraints gbctextFieldDescuento = new GridBagConstraints();
		gbctextFieldDescuento.gridwidth = 2;
		gbctextFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescuento.fill = GridBagConstraints.BOTH;
		gbctextFieldDescuento.gridx = 1;
		gbctextFieldDescuento.gridy = 5;
		getContentPane().add(textFieldDescuento, gbctextFieldDescuento);

		JLabel lblFechaDeAlta = new JLabel("Fecha de alta");
		GridBagConstraints gbclblFechaDeAlta = new GridBagConstraints();
		gbclblFechaDeAlta.anchor = GridBagConstraints.EAST;
		gbclblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
		gbclblFechaDeAlta.gridx = 0;
		gbclblFechaDeAlta.gridy = 6;
		getContentPane().add(lblFechaDeAlta, gbclblFechaDeAlta);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbctextFieldFechaAlta = new GridBagConstraints();
		gbctextFieldFechaAlta.gridwidth = 2;
		gbctextFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbctextFieldFechaAlta.fill = GridBagConstraints.BOTH;
		gbctextFieldFechaAlta.gridx = 1;
		gbctextFieldFechaAlta.gridy = 6;
		getContentPane().add(textFieldFechaAlta, gbctextFieldFechaAlta);

		JLabel lblActividades = new JLabel("Actividades");
		GridBagConstraints gbclblActividades = new GridBagConstraints();
		gbclblActividades.anchor = GridBagConstraints.EAST;
		gbclblActividades.insets = new Insets(0, 0, 5, 5);
		gbclblActividades.gridx = 0;
		gbclblActividades.gridy = 7;
		getContentPane().add(lblActividades, gbclblActividades);

		comboBoxActividades = new JComboBox();
		GridBagConstraints gbccomboBoxActividades = new GridBagConstraints();
		gbccomboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividades.fill = GridBagConstraints.BOTH;
		gbccomboBoxActividades.gridx = 1;
		gbccomboBoxActividades.gridy = 7;
		getContentPane().add(comboBoxActividades, gbccomboBoxActividades);

		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxActividades.getSelectedItem() != null) {
					String dact = comboBoxActividades.getSelectedItem().toString();
					Fabrica fabrica = Fabrica.getInstance();
					FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
					ConsultarActividad ifrcActividad = fab.getConsultarActividad();
					IControladorActividades controladorActividades = fabrica.getIControladorActividades();
					DataActividadTuristica act;
					try {
						act = controladorActividades.consultarActividad(dact);
						ifrcActividad.recargar();
						ifrcActividad.desdeConsultarPaquetes = true;
						ifrcActividad.fromOtraVentana(act);
						ifrcActividad.setVisible(true);
						setVisible(false);
					} catch (ActividadNoExisteException e) {
						JOptionPane.showMessageDialog(comboBoxActividades, e.getMessage(), "Sistema",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(comboBoxActividades, "No hay ninguna actividad seleccionada",
							"Sistema", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		GridBagConstraints gbcbtnVer = new GridBagConstraints();
		gbcbtnVer.fill = GridBagConstraints.BOTH;
		gbcbtnVer.insets = new Insets(0, 0, 5, 5);
		gbcbtnVer.gridx = 2;
		gbcbtnVer.gridy = 7;
		getContentPane().add(btnVer, gbcbtnVer);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (desdeConsultarActividad) {
					FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
					ConsultarActividad consultarActividad = fab.getConsultarActividad();
					consultarActividad.setVisible(true);
				}
				limpiarFormulario();
				setVisible(false);
			}
		});

		JLabel lblCategorias = new JLabel("Categorias");
		GridBagConstraints gbclblCategorias = new GridBagConstraints();
		gbclblCategorias.anchor = GridBagConstraints.EAST;
		gbclblCategorias.insets = new Insets(0, 0, 5, 5);
		gbclblCategorias.gridx = 0;
		gbclblCategorias.gridy = 8;
		getContentPane().add(lblCategorias, gbclblCategorias);

		scrollPane = new JScrollPane();
		GridBagConstraints gbcscrollPane = new GridBagConstraints();
		gbcscrollPane.insets = new Insets(0, 0, 5, 5);
		gbcscrollPane.fill = GridBagConstraints.BOTH;
		gbcscrollPane.gridx = 1;
		gbcscrollPane.gridy = 8;
		getContentPane().add(scrollPane, gbcscrollPane);

		demoList = new DefaultListModel<String>();
		listCategorias = new JList(demoList);
		listCategorias.setBackground(new Color(238, 238, 236));
		scrollPane.setViewportView(listCategorias);
		GridBagConstraints gbcbtnCerrar = new GridBagConstraints();
		gbcbtnCerrar.fill = GridBagConstraints.VERTICAL;
		gbcbtnCerrar.anchor = GridBagConstraints.EAST;
		gbcbtnCerrar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCerrar.gridx = 1;
		gbcbtnCerrar.gridy = 9;
		getContentPane().add(btnCerrar, gbcbtnCerrar);

	}

	public void fromConsultarActividad(DataPaqueteActividades paquete) {
		desdeConsultarActividad = true;
		comboBoxPaquete.removeAllItems();
		comboBoxPaquete.addItem(paquete.getNombre());
		comboBoxPaquete.setSelectedItem(paquete.getNombre());
		textFieldDescuento.setText(Double.toString(paquete.getDescuento()));
		textFieldNombre.setText(paquete.getNombre());
		textFieldValidez.setText(Integer.toString(paquete.getValidez()));
		textAreaDescripcion.setText(paquete.getDescripcion());
		textFieldFechaAlta.setText(paquete.getFechaAlta().toString());
		List<DataActividadTuristica> actividades = paquete.getActividades();
		comboBoxActividades.removeAllItems();
		demoList.removeAllElements();
		for (int i = 0; i < actividades.size(); i++) {
			comboBoxActividades.addItem(actividades.get(i).getNombre());
			for (String cat : actividades.get(i).getCategorias()) {
				if (!demoList.contains(cat)) {
					demoList.addElement(cat);
				}
			}
		}
	}

	private void limpiarFormulario() {
		textFieldDescuento.setText("");
		textFieldNombre.setText("");
		textFieldValidez.setText("");
		textAreaDescripcion.setText("");
		textFieldFechaAlta.setText("");
		comboBoxActividades.removeAllItems();
		comboBoxPaquete.removeAllItems();
		desdeConsultarActividad = false;
		demoList.removeAllElements();
	}

	void recargar() {
		comboBoxPaquete.removeAllItems();
		comboBoxPaquete.addItem(" ");
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iActividades = fabrica.getIControladorActividades();

		List<DataPaqueteActividades> paquetes;
		try {
			paquetes = iActividades.obtenerPaquetes();
			for (DataPaqueteActividades paq : paquetes) {
				comboBoxPaquete.addItem(paq.getNombre());
			}
		} catch (NoExisteEntidadException e) {
			JOptionPane.showMessageDialog(this, "No hay paquetes en el sistema", "Consultar Paquete",
					JOptionPane.ERROR_MESSAGE);
		}
		comboBoxPaquete.setSelectedIndex(-1);

	}

}
