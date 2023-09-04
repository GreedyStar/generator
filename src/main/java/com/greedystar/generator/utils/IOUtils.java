package com.greedystar.generator.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author GreedyStar
 * @since 2021-4-27
 */
public class IOUtils {

    /**
     * 输入流转字符串
     *
     * @param inputStream 输入流
     * @return 转换后的字符串
     * @throws IOException IOException
     */
    public static String toString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * 字符串转输入流
     *
     * @param string 字符串
     * @return 转换后的输入流
     */
    public static InputStream toInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }

}
