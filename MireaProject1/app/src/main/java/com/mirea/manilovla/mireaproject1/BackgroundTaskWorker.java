package com.mirea.manilovla.mireaproject;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.concurrent.TimeUnit;

public class BackgroundTaskWorker extends Worker {

    static final String TAG = "BackgroundTaskWorker";
    public static final String KEY_INPUT = "input_data";
    public static final String KEY_OUTPUT = "output_data";

    public BackgroundTaskWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start");

        String inputData = getInputData().getString(KEY_INPUT);

        try {
            TimeUnit.SECONDS.sleep(5);

            String result = "Обработано: " + inputData + ". Длина: " + inputData.length();

            androidx.work.Data outputData = new androidx.work.Data.Builder()
                    .putString(KEY_OUTPUT, result)
                    .build();

            Log.d(TAG, "doWork: end with result: " + result);
            return Result.success(outputData);

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d(TAG, "doWork: error");
            return Result.failure();
        }
    }
}