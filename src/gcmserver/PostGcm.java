/**
 * PostGcm possui o médodo "post", que enviar uma requisição HTTP para o 
 * servidor GCM.
 */

package gcmserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 *
 * @author Knetsteller
 */
public class PostGcm {
    
    public static void post(String idProjeto, MensagemJson conteudo) {
        try {
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + idProjeto);
            conn.setDoOutput(true);
            
            ObjectMapper mapeador = new ObjectMapper();
            mapeador.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            mapeador.writeValue(wr, conteudo);
            wr.flush();
            wr.close();
            
            int respostaHttp = conn.getResponseCode();
            System.out.println("\nEnviando HTTP POST para: " + url);
            System.out.println("Código HTTP: " + respostaHttp);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {                
                response.append(inputLine);            
            }
            in.close();
            
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
