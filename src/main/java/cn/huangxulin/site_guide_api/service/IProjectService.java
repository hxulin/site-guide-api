package cn.huangxulin.site_guide_api.service;

import cn.huangxulin.site_guide_api.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public interface IProjectService extends IService<Project> {

    /**
     * 新增或修改
     */
    void saveOrUpd(Project project);

    /**
     * 删除
     */
    void delete(Project[] projects);
}
