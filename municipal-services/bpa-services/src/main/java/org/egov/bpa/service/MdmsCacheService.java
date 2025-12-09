package org.egov.bpa.service;

import java.util.concurrent.ConcurrentHashMap;

import org.egov.bpa.util.BPAUtil;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for caching MDMS (Master Data Management System) responses.
 * <p>
 * This avoids repeated MDMS API calls for the same tenant and improves performance.
 * The cache is maintained in memory using {@link ConcurrentHashMap}, ensuring
 * thread-safety in concurrent environments.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *     <li>Caches MDMS data against tenantId.</li>
 *     <li>Uses computeIfAbsent for atomic cache insertions.</li>
 *     <li>Provides cache clearing method for refreshing stale data.</li>
 * </ul>
 */
@Service
public class MdmsCacheService {

    private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    @Autowired
    private BPAUtil util;

    public Object getMdmsData(RequestInfo requestInfo, String tenantId) {
        return cache.computeIfAbsent(tenantId, key -> {
          
            return util.mDMSCall(requestInfo, key);
        });
    }

    public void clearCache() {
        cache.clear();
    }
}
