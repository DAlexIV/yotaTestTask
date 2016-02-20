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
    public static boolean validateHttpUrl(String url) {
        return url.contains("http");
    }

    public static boolean validateUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    public static String downloadUrl(String given_url) throws IOException {
        URL url = new URL(given_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();

        String web_page = readStringFromInputStream(is);
        is.close();
        return web_page;
    }


    @NonNull
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
