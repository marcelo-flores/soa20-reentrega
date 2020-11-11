package apiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/** Cliente REST para Android y Java que nos permite realizar las peticiones con los parametros correspondientes a la API de la catedra. */

public class apiService {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
