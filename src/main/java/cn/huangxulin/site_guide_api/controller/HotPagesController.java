package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.HotPages;
import cn.huangxulin.site_guide_api.query.HotPagesQueryObject;
import cn.huangxulin.site_guide_api.service.IHotPagesService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/hot_pages")
@Validated
public class HotPagesController {

    private IHotPagesService hotPagesService;

    @Autowired
    public void setHotPagesService(IHotPagesService hotPagesService) {
        this.hotPagesService = hotPagesService;
    }

    @GetMapping("/max_sequence")
    public ApiResponse getMaxSequence() {
        int count = hotPagesService.count();
        return ApiResponse.success().addDataItem("maxSequence", count + 1);
    }

    @PostMapping("/save")
    public ApiResponse saveHotPages(@RequestBody HotPages hotPages) {
        hotPagesService.saveOrUpd(hotPages);
        return ApiResponse.successOfMessage("热点页面保存成功");
    }

    @PostMapping("/list")
    public ApiResponse listHotPages(@RequestBody HotPagesQueryObject qo) {
        Page<HotPages> page = hotPagesService.page(qo.getPageInfo(), qo.getWrapper());
        return ApiResponse.successOfData(page);
    }

    @PostMapping("/del")
    public ApiResponse delHotPages(@RequestBody HotPages[] hotPages) {
        hotPagesService.delete(hotPages);
        return ApiResponse.successOfMessage("删除成功");
    }

}
