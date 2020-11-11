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

import domain.User;

import service.RegisterService;

/**
 * Activity registración de usuario
 */
public class RegisterActivity extends Activity {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextDNI;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextCommission;

    private Button buttonLogin;
    private Button buttonRegister;
    private Intent intentLoginRegister;
    private static String LOGIN = "login";
    private static String REGISTER = "register";
    private LoginRegisterReceiver rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Obtengo objetos de la vista
        buttonLogin = (Button) findViewById(R.id.buttonReturn);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCommission = (EditText) findViewById(R.id.editTextCommission);


        //Seteo listeners de los botones
        buttonLogin.setOnClickListener(botonesListener);
        buttonRegister.setOnClickListener(botonesListener);

        //Registro los action del broadcast
        registerLoginReceiver();
    }

    //Listener de botones con lógica
    private View.OnClickListener botonesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String firstName = editTextName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String dni = editTextDNI.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String commission = editTextCommission.getText().toString();

            User user = new User(firstName,lastName,dni,email,password,commission);

            switch (v.getId()){
                case R.id.buttonReturn:
                    //Ir a Login
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    break;
                case R.id.buttonRegister:
                    //Llamar a un intent de Register
                    System.out.println("Click");
                    intentLoginRegister = new Intent(RegisterActivity.this, RegisterService.class);
                    intentLoginRegister.putExtra("User", user);
                    intentLoginRegister.putExtra("action",REGISTER);
                    startService(intentLoginRegister);
                    break;
                default:
                    Toast.makeText(RegisterActivity.this,"Error en Listener de botones",Toast.LENGTH_LONG).show();
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
                case RegisterService.LOGIN_OK:
                    String token = intent.getExtras().getString("token");
                    Toast.makeText(RegisterActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent goToHomeActivity = new Intent(RegisterActivity.this, HomeActivity.class);
                    goToHomeActivity.putExtra("token", token);
                    startActivity(goToHomeActivity);
                    break;
                case RegisterService.REGISTER_OK:
                    Toast.makeText(RegisterActivity.this, "Registro exitoso, favor de loguearse", Toast.LENGTH_SHORT).show();
                    break;
                case RegisterService.LOGIN_ERROR:
                case RegisterService.LOGIN_FAIL_CALL:
                case RegisterService.REGISTER_ERROR:
                case RegisterService.REGISTER_FAIL_CALL:
                    msgError = intent.getExtras().getString("msgError");
                    Toast.makeText(RegisterActivity.this, msgError, Toast.LENGTH_LONG).show();
                    break;
                default: break;
            }
        }
    }

    //Registro los action que espero recibir del broadcast receiver
    private void registerLoginReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(RegisterService.LOGIN_OK);
        filter.addAction(RegisterService.LOGIN_ERROR);
        filter.addAction(RegisterService.LOGIN_FAIL_CALL);
        filter.addAction(RegisterService.REGISTER_OK);
        filter.addAction(RegisterService.REGISTER_ERROR);
        filter.addAction(RegisterService.REGISTER_FAIL_CALL);
        rcv = new LoginRegisterReceiver();
        registerReceiver(rcv, filter);
    }

    //Saco el registro del broadCast Receiver y paro el servicio de LoginRegisterService
    @Override
    protected void onDestroy() {
        if(intentLoginRegister != null){
            stopService(intentLoginRegister);
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
        //boolean isWiFi = (activeNetwork != null)?activeNetwork.getType() == ConnectivityManager.TYPE_WIFI: false;

        if(!isConnected){
            Toast.makeText(RegisterActivity.this, "No se pudo establecer conexión de internet", Toast.LENGTH_LONG).show();
        }
    }

}
