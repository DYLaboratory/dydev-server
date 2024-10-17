package com.dylabo.dydev.common.components;

import com.dylabo.core.common.utils.ErrorLogUtils;
import com.dylabo.dydev.domain.file.service.dto.FileContentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * AWS S3 관련 컴포넌트
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AWSS3Component {

    @Value("${aws.s3.root-path}")
    public static String S3_ROOT_FILE_PATH;

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * S3 파일 다운로드
     *
     * @param filePath
     * @param fileName
     */
    public byte[] getDownloadS3FileByName(String filePath, String fileName) {
        // 파일 경로 + 이름
        fileName = filePath + fileName;

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

    /**
     * S3 파일 업로드
     *
     * @param uploadFile
     * @param fileContentDto
     * @throws IOException
     */
    public void setUploadS3File(MultipartFile uploadFile, FileContentDto fileContentDto) throws IOException {
        String filePath = fileContentDto.getFilePath()
                + File.separator
                + fileContentDto.getSystemFileName();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();

        // upload
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(uploadFile.getInputStream(), fileContentDto.getFileSize()));
    }

    /**
     * S3 파일 삭제
     *
     * @param fileContentDto
     */
    public void setDeleteS3File(FileContentDto fileContentDto) {
        String filePath = fileContentDto.getFilePath()
                + File.separator
                + fileContentDto.getSystemFileName();

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

}
