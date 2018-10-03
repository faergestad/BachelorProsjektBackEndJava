package schedularfx;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author GeorgPersen
 */
public class SchedularMain extends TimerTask {

    Date current;

    @Override
    public void run() {
        current = new Date();
        // Sett inn URL her
        String url = "";
        String params = "";
        executePost(url, "");
        System.out.println("Dato & klokkeslett: " + current);
    }

    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Opprett tilkobling
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", 
                "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", 
                Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");  

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Hent svar  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
              response.append(line);
              response.append('\r');
            }
            rd.close();
            return response.toString();
          } catch (Exception e) {
            e.printStackTrace();
            return null;
          } finally {
            if (connection != null) {
              connection.disconnect();
            }
          }
        }
    
}
