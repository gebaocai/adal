package ga.baocai.app.crypto;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import ga.baocai.app.AdalAppApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;


/**
 * <p>
 * UserDao 测试
 * </p>
 */
@Slf4j
public class AsymmetricCryptoTest extends AdalAppApplicationTests {

    @Test
    public void testRSA() {

        RSA rsa = new RSA();

        //获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
        //获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        //Junit单元测试
        Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

        //Junit单元测试
        Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
    }

    @Test
    public void testNewRSA() {

        KeyPair pair = SecureUtil.generateKeyPair("RSA");
//        pair.getPrivate();
//        pair.getPublic();

        log.info("privateKey:"+Base64.encode(pair.getPrivate().getEncoded()));
        log.info("publicKey:"+Base64.encode(pair.getPublic().getEncoded()));
    }
}