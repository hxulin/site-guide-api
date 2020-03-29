package cn.huangxulin.site_guide_api.mapper;

import cn.huangxulin.site_guide_api.entity.HotPages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Mapper
public interface HotPagesMapper extends BaseMapper<HotPages> {

    void updateAddSequence(@Param("smallSequence") int smallSequence, @Param("largeSequence") int largeSequence);

    void updateSubSequence(@Param("smallSequence") int smallSequence, @Param("largeSequence") int largeSequence);

    void updateSequence(int sequence);

    /**
     * 删除时维护排序信息
     */
    void updateSequenceWhenDel(int sequence);

}
