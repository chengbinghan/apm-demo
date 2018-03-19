package apm.web.apmservice.monitor.timer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * 
 * @author yangcq
 * @description 由于Spring提供对Quartz的支持，所以直接使用Spring提供的API
 * @description 继承  org.springframework.scheduling.quartz.QuartzJobBean
 *
 */
public class EBankJob extends QuartzJobBean {  
  /**
   * 
   */
  private EBankJobBean eBankJobBean; 
  
  @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {  
    eBankJobBean.printAnotherMessage();  
    }  
  
    public void setEBankJobBean(EBankJobBean eBankJobBean) {  
        this.eBankJobBean = eBankJobBean;  
    }  

} 