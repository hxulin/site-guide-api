package cn.huangxulin.site_guide_api.context;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Component
public class ClientKit {

    /**
     * 客户端母版文件
     */
    private static final String CLIENT_FILE_NAME = "client/site-guide.exe";

    /**
     * 客户端文件大小
     */
    private static final int CLIENT_FILE_SIZE = 19456;

    /**
     * 签名信息的起始位置
     */
    private static final int SIGNATURE_OFFSET = 16691;

    /**
     * 签名长度
     */
    public static final int SIGNATURE_LENGTH = 24;

    /**
     * 客户端母版文件的存储空间
     */
    private static final byte[] CLIENT_FILE_BUFFER = new byte[CLIENT_FILE_SIZE];

    /**
     * 初始化, 准备客户端软件
     */
    void prepareClient() throws IOException {
        Resource resource = new ClassPathResource(CLIENT_FILE_NAME);
        InputStream is = resource.getInputStream();
        is.read(CLIENT_FILE_BUFFER, 0, CLIENT_FILE_SIZE);
        is.close();
    }

    public byte[] generate(String signature) {
        byte[] targetBytes = new byte[CLIENT_FILE_SIZE];
        System.arraycopy(CLIENT_FILE_BUFFER, 0, targetBytes, 0, CLIENT_FILE_SIZE);
        byte[] signatureBytes = signature.getBytes();
        System.arraycopy(signatureBytes, 0, targetBytes, SIGNATURE_OFFSET, SIGNATURE_LENGTH);
        return targetBytes;
    }

}
