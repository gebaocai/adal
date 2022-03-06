package ga.baocai.adal.app.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import ga.baocai.adal.app.common.CommonResponse;
import ga.baocai.adal.app.config.RSASecurityConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

    /**
     * API解密
     */
    public static String decrypt(String reqBody, RSASecurityConfig rsaSecurityConfig){
        try {
            RSA rsa = new RSA(rsaSecurityConfig.getPrivateKey(), rsaSecurityConfig.getPublicKey());
            byte[] aByte = HexUtil.decodeHex(reqBody);
            byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
            return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        } catch (Throwable e) {
            //输出到日志文件中
            log.error(e.toString());
            throw new RuntimeException("ApiSecurityUtil.decrypt：解密异常！");
        }
    }

    /**
     * API加密
     */
    public static Object encrypt(Object object, RSASecurityConfig rsaSecurityConfig){
        try {
            RSA rsa = new RSA(rsaSecurityConfig.getPrivateKey(), rsaSecurityConfig.getPublicKey());
            if (object instanceof CommonResponse) {
                CommonResponse cro = (CommonResponse)object;
                Object data = cro.getData();
                if (null != data) {
                    String dataStr = JSONUtil.toJsonStr(data);
                    byte[] encrypt = rsa.encrypt(StrUtil.bytes(dataStr, CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
                    String encryptData = StrUtil.str(encrypt, CharsetUtil.CHARSET_UTF_8);
                    cro.setData(encryptData);
                    return cro;
                }
            }
            return object;
        } catch (Throwable e) {
            //输出到日志文件中
            log.error(e.toString());
            throw new RuntimeException("ApiSecurityUtil.encrypt：加密异常！");
        }
    }
}
