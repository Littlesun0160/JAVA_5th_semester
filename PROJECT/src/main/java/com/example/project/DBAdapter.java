package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;


public class DBAdapter
{
    Connection con;


    void create_or_connection()
    {
        try
        {

                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:diary.sqlite";
            con = DriverManager.getConnection("jdbc:sqlite:diary.sqlite");

            Statement stmt = con.createStatement();
            String sql= """
                    CREATE TABLE IF NOT EXISTS "Posts"(
                    \t"ID"\tINTEGER NOT NULL UNIQUE,
                    \t"date"\tTEXT NOT NULL,
                    \t"post"\tTEXT,
                    \t"photo"\tTEXT,
                    \t"mood"\tTEXT,
                    \tPRIMARY KEY("ID" AUTOINCREMENT)
                    );\
                    CREATE TABLE "Moods" (
                    	\t"ID"\tINTEGER NOT NULL UNIQUE,
                    	\t"Mood"\t	TEXT NOT NULL,
                    	\t"Color"\t	INTEGER NOT NULL,
                    	PRIMARY KEY("ID" AUTOINCREMENT)
                    );""";
            String sql_mood = """
                    INSERT INTO Moods (ID, Mood, Color)
                    SELECT *
                    FROM (VALUES (0, 'Ужасно', Color.DARKVIOLET),
                                 (1, 'Плохо', Color.VIOLET),
                                 (2, 'Нормально', Color.BLUE),
                                 (3, 'Хорошо', Color.LIGHTGREEN),
                                 (4, 'Отлично!', Color.GREEN))
                    WHERE NOT EXISTS (SELECT 1 FROM Moods);
                    """;
            stmt.execute(sql);
            stmt.execute(sql_mood);
            System.out.println("Table created");
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    void insert_data(String date,String post, int mood) throws SQLException
    {
        String sql = "INSERT INTO Posts(date, post, mood) VALUES('"+date+"','"+post+"', '"+mood+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Inserted data");
    }

    ObservableList<Post> select_data() throws SQLException
    {
        ObservableList<Post> Posts = FXCollections.observableArrayList();

        String sql = "SELECT *  FROM \"Posts\"";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("ID");
            String date = rs.getString("date");
            String post = rs.getString("post");
            String photo = rs.getString("photo");
            int mood = rs.getInt("mood");
            Posts.add(new Post(id, date,post,photo,mood));
        }
        return Posts;
    }

    void  update_data(Integer id, String name,String phone) throws SQLException {
        String sql = "UPDATE phonebook SET name='"+name+"', phone='"+phone+"' WHERE id='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Updated data");
    }

    void delete_data(Integer id) throws SQLException
    {
        String sql = "DELETE FROM Posts WHERE id='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }
}
