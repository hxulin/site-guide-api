package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.entity.ClientConf;
import cn.huangxulin.site_guide_api.mapper.ClientConfMapper;
import cn.huangxulin.site_guide_api.service.IClientConfService;
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
public class ClientConfServiceImpl  extends ServiceImpl<ClientConfMapper, ClientConf> implements IClientConfService {

}
