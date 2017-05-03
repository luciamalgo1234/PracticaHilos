package com.example.alonso.busquedalibros;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alonso on 03/05/2017.
 */

public class Name {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    static String getBookInfo(String consulta){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        Log.d(LOG_TAG,bookJSONString);

        try {
            // Construyendo tu consulta URI, limitando resultados de 10 items y imprimientod libros
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(CONSULTA_PARAM,consulta)
                    .appendQueryParameter(MAX_RESULTS,"10")
                    .appendQueryParameter(PRINT_TYPE,"books")
                    .build();
            URL respuestaURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection)respuestaURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                // no hace nada
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                /*Desde este JSON, agrega una nueva linea, no es necesario(esto no afecta la conversión)
                 pero esto hace hacer un debugging un "monton"
                  Más fácil si imprime el
Buffer completado para depuración
                 */
                buffer.append(line+"\n");
            }
            if (buffer.length() == 0){
                //Stream estaba bacio
                return null;
            }
            bookJSONString = buffer.toString();


        }catch (Exception ex){
            ex.printStackTrace();
            return null;

        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bookJSONString;

        }



    }

}

