package com.dylabo.dydev.domain.file.service.impl;

import com.dylabo.dydev.common.components.AWSS3Component;
import com.dylabo.dydev.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AWSS3Component awsS3Component;

    public byte[] getDownloadFile() {
        return awsS3Component.getDownloadS3FileByName("dylabo/", "증명사진", "jpg");
    }

}
