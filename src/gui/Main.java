package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcoes.Deslocamento;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Main extends JFrame {
	
	private Random random = new Random();

	private JPanel contentPane;
	private JTextArea taEntrada;
	private JTextArea taSaida;
	private JSpinner spinConstShift;
	private JSpinner spinVarShift;
	private JSpinner spinLimite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Encriptador");
		setBounds(100, 100, 550, 383);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrada.setBounds(10, 11, 252, 14);
		contentPane.add(lblEntrada);
		
		JScrollPane spEntrada = new JScrollPane();
		spEntrada.setBounds(10, 36, 257, 214);
		contentPane.add(spEntrada);
		
		taEntrada = new JTextArea();
		taEntrada.setWrapStyleWord(true);
		taEntrada.setTabSize(2);
		taEntrada.setLineWrap(true);
		spEntrada.setViewportView(taEntrada);
		
		JLabel lblSaida = new JLabel("Sa\u00EDda");
		lblSaida.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaida.setBounds(272, 11, 252, 14);
		contentPane.add(lblSaida);
		
		JScrollPane spSaida = new JScrollPane();
		spSaida.setBounds(277, 36, 257, 214);
		contentPane.add(spSaida);
		
		taSaida = new JTextArea();
		taSaida.setWrapStyleWord(true);
		taSaida.setEditable(false);
		taSaida.setTabSize(2);
		taSaida.setLineWrap(true);
		spSaida.setViewportView(taSaida);
		
		JLabel lblShiftConstante = new JLabel("Shift constante");
		lblShiftConstante.setBounds(10, 261, 120, 20);
		contentPane.add(lblShiftConstante);
		
		spinConstShift = new JSpinner();
		spinConstShift.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spinConstShift.setBounds(140, 261, 50, 20);
		contentPane.add(spinConstShift);
		
		JLabel lblShiftVariavel = new JLabel("Shift variavel");
		lblShiftVariavel.setBounds(10, 292, 120, 20);
		contentPane.add(lblShiftVariavel);
		
		spinVarShift = new JSpinner();
		spinVarShift.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spinVarShift.setBounds(140, 292, 50, 20);
		contentPane.add(spinVarShift);
		
		JLabel lblLimiteDaContagem = new JLabel("Limite da contagem");
		lblLimiteDaContagem.setBounds(10, 323, 120, 20);
		contentPane.add(lblLimiteDaContagem);
		
		spinLimite = new JSpinner();
		spinLimite.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinLimite.setBounds(140, 323, 50, 20);
		contentPane.add(spinLimite);
		
		JButton btnCriptografar = new JButton("Criptografar");
		btnCriptografar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				criptografar();
			}
		});
		
		JButton btnAleatorio = new JButton("Aleat\u00F3rio");
		btnAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aleatorio();
			}
		});
		btnAleatorio.setBounds(414, 322, 110, 20);
		contentPane.add(btnAleatorio);
		btnCriptografar.setBounds(414, 261, 110, 20);
		contentPane.add(btnCriptografar);
		
		JButton btnReverter = new JButton("Reverter");
		btnReverter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reverter();
			}
		});
		btnReverter.setBounds(414, 291, 110, 20);
		contentPane.add(btnReverter);
	}
	
	private void aleatorio() {
		spinConstShift.setValue(random.nextInt(1000));
		spinLimite.setValue(random.nextInt(1000));
		spinVarShift.setValue(random.nextInt((int)spinLimite.getValue() * 2) - (int)spinLimite.getValue());
	}
	
	private void criptografar() {
		taSaida.setText(Deslocamento.deslocamento(taEntrada.getText(), (int)spinConstShift.getValue(),
				(int)spinVarShift.getValue(), (int)spinLimite.getValue()));
	}
	
	private void reverter() {
		taSaida.setText(Deslocamento.deslocamento(taEntrada.getText(), -(int)spinConstShift.getValue(),
				-(int)spinVarShift.getValue(), (int)spinLimite.getValue()));
	}
}
