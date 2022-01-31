package ga.baocai.adal.web.model;

import ga.baocai.adal.web.entity.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class PermissionData {
    private List<MenuData> menuData;
    private List<String> perms;

    @Data
    public static class MenuData {
        private String id;
        private String path;
        private String name;
        private String component;
        private String redirect;
        private String icon;
        private List<MenuData> routes;

        public MenuData(SysPermission permission) {
            this.id = permission.getId();
            this.component = permission.getComponent();
            this.icon = permission.getIcon();
            this.name = permission.getName();
            this.redirect = permission.getRedirect();
            this.path = permission.getUrl();
        }
    }
}
