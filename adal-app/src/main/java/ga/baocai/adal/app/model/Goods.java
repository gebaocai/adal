package ga.baocai.adal.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goods {
    private String name;
    private String price;
    private String number;

}
