package venky.com.bookcabapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends Activity {
    DBController controller = new DBController(this);
    EditText edtuserid, edtpass;

    ProgressBar pbbar;
    String loc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtuserid= (EditText) findViewById(R.id.edtuserid);
        edtpass= (EditText) findViewById(R.id.edtpass);

        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);
        Button login= (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edtuserid.getText()!=null)&&(edtpass.getText()!=null)){
                    DoLogin login=new DoLogin();
                    login.execute();
                }
                else{
                    Toast.makeText(getApplicationContext(),"enter id and password",Toast.LENGTH_LONG).show();
                }

            }
        });



        TextView signup= (TextView) findViewById(R.id.btnsignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });


    }



    public class DoLogin extends AsyncTask<String, String, String> {


        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();





        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {

            pbbar.setVisibility(View.GONE);


            if(r.equals("success")){
                if(Utility.loc.equalsIgnoreCase("driver")){
                    Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent= new Intent(getApplicationContext(),Customer.class);
                    startActivity(intent);
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "failed login", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected String doInBackground(String... params) {

            HashMap<String, String> queryValues= new HashMap<String, String>();

            queryValues.put("email",userid);

            String pass=controller.getPassword(queryValues);
           Utility.loc = controller.getType(queryValues);
            if(pass!=null&& loc!=null){
                if(pass.equals(password)){
                    return "success";
                }else{
                    return "failed";
                }
            }
            return "failed";

        }
    }
}
