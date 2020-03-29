package cn.huangxulin.site_guide_api.service;

import cn.huangxulin.site_guide_api.entity.HotPages;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public interface IHotPagesService extends IService<HotPages> {

    /**
     * 新增或修改
     */
    void saveOrUpd(HotPages hotPages);

    /**
     * 删除
     */
    void delete(HotPages[] hotPages);

}
