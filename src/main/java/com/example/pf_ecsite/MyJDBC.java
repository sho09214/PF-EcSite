package com.example.pf_ecsite;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) throws Exception {
        //MySQL接続情報
        String url = "jdbc:mysql://localhost:3306/pf_ecsite";
        String user = "root";
        String password = "sho09214";

        //SQLクエリ
        String query = "SELECT * FROM product";

        //JDBC接続
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

                //メタデータを取得してカラム名を表示
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                //データを表示
                while(rs.next()){
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println("SQLエラー: " + e.getMessage());
            }
        }catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバが見つかりません: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
