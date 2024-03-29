package ga.baocai.adal.web.config;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 忽略配置
 * </p>
 */
@Data
public class IgnoreConfig {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private List<String> pattern = CollUtil.newArrayList();

    /**
     * 需要忽略的 GET 请求
     */
    private List<String> get = CollUtil.newArrayList();

    /**
     * 需要忽略的 POST 请求
     */
    private List<String> post = CollUtil.newArrayList();

    /**
     * 需要忽略的 DELETE 请求
     */
    private List<String> delete = CollUtil.newArrayList();

    /**
     * 需要忽略的 PUT 请求
     */
    private List<String> put = CollUtil.newArrayList();

    /**
     * 需要忽略的 HEAD 请求
     */
    private List<String> head = CollUtil.newArrayList();

    /**
     * 需要忽略的 PATCH 请求
     */
    private List<String> patch = CollUtil.newArrayList();

    /**
     * 需要忽略的 OPTIONS 请求
     */
    private List<String> options = CollUtil.newArrayList();

    /**
     * 需要忽略的 TRACE 请求
     */
    private List<String> trace = CollUtil.newArrayList();
}
