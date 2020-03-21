package cn.huangxulin.site_guide_api.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 功能描述: Spring 容器启动事件监听
 *
 * @author hxulin
 */
@Component
public class AppStartedListener implements CommandLineRunner {

    private ClientKit clientKit;

    @Autowired
    public void setClientKit(ClientKit clientKit) {
        this.clientKit = clientKit;
    }

    @Override
    public void run(String... args) throws Exception {
        clientKit.prepareClient();
    }
}
