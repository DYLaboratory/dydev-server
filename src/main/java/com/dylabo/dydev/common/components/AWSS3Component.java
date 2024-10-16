package com.dylabo.dydev.common.components;

import com.dylabo.core.common.constants.CommonConstants;
import com.dylabo.core.common.utils.ErrorLogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * AWS S3 관련 컴포넌트
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AWSS3Component {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * 파일 다운로드
     *
     * @param filePath
     * @param fileName
     * @param fileExt
     * @throws IOException
     */
    public byte[] getDownloadS3FileByName(String filePath, String fileName, String fileExt) {
        // 파일 경로 + 이름
        fileName = filePath + fileName + CommonConstants.DOT + fileExt;

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try (InputStream is = s3Client.getObject(getObjectRequest);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

            return os.toByteArray();
        } catch (Exception e) {
            ErrorLogUtils.printError(log, e);
            return null;
        }
    }

    public void setUploadS3File(String filePath, String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();


    }

}
