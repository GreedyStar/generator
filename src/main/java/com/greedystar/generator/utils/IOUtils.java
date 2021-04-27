package com.greedystar.generator.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author GreedyStar
 * @date 2021-4-27
 */
public class IOUtils {

    /**
     * 输入流转字符串
     *
     * @param inputStream
     * @return
     */
    public static String toString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * 字符串转输入流
     *
     * @param string
     * @return
     */
    public static InputStream toInputStream(String string) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }

}
