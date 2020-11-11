package activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.soa20.tpandroid.R;

import domain.LoginUser;
import service.LoginService;

/**
 * Activity para login
 */
public class LoginActivity extends Activity {


    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonLoginRegister;
    private Intent intentLogin;
    private static String LOGIN = "login";
    private static String REGISTER = "register";
    private LoginRegisterReceiver rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Obtengo objetos de la vista
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLoginRegister = (Button) findViewById(R.id.buttonLoginRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //Seteo listeners de los botones
        buttonLogin.setOnClickListener(botonesListener);
        buttonLoginRegister.setOnClickListener(botonesListener);

        //Registro los action del broadcast
        registerLoginReceiver();
    }

    //Listener de botones con lógica
    private View.OnClickListener botonesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();



            LoginUser user = new LoginUser(email,password);

            switch (v.getId()){
                case R.id.buttonLogin:
                    //LLAMAR INTENT de Login
                    intentLogin = new Intent(LoginActivity.this, LoginService.class);
                    intentLogin.putExtra("User", user);
                    intentLogin.putExtra("action",LOGIN);
                    startService(intentLogin);
                    break;
                case R.id.buttonLoginRegister:
                    //Ir a pantalla Registro
                    //setContentView(R.layout.activity_main);
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);

                    break;
                default:
                    Toast.makeText(LoginActivity.this,"Error en Listener Login de botones",Toast.LENGTH_LONG).show();
            }
        }
    };

    //BroadCastReceiver
    public class LoginRegisterReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String msgError;
            String responseCallback = intent.getAction();
            switch (responseCallback) {
                case LoginService.LOGIN_OK:
                    String token = intent.getExtras().getString("token");
                    Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent goToHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                    goToHomeActivity.putExtra("token", token);
                    startActivity(goToHomeActivity);
                    break;

                case LoginService.LOGIN_ERROR:
                case LoginService.LOGIN_FAIL_CALL:
                    msgError = intent.getExtras().getString("msgError");
                    Toast.makeText(LoginActivity.this, msgError, Toast.LENGTH_LONG).show();
                default: break;
            }
        }
    }

    //Registro los action que espero recibir del broadcast receiver
    private void registerLoginReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoginService.LOGIN_OK);
        filter.addAction(LoginService.LOGIN_ERROR);
        filter.addAction(LoginService.LOGIN_FAIL_CALL);

        rcv = new LoginRegisterReceiver();
        registerReceiver(rcv, filter);
    }

    //Saco el registro del broadCast Receiver y paro el servicio de LoginRegisterService
    @Override
    protected void onDestroy() {
        if(intentLogin != null){
            stopService(intentLogin);
        }
        unregisterReceiver(rcv);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Validar conexión a internet
        validateConnectivity();
    }

    //Valido si tiene conexión a internet
    public void validateConnectivity(){
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(!isConnected){
            Toast.makeText(LoginActivity.this, "No se pudo establecer conexión de internet", Toast.LENGTH_LONG).show();
        }
    }

}
