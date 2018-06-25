package com.apusic.agent.service.detector;

import com.apusic.agent.context.DetectorContext;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChengBing Han
 * @date 11:04  2018/6/22
 * @description
 */
public class DetectorService {

    public void addDetector(Detector detector) {
        final String detectorStr = createDetectorStr(detector);

        final boolean contains = DetectorContext.list.contains(detectorStr);
        if (!contains) {
            DetectorContext.list.add(detectorStr);
        }
    }

    public void removeDetector(Detector detector) {
        final String detectorStr = createDetectorStr(detector);
        final boolean contains = DetectorContext.list.contains(detectorStr);

        if (contains) {
            DetectorContext.list.remove(detectorStr);
        }
    }

    private String createDetectorStr(Detector detector) {
        final String clazz = detector.getClazz();
        final String method = detector.getMethod();
        String detectorStr = clazz + DetectorContext.SEPARTOR + method;
        return detectorStr;
    }
}
