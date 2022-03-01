package itemAm.manager;

import com.mysql.cj.jdbc.CallableStatementWrapper;
import itemAm.db.DBConnectionProvider;
import itemAm.model.Item;
import itemAm.model.Picture;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final ItemPictureManager itemPictureManager = new ItemPictureManager();


    public int addPic(Picture picture) {
        String sql = "INSERT INTO picture(url) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, picture.getPicUrl());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                picture.setId(resultSet.getInt(1));
            }
            return picture.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteImageStepTwo(int picId) {
        String sql = "DELETE FROM picture WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, picId);
            if (itemPictureManager.deleteImage(picId)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Picture> getPicturesById(List<Integer> picIds) {
        List<Picture> picUrls = new ArrayList<>();
        try {
            for (Integer picId : picIds) {
                String sql = "SELECT * FROM picture WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, picId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    picUrls.add(getPicFromResultSet(resultSet));
                }
            }
            return picUrls;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Picture getPicFromResultSet(ResultSet resultSet) {
        try {
            return Picture.builder()
                    .id(resultSet.getInt("id"))
                    .picUrl(resultSet.getString("url"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
