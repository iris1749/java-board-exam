package org.example.test;

import org.example.base.CommonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBTest {
    private static final String URL = "jdbc:mysql://localhost:3306/t2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC 드라이버를 찾을 수 없습니다.");
        }
    }

    public static void main(String[] args) {
        CommonUtil commonUtil = new CommonUtil();
        String sql = "INSERT INTO article (title, body, hit, regDate) VALUES (?, ?, ?, ?)";

        // 자동으로 닫혀야 하는 리소스들을 try-with-resources 구문을 사용해 관리
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // PreparedStatement에 파라미터 설정
            pstmt.setString(1, "제목1");
            pstmt.setString(2, "내용1");
            pstmt.setInt(3, 0);
            pstmt.setString(4, commonUtil.getCurrentDateTime());

            // 쿼리 실행
            pstmt.executeUpdate();
            System.out.println("데이터가 성공적으로 저장되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

    }
}