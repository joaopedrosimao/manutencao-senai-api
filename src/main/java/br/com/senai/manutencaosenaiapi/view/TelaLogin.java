package br.com.senai.manutencaosenaiapi.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.service.LoginService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@Component
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField edtLogin;
	private JPasswordField pfSenha;

	@Autowired
	private LoginService loginService;

	private JComboBox<String> cbPerfil;

	private void carregarOpcoes() {
		this.cbPerfil.addItem("Atendente");
		this.cbPerfil.addItem("Técnico");
	}

	/**
	 * Launch the application.
	 * 
	 * /** Create the frame.
	 */
	public TelaLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Login");

		edtLogin = new JTextField();
		edtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");

		pfSenha = new JPasswordField();

		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String login = edtLogin.getText();
					String senha = new String(pfSenha.getPassword());
					Login loginEncontrado = loginService.logar(login, senha, cbPerfil.getSelectedItem().toString());
					if (loginEncontrado.getPerfil().equals(cbPerfil.getItemAt(0))) {
						JOptionPane.showMessageDialog(contentPane, "Seja bem vindo " + loginEncontrado.getLogin()
								+ "\nPerfil: " + cbPerfil.getSelectedItem());
					} else {
						JOptionPane.showMessageDialog(contentPane, "Técnico acessando");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});

		cbPerfil = new JComboBox<String>();
		this.carregarOpcoes();

		JLabel lblNewLabel_2 = new JLabel("Perfil");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1).addComponent(pfSenha)
								.addComponent(edtLogin, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(101).addComponent(btnLogar))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_2))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(cbPerfil,
								GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(39, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(edtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_2)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(cbPerfil, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE).addComponent(btnLogar)
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
