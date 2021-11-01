package com.greedystar.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @author GreedyStar
 * @since 2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @param fileName 文件名
     * @throws IOException       文件读写异常
     * @throws TemplateException 模板异常
     */
    public static void generateToJava(int type, Object data, String filePath, String fileName) throws IOException, TemplateException {
        String path = filePath + fileName; // 待生成的代码文件路径
        // 已存在的文件不予覆盖
        File file = new File(path);
        if (file.exists() && !ConfigUtil.getConfiguration().isFileOverride()) {
            path += ".generated";
            System.err.printf("%s already exit. Generating %s \n", fileName, path);
        } else {
            System.out.printf("Generating %s \n", path);
        }
        // 代码生成路径目录不存在则自动创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(path);
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
            case FreemarkerConfigUtil.TYPE_ENTITY:
                return FreemarkerConfigUtil.getInstance().getTemplate("Entity.ftl");
            case FreemarkerConfigUtil.TYPE_DAO:
                return FreemarkerConfigUtil.getInstance().getTemplate("Dao.ftl");
            case FreemarkerConfigUtil.TYPE_SERVICE:
                return FreemarkerConfigUtil.getInstance().getTemplate("Service.ftl");
            case FreemarkerConfigUtil.TYPE_CONTROLLER:
                return FreemarkerConfigUtil.getInstance().getTemplate("Controller.ftl");
            case FreemarkerConfigUtil.TYPE_MAPPER:
                return FreemarkerConfigUtil.getInstance().getTemplate("Mapper.ftl");
            case FreemarkerConfigUtil.TYPE_INTERFACE:
                return FreemarkerConfigUtil.getInstance().getTemplate("Interface.ftl");
            case FreemarkerConfigUtil.TYPE_DTO:
                return FreemarkerConfigUtil.getInstance().getTemplate("Dto.ftl");
            case FreemarkerConfigUtil.TYPE_VO:
                return FreemarkerConfigUtil.getInstance().getTemplate("Vo.ftl");
            default:
                return null;
        }
    }

    /**
     * 获取项目主目录
     *
     * @return 项目根路径
     */
    private static String getBasicProjectPath() {
        StringBuilder sb = new StringBuilder();
        String path = FileUtil.class.getClassLoader().getResource("").getPath().replace("/", File.separator);
        if (path.contains("target")) {
            sb.append(path, 0, path.indexOf("target"));
        } else if (path.contains("build")) {
            sb.append(path, 0, path.indexOf("build"));
        }
        sb.append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return 源码路径
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return 资源路径
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getResourcePath());
        System.out.println(getSourcePath());
    }

}
