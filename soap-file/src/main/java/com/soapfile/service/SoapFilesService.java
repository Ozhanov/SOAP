package com.soapfile.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import soap_files.soap_files_web_service.FilesHashCodes;
import soap_files.soap_files_web_service.GetFilesRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SoapFilesService {

    public FilesHashCodes getFiles(GetFilesRequest request){
        FilesHashCodes fileHash = new FilesHashCodes();
        Resource resource = new ClassPathResource(request.getName());
        try {
            InputStream input = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = null;

        try {
            file = resource.getFile();
            fileHash.setName(file.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

//Use MD5 algorithm
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//Get the checksum
        String checksum = null;
        try {
             checksum = getFileChecksum(md5Digest, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileHash.setHashCode(checksum);

        return fileHash;
    }

    private String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        fis.close();

        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
