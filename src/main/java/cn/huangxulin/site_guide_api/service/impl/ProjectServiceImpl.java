package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.mapper.ProjectMapper;
import cn.huangxulin.site_guide_api.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Service
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
