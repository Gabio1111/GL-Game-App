package com.example.gl.response;

import androidx.annotation.NonNull;

import com.example.gl.model.GameModel;

import java.util.List;

public interface IMyCallback
{

   <T> void onSuccess(@NonNull List<T> games);
   <T> void onSuccess(@NonNull T obj);

    void onError(@NonNull Throwable throwable);

}
