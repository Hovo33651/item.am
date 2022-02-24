package itemAm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private int id;
    private String title;
    private String description;
    private double price;
    private String currency;
    private Category category;
    private User user;
    private String picUrl;
}
