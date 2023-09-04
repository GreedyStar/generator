package com.greedystar.generator.utils;

import com.greedystar.generator.entity.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

import static java.io.File.separator;

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
            case FreemarkerConfigUtil.TYPE_POM:
                return FreemarkerConfigUtil.getInstance().getTemplate("Pom.ftl");
            case FreemarkerConfigUtil.TYPE_APPLICATION_FILE:
                return FreemarkerConfigUtil.getInstance().getTemplate("application-yml.ftl");
            case FreemarkerConfigUtil.TYPE_BOOTSTRAP_CLASS:
                return FreemarkerConfigUtil.getInstance().getTemplate("Application.ftl");
            case FreemarkerConfigUtil.TYPE_SWAGGER_CONFIG:
                return FreemarkerConfigUtil.getInstance().getTemplate("DockApi.ftl");
            default:
                return null;
        }
    }

    /**
     * 获取项目主目录
     *
     * @return 项目根路径
     */
    private static StringBuilder getBasicProjectPath() {
        Configuration configuration = ConfigUtil.getConfiguration();
        if (!StringUtil.isEmpty(configuration.getProjectPath())) {
            return new StringBuilder().append(configuration.getProjectPath())
                    .append(configuration.getProjectName())
                    .append(separator);
        }
        StringBuilder sb = new StringBuilder();
        String path = FileUtil.class.getClassLoader().getResource("").getPath().replace("/", File.separator);
        if (path.contains("target")) {
            sb.append(path, 0, path.indexOf("target"));
        } else if (path.contains("build")) {
            sb.append(path, 0, path.indexOf("build"));
        }
        return sb;
    }

    /**
     * 获取源码路径
     *
     * @return 源码路径
     */
    public static String getSourcePath() {
        //若指定项目目录则使用指定的
        StringBuilder path = getBasicProjectPath();
        path.append("src").append(separator)
                .append("main").append(separator)
                .append("java").append(separator);
        return path.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return 资源路径
     */
    public static String getResourcePath() {
        StringBuilder path = getBasicProjectPath();
        path.append("src").append(separator)
                .append("main").append(separator)
                .append("resources").append(File.separator);
        return path.toString();
    }

    public static void main(String[] args) {
        System.out.println(getResourcePath());
        System.out.println(getSourcePath());
    }

}
