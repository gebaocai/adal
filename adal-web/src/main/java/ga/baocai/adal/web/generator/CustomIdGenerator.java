package ga.baocai.adal.web.generator;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    @Autowired
    private Snowflake snowflake;

    @Override
    public Number nextId(Object entity) {
        long nextId = snowflake.nextId();
        log.debug("生成nextId:{}", nextId);
        return nextId;
    }
}
