package ga.baocai.adal.web.common;

/**
 * <p>
 * 常量池
 * </p>
 */
public interface Consts {
    /**
     * 启用
     */
    Integer ENABLE = 1;
    /**
     * 禁用
     */
    Integer DISABLE = 0;

    /**
     * 一级菜单
     */
    Integer MENU = 0;
    /**
     * 子菜单
     */
    Integer SUB_MENU = 1;

    /**
     * 按钮/链接
     */
    Integer BUTTON = 2;

    /**
     * JWT 在 Redis 中保存的key前缀
     */
    String REDIS_JWT_KEY_PREFIX = "adal:jwt:";

    /**
     * Captcha 在 Redis 中保存的key前缀
     */
    String REDIS_CAPTCHA_PREFIX = "adal:captcha:";
    /**
     * Role在CASBIN中保存的前缀
     */
    String CASBIN_ROLE_KEY_PREFIX = "role:";

    /**
     * DEPART在CASBIN中保存的前缀
     */
    String CASBIN_DEPART_KEY_PREFIX = "depart:";

    /**
     * PERMISSON_TYPE (link/departId)
     */
    String CASBIN_PERMISSON_TYPE_LINK = "link";
    String CASBIN_PERMISSON_TYPE_DATA_SCOPE = "data_scope";

    /**
     * Captcha 有效期
     */
    Integer CAPTCHA_EXPIRATION = 60;

    /**
     * 星号
     */
    String SYMBOL_STAR = "*";

    /**
     * 邮箱符号
     */
    String SYMBOL_EMAIL = "@";

    /**
     * 默认当前页码
     */
    Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 默认每页条数
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 匿名用户 用户名
     */
    String ANONYMOUS_NAME = "匿名用户";

    /**
     * 数据范围类型（1全部数据 2本部门及以下数据 3本部门数据 4仅本人数据 5自定义数据）
     */
    Integer DATA_SCOPE_ALL = 1;
    Integer DATA_SCOPE_DEPART_AND_SUB = 2;
    Integer DATA_SCOPE_DEPART = 3;
    Integer DATA_SCOPE_SELF = 4;
    Integer DATA_SCOPE_CUSTOM = 5;


}
