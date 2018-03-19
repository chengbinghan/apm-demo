package apm.web.core.plugin;

import apm.web.core.properties.ApmProperties;
import apm.web.util.apmutil.ApmStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * @author ChengBing Han
 * @date 15:28  2018/3/10
 * @description
 */
public class PluginIoOperateUtils {


    private static final Logger logger = LoggerFactory.getLogger(CreatePluginImpl.class);

    private PluginIoOperateUtils() {
    }

    ;

    public final static Integer BUFFER_SIZE = 2097152;


    /*
     copy 文件夹下的所有文件到另外一个文件加
     */
    public static void copyWithUpddateDir(String oldPath, String newPath, PluginInfo pluginInfo) throws IOException {
        File file = new File(oldPath);
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }


        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                copyWithUpddateDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i], pluginInfo);
            }

            if (new File(oldPath + file.separator + filePath[i]).isFile()) {
                copyFileWithUpdateFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i], pluginInfo);
            }

        }
    }


    public static void copyDir(String oldPath, String newPath) {
        File file = new File(oldPath);
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }


        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                copyDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }

            if (new File(oldPath + file.separator + filePath[i]).isFile()) {
                copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }

        }

    }

    /**
     * 字节流coopy 字节码等二进制文件
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyDirByByteIo(String oldPath, String newPath) {
        File file = new File(oldPath);
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                copyDirByByteIo(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }

            if (new File(oldPath + file.separator + filePath[i]).isFile()) {
                byteCopy(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }

        }

    }


    public static void byteCopy(String src, String desc) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //输入流读取 E:/3.jpg
            fis = new FileInputStream(src);
            //输出流
            fos = new FileOutputStream(desc);
            //字节缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 在指定路径下创建一个文件夹
     *
     * @param path
     * @param folder
     */
    public static void makeDir(String path) throws Exception {


        if (ApmStringUtil.isEmpty(path)) {

            throw new Exception("无法创建文件夹，路径path参数为空或null");
        }
        File file = new File(path);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

    }


    /**
     * @param newPackageName
     */
    public void replacePackageNameInJavaFile(String newPackageName, File file) throws Exception {

        if (file == null || ApmStringUtil.isEmpty(newPackageName)) {
            throw new Exception("param new PackageName or file is null or empty");
        }

        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
            return;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                replacePackageNameInJavaFile(newPackageName, file);
            } else {
                boolean javaFile = validateJavaFile(file);
                if (javaFile) {
                    //替换java 代码的第一行，package ***


                }
            }
        }


    }


    public void replaceJavaFilePackage(String packageName, File file) throws Exception {

        if (file == null || ApmStringUtil.isEmpty(packageName)) {
            throw new Exception("param new PackageName or file is null or empty");
        }


    }


    /**
     * 创建文
     */
    public static void createFolder(String path) throws Exception {

        if (ApmStringUtil.isEmpty(path)) {
            throw new Exception("path 参数为null 或为空");
        }

        File folderFile = new File(path);

        //已经存在该文件夹
        if (folderFile.exists() && folderFile.isDirectory()) {
            return;
        }

        folderFile.mkdirs();
    }

    /**
     * copy 文件,同时替换包名
     *
     * @param srcFile
     * @param desctionFile
     * @throws IOException
     */
    public static void copyFileWithUpdateFile(String src, String desction, PluginInfo pluginInfo) {

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File srcFile = new File(src);
            File desctionFile = new File(desction);
            br = new BufferedReader(new FileReader(srcFile));
            bw = new BufferedWriter(new FileWriter(desctionFile));
            String str = null;


            ApmProperties apmProperties = ApmProperties.getApmProperties();
            String oldPackageName = (String) apmProperties.get(CreatePluginImpl.TEMPLATE_PACKAGE_NAME_KEY);

            //定义一个标志位，避免过多判断
            boolean replaceFlag = false;
            while ((str = br.readLine()) != null) {

                //如果是java 文件
                if (!replaceFlag && src.endsWith(".java")) {
                    //替换包名
                    if (str.contains(oldPackageName) && str.contains("package")) {

                        str = str.replace(oldPackageName, pluginInfo.getFirstPluginPackageName() + "." + oldPackageName);
                        replaceFlag = true;
                    }
                }

                //替换meta-inf 中的service 文件内容
                if (src.contains("com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin")
                        || src.contains("com.navercorp.pinpoint.common.trace.TraceMetadataProvider")
                        ) {

                    str = str.replace(oldPackageName, pluginInfo.getFirstPluginPackageName() + "." + oldPackageName);
                }
                bw.write(str + "\r\n");
            }
        } catch (IOException e) {
            logger.error("插件创建失败！！！！" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    logger.error("插件创建失败！！！！" + e1.getMessage());
                    e1.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e2) {
                    logger.error("插件创建失败！！！！" + e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }


    /**
     * 字符流 copy 文件,每行添加了换行符号
     *
     * @param srcFile
     * @param desctionFile
     * @throws IOException
     */
    public static void copyFileWithUpdate(String src, String desction) {

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File srcFile = new File(src);
            File desctionFile = new File(desction);
            br = new BufferedReader(new FileReader(srcFile));
            bw = new BufferedWriter(new FileWriter(desctionFile));
            String str = null;


            ApmProperties apmProperties = ApmProperties.getApmProperties();
            String oldPackageName = (String) apmProperties.get(CreatePluginImpl.TEMPLATE_PACKAGE_NAME_KEY);

            //定义一个标志位，避免过多判断
            boolean replaceFlag = false;
            while ((str = br.readLine()) != null) {

                bw.write(str + "\r\n");  //加上换行符号
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * copy 文件，不修改
     *
     * @param src
     * @param desc
     */
    public static void copyFile(String src, String desc) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File srcFile = new File(src);
            File desctionFile = new File(desc);
            br = new BufferedReader(new FileReader(srcFile));
            bw = new BufferedWriter(new FileWriter(desctionFile));
            String str = null;
            while ((str = br.readLine()) != null) {
                bw.write(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    判断是否是java 文件
     */
    public boolean validateJavaFile(File file) {
        if (file.isFile() && file.getAbsolutePath().endsWith("*.java")) {
            return true;
        }
        return false;
    }


    /**
     * 删除path 下的 所有文件，文件夹，保留path这个空文件夹,删除失败，抛出异常
     *
     * @param path
     * @throws Exception
     */
    public static void deleteFolders(String path) throws Exception {


        //删除该文件假及文件夹下的所有东西
        delFolder(path);

        //创建一个该空文件夹
        File emptyFolder = new File(path);
        boolean exists = emptyFolder.exists();
        if (exists) {
            //说明没删除干净
            throw new Exception("deleteFolders fail,删除操作后发现该文件任然存在，表面删除失败，请确认！！");
        } else {
            //删除成功
            emptyFolder.mkdir();
        }
    }


    /**
     * 删除某个 文件夹 及文件夹下的所有文件
     * <p>
     * eg:参数是c://user 会将user 也删除。
     *
     * @param path
     * @return
     */
    private static void delFolder(String folderPath) {

        delAllFile(folderPath); //删除完里面所有内容
        String filePath = folderPath;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        myFilePath.delete(); //删除空文件夹

    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + File.separator + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    @Deprecated
    public static void main(String[] args) {

        delFolder("C:\\kingdee\\workspace\\idea\\apmweb\\apmweb\\src\\main\\resources\\plugin\\workspace\\jdk1.6");
    }

    /**
     * 创建配置文件，返回是否成功。
     *
     * @param filePath
     * @param pluginConfigurationProperties
     * @return
     */
    public static void createFileByProperty(String filePath, Properties pluginConfigurationProperties) throws Exception {

        boolean flag = true;
        if (pluginConfigurationProperties == null) {
            pluginConfigurationProperties = new Properties();
            pluginConfigurationProperties.setProperty("aa", "bb");

        }

        FileWriter fos = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileWriter(file);


            Set<Object> keySet = pluginConfigurationProperties.keySet();

            for (Object key : keySet) {
                StringBuilder sb = new StringBuilder();
                String k = (String) key;
                String value = (String) pluginConfigurationProperties.get(k);
                sb.append(k).append("=").append(value).append("\r\n");

                fos.write(sb.toString());


            }

        } catch (IOException e) {
            flag = false;
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                flag = false;
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        if (!flag) {
            throw new Exception("为插件创建配置文件失败！！！");
        }

    }
}

