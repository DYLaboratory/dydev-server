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
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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
        String filePath = generateFilePath(fileContentDto);

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    /**
     * S3 파일 다건 삭제
     * 
     * @param fileContentDtoList
     */
    public void setDeleteS3Files(List<FileContentDto> fileContentDtoList) {
        Delete delete = Delete.builder()
                .objects(fileContentDtoList.stream()
                        .map(f -> ObjectIdentifier.builder()
                                .key(generateFilePath(f))
                                .build())
                        .collect(Collectors.toList()))
                .build();

        DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(delete)
                .build();

        s3Client.deleteObjects(deleteObjectsRequest);
    }

    private String generateFilePath(FileContentDto fileContentDto) {
        return fileContentDto.getFilePath()
                + File.separator
                + fileContentDto.getSystemFileName();
    }

}
