package cn.huangxulin.site_guide_api.query;

import cn.huangxulin.site_guide_api.entity.Project;
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
public class ProjectQueryObject extends QueryObject<Project> {

    // 项目名称
    private String name;

    // 项目类型
    private String type;

    @Override
    protected void customizedQuery(LambdaQueryWrapper<Project> wrapper) {
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(Project::getName, name);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq(Project::getType, Integer.valueOf(type));
        }
        wrapper.orderByAsc(Project::getSequence);
    }
}
