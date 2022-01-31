package ga.baocai.adal.web.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import ga.baocai.adal.web.generator.CustomIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@Slf4j
@MapperScan("ga.baocai.adal.web.mapper")
public class MybatisPlusConfig {
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//        // paginationInterceptor.setOverflow(false);
//        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        // paginationInterceptor.setLimit(500);
//        // 开启 count 的 join 优化,只针对部分 left join
//        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//
////        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
////        dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2) {
////            {
////                put("adal_goods", (metaObject, sql, tableName) -> {
////                    // metaObject 可以获取传入参数，这里实现你自己的动态规则
////                    String today = DateUtil.format(DateUtil.date(), "yyyyMMdd");
////                    log.debug("dynamic table name {}, metaObject: {}, sql: {}", tableName, metaObject, sql);
////                    return tableName + "_" + today;
////                });
////            }
//////        {
//////            put("adal_user", (metaObject, sql, tableName) -> {
//////                // metaObject 可以获取传入参数，这里实现你自己的动态规则
//////                String today= DateUtil.format(DateUtil.date(), "yyyyMMdd");
//////                log.debug("dynamic table name {}, metaObject: {}, sql: {}", tableName, metaObject, sql);
//////                return tableName+"_"+today;
//////            });
//////        }
////        });
////        paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
//
//
//        return paginationInterceptor;
//    }

    @Bean
    public IdentifierGenerator idGenerator() {
        return new CustomIdGenerator();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
}
