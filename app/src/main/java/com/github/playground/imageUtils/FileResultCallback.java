package com.github.playground.imageUtils;

import java.util.List;

public interface FileResultCallback<T> {
    void onResultCallback(List<T> files);
  }