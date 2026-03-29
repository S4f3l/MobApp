package com.mirea.manilovla.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Используем текущее время как значения по умолчанию
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Создаём и возвращаем новый экземпляр TimePickerDialog
        return new TimePickerDialog(requireActivity(), this, hour, minute,
                DateFormat.is24HourFormat(requireActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Вызываем метод активности для обработки выбранного времени
        if (getActivity() != null) {
            ((MainActivity) getActivity()).onTimeSelected(hourOfDay, minute);
        }
    }
}
