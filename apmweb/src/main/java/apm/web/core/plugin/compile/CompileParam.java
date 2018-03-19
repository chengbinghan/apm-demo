package apm.web.core.plugin.compile;

/**
 * @author ChengBing Han
 * @date 16:43  2018/3/11
 * @description
 */
public class CompileParam {



    //编译编码
    private String compileEncoding;
    //文件或者目录
   private String filePath;
    //java源文件存放目录
   private String sourceDir;
    // 需要加载的jar
   private String jarPath;
    //编译后class类文件存放目录
   private String targetDir;


    public String getCompileEncoding() {
        return compileEncoding;
    }

    public void setCompileEncoding(String compileEncoding) {
        this.compileEncoding = compileEncoding;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {

        this.sourceDir = sourceDir;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }
}
