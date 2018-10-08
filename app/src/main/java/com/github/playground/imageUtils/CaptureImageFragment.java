package com.github.playground.imageUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.playground.PlaygroundApp;
import com.github.playground.R;
import com.github.playground.utils.ToastUtils;
import com.github.playground.utils.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Prashant Kashetti on 18/7/17.
 */

public class CaptureImageFragment extends Fragment {
    private static final String INTENT_EXTRA_FILE_NAME = "fileName";
    private static final int PICK_IMAGE_REQUEST_CODE = 100;
    private CaptureImageListener listener;
    private File finalFile;

    public static CaptureImageFragment getInstance(String fileName) {
        CaptureImageFragment fragment = new CaptureImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_EXTRA_FILE_NAME, fileName);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (getParentFragment() != null)
                listener = (CaptureImageListener) getParentFragment();
            else
                listener = (CaptureImageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + CaptureImageListener.class.getName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String fileName = getArguments().getString(INTENT_EXTRA_FILE_NAME);
        captureImage(fileName);
    }

    public void captureImage(String fileName) {
        List<Intent> pickFileIntents = new ArrayList<Intent>();


        File storagePath = new File(PlaygroundApp.path);
        if (!storagePath.exists()) {
            if (!storagePath.mkdirs()) {
                ToastUtils.show(getActivity(), getString(R.string.storage_full));
                return;
            }
        }
        finalFile = new File(storagePath, fileName);

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra("crop", "true");
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(finalFile));

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(finalFile));

//        Intent filePickerIntent = new Intent(getContext(), FilePickerActivity.class);
//        pickFileIntents.add(galleryIntent);
//        pickFileIntents.add(cameraIntent);
//        pickFileIntents.add(filePickerIntent);

//        Intent chooserIntent = Intent.createChooser(pickFileIntents.remove(0), getString(R.string.pick_file));
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, pickFileIntents.toArray(new Parcelable[]{}));
//
//        startActivityForResult(chooserIntent, PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        Uri originalUri;
        if (requestCode == PICK_IMAGE_REQUEST_CODE) {
            if (data != null) {
                originalUri = data.getData();
                if (originalUri != null) {
                    String path = GetPathUtils.getPath(getContext(), originalUri);
                    if (!Validator.isValid(path))
                        path = getFilePathFromInputStreamUri(originalUri);
                    if (!finalFile.getAbsolutePath().equals(path)) {
                        try {
                            if (path != null)
                                copy(new File(path), finalFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    compressImage();
                } else {
                    String pdfPath = "";//data.getStringExtra(FilePickerActivity.INTENT_EXTRA_PDF_PATH);
                    if (Validator.isValid(pdfPath)) {
                        try {
                            File originalFile = new File(pdfPath);
                            File newFile = new File(PlaygroundApp.path, finalFile.getName().split("\\.")[0] + ".pdf");
                            copy(originalFile, newFile);
                            listener.onImageSelected(newFile.getAbsolutePath(), true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        compressImage();
                    }
                }
            } else {
                compressImage();
            }
        }
    }

    public String getFilePathFromInputStreamUri(Uri uri) {
        InputStream inputStream = null;
        String filePath = null;

        if (uri.getAuthority() != null) {
            try {
                inputStream = getActivity().getContentResolver().openInputStream(uri); // context needed
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                // log
            } catch (IOException e) {
                // log
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;
    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        File targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }

    private File createTemporalFile() {
        return new File(getActivity().getExternalCacheDir(), "tempFile.jpg"); // context needed
    }

    public void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * Compressing output image
     */
    private void compressImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = ImageCompressionUtils.compressImage(finalFile.getAbsolutePath());
                if (result) {
                    final String path = finalFile.getAbsolutePath();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onImageSelected(path, false);
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.show(getContext(), getString(R.string.capture_again));
                        }
                    });
                }
            }
        }).start();
    }

    public interface CaptureImageListener {
        void onImageSelected(String path, boolean isPdfFile);
    }
}
