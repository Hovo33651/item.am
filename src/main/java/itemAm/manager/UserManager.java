package itemAm.manager;

import itemAm.db.DBConnectionProvider;
import itemAm.model.User;

import java.sql.*;

public class UserManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();


    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        return getUser(email, sql);
    }

    public User getUserByNickname(String nickname) {
        String sql = "SELECT * FROM user WHERE nickname = ?";
        return getUser(nickname, sql);
    }

    private User getUser(String nickname, String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean addUser(User user) {
        String sql = "INSERT INTO user(name,surname,email,nickname,password) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            if (user.getNickname() != null) {
                statement.setString(4, user.getNickname());
            }
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .nickname((resultSet.getString(5))!=null?resultSet.getString(5):null)
                    .password(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
