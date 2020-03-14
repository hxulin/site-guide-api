package cn.huangxulin.site_guide_api.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 功能描述: MyBatisPlus 自动填充器
 *
 * @author hxulin
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createTime";
    /**
     * 修改时间
     */
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        setFieldValByName(CREATE_TIME, date, metaObject);
        setFieldValByName(UPDATE_TIME, date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }

}
