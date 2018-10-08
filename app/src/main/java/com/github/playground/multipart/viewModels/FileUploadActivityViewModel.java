package com.github.playground.multipart.viewModels;

public class FileUploadActivityViewModel {

    private FileUploadActivityListener listener;

    public FileUploadActivityViewModel(FileUploadActivityListener listener) {
        this.listener = listener;
    }

    public void upload() {

    }

    public interface FileUploadActivityListener {

        void onUploadClicked();

        void showToast(String message);

    }
}
