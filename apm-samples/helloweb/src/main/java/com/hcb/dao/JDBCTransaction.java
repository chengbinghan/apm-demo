package com.hcb.dao;

import com.sun.media.sound.SoftTuning;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.sql.*;
import java.util.Random;


public class JDBCTransaction {

    public static final String URL = "jdbc:mysql://localhost:3306/test";

    public static final String USER = "root";

    public static final String PASSWD = "pp123";


    public static void jdbcTransaction(int id) {
        Connection conn = null;
        PreparedStatement pstmtupdate = null;
        PreparedStatement pstmtquery = null;

        Random random = new Random();
        int p1 = random.nextInt(10000);
        int p2 = random.nextInt(20000);
        String updatesql1 = "UPDATE my_table SET phonenumber=" + p1 + " WHERE username='zhangsan'";
        String updatesql2 = "UPDATE my_table set  phonenumber=" + p1 + " WHERE username='lisi'";
        String querysql = "select * from my_table";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWD);

            conn.setAutoCommit(false); // 自动提交设置为false 


            // 执行更新操作
            pstmtupdate = conn.prepareStatement(updatesql1);
            pstmtupdate.executeUpdate();

            // 执行更新操作
            pstmtupdate = conn.prepareStatement(updatesql2);
            pstmtupdate.executeUpdate();


            // 执行查找操作 
            pstmtquery = conn.prepareStatement(querysql);
            ResultSet resultSet = pstmtquery.executeQuery();

            System.out.println("================================================>sql");
            System.out.println("sql:" + updatesql1);
            System.out.println("sql:" + updatesql2);


            conn.commit();
            conn.setAutoCommit(true);

            pstmtupdate.close();
            pstmtquery.close();
            conn.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (pstmtupdate != null) {
                    pstmtupdate.close();
                }

                if (pstmtquery != null) {
                    pstmtquery.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        jdbcTransaction(1);
    }
} 