package cn.huangxulin.site_guide_api.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述: 高级查询对象
 *
 * @author hxulin
 */
@Getter
@Setter
public class QueryObject<T> {

    /**
     * 限制分页大小
     */
    private static final int MAX_PAGE_SIZE = 100;

    /**
     * 当前页数
     */
    protected int currentPage = 1;

    /**
     * 分页大小
     */
    protected int pageSize = 20;

    public Page<T> getPageInfo() {
        return new Page<>(currentPage, pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize);
    }

    public LambdaQueryWrapper<T> getWrapper() {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        customizedQuery(wrapper);
        return wrapper;
    }

    /**
     * 暴露给子类，在该方法中自定义查询条件
     */
    protected void customizedQuery(LambdaQueryWrapper<T> wrapper) {

    }

}
