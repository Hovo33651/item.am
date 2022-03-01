package itemAm.manager;

import itemAm.db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPictureManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();


    public boolean addItemPic(int itemId, int picId) {
        String sql = "INSERT INTO item_pic(item_id,pic_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            statement.setInt(2, picId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> getPicIdsByItemId(int itemId) {
        String sql = "SELECT pic_id FROM item_pic WHERE item_id = ?";
        List<Integer> picIds = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                picIds.add(resultSet.getInt("pic_id"));
            }
            return picIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteImage(int picId) {
        String sql = "DELETE FROM item_pic WHERE pic_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, picId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteItemById(int itemId) {
        String sql = "DELETE FROM item_pic WHERE item_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
