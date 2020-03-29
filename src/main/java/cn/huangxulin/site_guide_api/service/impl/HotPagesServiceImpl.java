package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.entity.HotPages;
import cn.huangxulin.site_guide_api.mapper.HotPagesMapper;
import cn.huangxulin.site_guide_api.service.IHotPagesService;
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
public class HotPagesServiceImpl extends ServiceImpl<HotPagesMapper, HotPages> implements IHotPagesService {

    private HotPagesMapper hotPagesMapper;

    @Autowired
    public void setHotPagesMapper(HotPagesMapper hotPagesMapper) {
        this.hotPagesMapper = hotPagesMapper;
    }

    @Override
    public void saveOrUpd(HotPages hotPages) {
        if (hotPages.getId() != null) {  // 修改
            HotPages dbHotPages = this.getById(hotPages.getId());
            this.removeById(hotPages.getId());
            if (dbHotPages.getSequence() > hotPages.getSequence()) {
                hotPagesMapper.updateAddSequence(hotPages.getSequence(), dbHotPages.getSequence());
            } else if (dbHotPages.getSequence() < hotPages.getSequence()) {
                hotPagesMapper.updateSubSequence(dbHotPages.getSequence(), hotPages.getSequence());
            }
        } else {  // 新增
            hotPagesMapper.updateSequence(hotPages.getSequence());
        }
        this.save(hotPages);
    }

    @Override
    public void delete(HotPages[] hotPages) {
        List<HotPages> hotPagesList = Arrays.asList(hotPages);
        hotPagesList.sort((a, b) -> Integer.compare(b.getSequence(), a.getSequence()));
        this.removeByIds(hotPagesList.stream().map(HotPages::getId).collect(Collectors.toList()));
        hotPagesList.forEach(item -> hotPagesMapper.updateSequenceWhenDel(item.getSequence()));
    }

}
