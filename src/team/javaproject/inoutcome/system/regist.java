package team.javaproject.inoutcome.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import team.javaproject.inoutcome.system2.Research;

public class regist extends JFrame{
    private static int count=0;
    private static String vcode="";
    JButton jb;
    JTextField jt1,jt2,jt3,jt4,jt5,jy;
    JOptionPane jo;
    JPasswordField jp1,jp2;
    JLabel jl1,jl2,jl3,jl4,jl5,jlp1,jlp2,jl,jly;
    JPanel jp;
    public void init(){
        setTitle("收支管理系统注册");
        setSize(300, 300);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jl=new JLabel("注册",JLabel.CENTER);
        jl.setFont(new Font("黑体",Font.BOLD,30));
        jl.setSize(400,100);
        add(jl,BorderLayout.NORTH);

        jp=new JPanel();
        jp.setLayout(null);
        jp.setVisible(true);

        jl1=new JLabel("账号:");
        jl1.setBounds(10, 20, 80, 25);
        jt1=new JTextField(20);
        jt1.setBounds(100,20,165,25);
        jp.add(jl1);
        jp.add(jt1);

        jlp1=new JLabel("密码:");
        jlp1.setBounds(10, 50, 80, 25);
        jp1=new JPasswordField(20);
        jp1.setBounds(100,50,165,25);
        jp.add(jlp1);
        jp.add(jp1);

        jlp2=new JLabel("确认密码:");
        jlp2.setBounds(10, 80, 80, 25);
        jp2=new JPasswordField(20);
        jp2.setBounds(100,80,165,25);
        jp.add(jlp2);
        jp.add(jp2);

        // 验证码
        VCodeGenerator vc=new VCodeGenerator(4);
        vcode=vc.generatorVCode();
        Image image=vc.generatorVCodeImage(vcode,true);
        JLabel Jimg=new JLabel();
        ImageIcon imgi=new ImageIcon(image);
        Jimg.setIcon(imgi);
        Jimg.setBounds(100,110,200,40);
        jp.add(Jimg);

        jly=new JLabel("验证码:");
        jly.setBounds(10, 150, 80, 25);
        jy=new JTextField();
        jy.setBounds(100,150,165,25);
        jp.add(jy);
        jp.add(jly);

        add(jp,BorderLayout.CENTER);

        jb=new JButton("注册");
        jb.setFont(new Font("宋体",Font.BOLD,20));
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zhuce();
            }
        });
        add(jb,BorderLayout.SOUTH);
    }

    public regist(){
        init();
    }

    public void zhuce(){
        if(!jy.getText().equals(vcode))
            JOptionPane.showMessageDialog(null, "验证码错误！  请重新输入！", "注册提示",JOptionPane.WARNING_MESSAGE);
        else if(jt1.getText().equals("")||jp1.getText().equals("")||jp2.getText().equals(""))
            JOptionPane.showMessageDialog(null, "未输入账号或密码！  请重新输入！", "注册提示",JOptionPane.WARNING_MESSAGE);
        else if(!jp1.getText().equals(jp2.getText()))
            JOptionPane.showMessageDialog(null, "两次密码输入不匹配！  请重新输入！", "注册提示",JOptionPane.WARNING_MESSAGE);
        else{
            shaenc enc=new shaenc();
            String password=enc.SHA(jp1.getText(),"SHA-256");
            if(sql.regist(jt1.getText(),password)==1){
                JOptionPane.showMessageDialog(null, "注册成功！", "注册提示",JOptionPane.INFORMATION_MESSAGE);
                int n = JOptionPane.showConfirmDialog(null, "是否直接进入系统?", "注册提示",JOptionPane.YES_NO_OPTION);
                if(n==1){
                	
                    this.dispose();
                }
                //TODO 根据n为1或0选择进入系统或返回登录界面
            }
            else
                JOptionPane.showMessageDialog(null, "注册失败！  请重新输入注册信息！", "注册提示",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //进入系统
    public static void research(int i) {
    	Research res=new Research(i);
    }

}
