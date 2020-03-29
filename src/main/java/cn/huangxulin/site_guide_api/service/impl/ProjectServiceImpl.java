package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.mapper.ProjectMapper;
import cn.huangxulin.site_guide_api.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Service
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    private ProjectMapper projectMapper;

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public void saveOrUpd(Project project) {
        if (project.getId() != null) {  // 修改
            Project dbProject = this.getById(project.getId());
            this.removeById(project.getId());
            if (dbProject.getSequence() > project.getSequence()) {
                projectMapper.updateAddSequence(project.getSequence(), dbProject.getSequence());
            } else if (dbProject.getSequence() < project.getSequence()) {
                projectMapper.updateSubSequence(dbProject.getSequence(), project.getSequence());
            }
        } else {  // 新增
            projectMapper.updateSequence(project.getSequence());
        }
        this.save(project);
    }

    @Override
    public void delete(Project[] projects) {
        List<Project> projectList = Arrays.asList(projects);
        projectList.sort((a, b) -> Integer.compare(b.getSequence(), a.getSequence()));
        this.removeByIds(projectList.stream().map(Project::getId).collect(Collectors.toList()));
        projectList.forEach(item -> projectMapper.updateSequenceWhenDel(item.getSequence()));
    }
}
