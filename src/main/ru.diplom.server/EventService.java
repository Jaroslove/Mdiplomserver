import entities.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    Connection connection = new Connect().getCon();

    public List<Event> getAllEvent() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Event> list = new ArrayList<Event>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.ALL_EVENTS.getValue());
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

    public boolean insertNewEvent(String name, int idUser) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_EVENT.getValue());
            preparedStatement.setString(1, name);
            preparedStatement.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            preparedStatement.setInt(3, idUser);
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

    public boolean updateEvent(String oldName, String newName) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.UPDATE_EVENT.getValue());
            preparedStatement.setString(2, oldName);
            preparedStatement.setString(1, newName);
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

    public boolean deleteEvent(String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Queries.DELETE_EVENT.getValue());
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
