package com.android.p11ps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BioFragment extends Fragment {

    Button btnEdit;

    public BioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bio, container, false);
        btnEdit = view.findViewById(R.id.btnEdit);

        final TextView tvData = (TextView) view.findViewById(R.id.tvData);

        LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewDialog = inflater.inflate(R.layout.edit, null );

        final EditText etData = (EditText) viewDialog.findViewById(R.id.etData);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(getContext());
                myBuilder.setTitle("Edit Bio");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Cancel", null);
                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String data = etData.getText().toString();
                        tvData.setText(data);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });



        return view;
    }
}