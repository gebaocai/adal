package ga.baocai.adal.web.playload;

import lombok.Data;

@Data
public class ChangePW {
    private String id;
    private String curPassword;
    private String newPassword;
    private String rePassword;
}
