package apm.web.apmcontroller.ppcontroller.information;


import apm.web.apmservice.informationservice.*;
import com.navercorp.pinpoint.common.server.bo.stat.*;

import com.navercorp.pinpoint.web.controller.AgentStatController;
import com.navercorp.pinpoint.web.service.stat.*;

import com.navercorp.pinpoint.web.util.TimeWindow;
import com.navercorp.pinpoint.web.util.TimeWindowSampler;
import com.navercorp.pinpoint.web.util.TimeWindowSlotCentricSampler;
import com.navercorp.pinpoint.web.vo.Range;
import com.navercorp.pinpoint.web.vo.stat.chart.StatChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 11:27  2018/2/3
 * @description 服务器响应时间controller
 */

public abstract class AbstractResponseTimeController<T extends AgentStatDataPoint> {

    private final AgentStatService<T> agentStatService;

    private final ApmAgentStatChartService agentStatChartService;

    public AbstractResponseTimeController(AgentStatService<T> agentStatService, ApmAgentStatChartService agentStatChartService) {
        this.agentStatService = agentStatService;
        this.agentStatChartService = agentStatChartService;
    }

    @Controller
    @CrossOrigin
    @RequestMapping("/apmResponseTime")
    public static class ResponseTimeController extends AbstractResponseTimeController<ResponseTimeBo> {

        @Autowired
        public ResponseTimeController(ResponseTimeService responseTimeService, ApmResponseTimeChartService responseTimeChartService) {
            super(responseTimeService, responseTimeChartService);
        }
    }



    @Controller
    @CrossOrigin
    @RequestMapping("/apmTransaction")
    public static class TransactionController extends AbstractResponseTimeController <TransactionBo> {
        @Autowired
        public TransactionController(TransactionService transactionService, ApmTransactionChartService transactionChartService) {
            super(transactionService, transactionChartService);
        }
    }


    @Controller
    @RequestMapping("/apmCpuLoad")
    public static class CpuLoadController extends AbstractResponseTimeController<CpuLoadBo> {
        @Autowired
        public CpuLoadController(CpuLoadService cpuLoadService, ApmCpuLoadChartService cpuLoadChartService) {
            super(cpuLoadService, cpuLoadChartService);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<T> getAgentStat(
            @RequestParam("agentId") String agentId,
            @RequestParam("from") long from,
            @RequestParam("to") long to) {
        Range rangeToScan = new Range(from, to);
        return this.agentStatService.selectAgentStatList(agentId, rangeToScan);
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    @ResponseBody
    public StatChart getAgentStatChart(
            @RequestParam("agentId") String agentId,
            @RequestParam("from") long from,
            @RequestParam("to") long to) {
        TimeWindowSampler sampler = new TimeWindowSlotCentricSampler();
        TimeWindow timeWindow = new TimeWindow(new Range(from, to), sampler);
        return this.agentStatChartService.selectAgentChart(agentId, timeWindow);
    }

    @Controller
    @RequestMapping("/apmActiveTrace")
    public static class ActiveTraceController extends AbstractResponseTimeController<ActiveTraceBo> {
        @Autowired
        public ActiveTraceController(ActiveTraceService activeTraceService, ApmActiveTraceChartService activeTraceChartService) {
            super(activeTraceService, activeTraceChartService);
        }
    }


    @Controller
    @RequestMapping("/apmJvmGc")
    public static class JvmGcController extends AbstractResponseTimeController<JvmGcBo> {
        @Autowired
        public JvmGcController(JvmGcService jvmGcService, ApmJvmGcChartService jvmGcChartService) {
            super(jvmGcService, jvmGcChartService);
        }
    }


    @Controller
    @RequestMapping("/apmJvmGcDetailed")
    public static class JvmGcDetailedController extends AbstractResponseTimeController<JvmGcDetailedBo> {
        @Autowired
        public JvmGcDetailedController(JvmGcDetailedService jvmGcDetailedService, ApmJvmGcDetailedChartService jvmGcDetailedChartService) {
            super(jvmGcDetailedService, jvmGcDetailedChartService);
        }
    }

    @Controller
    @RequestMapping("/apmDataSource")
    public static class DataSourceController extends AbstractResponseTimeController<DataSourceListBo> {
        @Autowired
        public DataSourceController(DataSourceService dataSourceService, ApmDataSourceChartService dataSourceChartService) {
            super(dataSourceService, dataSourceChartService);
        }
    }


    @RequestMapping(value = "/chart", method = RequestMethod.GET, params = {"interval"})
    @ResponseBody
    public StatChart getAgentStatChart(
            @RequestParam("agentId") String agentId,
            @RequestParam("from") long from,
            @RequestParam("to") long to,
            @RequestParam("interval") Integer interval) {
        final int minSamplingInterval = 5;
        final long intervalMs = interval < minSamplingInterval ? minSamplingInterval * 1000L : interval * 1000L;
        TimeWindowSampler sampler = new TimeWindowSampler() {
            @Override
            public long getWindowSize(Range range) {
                return intervalMs;
            }
        };
        TimeWindow timeWindow = new TimeWindow(new Range(from, to), sampler);
        return this.agentStatChartService.selectAgentChart(agentId, timeWindow);
    }

/*
    @Deprecated
    @RequestMapping(value = "/chartList", method = RequestMethod.GET)
    @ResponseBody
    public List<StatChart> getAgentStatChartList(
            @RequestParam("agentId") String agentId,
            @RequestParam("from") long from,
            @RequestParam("to") long to) {
        TimeWindowSampler sampler = new TimeWindowSlotCentricSampler();
        TimeWindow timeWindow = new TimeWindow(new Range(from, to), sampler);
        return this.agentStatChartService.selectAgentChartList(agentId, timeWindow);
    }

    @Deprecated
    @RequestMapping(value = "/chartList", method = RequestMethod.GET, params = {"interval"})
    @ResponseBody
    public List<StatChart> getAgentStatChartList(
            @RequestParam("agentId") String agentId,
            @RequestParam("from") long from,
            @RequestParam("to") long to,
            @RequestParam("interval") Integer interval) {
        final int minSamplingInterval = 5;
        final long intervalMs = interval < minSamplingInterval ? minSamplingInterval * 1000L : interval * 1000L;
        TimeWindowSampler sampler = new TimeWindowSampler() {
            @Override
            public long getWindowSize(Range range) {
                return intervalMs;
            }
        };
        TimeWindow timeWindow = new TimeWindow(new Range(from, to), sampler);
        return this.agentStatChartService.selectAgentChartList(agentId, timeWindow);
    }
*/



}
