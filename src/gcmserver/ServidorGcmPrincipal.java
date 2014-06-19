/**
 * ServidorGcmPrincipal é a classe principal da aplicação.
 * Nesta classe são atribuídos a chave de registro no GCM da aplicação Android
 * cliente e a mensagem a ser enviada.
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
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Scanner;


/**
 *
 * @author Knetsteller
 */
public class ServidorGcmPrincipal {
    public static void main(String[] args) {
        
        String mensagem;
        mensagem = escreveMensagem();
        System.out.println("Enviando HTTP POST para o Servidor GCM...");
        String gcmApi = "AIzaSyBQgfgQpj_CnClMeI9FeU1-AL9QOpKzxYY";        
        MensagemJson conteudoMensagem = criaConteudo(mensagem);
        
        PostGcm.post(gcmApi, conteudoMensagem);        
    }
    
    public static MensagemJson criaConteudo(String msg) {
        MensagemJson c = new MensagemJson();
        c.addRegId("APA91bGY55zBxXwxDhr-VRRBkCy0oZM1H2MBvjEKFmnNW8vCie-eP9NDoniJ-fG2bEfrnScZMOPoIjBQr34ZyQDHT8pyO0YQG6GzevQp_L99CmBDJRvTL0uRaa8MP9ijq4fKmWE5Cf5nHHQlP0eb_BjMsNqiJrd-y-GbzmY-F5LgmUt3Iil3nEU");
        c.createData(msg, "Test Message");
        
        return c;
    }

    public static String escreveMensagem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva sua Mensagem:\n");
        String mensagem = sc.nextLine();
        return mensagem;
    }
}
