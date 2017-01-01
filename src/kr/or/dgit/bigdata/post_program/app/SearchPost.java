package kr.or.dgit.bigdata.post_program.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.bigdata.post_program.dto.Post;
import kr.or.dgit.bigdata.post_program.service.PostService;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class SearchPost extends JFrame {

	private JPanel contentPane;
	private JTextField tfDoro;
	private JTable table;
	private JComboBox cmbSido;
	
	public SearchPost() {
		setTitle("우편번호 검색");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblSido = new JLabel("시도");
		panel.add(lblSido);
		
		cmbSido = new JComboBox();
		List<Post> sidoList = PostService.getInstance().selectSido();
		for (int i = 0; i < sidoList.size(); i++) {
			cmbSido.addItem(sidoList.get(i));
		}
		panel.add(cmbSido);
		
		JLabel lblDoro = new JLabel("도로명");
		panel.add(lblDoro);
		
		tfDoro = new JTextField();
		panel.add(tfDoro);
		tfDoro.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfDoro.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도로명을 입력해주세요.");
					tfDoro.requestFocus();
				}else{
					reload();
				}
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setViewportView(table);
	}
	private void reload(){
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColName());
		if (getRowData() == null) {
			JOptionPane.showMessageDialog(null, "도로명을 정확히 입력해주세요.");
			revalidate();
		}else{
			table.setModel(model);
			tableCellAlignment(SwingConstants.CENTER, 0);
			tableCellAlignment(SwingConstants.LEFT, 1);
			tableSetWidth(120, 500);
			
			table.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "\""+model.getValueAt(table.getSelectedRow(), 1)+"\" 이 주소가 정확합니까?");
					
					if (res == 0) {
						setVisible(false);
						PostApplication.tfZipcode.setText(model.getValueAt(table.getSelectedRow(), 0)+"");//우편번호
						PostApplication.tfAddr.setText(model.getValueAt(table.getSelectedRow(), 1)+"");//주소
					}
				}
				
			});
		}
	}

	private String[][] getRowData() {
		List<Post> rowDatas = PostService.getInstance().searchDoro(getPost());
		
		String[][] arRowDatas = new String[rowDatas.size()][];
		for (int i = 0; i < rowDatas.size(); i++) {
			arRowDatas[i] = rowDatas.get(i).toArray();
		}
		
		if (rowDatas.isEmpty()) {
			return null;
		}else{
			return arRowDatas;
		}
		
	}
	

	private String[] getColName() {
		return new String[]{"우편번호", "주소"};
	}


	private void tableCellAlignment(int align, int ...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
		
	}
	
	public void tableSetWidth(int ...width){
		TableColumnModel cModel = table.getColumnModel();
		
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	private Post getPost() {
		String sido = cmbSido.getSelectedItem()+"";
		String doro = tfDoro.getText().trim();
		return new Post(sido, doro);
	}
}
