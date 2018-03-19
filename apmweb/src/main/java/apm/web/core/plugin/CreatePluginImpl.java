package apm.web.core.plugin;

import apm.web.beans.module.ApmPlugin;
import apm.web.core.plugin.compile.CompileParam;
import apm.web.core.plugin.compile.DynamicCompilerUtil;
import apm.web.core.properties.ApmProperties;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.*;
import java.util.Properties;
import java.util.UUID;

/**
 * @author ChengBing Han
 * @date 18:54  2018/3/9
 * @description
 */
@Service
public class CreatePluginImpl implements CreatePlugin {

    private static final Logger logger = LoggerFactory.getLogger(CreatePluginImpl.class);


    //模板包名，是插件模板代码的包名，生成插件的的包名是插件名+模板包名作为新包名。
    public final static String TEMPLATE_PACKAGE_NAME_KEY = "plugin_template_package_name";

    public final static String FIRST_PACKAGE_PREFIX = "apm";


    public static String HOME_PATH = "C:\\kingdee\\workspace\\idea\\apmweb\\apmweb\\src\\main\\resources\\plugin";

    public final String TEMPLAT_FOLDER_NAME = "template";
    public final String WORKSPACE_FOLDER_NAME = "workspace";
    public final String OUTPUT_FOLDER_NAME = "output";
    public final String META_INF_FOLDER_NAME = "META-INF";
    public final String REPOSITORY_FOLDER_NAME = "pluginRepository";
    public final String DEPENDENCY_JAR_FOLDER_NAME = "dependency" + File.separator + "lib";

    // 打包输出的class,输出到插件的第一个包名加上前缀名。
    public final String PLUGIN_PREFIX_NAME = "plugin_";

    //插件配置文件对于properties 对象。
    private Properties pluginConfigurationProperties;


    //模板路径
    String templateFolderPath;
    //工作空间路径
    String workspaceFolderPath;


    //1.1插件信息
    PluginInfo pluginInfo = new PluginInfo();




    /**
     * 是否遇到异常；
     *
     * @return
     */
    @Override
    public Boolean start() {


        /**
         * 第二步、copy 模板代码到工作区，并替换代码
         */

        try {

            /* 2.1copy 模板中的文件到工作区，替换java 代码前的包名package xx.xx.xx，  替换meta-inf 中的service 文件
            copy 模板代码中，的代码等到工作空间的新添加的第一个包名（该包名即每个插件的第一个包名）文件夹下。
            */
            PluginIoOperateUtils.copyWithUpddateDir(templateFolderPath, workspaceFolderPath + File.separator + pluginInfo.getFirstPluginPackageName(), pluginInfo);
        } catch (Exception e) {

            //发生错误，清空
            try {
                giveUp();
            } catch (Exception e1) {
                logger.error("插件创建失败，请检查plugin工作文件夹，输出文件夹是否有未删除的文件，以免影响下次创建" + e1.getMessage());
                e1.printStackTrace();
            }
            logger.error("从模板区copy 模板工程文件到工作区失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }


        /*
        第三步：编译
         */
        String outputFolder = HOME_PATH + File.separator + OUTPUT_FOLDER_NAME + File.separator + pluginInfo.getPluginName();
        try {

            /*
           3、1 配置编译参数
             */
            CompileParam compileParam = new CompileParam();
            compileParam.setCompileEncoding("UTF-8");
            compileParam.setSourceDir(workspaceFolderPath + File.separator + pluginInfo.getFirstPluginPackageName());
            compileParam.setFilePath(workspaceFolderPath);
            compileParam.setJarPath(HOME_PATH + File.separator + DEPENDENCY_JAR_FOLDER_NAME);
            compileParam.setTargetDir(HOME_PATH + File.separator + OUTPUT_FOLDER_NAME + File.separator + pluginInfo.getPluginName());
            DynamicCompilerUtil dynamicCompilerUtil = new DynamicCompilerUtil();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            /*
            3.2 执行编译
             */
            //创建插件文件夹
            PluginIoOperateUtils.createFolder(compileParam.getTargetDir());
            boolean compilerResult = dynamicCompilerUtil.compiler(compileParam.getCompileEncoding(), dynamicCompilerUtil.getJarFiles(compileParam.getJarPath()), compileParam.getFilePath(), compileParam.getSourceDir(), compileParam.getTargetDir(), diagnostics);

            //如果编译失败，放弃创建插件,编译失败的原因可能是依赖的jar 包找不到，或 路径问题
            if (!compilerResult) {
                logger.error("编译插件fail,无法创建插件");
                giveUp();
                return false;
            }
        } catch (Exception e) {
            logger.error("插件创建失败：" + e.getMessage());
            try {
                giveUp();
            } catch (Exception e1) {
                logger.error("插件创建失败：" + e.getMessage());
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }


        String workSpaceFolder = HOME_PATH + File.separator + WORKSPACE_FOLDER_NAME;
        //copy META-INF 到输出路径
        PluginIoOperateUtils.copyDir(workSpaceFolder + File.separator + pluginInfo.getFirstPluginPackageName() + File.separator + META_INF_FOLDER_NAME, outputFolder + File.separator + META_INF_FOLDER_NAME);


        // TODO: 2018/3/11 打成jar 包
        //copy 打包结果到 仓库中
        String repositoryFolder = HOME_PATH + File.separator + REPOSITORY_FOLDER_NAME + File.separator + PLUGIN_PREFIX_NAME + pluginInfo.getFirstPluginPackageName();
        PluginIoOperateUtils.copyDirByByteIo(outputFolder, repositoryFolder);
        // TODO: 2018/3/11  copy 配置文件到类路径下

        //找的.class 文件路径，copy 插件配置文件
        ApmProperties apmProperties = ApmProperties.getApmProperties();
        String packageName = (String) apmProperties.get(TEMPLATE_PACKAGE_NAME_KEY);
        String pathByPackage = packageName.replace(".", File.separator);
        String packagePath = HOME_PATH + File.separator + REPOSITORY_FOLDER_NAME + File.separator + pluginInfo.getPluginName() + File.separator + pluginInfo.getFirstPluginPackageName() + File.separator + pathByPackage + File.separator + PluginConfigurationConstant.PLUGIN_PROPERTIES_FILE_NAME;

        try {
            PluginIoOperateUtils.createFileByProperty(packagePath, getPluginConfigurationProperties());
        } catch (Exception e) {
            logger.error("创建插件的配置文件失败" + e.getMessage());
            try {
                giveUp();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }


        // TODO: 2018/3/9 package
        //jar -cvf Manager.jar *

        return true;

    }


    /**
     * 放弃创建
     */
    @Override
    public void giveUp() {

        clear();
    }


    /**
     * 创建结束
     */
    @Override
    public boolean end() {

        //
        try {
            clear();
        } catch (Exception e) {
            logger.error("插件创建失败" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 清空
     */
    @Override
    public boolean clear() {

        try {

            //清空targt，输出目录
            PluginIoOperateUtils.deleteFolders(HOME_PATH + File.separator + OUTPUT_FOLDER_NAME);

            //清空工作区
            PluginIoOperateUtils.deleteFolders(HOME_PATH + File.separator + WORKSPACE_FOLDER_NAME);
        } catch (Exception e) {
            logger.error("清理插件环境遇到问题，无法创建插件" + e.getMessage());
            e.printStackTrace();
            return false;

        }
        return true;
    }

/*
    void testInit() {


        //获取路径
        //HOME_PATH = ResourceUtils.getFile("classpath:plugin/plugin.txt").getPath();


        //创建一个插件名，该插件名作为插件的第一个包名。
        String firstPluginJarPackage = createFirstPackageName();
        pluginInfo.setFirstPluginJarPackage(firstPluginJarPackage);

        String pluginName = PLUGIN_PREFIX_NAME + firstPluginJarPackage;
        pluginInfo.setPluginName(pluginName);


        //plugin 根目录

        templateFolderPath = HOME_PATH + File.separator + TEMPLAT_FOLDER_NAME;
        workspaceFolderPath = HOME_PATH + File.separator + WORKSPACE_FOLDER_NAME;


    }*/

    public boolean init(ApmPlugin apmPlugin) {

        try {


            pluginInfo.setApmPlugin(apmPlugin);




            //获取路径
            File file = ResourceUtils.getFile("classpath:plugin");
            if (!file.exists()) {
                return false;
            }

            String name = file.getName();

            // TODO: 2018/3/13 测试注释掉 
            //获取路径
            // HOME_PATH = file.getPath();


            //创建一个插件名，该插件名作为插件的第一个包名。
            String firstPackageName = createFirstPackageName();
            pluginInfo.setFirstPluginPackageName(firstPackageName);

            String pluginName = PLUGIN_PREFIX_NAME + firstPackageName;
            pluginInfo.setPluginName(pluginName);

            //新插件的包名
            ApmProperties apmProperties = ApmProperties.getApmProperties();
            String templatePluginPackage = (String) apmProperties.get(pluginInfo.getFirstPluginPackageName() + "." + TEMPLATE_PACKAGE_NAME_KEY);
            pluginInfo.setPackageName(templatePluginPackage);




            //plugin 根目录

            templateFolderPath = HOME_PATH + File.separator + TEMPLAT_FOLDER_NAME;
            workspaceFolderPath = HOME_PATH + File.separator + WORKSPACE_FOLDER_NAME;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;


    }


    /**
     * 生成一个唯一字符串，作为第一个包名，避免多个插件之间冲突。
     */
    public static String createFirstPackageName() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //java 包名不能是数字开头，所以在第一个字符前添加字母a
        String packageName = FIRST_PACKAGE_PREFIX + uuid;
        return packageName;
    }

    public Properties getPluginConfigurationProperties() {
        return pluginConfigurationProperties;
    }

    public void setPluginConfigurationProperties(Properties pluginConfigurationProperties) {

        this.pluginConfigurationProperties = pluginConfigurationProperties;
    }


    public static Properties createPluginConfigurationProperties(ApmPlugin apmPlugin) {

        Properties properties = new Properties();
        properties.setProperty(PluginConfigurationConstant.SERVICENAME_KEY, apmPlugin.getServiceName());
        properties.setProperty(PluginConfigurationConstant.SERVICECODE_KEY, apmPlugin.getServiceCode().toString());
        properties.setProperty(PluginConfigurationConstant.ANNOTATION_KEY_CODE_KEY, apmPlugin.getAnnotationKeyCode().toString());
        properties.setProperty(PluginConfigurationConstant.ANNOTATION_KEY_NAME_KEY, apmPlugin.getAnnotationKeyName());

        //设置埋点
        for (int i = 0; i < apmPlugin.getDetectorList().size(); i++) {
            String detectorKey = PluginConfigurationConstant.DETECTOR_PREFIX + i;
            properties.setProperty(detectorKey, apmPlugin.getDetectorList().get(i));
        }

        return properties;

    }


    @Override
    public PluginInfo createPlugin(ApmPlugin apmPlugin) {


        Boolean correctParam = validateApmPlugin(apmPlugin);
        if (!correctParam) {
            return null;
        }


        //clear 插件环境

        boolean hasClear = clear();
        if (!hasClear) {
            return null;
        }


        //1.2初始化数据
        boolean initWithoutError = init(apmPlugin);
        if (!initWithoutError) {
            return null;
        }


        //封装插件信息到properteis 文件中,用于创建插件的配置文件。
        setPluginConfigurationProperties(null);
        Properties pluginConfigurationProperties = createPluginConfigurationProperties(apmPlugin);
        setPluginConfigurationProperties(pluginConfigurationProperties);

        //开始创建;
        Boolean isNoError = start();
        if (!isNoError) {
            return null;
        }


        //创建结束
        boolean end = end();
        if (!end) {
            return null;
        }

        //插件创建时间
        pluginInfo.setCreateTime(TimeUtil.getCurrentMillisecond());



        //返回插件信息,保存到数据库
        return pluginInfo;

    }

    @Override
    public Boolean validateApmPlugin(ApmPlugin apmPlugin) {

        if (apmPlugin == null) {
            return false;
        }
        if (apmPlugin.getServiceCode() == null || apmPlugin.getServiceCode() <= 0) {
            return false;
        }
        if (ApmStringUtil.isEmpty(apmPlugin.getServiceName())) {

            return false;
        }
        if (apmPlugin.getAnnotationKeyCode() == null || apmPlugin.getAnnotationKeyCode() <= 0) {
            return false;
        }

        if (apmPlugin.getDetectorList() == null || apmPlugin.getDetectorList().size() == 0) {

            return false;
        }


        return true;
    }
}
