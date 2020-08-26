package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DataAccess {

    private static String AssemblyName="ch10";
    //private static String db="Access";
    public static String getDB() throws Exception {
        Properties prop = new Properties();
        String value = null;
        // 通过输入缓冲流进行读取配置文件
        InputStream InputStream = new BufferedInputStream(new FileInputStream(new File("src/main/java/test/config.properties")));
        // 加载输入流
        prop.load(InputStream);
        // 根据关键字获取value值¥
        value = prop.getProperty("DB");
        System.out.println(value);
        return value;
    }

    public static void main(String[] args) throws Exception {
        getDB();
    }

}
