package team.javaproject.inoutcome.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login{
    private static JFrame jf;
    public static void main(String[] args) {
        String user,password;
        // 创建 JFrame 实例
        JFrame frame = new JFrame("收支管理系统登陆");
        // Setting the width and height of frame
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // 创建面板
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);

        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
        jf=frame;
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("用户:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);

        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 验证码


        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(30, 80, 200, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("user:"+userText.getText()+" password:"+passwordText.getText());
                login(userText.getText(),passwordText.getText());
            }
        });
        panel.add(loginButton);

        // 创建注册按钮
        JButton registButton = new JButton("没有账号？点此注册");
        registButton.setBounds(120, 130, 150, 15);
        registButton.setFont(new Font("楷体",Font.ITALIC,12));
        registButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regist();
            }
        });
        panel.add(registButton);
    }

    public static void login(String user,String password){
        if(user.equals("")||password.equals("")){
            JOptionPane.showMessageDialog(null, "未输入用户名或密码！  请重新输入！", "登录提示",JOptionPane.WARNING_MESSAGE);
        }
        else{
            sql sql1=new sql();
            shaenc enc=new shaenc();
            password=enc.SHA(password,"SHA-256");
            int id=sql1.login(user, password);
            if(id!=0){
                JOptionPane.showMessageDialog(null, "登录成功！", "登录提示",JOptionPane.INFORMATION_MESSAGE);
                //TODO
                // 进入系统
            }
            else
                JOptionPane.showMessageDialog(null, "用户名或密码错误！  请重新输入！", "登录提示",JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void regist(){
        regist reg=new regist();
        jf.dispose();
    }
}