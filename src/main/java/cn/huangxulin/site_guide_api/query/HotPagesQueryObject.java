package cn.huangxulin.site_guide_api.query;

import cn.huangxulin.site_guide_api.entity.HotPages;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Getter
@Setter
public class HotPagesQueryObject extends QueryObject<HotPages> {

    // 关键字
    private String keyword;

    // 项目类型
    private String type;

    @Override
    protected void customizedQuery(LambdaQueryWrapper<HotPages> wrapper) {
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(HotPages::getName, keyword).or().like(HotPages::getRemark, keyword);
        }
        wrapper.orderByAsc(HotPages::getSequence);
    }
}
