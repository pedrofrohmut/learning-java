package com.pedrofrohmut.content_calendar;

//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pedrofrohmut.content_calendar.models.Content;
import com.pedrofrohmut.content_calendar.models.ContentType;
import com.pedrofrohmut.content_calendar.models.Status;
import com.pedrofrohmut.content_calendar.repositories.ContentRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // var jdbcUrl = "jdbc://postgres://localhost:5432/postgres";
        // var sql = "SELECT * FROM runs";
        //
        // try (var con = DriverManager.getConnection(jdbcUrl, "postgres", "password"))
        // {
        // if (! con.isValid(0)) {
        // System.out.println("Unable to connect to the database");
        // System.exit(0);
        // }
        //
        // var stm = con.prepareStatement(sql);
        // var rs = stm.executeQuery();
        //
        // while (rs.next()) {
        // var title = rs.getString("title");
        // var miles = rs.getString("miles");
        // System.out.println(title + " " + miles);
        // }
        // } catch (SQLException e) {
        // System.err.println("Error to on querying the database: " + e.getMessage());
        // throw new RuntimeException(e);
        // }

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ContentRepository repo) {
        return args -> {
            // Insert some data into the database
            var c1 = new Content(null, "Post One", "Post One Description", Status.IDEA, ContentType.ARTICLE, LocalDateTime.now(), null, "");
            repo.save(c1);

            //var c2 = new Content(null, "Video One", "Video One Description", Status.COMPLETED, ContentType.VIDEO, LocalDateTime.now(), null, "");
            //repo.save(c2);
        };
    }

}
