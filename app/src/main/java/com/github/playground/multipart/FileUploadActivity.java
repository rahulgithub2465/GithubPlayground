package com.github.playground.multipart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.playground.R;
import com.github.playground.base.BaseActivity;
import com.github.playground.multipart.viewModels.FileUploadActivityViewModel;

public class FileUploadActivity extends BaseActivity implements FileUploadActivityViewModel.FileUploadActivityListener {

    private FileUploadActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);
        viewModel = new FileUploadActivityViewModel(this, this);
    }

    @Override
    public void onUploadClicked() {

    }

    @Override
    public void showToast(String message) {

    }
}
