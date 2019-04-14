package cn.whu.gugugu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadData {
    public static String readData(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        String str = null;
        while ((str = br.readLine()) != null) {
            builder.append(str);
        }

        br.close();
        return builder.toString();
    }
}
