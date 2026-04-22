package com.mirea.manilovla.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import com.mirea.manilovla.mireaproject.databinding.FragmentBackgroundTaskBinding;

public class BackgroundTaskFragment extends Fragment {

    private FragmentBackgroundTaskBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBackgroundTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = binding.editTextInput.getText().toString();

                if (inputText.isEmpty()) {
                    Toast.makeText(getActivity(), "Введите данные", Toast.LENGTH_SHORT).show();
                    return;
                }

                startBackgroundTask(inputText);
            }
        });
    }

    private void startBackgroundTask(String inputData) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.textViewResult.setText("Выполнение фоновой задачи...");

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data inputDataObj = new Data.Builder()
                .putString(BackgroundTaskWorker.KEY_INPUT, inputData)
                .build();

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(BackgroundTaskWorker.class)
                .setConstraints(constraints)
                .setInputData(inputDataObj)
                .build();

        WorkManager.getInstance(requireContext()).enqueue(workRequest);

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), workInfo -> {
                    if (workInfo != null && workInfo.getState().isFinished()) {
                        binding.progressBar.setVisibility(View.GONE);

                        if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            String result = workInfo.getOutputData().getString(BackgroundTaskWorker.KEY_OUTPUT);
                            binding.textViewResult.setText("Результат: " + result);
                            Toast.makeText(getActivity(), "Задача выполнена", Toast.LENGTH_SHORT).show();
                        } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                            binding.textViewResult.setText("Ошибка выполнения задачи");
                            Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}