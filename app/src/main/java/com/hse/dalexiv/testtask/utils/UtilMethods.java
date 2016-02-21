package com.hse.dalexiv.testtask.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dalexiv on 19.02.16.
 */
public class UtilMethods {
    /**
     * Pre-filters url
     * @param url
     * @return true if it contains http header
     */
    public static boolean validateHttpUrl(String url) {
        return url.contains("http");
    }

    /**
     * Checks if the given url matches url valid
     * @param url
     * @return true if it is valid
     */
    public static boolean validateUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    /**
     * Downloads web page from the url
     * @param given_url
     * @return Web page text
     * @throws IOException if there was an error
     */
    public static String downloadUrl(String given_url) throws IOException {
        // Setting up a connection
        URL url = new URL(given_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        // Receiving data through the input stream
        InputStream is = conn.getInputStream();
        String web_page = readStringFromInputStream(is);
        is.close();

        return web_page;
    }

    /**
     * Method for getting string from input stream
     * @param is a reference to the input stream
     * @return line contains all stream data
     * @throws IOException if there was an error while reading from stream
     */
    private static String readStringFromInputStream(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while ((line = reader.readLine()) != null) {
            total.append(line);
        }

        line = total.toString();
        return line;
    }
}
