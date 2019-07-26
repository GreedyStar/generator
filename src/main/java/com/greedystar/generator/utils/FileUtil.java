package com.greedystar.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        // 已存在的文件不予覆盖
        File file = new File(filePath);
        if (file.exists()) {
            filePath += ".generated";
        }
        // 代码生成路径目录不存在则自动创建
        String dirPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        System.out.println("Generating " + filePath);
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        writer.close();
        bw.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtil.TYPE_ENTITY:
                return FreemarketConfigUtil.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtil.TYPE_DAO:
                return FreemarketConfigUtil.getInstance().getTemplate("Dao.ftl");
            case FreemarketConfigUtil.TYPE_SERVICE:
                return FreemarketConfigUtil.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtil.TYPE_CONTROLLER:
                return FreemarketConfigUtil.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtil.TYPE_MAPPER:
                return FreemarketConfigUtil.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtil.TYPE_INTERFACE:
                return FreemarketConfigUtil.getInstance().getTemplate("Interface.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

}
