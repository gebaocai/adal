package ga.baocai.adal.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义配置
 * </p>
 */
@ConfigurationProperties(prefix = "security.config")
@Component
@Data
public class RSASecurityConfig {
    private String privateKey;
    private String publicKey;
}
