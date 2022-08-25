package com.changji.cloud.file;

import com.changji.cloud.common.core.utils.DateUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.core.utils.uuid.Seq;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:05 2022/8/26
 * @ Modified By：
 */

@SpringBootTest
public class FileTest {

    @Test
    public void test01() {

        String fileName = StringUtils.format("{}/{}_{}.{}", DateUtils.datePath() ,FilenameUtils.getBaseName("xiaoenhao.jpg")
                ,Seq.getId(Seq.uploadSeqType) , FilenameUtils.getExtension("xiaoenhao.jpg"));
        System.out.println(fileName);

    }
}
