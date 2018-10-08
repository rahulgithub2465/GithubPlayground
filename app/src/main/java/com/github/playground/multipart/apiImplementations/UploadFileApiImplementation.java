package com.github.playground.multipart.apiImplementations;

import com.github.playground.multipart.apiClient.UploadFileClient;
import com.github.playground.utils.Validator;

import java.io.File;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadFileApiImplementation {

    private UploadFileClient uploadFileClient;

    public UploadFileApiImplementation(UploadFileClient uploadFileClient) {
        this.uploadFileClient = uploadFileClient;
    }

    public void uploadFile(String filePath) {
        MultipartBody.Part fileBody = null;
        if (Validator.isValid(filePath)) {
            File uploadFile = new File(filePath);
            String fileMimeType = URLConnection.guessContentTypeFromName(uploadFile.getName());
            RequestBody requestBodyFile = RequestBody.create(MediaType.parse(fileMimeType), uploadFile);
            fileBody = MultipartBody.Part.createFormData("image_param", uploadFile.getName(), requestBodyFile);
        }
        uploadFileClient.uploadFile(fileBody);
    }
}
