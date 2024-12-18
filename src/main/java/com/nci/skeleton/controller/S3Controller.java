package com.nci.skeleton.controller;

import com.nci.skeleton.model.ResponseModel;
import com.nci.skeleton.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/storage")
public class S3Controller {
    private final S3Client s3Client;

    public S3Controller(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getImageInfo() {
        ListBucketsResponse bucketsResponse = s3Client.listBuckets();

        return new ResponseEntity<>(bucketsResponse.buckets().stream()
                .map(bucket -> bucket.name())
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
