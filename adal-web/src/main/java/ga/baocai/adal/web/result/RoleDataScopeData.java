package ga.baocai.adal.web.result;

import lombok.Data;

import java.util.List;

@Data
public class RoleDataScopeData {
    private String roleId;
    private Integer dataScopeType;
    private List<String> departIds;
}
