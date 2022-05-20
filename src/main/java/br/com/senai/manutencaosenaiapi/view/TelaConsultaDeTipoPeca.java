package br.com.senai.manutencaosenaiapi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TipoDePecaService;
import br.com.senai.manutencaosenaiapi.view.table.PecaTableModel;
import br.com.senai.manutencaosenaiapi.view.table.TipoTableModel;

@Component
public class TelaConsultaDeTipoPeca extends JFrame {

	private JPanel contentPane;
	private JTextField edtFiltro;
	private JTable table;
	@Autowired
	private TelaDeCadastroDeTipoDePeca telaDeCadastro;
	@Autowired
	private TipoDePecaService tipoDePecaService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaDeTipoPeca frame = new TelaConsultaDeTipoPeca();
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
	public TelaConsultaDeTipoPeca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblParametro = new JLabel("Parametro");

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TipoDePeca> tiposDePecas = tipoDePecaService.listarPor(edtFiltro.getText());
				TipoTableModel model = new TipoTableModel(tiposDePecas);
				table.setModel(model);
				TableColumnModel cm = table.getColumnModel();
				cm.getColumn(0).setPreferredWidth(50);
				cm.getColumn(1).setPreferredWidth(500);
				table.updateUI();
			}
		});

		edtFiltro = new JTextField();
		edtFiltro.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeCadastro.setVisible(true);

			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es para a linha selecionada", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblParametro)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnPesquisar)
												.addGap(18).addComponent(btnAdicionar)))))
				.addGap(138))
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(102, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(lblParametro).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar).addComponent(btnAdicionar))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)));

		
		scrollPane.setColumnHeaderView(table);

		JButton btnEditar = new JButton("Editar");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Teste");
				int linhaSelecionada = table.getSelectedRow();
				TipoTableModel model = (TipoTableModel) table.getModel();
				TipoDePeca tipoDePecaSalva = model.getPor(linhaSelecionada);
				tipoDePecaService.removerPor(tipoDePecaSalva.getId());
				model.removerPor(linhaSelecionada);
				table.updateUI();
				JOptionPane.showInternalMessageDialog(contentPane, "Peça removida com sucesso");

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE).addComponent(btnEditar).addGap(18)
						.addComponent(btnExcluir).addGap(25)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(31, Short.MAX_VALUE).addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(btnExcluir).addComponent(btnEditar))
						.addGap(27)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
