package team.javaproject.inoutcome.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class layouttest extends JFrame {
    private static String[] labelWord={"账号:","密码:","确认密码:","姓名:","性别:","电话:","邮箱:","地址:"};
    private String account,password,name,sex,phone,mail,address;//保存用户数据
    private String _account,_password,_name,_sex,_phone,_mail,_address;//保存用户修改后数据
    JButton xiugai_huifu,tijiao;//只能修改除id，account，portrait的部分
    JLabel[] jl=new JLabel[8];//密码第一次显示为******
    JPasswordField ppa1,ppa2;
    JComboBox cse;
    JTextField tac,tna,tse,tph,tma,tad;
    JLabel biaoti;
    JPanel panel;

    public void init(){
        setTitle("收支管理系统信息修改");
        setSize(320, 400);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel=new JPanel();
        add(panel);

        panel.setLayout(null);
        biaoti=new JLabel("用户信息修改");
        biaoti.setFont(new Font("宋体",Font.BOLD,28));
        biaoti.setBounds(70,10,200,30);
        panel.add(biaoti);

        int count=70;
        int countn=0;
        for(JLabel label:jl){
            label=new JLabel(labelWord[countn++]);
            label.setBounds(20,count,80,25);
            count+=30;
            panel.add(label);
        }

        count=70;

        tac=new JTextField(account);
        tac.setBounds(120,count,175,25);
        tac.setEditable(false);
        count+=30;
        panel.add(tac);

        ppa1=new JPasswordField(password);
        ppa1.setBounds(120,count,175,25);
        ppa1.setEchoChar('●');
        ppa1.setEditable(false);
        count+=30;
        panel.add(ppa1);

        ppa2=new JPasswordField(password);
        ppa2.setBounds(120,count,175,25);
        ppa2.setEchoChar('●');
        ppa2.setEditable(false);
        count+=30;
        panel.add(ppa2);

        tna=new JTextField(name);
        tna.setBounds(120,count,175,25);
        tna.setEditable(false);
        count+=30;
        panel.add(tna);

        String[] s={"男","女"};
        cse=new JComboBox(s);
        cse.setBounds(120,count,175,25);
        cse.setEditable(false);
        count+=30;
        panel.add(cse);

        tph=new JTextField(phone);
        tph.setBounds(120,count,175,25);
        tph.setEditable(false);
        count+=30;
        panel.add(tph);

        tma=new JTextField(mail);
        tma.setBounds(120,count,175,25);
        tma.setEditable(false);
        count+=30;
        panel.add(tma);

        tad=new JTextField(address);
        tad.setBounds(120,count,175,25);
        tad.setEditable(false);
        count+=30;
        panel.add(tad);

        // 创建修改按钮
        xiugai_huifu = new JButton("修改");
        xiugai_huifu.setBounds(40, 320, 100, 35);
        xiugai_huifu.setFont(new Font("黑体",Font.BOLD,20));
        xiugai_huifu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(xiugai_huifu.getText()=="修改"){
                    ppa1.setEditable(true);
                    ppa2.setEditable(true);
                    tad.setEditable(true);
                    tma.setEditable(true);
                    tna.setEditable(true);
                    tph.setEditable(true);
                    xiugai_huifu.setText("重置");
                }
                else{
                    tad.setText(address);
                    tma.setText(mail);
                    tna.setText(name);
                    tph.setText(phone);
                }
            }
        });
        panel.add(xiugai_huifu);

        // 创建保存按钮
        tijiao = new JButton("保存");
        tijiao.setBounds(170, 320, 100, 35);
        tijiao.setFont(new Font("黑体",Font.BOLD,20));
        tijiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO 提交修改
                //TODO 成功则跳转
            }
        });
        panel.add(tijiao);

    }

    public static void main(String[] args){
        layouttest l=new layouttest();
        l.init();
    }
}
