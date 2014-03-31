package me.aktor.corsobari.app;

import android.app.Activity;
import android.os.*;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by eto on 24/03/14.
 */
public class FragmentEdit extends Fragment {

    private boolean mStatus;
    private TextView mInput;

    public static interface OnEditCompletedListener {
        public void onCancel();
        public void onResult(String text);
    }

    private OnEditCompletedListener mListener;

    public void setOnEditCompleteListener(OnEditCompletedListener l){
        mListener = l;
    }

    private final View.OnClickListener listener =
            new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    ProcessBuilder p = new ProcessBuilder("ls /system/apps");
                    try {
                        p.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (mListener==null)return;
                    switch (v.getId()){
                        case R.id.btn_cancel:
                            mListener.onCancel();
                            break;
                        case R.id.btn_save:
                            String res = mInput.getText().toString();
                            mListener.onResult(res);
                            break;
                    }
                }
            };


    public String getText(){
        return mInput.getText().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        mInput = (EditText)view.findViewById(R.id.input_text);
        view.findViewById(R.id.btn_cancel).setOnClickListener(listener);
        view.findViewById(R.id.btn_save).setOnClickListener(listener);
        return view;
    }
}
