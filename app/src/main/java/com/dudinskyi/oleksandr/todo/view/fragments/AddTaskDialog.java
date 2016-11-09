package com.dudinskyi.oleksandr.todo.view.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dudinskyi.oleksandr.todo.R;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class AddTaskDialog extends DialogFragment {

    public static final String NEW_TASK_NAME_KEY = "new_task_name_key";
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
                            String taskName = this.taskName.getText().toString();
                            if (!TextUtils.isEmpty(taskName)) {
                                Intent resultIntent = new Intent().putExtra(NEW_TASK_NAME_KEY, taskName);
                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                            } else {
                                Toast.makeText(getActivity(), R.string.empty_task_txt, Toast.LENGTH_SHORT).show();
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
