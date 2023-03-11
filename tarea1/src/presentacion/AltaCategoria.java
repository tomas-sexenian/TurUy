package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;

import excepciones.EntidadRepetidaException;
import logica.Fabrica;
import logica.interfaces.IControladorActividades;

public class AltaCategoria extends JInternalFrame {

	Fabrica fabrica = Fabrica.getInstance();
	private IControladorActividades ica = fabrica.getIControladorActividades();
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnRechazarActividad;
	private JTextField textFieldNombre;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public AltaCategoria() {
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
		setTitle("Alta de Categoría");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(640, 280);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 105, 148, 148, 190, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.EAST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbclblNombre);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnRechazarActividad = new JButton("Aceptar");
		btnRechazarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptarActionPerformed(arg0);
			}
		});

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		/**
		 * Excepción utilizada para indicar la existencia de un usuario repetido en el
		 * sistema.
		 * 
		 * @author TProg2017
		 *
		 */
		textFieldNombre.setColumns(10);
		GridBagConstraints gbcbtnRechazarActividad = new GridBagConstraints();
		gbcbtnRechazarActividad.anchor = GridBagConstraints.WEST;
		gbcbtnRechazarActividad.insets = new Insets(0, 0, 5, 5);
		gbcbtnRechazarActividad.gridx = 2;
		gbcbtnRechazarActividad.gridy = 4;
		getContentPane().add(btnRechazarActividad, gbcbtnRechazarActividad);
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.anchor = GridBagConstraints.WEST;
		gbcbtnCancelar.insets = new Insets(0, 0, 5, 0);
		gbcbtnCancelar.gridx = 3;
		gbcbtnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbcbtnCancelar);

	}

	protected void aceptarActionPerformed(ActionEvent arg0) {
		if (textFieldNombre.getText().equals("")) {

			JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Alta de Categoria",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String nombre = textFieldNombre.getText().trim();
			try {
				ica.agregarCategoria(nombre);

				JOptionPane.showMessageDialog(this, "Se ha agregado correctamente la categoría", "Alta de Categoría",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				this.setVisible(false);
			} catch (EntidadRepetidaException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Categoria", JOptionPane.ERROR_MESSAGE);
				textFieldNombre.setText("");
			}
		}
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
	}

}
