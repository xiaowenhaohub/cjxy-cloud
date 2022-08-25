package com.changji.cloud.file.utils;

import com.changji.cloud.common.core.utils.DateUtils;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.common.core.utils.uuid.Seq;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:23 2022/8/26
 * @ Modified By：
 */
public class FileUploadUtils {


    /**
     * 重命名文件
     * @param file
     * @return
     */
    public static String extractFileName(MultipartFile file) {
        return StringUtils.format("{}/{}_{}.{}", DateUtils.datePath() , FilenameUtils.getBaseName(file.getOriginalFilename())
                , Seq.getId(Seq.uploadSeqType) , FilenameUtils.getExtension(file.getOriginalFilename()));
    }

}
