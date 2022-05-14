package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import javax.swing.JTextArea;

@Component
public class TelaCadastroDePeca extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;
	private JLabel lblEspecificacoes;
	private JTextArea jtaEspecificacoes;

	@Autowired
	private PecaService service;
	private JLabel lblQtde;
	private JTextField edtQtde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroDePeca() {
		setTitle("Cadastro de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblID = new JLabel("ID");

		edtId = new JTextField();
		edtId.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição");

		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (edtId.getText() != null && edtId.getText().length() > 0) {
						Peca pecaSalva = new Peca();
						pecaSalva.setDescricao(edtDescricao.getText());
						pecaSalva.setQtdeEmEstoque(Integer.parseInt(edtQtde.getText()));
						pecaSalva.setEspecificacoes(jtaEspecificacoes.getText());
						pecaSalva.setId(Integer.parseInt(edtId.getText()));
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(contentPane, "Peça alterada com sucesso");
					} else {
						Peca novaPeca = new Peca();
						novaPeca.setDescricao(edtDescricao.getText());
						novaPeca.setQtdeEmEstoque(Integer.parseInt(edtQtde.getText()));
						novaPeca.setEspecificacoes(jtaEspecificacoes.getText());
						Peca pecaSalva = service.inserir(novaPeca);
						edtId.setText(pecaSalva.getId().toString());
						JOptionPane.showMessageDialog(contentPane, "Peça inserida com sucesso");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});

		lblQtde = new JLabel("Qtde");

		edtQtde = new JTextField();
		edtQtde.setColumns(10);

		lblEspecificacoes = new JLabel("Especificações");

		jtaEspecificacoes = new JTextArea();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(392, Short.MAX_VALUE)
						.addComponent(btnSalvar).addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING).addComponent(lblEspecificacoes)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblID)
										.addComponent(edtId, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE))
								.addGap(8)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblDescricao)
												.addGap(179).addComponent(lblQtde, GroupLayout.PREFERRED_SIZE, 74,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, 225,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(edtQtde,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))))
						.addGap(56))
				.addGroup(Alignment.LEADING,
						gl_contentPane
								.createSequentialGroup().addGap(12).addComponent(jtaEspecificacoes,
										GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblID)
								.addComponent(lblDescricao).addComponent(lblQtde))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(edtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblEspecificacoes)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jtaEspecificacoes, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnSalvar)));
		contentPane.setLayout(gl_contentPane);
	}

	public void colocarEmEdicao(Peca pecaSalva) {
		edtId.setText(pecaSalva.getId().toString());
		edtDescricao.setText(pecaSalva.getDescricao());
		jtaEspecificacoes.setText(pecaSalva.getEspecificacoes());
		edtQtde.setText(pecaSalva.getQtdeEmEstoque().toString());
	}
}
