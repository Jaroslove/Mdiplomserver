import entities.Event;
import entities.Location;
import entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 10.02.2017.
 */
public class UserService {

    private final String ALL_USERS_ONE_HOUR = "SELECT * FROM mydbtest.user WHERE date" +
            " >= DATE_SUB(NOW(), INTERVAL 1 hour);";
    private final String ALL_EVENTS = "SELECT * FROM mydbtest.event";
    Connection connection = new Connect().getCon();

    public List<User> getAllUsersForLastHour() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<User>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ALL_USERS_ONE_HOUR);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLocation(
                        new Location(resultSet.getDouble("longitude")
                                , resultSet.getDouble("latitude")));
                user.setDate(resultSet.getTimestamp("date"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Event> getAllEvent() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Event> list = new ArrayList<Event>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ALL_EVENTS);
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getTimestamp("date"));
                event.setIdUser(resultSet.getInt("idUser"));
                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
