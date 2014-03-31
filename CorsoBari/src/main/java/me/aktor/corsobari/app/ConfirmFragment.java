package me.aktor.corsobari.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by eto on 24/03/14.
 */
public class ConfirmFragment extends Fragment {
    public static final String ARG_NAME = "args";
    private String mString;
    public void enableConfirmFor(String text) {
       mString=text;
       mConfirm.setText(mString);
    }

    public static ConfirmFragment createWithText(String text) {
        ConfirmFragment f = new ConfirmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME,text);
        f.setArguments(args);
        return f;
    }

    public static interface OnConfirmListener {
        public void onConfirm(String text);

    }

    private OnConfirmListener mListener;
    private Button mConfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args!=null){
            mString = args.getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.confirm_fragment,container,false);
        mConfirm = (Button)v.findViewById(R.id.btn_confirm);
        mConfirm.setText(mString);
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = mString;
                mString = null;
                mConfirm.setText(null);
                if (mListener!=null){
                    mListener.onConfirm(txt);
                }
            }
        });
        return v;
    }

    public void setOnConfirmListener(OnConfirmListener listener) {
        mListener = listener;
    }
}
