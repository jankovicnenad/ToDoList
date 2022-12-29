package com.example.ToDoList.service;

import com.example.ToDoList.DAO.ImageRepository;
import com.example.ToDoList.DTO.ImageDTO;
import com.example.ToDoList.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.storage.*;
import org.springframework.core.env.Environment;
import org.apache.commons.io.IOUtils;
import com.example.ToDoList.rest.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService{

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final Environment environment;
    private StorageOptions storageOptions;
    private ImageRepository imageRepository;
    private String bucketName;
    private String projectId;

    private final MapperDto mapperDto;

    private final String storageBaseUrl = "storage.cloud.google.com/tasksimage.appspot.com/";
    public ImageServiceImpl (ImageRepository imgRepo, Environment environment, MapperDto mapperDto)
    {
        imageRepository = imgRepo;
        this.environment = environment;
        this.mapperDto = mapperDto;
    }


    @Override
    public List<ImageDTO> getAllImage() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> iDTO = new ArrayList<>();
        for(Image i : images)
        {
           ImageDTO imageDTOS = mapperDto.convertImageToImageDto(i);
           iDTO.add(imageDTOS);
        }
       return iDTO;

    }

//    @Override
//    public ImageDTO findById(int id) {
//        Optional<Image> image = Optional.ofNullable(imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id is not found - " + id)));
//        ImageDTO imageDTO = mapperDto.convertImageToImageDto(image);
//        return imageDTO;
//    }
    @PostConstruct
    private void initializeFirebase() throws IOException {
        bucketName = "tasksimage.appspot.com";
        projectId = "tasksimage";

        InputStream firebaseCredential = createFirebaseCredential();
        this.storageOptions = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(firebaseCredential)).build();

    }
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        log.debug("bucket name====" + bucketName);
        File file = convertMultiPartToFile(multipartFile);
        Path filePath = file.toPath();
        String objectName = generateFileName(multipartFile);

        Storage storage = storageOptions.getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
        file.delete();
        log.info("File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
        return storageBaseUrl+objectName;
    }
    private InputStream createFirebaseCredential() throws JsonProcessingException {
        String privateKeyTemp = "-----BEGIN PRIVATE KEY-----\nMIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQDxE6M/BU9MlNSW\nRHyobkn+qN9fgsbvcw/wQzaZmRCKIB4pDCMBO6Xw7hvBT6EscFwh+CaZTMyADgYy\nSj1TQJ5O6nvH5L6pjZkuOVFbhhoQr4tDqbYm00RO32RPRxlmDi2lKRteAgj/0YCk\n7ubcgXaiTZaXKFb+0MmAlzJIrTDjNuL5D6P7T/BKphmJRE4hYOfFOv6LXYv6zviZ\n5ipke2H8OO7VaFoPR1y8qJUvMmuC2pa4OfyEi5PVReVgazLArSfKzrTB/882YE3y\nsoCYwHfJ32RFHA4N9RUub9AfO5MNltmns6xWNtOPTDz77mUH/Hhscn3PnlBh1vwk\nF8hFMKyTAgMBAAECggEAX/EyzMfuMUoh+NO00Mtjw2etzjMbvPwL4dC+EA7smCwE\nFM3xuHHmrqX0gdCREkB9Sj+aDPSVhnkOWkFVeqaC2mFTddBBWPUze0Pwbv5FrVJP\nmFQYXAdEbidVon9nSkhmPg7IB3fD4RudmQZC9orHjfxW7D1vseWh6/1AU9ApXqMZ\noKTLY/o7wDupZMU0UUs3Kz9zDYDzo8QiyS6TaNwFhCjWj3B6ULb98AykiJ7EAYux\nJ7T7cK2Ci+B1je4hvg8nnXn0zfmZGEk1vDDCDkUcKinc88jpWe0dR/CH14kKZB6s\nfdJoopux4ywpoZLR8UujuaD9UMhTKtjUDVmgUBl7wQKBgQD5Bzc0LllnOpjenNDU\nI7FQXKviQGsq6INKFjnau4NdRI0X3q+j1pdVmGX3nGArUmqWltq/cErgNvKHJkTf\nSbXIXFrsfAn9iDxuKUH+GK6AIi4XSoSb5bZbtqd17qt5WX6RvTAE0g+6//CFsKiZ\nxCgPGxvd1dCZ5wJKEqCt/S0T8QKBgQD3028Pd3nxphy44z0R7OMJxURu/HvrC+X4\noDPLW1EJ4ORAGfewQwAYCH9EiYsBgZy2MEUofXtibuOFCFm4vG3xjc5kumxlUlNm\nLrU2t4O9rIqABXfZdrkHlicXOtIoTBAb0kML1lKDdWgJNtpDcOzDQHMATexgQGkr\ny2hTBA48wwJ/S9/m8f8tPkacTTd6aSh23gVeWZFHFcvCNNCQ0BRR8hjw5FT1LvYz\nJeFJMCh1JL33u+o6vBl6ttYHDyaZ1W6f9GwvR96DLLxVrTrk4IakpGXFpVMPFHDp\nHwiH/Wa62D5sUftSpiVapZ9VqWYp+K/LhM69rtl2tW2tRORoEJsTIQKBgQDQbzPp\nfkNBysQ9fGHQwbvya8ey0QgoGEnDYnotfxAZjtxqTWzVAoUBsaPYsRYInkp/sPl5\npJAxqbISIYPUrCaMEwiUD3c7gJJETuR6sL5MuOGD6xVyATh9+PvHveEjo1WpEJ7z\nRb8Aca6ekVPhhQic74fJqcA08/eArgOATtjqoQKBgDU1L7wPV7ECSivczYIsvAum\ns2cw6PwsOp/xQSbQP4atEnAtNpyEuusfODi5k6V79noILzmJjVaoUmfCANn3fUI4\na+h226qjmoYsOSSCeImS/DyMu9IhxbADshebjO5fo2sQ00dKMTPde90M5AD7c60R\ndWqdRBKPGJg51Usb5bb2\n-----END PRIVATE KEY-----\n";

        String privateKey = privateKeyTemp.replace("\\n", "\n");

        FirebaseCredential firebaseCredential = new FirebaseCredential();
        firebaseCredential.setType("service_account");
        firebaseCredential.setProject_id("tasksimage");
        firebaseCredential.setPrivate_key_id("9ab1bea573ff883aa7117d503a49246360a7ff24");
        firebaseCredential.setPrivate_key(privateKey);
        firebaseCredential.setClient_email("firebase-adminsdk-abwfg@tasksimage.iam.gserviceaccount.com");
        firebaseCredential.setClient_id("100432933306289915434");
        firebaseCredential.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
        firebaseCredential.setToken_uri("https://oauth2.googleapis.com/token");
        firebaseCredential.setAuth_provider_x509_cert_uri("https://www.googleapis.com/oauth2/v1/certs");
        firebaseCredential.setClient_x509_cert_uri("https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-abwfg%40tasksimage.iam.gserviceaccount.com");

        //serialize with Jackson
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        //convert jsonString string to InputStream using Apache Commons
        return IOUtils.toInputStream(jsonString);
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }


    @Override
    public void save(ImageDTO imageDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, ImageDTO imageDTO) {

    }
}
