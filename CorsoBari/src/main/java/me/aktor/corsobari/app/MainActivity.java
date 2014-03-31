package me.aktor.corsobari.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String SAVED_TEXT = "text";
    public static final int REQUEST_CODE = 2;


    private ArrayList<String> mModel;
    private TextListAdapter mAdapter;
    private ListView mListView;


    private String mText;

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.add:
                    Intent activity = new Intent(MainActivity.this,ActivityEdit.class);
                    startActivityForResult(activity,REQUEST_CODE);
            }
        }
    };

    private AdapterView.OnItemClickListener itemListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> list, View row, int position, long id) {
                    Toast.makeText(MainActivity.this,mAdapter.getItem(position),Toast.LENGTH_LONG).show();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        if (savedInstanceState != null){
            mModel  = savedInstanceState.getStringArrayList(SAVED_TEXT);
        } else {
            mModel = new ArrayList<String>();
        }

        mListView = (ListView)findViewById(R.id.list);
        mListView.setOnItemClickListener(itemListener);
        mAdapter  = new TextListAdapter(this,mModel);
        mListView.setAdapter(mAdapter);
        findViewById(R.id.add).setOnClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mText!=null){
            showFragment(mText);
            mText = null;
        }
    }

    private void showFragment(String text){
        ConfirmFragment f  = ConfirmFragment.createWithText(text);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_host,f,"TAG")
                .commit();

    }
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ConfirmFragment){
            ((ConfirmFragment) fragment).setOnConfirmListener(new ConfirmFragment.OnConfirmListener() {
                @Override
                public void onConfirm(String text) {
                    mModel.add(text);
                    mAdapter.notifyDataSetChanged();

                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString(SAVED_TEXT,mTextView.getText().toString());
        outState.putStringArrayList(SAVED_TEXT,mModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK){
                mText  = data.getStringExtra(ActivityEdit.RESULT_KEY);

//                ConfirmFragment f =(ConfirmFragment)getSupportFragmentManager().findFragmentById(R.id.ConfirFragment);


//                mTextView.setText(data.getStringExtra(ActivityEdit.RESULT_KEY));
            } else {
                Log.e("CANCEL","cancel");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
