
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.*;
import org.jfree.data.general.DefaultPieDataset;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Data_Manage extends JFrame{
	JButton person_button,data_button,research_button,delete_button,up_button;
	JPanel panel;
	JTextField money_text,date_text,type_text;
	JLabel money_label,date_label,type_label;
	ChartPanel frame1;
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
		
		//饼状图
		DefaultPieDataset data = getDataSet(id);
		//名称，数据集，是否显示图例，是否生成工具，是否生成URL链接
	    JFreeChart chart = ChartFactory.createPieChart3D("收支分布",data,true,false,false);
	    //设置百分比
	    PiePlot pieplot = (PiePlot) chart.getPlot();
	    DecimalFormat df = new DecimalFormat("0.00%");//设置小数的格式
	    NumberFormat nf = NumberFormat.getNumberInstance();//返回默认数字格式
	    //获得StandardPieSectionLabelGenerator对象,生成的格式，
	    //{0}表示section名，{1}表示section的值，{2}表示百分比。可以自定义  
	    StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
	    pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	    
	    pieplot.setNoDataMessage("无数据显示");
	    pieplot.setCircular(false);
	    pieplot.setLabelGap(0.02D);
	    
	    pieplot.setIgnoreNullValues(true);//设置不显示空值
	    pieplot.setIgnoreZeroValues(true);//设置不显示零值
	    frame1=new ChartPanel (chart,true);
	    frame1.setBounds(130, 0, 370, 400);
	    chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
	    PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	    piePlot.setLabelFont(new Font("宋体",Font.BOLD,15));//为饼图元素设置字体
	    chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,15));//为图例说明设置字体
	    
		
		panel=new JPanel();
		panel.setBounds(0, 0, 130, 400);
		add(frame1);
		add(panel);
		panel.setLayout(null);
		panel.add(person_button);
		panel.add(data_button);
		panel.add(research_button);
	}
	
	public Data_Manage(int i) {
		init(i);
	}
	
	private DefaultPieDataset getDataSet(int id) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		sql s=new sql();
		dataset=s.getDataset(id);
		return dataset;
	}
	
	public void person_Infor(int i) {
		alter a=new alter(i);
		this.dispose();
	}
	public void data_Infor(int i) {
		
	}
	public void research_Infor(int i) {
		Research r=new Research(i);
		this.dispose();
	}
}
