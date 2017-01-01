package kr.or.dgit.bigdata.post_program.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PostApplication extends JFrame {

	private JPanel contentPane;
	private JTextField tfAddrDetail;
	public static JTextField tfAddr;
	public static JTextField tfZipcode;
	SearchPost searchPost = new SearchPost();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PostApplication frame = new PostApplication();
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
	public PostApplication() {
		setTitle("우편번호");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel panelLbl = new JPanel();
		contentPane.add(panelLbl);
		panelLbl.setLayout(new GridLayout(4, 1, 0, 10));
		
		JLabel lblZipcode = new JLabel("우편번호");
		lblZipcode.setFont(new Font("굴림", Font.PLAIN, 13));
		lblZipcode.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLbl.add(lblZipcode);
		
		JLabel lblAddr = new JLabel("주소");
		lblAddr.setFont(new Font("굴림", Font.PLAIN, 13));
		lblAddr.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLbl.add(lblAddr);
		
		JLabel lblAddrDetail = new JLabel("세부 주소");
		lblAddrDetail.setFont(new Font("굴림", Font.PLAIN, 13));
		lblAddrDetail.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLbl.add(lblAddrDetail);
		
		JPanel panelTf = new JPanel();
		contentPane.add(panelTf);
		panelTf.setLayout(new GridLayout(4, 1, 10, 10));
		
		JPanel panelSearch = new JPanel();
		panelTf.add(panelSearch);
		panelSearch.setLayout(new GridLayout(0, 2, 0, 0));
		
		tfZipcode = new JTextField();
		tfZipcode.setEnabled(false);
		tfZipcode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				searchPost.setVisible(true);
			}
		});
		
		panelSearch.add(tfZipcode);
		tfZipcode.setColumns(10);
		
		JButton btnSearch = new JButton("우편번호 검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPost.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("굴림", Font.PLAIN, 11));
		panelSearch.add(btnSearch);
		
		tfAddr = new JTextField();
		panelTf.add(tfAddr);
		tfAddr.setColumns(10);
		
		tfAddrDetail = new JTextField();
		panelTf.add(tfAddrDetail);
		tfAddrDetail.setColumns(10);
		
		JButton btnRegister = new JButton("등록");
		btnRegister.setFont(new Font("굴림", Font.PLAIN, 13));
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfZipcode.getText().equals("") || tfZipcode.getText()==null) {
					JOptionPane.showMessageDialog(null, "주소를 입력해주세요.");
				}else{
					String message = String.format("%s %s %s", tfZipcode.getText(), tfAddr.getText(), tfAddrDetail.getText());
					JOptionPane.showMessageDialog(null, message);
					tfZipcode.setText("");
					tfAddr.setText("");
					tfAddrDetail.setText("");
				}
			}
		});
		panelTf.add(btnRegister);
	}

}
