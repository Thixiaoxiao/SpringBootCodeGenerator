package com.softdev.system.generator.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Thixiaoxiao
 */
public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;
    private static void compress(ZipOutputStream zos, String path, String name, String data) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        zos.putNextEntry(new ZipEntry(path + name));
        int len;
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes("UTF-8"));
        while ((len = in.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        zos.closeEntry();
        in.close();
    }

    /**
     * 文本直接转zip压缩成文件
     *
     * @param list -> map -> path 路径; name 文件名; data 具体文本内容;
     * @param out  传入输出流
     * @throws RuntimeException 抛出异常
     */

    public static void toZip(List<Map<String, String>> list, OutputStream out) throws RuntimeException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);

            for (Map<String, String> map : list) {
                String path = map.get("path");
                String name = map.get("name");
                String data = map.get("data");
                compress(zos, path, name, data);

            }

        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);

        } finally {
            if (zos != null) {
                try {
                    zos.close();

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

        }

    }

    public static void main(String[] args) throws Exception {
        List<Map<String, String>> list = new ArrayList<>();

        OutputStream outputStream = new FileOutputStream(new File("/test.zip"));

        Map m1 = new HashMap() {{
            put("path", "/f1/f2/f3/");
            put("name", "1.txt");
            put("data", "abcdefg");
        }};

        Map m2 = new HashMap() {{
            put("path", "/f1/f2/f3/f4/");
            put("name", "2.txt");
            put("data", "abcdefg");
        }};

        Map m3 = new HashMap() {{
            put("path", "");
            put("name", "3.txt");
            put("data", "abcdefg");
        }};

        list.add(m1);

        list.add(m2);

        list.add(m3);

        toZip(list, outputStream);

        if (outputStream != null) {
            outputStream.close();
        }
    }
}
