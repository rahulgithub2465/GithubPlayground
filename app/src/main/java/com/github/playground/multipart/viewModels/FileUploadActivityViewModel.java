package com.github.playground.multipart.viewModels;

import android.support.v7.app.AppCompatActivity;

import com.github.playground.base.BaseActivityViewModel;
import com.github.playground.multipart.apiImplementations.UploadFileApiImplementation;

import javax.inject.Inject;

public class FileUploadActivityViewModel extends BaseActivityViewModel {

    @Inject
    UploadFileApiImplementation uploadFileApiImplementation;
    private FileUploadActivityListener listener;

    public FileUploadActivityViewModel(AppCompatActivity activity, FileUploadActivityListener listener) {
        super(activity);
        component.injectFileUploadActivityViewModel(this);
        this.listener = listener;
    }

    public void upload() {

    }

    public interface FileUploadActivityListener {

        void onUploadClicked();

        void showToast(String message);

    }
}
