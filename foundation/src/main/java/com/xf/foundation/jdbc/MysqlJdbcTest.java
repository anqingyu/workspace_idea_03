package com.xf.foundation.jdbc;

import java.sql.*;

/**
 * @Description: 使用jdbc连接Oracle
 * @Author: xiefu
 * @Date: 2019/5/21 10:23
 */
public class MysqlJdbcTest {

    public static void main(String[] args){
        String url = "jdbc:mysql://127.0.0.1:3306/database_test01?useUnicode=true&amp;characterEncoding=utf-8";
        String userName = "root";
        String password = "123";

        try {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取数据库连接
            Connection connection = DriverManager.getConnection(url, userName, password);
            // 3.创建Statement对象
            Statement statement = connection.createStatement();
            // 4.向数据库发送SQL命令
            String sql = "select * from user";
            ResultSet rs = statement.executeQuery(sql);
            // 5.处理返回的结果集（使用ResultSet类）
            while (rs.next()){
                System.out.println(rs.getString("username")+" "
                        +rs.getString("password"));
            }
            // 6.关闭资源
            rs.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
