package cn.huangxulin.site_guide_api.bean;

/**
 * 功能描述: 系统数据字典常量
 *
 * @author hxulin
 */
public interface Const {
	
    /**
     * 日期格式化类型
     */
	interface DateFormat {

        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

        String DATE_PATTERN = "yyyy-MM-dd";

        String TIME_PATTERN = "HH:mm:ss";

        String MINUTE_PATTEN = "yyyy-MM-dd HH:mm";
	}

    /**
     * 状态
     */
    interface Status {
        int NORMAL = 0;
        int DISABLE = 1;
    }

}
