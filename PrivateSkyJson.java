package JarPrivateSky;

import Utilities.QRGenerator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

public class PrivateSkyJson {

    private static String name;
    private static String patch;

    public static void main(String args[]) throws IOException {

        String fileName = "Config.txt";
        String line = null;
        String[] a = new String[3];

        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                a[i]= line;
                i++;
            }
            bufferedReader.close();

            //Begin
            URL url = new URL("http://"+a[2]+":"+a[0]+"/beginCSB");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int status = con.getResponseCode();
            System.out.println("Status BeginCsb: " + status);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println("Raspuns: " + content);
            in.close();
            con.disconnect();

            //Attach file

            CloseableHttpClient httpclient = HttpClients.createDefault();
            File file = new File(patch + name + ".json");

            HttpPost httppost = new HttpPost("http://"+a[2]+":"+a[0]+"/attachFile/" + content + "/" + name + ".json");

            httppost.addHeader("Content-type", "application/octet-stream");
            httppost.addHeader("Content", "data stream");

            FileEntity entity = new FileEntity(file);

            httppost.setEntity(entity);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("Status Attach File :  " + response.getStatusLine());
            if (resEntity != null) {
                ;
                System.out.println("Raspuns Attach file:" + EntityUtils.toString(resEntity));
            }

            httpclient.close();

            //Add backup

            CloseableHttpClient httpclient1 = HttpClients.createDefault();
            HttpPost httppost1 = new HttpPost("http://"+a[2]+":"+a[0]+"/addBackup/" + content);

            httppost1.addHeader("Content-type", "text/plain");
            httppost1.addHeader("Content", a[1]);

            String payLoadLogin = a[1];
            StringEntity se = new StringEntity(payLoadLogin);
            httppost1.setEntity(se);

            HttpResponse response1 = httpclient1.execute(httppost1);
            HttpEntity resEntity1 = response1.getEntity();

            System.out.println("Status Add backup  " + response1.getStatusLine());
            if (resEntity != null) {
                ;
                System.out.println("Raspunds Add BackUP: " + response1.getStatusLine());
            }

            httpclient1.close();

            //BuildCSB

            CloseableHttpClient httpclient4 = HttpClients.createDefault();
            HttpPost httppost4 = new HttpPost("http://"+a[2]+":"+a[0]+"/buildCSB/" + content);

            httppost4.addHeader("url", a[1]);
            httppost4.addHeader("channel", "domeniu/agent/tester");

            HttpResponse response4 = httpclient4.execute(httppost4);
            HttpEntity resEntity4 = response4.getEntity();

            System.out.println("Status BuildCSB  " + response4.getStatusLine());

            if (resEntity != null) {
                ;
                String token_rez = EntityUtils.toString(resEntity4);
                System.out.println("Raspuns BuildCBS: " + token_rez);
                JarPrivateSky.PrivateSkyToken token = new JarPrivateSky.PrivateSkyToken(token_rez);
                token.setToken(token_rez);
            }
            httpclient4.close();
        } catch (
                SocketException e) {
            e.printStackTrace();
            System.out.println("Server picat");
        } catch (
                NoHttpResponseException e) {
            e.printStackTrace();
            System.out.println("localhost failed to respond");
        } catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
}