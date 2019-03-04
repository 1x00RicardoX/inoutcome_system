
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Research extends JFrame{
	JButton person_button,data_button,research_button,delete_button,up_button;
	JPanel panel;
	JTable table;
	DefaultTableModel model;
	JTextField delete_text,money_text,date_text;
	JComboBox type_text;
	JLabel money_label,date_label,type_label;
	int row;
	
	public void init(int i) {
		final int id=i;
		setTitle("财务管理系统");
		setSize(600, 400);
        setLocationRelativeTo(null);
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //侧边栏
        person_button = new JButton("信息管理");
		person_button.setBounds(0, 10, 100, 30);
		person_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person_Infor(i);
            }
        });
		data_button =new JButton("财务管理");
		data_button.setBounds(0, 50, 100, 30);
		data_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	research_Infor(i);
            }
        });
		research_button =new JButton("财务分析");
		research_button.setBounds(0, 90, 100, 30);
		research_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data_Infor(i);
            }
        });
		
		//表格组件
		table=new JTable();
		model=new DefaultTableModel();
		table.setModel(model);
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(100, 10, 450, 200);
		
		////将列名添加到表格模型作为标题
		String [] s= {"标号","金额","日期","类型"};
    	for(int i1=0;i1<=3;i1++){
    		model.addColumn(s[i1]);
    	}
    	
		//删除部分组件
		JLabel jl=new JLabel("行数:");
		jl.setBounds(100, 220,50,30);

		delete_text=new JTextField();
		delete_text.setBounds(220, 220, 100, 30);

		delete_button=new JButton("删除");
		delete_button.setBounds(360, 220, 70, 30);
		delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(delete_text.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "请输入行数！", "格式错误",
            				JOptionPane.WARNING_MESSAGE);
            		
            	}
            	else {
            		int row=Integer.parseInt(delete_text.getText());
            		String count=(String)model.getValueAt(row-1, 0);
            		String delete="use jdbctest1;\n delete from userIncome \n where number= "+count;
            		sql s1=new sql();
            		s1.delete(delete);
            		model.removeRow(row-1);
            	}
            }
        });
		
		//更新部分组件
		money_label=new JLabel("请输入金额:");
		money_label.setBounds(100, 250, 100, 30);
		date_label=new JLabel("请输入日期：");
		date_label.setBounds(100,280,100,30);
		type_label=new JLabel("收支类型：");
		type_label.setBounds(100,310,100,30);
		
		money_text=new JTextField();
		money_text.setBounds(220, 250, 100, 30);
		date_text=new JTextField();
		date_text.setBounds(220, 280, 100, 30);
		type_text=new JComboBox();
		type_text.setBounds(220, 310, 100, 30);
		type_text.addItem("请选择");
		type_text.addItem("收入");
		type_text.addItem("教育支出");
		type_text.addItem("饮食支出");
		type_text.addItem("交通支出");
		type_text.addItem("其他支出");
		
		up_button=new JButton("记录");
		up_button.setBounds(360, 310, 70, 30);
		up_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String money=money_text.getText();
            	String date=date_text.getText();
            	String type=(String)type_text.getSelectedItem();
            	if(money_text.getText().equals("")||date_text.getText().equals("")||type_text.getSelectedItem().equals("请选择")) {
            		JOptionPane.showMessageDialog(null, "信息不完整！  ", "格式错误",JOptionPane.WARNING_MESSAGE);
            	}
            	else {
            		String money_pattern="^[\\+]?[\\d]+(\\.[\\d]+)?$";
            		Pattern pattern1=Pattern.compile(money_pattern);
            		Matcher match1 = pattern1.matcher(money);
            		String date_pattern="((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|"
            				+ "(((19|20)\\d{2})-(0?[13578]|1[02])-31)|"
            				+ "(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|"
            				+ "((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$";
            		Pattern pattern2=Pattern.compile(date_pattern);
            		Matcher match2 = pattern2.matcher(date);
            		if (!match1.matches()) {
            			JOptionPane.showMessageDialog(null, "收支格式错误！  请重新输入！", "格式错误",
            					JOptionPane.WARNING_MESSAGE);
            		}
            		else if(!match2.matches()) {
            			JOptionPane.showMessageDialog(null, "日期格式错误！  请重新输入！", "格式错误",
            					JOptionPane.WARNING_MESSAGE);
            		}
            		else {
            			String update="use jdbctest1;\n insert into userIncome(id,mone_y,dat_e,typ_e) \n values ('"
            				+id+"','"+money+"','"+date+"','"+type+"')";
            			sql s1=new sql();
            			s1.insert(id,update);
            			for(int j=0;j<row;j++) {
            				model.removeRow(0);
            			}
            			row=s1.researIn(id, model);
            			}
            	}
            }
        });

		panel=new JPanel();
		add(panel);
		
		panel.setLayout(null);
		panel.add(person_button);
		panel.add(data_button);
		panel.add(research_button);
		panel.add(jsp);
		panel.add(jl);
		panel.add(delete_text);
		panel.add(delete_button);
		panel.add(money_label);
		panel.add(date_label);
		panel.add(type_label);
		panel.add(money_text);
		panel.add(date_text);
		panel.add(type_text);
		panel.add(up_button);
	}
	public Research(int i) {
		init(i);
		row=sql.researIn(i, model);
	}
	
	public void person_Infor(int i) {
		alter a=new alter(i);
		this.dispose();
	}
	public void data_Infor(int i) {
		Data_Manage d=new Data_Manage(i);
		this.dispose();
	}
	public void research_Infor(int i) {
		sql.researIn(i, model);
	}
}
