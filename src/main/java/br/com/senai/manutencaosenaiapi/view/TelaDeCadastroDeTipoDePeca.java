package br.com.senai.manutencaosenaiapi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TipoDePecaService;

@Component
public class TelaDeCadastroDeTipoDePeca extends JFrame {

	private JPanel contentPane;
	private JTextField lblIdTipoPeca;
	private JTextField lblDesc;
	@Autowired
	private TipoDePecaService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastroDeTipoDePeca frame = new TelaDeCadastroDeTipoDePeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDeCadastroDeTipoDePeca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblId = new JLabel("Id");

		JLabel lblDescricao = new JLabel("Descrição");

		lblIdTipoPeca = new JTextField();
		lblIdTipoPeca.setEditable(false);
		lblIdTipoPeca.setColumns(10);

		lblDesc = new JTextField();
		lblDesc.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoDePeca tipoPeca = new TipoDePeca();
				if (lblIdTipoPeca.getText() != null && lblIdTipoPeca.getText().length() > 0) {
					tipoPeca.setId(Integer.parseInt(lblIdTipoPeca.getText()));
				}
				tipoPeca.setDescricao(lblDesc.getText());
				service.inserir(tipoPeca);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblId).addGap(46)
								.addComponent(lblDescricao))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblIdTipoPeca, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblDesc, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(267, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(418, Short.MAX_VALUE).addComponent(btnSalvar).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(lblDescricao))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIdTipoPeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 209, Short.MAX_VALUE).addComponent(btnSalvar)
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
