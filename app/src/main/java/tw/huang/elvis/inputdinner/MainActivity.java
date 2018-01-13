package tw.huang.elvis.inputdinner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText[] edts;
    TextView txtResult;
    int number;
    String[] Result;
    Context context;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        edts = new EditText[5];
        Result = new String[5];
        txtResult = (TextView) findViewById(R.id.txtResult);
        for (i = 0; i < edts.length; i++) {
            edts[i] = (EditText) findViewById(R.id.edt1 + i);
        }
    }

    public void onClick(View v) {
        for(i=0; i<edts.length; i++) {
           if (edts[i].length()==0) {
                showmsg("沒輸入餐廳是要吃甚麼鬼!");
                return;
            }
            else{
                Result[i] = edts[i].getText().toString();
                number = (int) (Math.random() * 5 + 1) - 1;
                txtResult.setText("你可以吃" + Result[number]);

            }

            InputMethodManager imm = (InputMethodManager) getSystemService(context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
//        Intent it = new Intent(context, Main2Activity.class);
//        it.putExtra("aaa",Result[number]);
//        context.startActivity(it);
    }

    public void onClickClear(View view){
        for(i=0; i<edts.length; i++){
            if(edts[i].length()==0){
                showmsg("空的，是想清甚麼!");
                return;
            }
            else{
                edts[i].setText("");
                txtResult.setText("結果");
            }
        }
    }

    public void onNextpage(View v){
        Intent it = new Intent(context,Main2Activity.class);
        context.startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent it = new Intent(Intent.ACTION_VIEW);
        if(id == R.id.menuWeb){
            it.setData(Uri.parse("http://www.ipeen.com.tw/"));
        }
        else if(id==R.id.map){
            it.setData(Uri.parse("https://www.google.com.tw/maps"));
        }
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }


    public void showmsg(String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 650);
        toast.show();
    }

}

