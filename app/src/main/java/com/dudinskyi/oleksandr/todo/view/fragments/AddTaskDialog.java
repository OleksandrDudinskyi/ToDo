package com.dudinskyi.oleksandr.todo.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.view.activity.TasksActivity;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class AddTaskDialog extends DialogFragment {

    private EditText taskName;

    public View onCreateDialogView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.add_task_layout, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        taskName = (EditText) view.findViewById(R.id.task_name);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_task_title)
                .setPositiveButton(android.R.string.ok,
                        (dialog, whichButton) -> {
                            if (getActivity() instanceof TasksActivity) {
                                ((TasksActivity) getActivity()).addNewTask(taskName.getText().toString());
                            }
                        }
                )
                .setNegativeButton(android.R.string.cancel,
                        (dialog, whichButton) -> dialog.dismiss()
                );
        View view = onCreateDialogView(getActivity().getLayoutInflater(), null);
        onViewCreated(view, null);
        builder.setView(view);
        return builder.create();
    }

}
