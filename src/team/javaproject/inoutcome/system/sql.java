package team.javaproject.inoutcome.system;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class sql {
    public static int login(String user,String password){
        int ret=0;
        Connection conn;
        Statement sm;
        ResultSet rs;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        String sql = "use jdbctest1;"+"\n select id from userfile1" +
                "\n where account='"+user+"' and password='"+password+"'";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句
            rs = sm.executeQuery(sql);
            /**
             * 执行给定的 SQL
             * 语句，该语句返回单个 ResultSet 对象
             */
            while (rs.next()) {
                ret = rs.getInt("id");
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return ret;
    }

    public static int regist(String user,String password){
        int i=0;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        String sql = "use jdbctest1;"+"\n insert into userfile1" +
                " (account,password)"+" values ('"+user+"','"+password+"')";
        String sql2 = "use jdbctest1;"+"\n select id from userfile1" +
                "\n where account='"+user+"' and password='"+password+"'";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库插入语句
            if(sm.executeUpdate(sql)==0)
                return 0;
            // 执行数据库查询语句
            i=sm.executeUpdate(sql2);

            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return i;
    }

    /*public static void main(String args[]){
        Connection conn;
        Statement sm;
        ResultSet rs;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        String sql = "use jdbctest1;"+"\n select id from userfile1" +
                "\n where account='"+'5'+"' and password='"+"ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d"+"'";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句
            //rs = sm.executeQuery(sql);
            /**
             * 执行给定的 SQL
             * 语句，该语句返回单个 ResultSet 对象
             */
            /*while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("id:" + id);
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }*/
    
    public static void researIn(int i,DefaultTableModel model) {//查询信息
    	final int id=i;
    	ResultSet rs;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        String sql = "use jdbctest1;"+"\n select * from userIncome" +
                " \n where id='"+id+"'";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句

            rs= sm.executeQuery(sql);

            //获取表中列数及列名，作为表格组件的标题
        	ResultSetMetaData rsmd=rs.getMetaData();
        	//获得列数
        	int count=rsmd.getColumnCount();
        	////将列名添加到表格模型作为标题
        	//for(int i1=1;i1<=count;i1++){
        		//model.addColumn(rsmd.getColumnName(i1));
        	//}
        	//每行设置一个数组
        	String[] row=new String[count];

        	while(rs.next()){
        		//将查询到的每行数据赋入数组内
        		for(int i1=0;i1<count;i1++)
        			row[i1]=rs.getString(i1+1);
        		//增加一行
        		model.addRow(row);
        	}
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
    public static void delete(int i) {//删除信息
    	
    }
    public static void insert() {//插入收支信息
    	
    }
    public static void alter(int i) {//修改个人信息
    	
    }
}