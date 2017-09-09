package venky.com.bookcabapp;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {
    DBController controller = new DBController(this);
    private ProgressBar pbbar;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;
String type = "";
    ArrayList<String> location = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

       Spinner sp = (Spinner)findViewById(R.id.spinner3);
        location.add("Driver");
        location.add("Customer");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, location);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter3);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = location.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button = (Button) findViewById(R.id.button);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    InsertUser insert1 = new InsertUser();
                    insert1.execute();


            }
        });

    }


    public class InsertUser extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;

        String name = et1.getText().toString();
        String contact = et2.getText().toString();
        String location = type;
        String email = et4.getText().toString();
        String idproof = et5.getText().toString();
        String pass = et6.getText().toString();



        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {

            pbbar.setVisibility(View.GONE);
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");
            if (Integer.parseInt(r) != (-1)) {
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
            }
            if(type.equalsIgnoreCase("driver")){
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), Customer.class);
                startActivity(intent);
            }


        }

        @Override
        protected String doInBackground(String... params) {
            ContentValues queryValues = new ContentValues();
            queryValues.put("name", name);
            queryValues.put("contactnum", contact);
            queryValues.put("Location", type);
            queryValues.put("email", email);
            queryValues.put("name", idproof);
            queryValues.put("password", pass);


            long r = controller.insertUser(queryValues);
            return r + "";

        }
    }
}















