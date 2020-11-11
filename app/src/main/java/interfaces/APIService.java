package interfaces;

import datacontractEvent.RegisterEventResponse;
import datacontractImpl.LoginResponse;
import datacontractImpl.RefreshResponse;
import datacontractImpl.RegisterResponse;
import domain.LoginUser;
import domain.SensorEvent;
import domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Interfaz para consumir los métodos Rest que expone la API, sendEvent se le pasa el token de Header
 */
public interface APIService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginUser user);

    @POST("register")
    Call<RegisterResponse> register(@Body User user);

    @POST("event")
    Call<RegisterEventResponse> sendEvent(  @Header("Authorization") String token,
                                            @Body SensorEvent sensorEvent);
    @PUT("refresh")
    Call<RefreshResponse> refresh(@Header("Authorization") String token);
}
