import com.sun.xml.internal.ws.api.ha.StickyFeature;
import entities.Event;
import entities.Location;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserService {


    Connection connection = new Connect().getCon();

    public List<User> getAllUsersForLastHour() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<User>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.ALL_USERS_ONE_HOUR.getValue());
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

    public boolean updateUser(String name, double longitude, double latitude) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.UPDATE_USER.getValue());
            preparedStatement.setDouble(1, longitude);
            preparedStatement.setDouble(2, latitude);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean insertNewUser(String name, Double longitude, Double latitude) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_USER.getValue());
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, longitude);
            preparedStatement.setDouble(3, latitude);
            preparedStatement.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean deleteUser(String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.DELETE_USER.getValue());
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void shotDown() {
        try {
            if (connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
