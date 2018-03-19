package apm.web.core.plugin;

import apm.web.beans.module.ApmPlugin;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 15:00  2018/3/10
 * @description
 */
public class PluginInfo {

     private ApmPlugin apmPlugin;

    //插件第一个包名
    private String firstPluginPackageName;

    //插件名称
    private String pluginName;

    //插件生成时间
    private Long createTime;

    //插件的埋点全类名和方法名。用，隔开。
    private List<String> detectors;

    //META-INF/services中的内容
    private String profilerPluginImpQualifiedName;
    private String traceMetadataProviderQualifiedName;


    //插件的包名，此包名为替换后的，即插件中的包名
    private String packageName;

  /*  private String annotationKeyName;
    private String annotationKeyCode;


    public String getAnnotationKeyName() {
        return annotationKeyName;
    }

    public void setAnnotationKeyName(String annotationKeyName) {
        this.annotationKeyName = annotationKeyName;
    }

    public String getAnnotationKeyCode() {
        return annotationKeyCode;
    }

    public void setAnnotationKeyCode(String annotationKeyCode) {
        this.annotationKeyCode = annotationKeyCode;
    }*/

    public String getProfilerPluginImpQualifiedName() {
        return profilerPluginImpQualifiedName;
    }

    public void setProfilerPluginImpQualifiedName(String profilerPluginImpQualifiedName) {
        this.profilerPluginImpQualifiedName = profilerPluginImpQualifiedName;
    }

    public String getTraceMetadataProviderQualifiedName() {
        return traceMetadataProviderQualifiedName;
    }

    public void setTraceMetadataProviderQualifiedName(String traceMetadataProviderQualifiedName) {
        this.traceMetadataProviderQualifiedName = traceMetadataProviderQualifiedName;
    }


    public String getFirstPluginPackageName() {
        return firstPluginPackageName;
    }

    public void setFirstPluginPackageName(String firstPluginPackageName) {
        this.firstPluginPackageName = firstPluginPackageName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<String> getDetectors() {
        return detectors;
    }

    public void setDetectors(List<String> detectors) {
        this.detectors = detectors;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ApmPlugin getApmPlugin() {
        return apmPlugin;
    }

    public void setApmPlugin(ApmPlugin apmPlugin) {
        this.apmPlugin = apmPlugin;
    }
}
