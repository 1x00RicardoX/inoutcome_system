package team.javaproject.inoutcome.system2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import team.javaproject.inoutcome.system.sql;

public class Research extends JFrame{
	JButton person_button,data_button,research_button,delete_button;
	JPanel panel;
	JTable table;
	DefaultTableModel model;
	JTextField delete_text;
	
	public void init(int i) {
		final int id=i;
		setTitle("财务管理系统");
		setSize(600, 400);
        setLocationRelativeTo(null);
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
                data_Infor(i);
            }
        });
		research_button =new JButton("收支查询");
		research_button.setBounds(0, 90, 100, 30);
		research_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                research_Infor(i);
            }
        });
		table=new JTable();
		model=new DefaultTableModel();
		table.setModel(model);
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(100, 10, 450, 200);
		
		String [] s= {"ID","金额","日期","类型"};
    	////将列名添加到表格模型作为标题
    	for(int i1=0;i1<=3;i1++){
    		model.addColumn(s[i1]);
    	}
		
		JLabel jl=new JLabel("第几行:");
		jl.setBounds(220, 220,50,30);

		delete_text=new JTextField();
		delete_text.setBounds(300, 220, 50, 30);

		delete_button=new JButton("删除");
		delete_button.setBounds(360, 220, 70, 30);

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
	}
	public Research(int i) {
		init(i);
		sql s=new sql();
		sql.researIn(i, model);
	}
	
	public void person_Infor(int i) {
		
	}
	public void data_Infor(int i) {
		JOptionPane.showConfirmDialog(null, "改功能尚未完善"," ",JOptionPane.DEFAULT_OPTION);
	}
	public void research_Infor(int i) {
		
	}
}
