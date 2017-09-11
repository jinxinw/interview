package company.delphix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Nasa {

    private static final String NASA_ASSETS_URL = "https://api.nasa.gov/planetary/earth/assets";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
    private static final String BEGIN = "begin";
    private static final String END = "end";
    private static final String API_KEY = "api_key=9Jz6tLIeJ0yY9vjbEUWaH9fsXA930J9hspPchute";
    private static final int HTTP_OK = 200;
    private static final int DEFAULT_WINDOW = 1;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public JSONObject get(double latitude, double longitude, String begin, String end) throws IOException, ParseException {
        System.out.println(constructUrl(latitude, longitude, begin, end));
        URL url = new URL(constructUrl(latitude, longitude, begin, end));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(br);
        conn.disconnect();
        return jsonObject;
    }

    private List<Date> parseResults(JSONObject jsonObject) throws java.text.ParseException {
        List<Date> result = new ArrayList<>();
        JSONArray ja = (JSONArray) jsonObject.get("results");
        for (int i = 0; i < ja.size(); i++) {
            JSONObject entry = (JSONObject) ja.get(i);
            result.add(sdf.parse((String) entry.get("date")));
        }
        Collections.sort(result);
        return result;
    }

    private String constructUrl(double latitude, double longitude, String begin, String end) {
        StringBuilder sb = new StringBuilder();
        sb.append(NASA_ASSETS_URL);
        sb.append("?").append(LONGITUDE).append("=").append(longitude);
        sb.append("&").append(LATITUDE).append("=").append(latitude);
        sb.append("&").append(BEGIN).append("=").append(begin);
        if (end != null) {
            sb.append("&").append(END).append("=").append(end);
        }
        sb.append("&").append(API_KEY);
        return sb.toString();
    }

    public void flyby(double latitude, double longitude) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = new Date();
        cal.setTime(currentDate);
        cal.add(Calendar.YEAR, -DEFAULT_WINDOW);
        Date begin = cal.getTime();
        try {
            JSONObject jsonObject = get(latitude, longitude, sdf.format(begin), sdf.format(currentDate));
            List<Date> list = parseResults(jsonObject);
            long sum = 0;
            for (int i = 1; i < list.size(); i++) {
                long diff = (list.get(i).getTime() - list.get(i - 1).getTime()) / 1000;
                sum += diff;
            }
            int average = (int) (sum / (list.size() - 1));
            cal.setTime(list.get(list.size() - 1));
            cal.add(Calendar.SECOND, average);
            System.out.println(String.format("Next Time: %s, Average Time %d seconds (about %d days)", sdf.format(cal.getTime()), average, average / 24 / 60 / 60));
        } catch (IOException ex) {
            Logger.getLogger(Nasa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Nasa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Nasa nasa = new Nasa();
            nasa.flyby(36.098592, -112.097796);
            nasa.flyby(43.078154, -79.075891);
            nasa.flyby(36.998979, -109.045183);
            nasa.flyby(37.7937007, -122.4039064);
    }
}
