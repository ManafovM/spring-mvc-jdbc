package app;

import dao.UsersDao;
import dao.UsersDaoJdbcTemplateImpl;
import model.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("H110mprovd");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");

        UsersDao usersDao = new UsersDaoJdbcTemplateImpl(dataSource);
        List<User> users = usersDao.findAll();
        System.out.println(users);
    }
}
