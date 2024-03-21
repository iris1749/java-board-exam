package org.example.domain.article.model;

import org.example.base.CommonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleMySQLRepository implements Repository {

    private static final String URL = "jdbc:mysql://localhost:3306/t2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void makeTestData() {

    }

    @Override
    public ArrayList<Article> findArticleByKeyword(String keyword) {
        return null;
    }

    @Override
    public Article findArticleById(int id) {
        return null;
    }

    @Override
    public void deleteArticle(Article article) {

    }

    @Override
    public void updateArticle(Article article, String newTitle, String newBody) {
        String sql = "UPDATE article SET title = ?, 'body' = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // PreparedStatement에 파라미터 설정
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newBody);
            pstmt.setInt(3, article.getId());

            // 쿼리 실행
            pstmt.executeUpdate();
            System.out.println("데이터가 성공적으로 저장되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

    }

    @Override
    public ArrayList<Article> findAll() {
        return null;
    }

    @Override
    public Article saveArticle(String title, String body) {

        // article 테이블에 게시물 저장
        CommonUtil commonUtil = new CommonUtil();
        String sql = "INSERT INTO article (title, body, hit, regDate) VALUES (?, ?, ?, ?)";

        // 자동으로 닫혀야 하는 리소스들을 try-with-resources 구문을 사용해 관리
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // PreparedStatement에 파라미터 설정
            pstmt.setString(1, title);
            pstmt.setString(2, body);
            pstmt.setInt(3, 0);
            pstmt.setString(4, commonUtil.getCurrentDateTime());

            // 쿼리 실행
            pstmt.executeUpdate();
            System.out.println("데이터가 성공적으로 저장되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

        return null;
    }
}