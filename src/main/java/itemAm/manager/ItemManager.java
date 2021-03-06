package itemAm.manager;

import itemAm.db.DBConnectionProvider;
import itemAm.model.Category;
import itemAm.model.Item;
import itemAm.model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserManager userManager = new UserManager();
    private final CategoryManager categoryManager = new CategoryManager();
    private final ItemPicRelatTableManager itemPicRelatTableManager = new ItemPicRelatTableManager();
    private final PictureManager pictureManager = new PictureManager();


    public int addItem(Item item) {
        String sql = "INSERT INTO item(title,description,price,currency,category_id,user_id) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getCurrency());
            statement.setInt(5, item.getCategory().getId());
            statement.setInt(6, item.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
            return item.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public List<Item> getLastItems() {
        String sql = "SELECT * FROM item ORDER BY id DESC LIMIT 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Item> getLastItemsByCategory(Category category) {
        String sql = "SELECT * FROM item WHERE category_id = ? ORDER BY id DESC LIMIT 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Item getItemsFromResultSet(ResultSet resultSet) {
        try {
            return Item.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .price(resultSet.getDouble(4))
                    .currency(resultSet.getString(5))
                    .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                    .user(userManager.getUserById(resultSet.getInt(7)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Item> getCurrentUserAds(int userId) {
        String sql = "SELECT * FROM item WHERE user_id = ?";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteItemById(int itemId) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            if (itemPicRelatTableManager.deleteItemById(itemId)) {
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Item getItemById(int itemId) {
        String sql = "SELECT * FROM item WHERE id = ?";
        List<Integer> picIds = itemPicRelatTableManager.getPicIdsByItemId(itemId);
        List<Picture> picturesById = pictureManager.getPicsById(picIds);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Item item = getItemsFromResultSet(resultSet);
                assert item != null;
                item.setPictures(picturesById);
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
