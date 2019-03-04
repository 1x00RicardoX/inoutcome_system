

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.jfree.data.general.DefaultPieDataset;

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
                " (account,password,portrait)"+" values ('"+user+"','"+password+",1')";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句

            i=sm.executeUpdate(sql);

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
    
    public static int researIn(int i,DefaultTableModel model) {//查询信息
    	final int id=i;
    	int f=0;
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

            //获取表中列数及列名
        	ResultSetMetaData rsmd=rs.getMetaData();
        	//获得列数
        	int count=rsmd.getColumnCount();
        	//每行设置一个数组,删除前两列两项
        	String[] row=new String[count-1];

        	while(rs.next()){
        		//将查询到的每行数据赋入数组内
        		for(int i1=0;i1<count-1;i1++)
        			row[i1]=rs.getString(i1+2);
        		//增加一行
        		model.addRow(row);
        		f++;
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
        return f;
    }
    public static void delete(String sql) {//删除信息
    	int k=0;
    	ResultSet rs;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            
            k= sm.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
        
    }
    public static void insert(int i,String sql) {//插入收支信息
    	final int id=i;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        try {
        	conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句

            i=sm.executeUpdate(sql);
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
    public static String[] alter(int i) {//修改个人信息
        String[] what=new String[7];
        final int id=i;
    	ResultSet rs;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        String sql = "use jdbctest1;"+"\n select * from userfile1" +
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
            
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int count=rsmd.getColumnCount();
            
            while(rs.next()) {
            	for(int i1=0;i1<count-2;i1++)
            			what[i1]=rs.getString(i1+2);
            }
            
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("数据库连接失败");
            }
        return what;
    }
    public DefaultPieDataset getDataset(int i) {
    	DefaultPieDataset d = new DefaultPieDataset();
    	final int id=i;
    	double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0;
    	ResultSet rs;
        Connection conn;
        Statement sm;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbctest1;";
        try {
            // 连接数据库
            conn = DriverManager.getConnection(url, "sa", "123");
            // 建立Statement对象
            sm = conn.createStatement();
            /**
             * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
             */
            // 执行数据库查询语句
            String sql1 = "use jdbctest1;"+"\n select mone_y from userIncome" +
                    " \n where id='"+id+"'and typ_e = '收入'";
            String sql2 = "use jdbctest1;"+"\n select mone_y from userIncome" +
                    " \n where id='"+id+"'and typ_e='教育支出'";
            String sql3 = "use jdbctest1;"+"\n select mone_y from userIncome" +
                    " \n where id='"+id+"'and typ_e='饮食支出'";
            String sql4 = "use jdbctest1;"+"\n select mone_y from userIncome" +
                    " \n where id='"+id+"' and typ_e='交通支出'";
            String sql5 = "use jdbctest1;"+"\n select mone_y from userIncome" +
                    " \n where id='"+id+"' and typ_e='其它支出'";

            rs = sm.executeQuery(sql1);
            while(rs.next()) {
            	temp1+=rs.getDouble(1);
            }
            d.setValue("收入",temp1);
            
            rs = sm.executeQuery(sql2);
            while(rs.next()) {
            	temp2+=rs.getDouble(1);
            }
            d.setValue("教育支出",temp2);
            
            rs = sm.executeQuery(sql3);
            while(rs.next()) {
            	temp3+=rs.getDouble(1);
            }
            d.setValue("饮食支出",temp3);
            
            rs = sm.executeQuery(sql4);
            while(rs.next()) {
            	temp4+=rs.getDouble(1);
            }
            d.setValue("交通支出",temp4);
            
            rs= sm.executeQuery(sql5);
            while(rs.next()) {
            	temp5+=rs.getDouble(1);
            }
            d.setValue("其它支出",temp5);
            
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }  
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据失败");
        }
        return d;
    }
}