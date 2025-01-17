/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context;

import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceId;
import com.navercorp.pinpoint.common.annotations.InterfaceAudience;
import java.util.Objects;
import com.navercorp.pinpoint.profiler.context.id.TraceRoot;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author Woonduk Kang(emeroad)
 */
public class LoggingBaseTraceFactory implements BaseTraceFactory {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final BaseTraceFactory baseTraceFactory;

    public static BaseTraceFactory wrap(BaseTraceFactory baseTraceFactory) {
        Objects.requireNonNull(baseTraceFactory, "baseTraceFactory");

        return new LoggingBaseTraceFactory(baseTraceFactory);
    }

    private LoggingBaseTraceFactory(BaseTraceFactory baseTraceFactory) {
        this.baseTraceFactory = Objects.requireNonNull(baseTraceFactory, "baseTraceFactory");
    }

    @Override
    public Trace disableSampling() {
        logger.debug("disableSampling()");

        return baseTraceFactory.disableSampling();
    }

    @Override
    public Trace continueTraceObject(TraceId traceId) {
        if (logger.isDebugEnabled()) {
            logger.debug("continueTraceObject(traceId:{})", traceId);
        }

        return baseTraceFactory.continueTraceObject(traceId);
    }


    @Override
    @InterfaceAudience.LimitedPrivate("vert.x")
    public Trace continueAsyncTraceObject(TraceId traceId) {
        if (logger.isDebugEnabled()) {
            logger.debug("continueAsyncTraceObject(traceId:{})", traceId);
        }


        return baseTraceFactory.continueAsyncTraceObject(traceId);
    }

    @Override
    public Trace continueAsyncContextTraceObject(TraceRoot traceRoot, LocalAsyncId localAsyncId, boolean canSampled) {
        if (logger.isDebugEnabled()) {
            logger.debug("continueAsyncTraceObject(traceRoot:{}, localAsyncId:{}, canSampled:{})", traceRoot, localAsyncId, canSampled);
        }

        return baseTraceFactory.continueAsyncContextTraceObject(traceRoot, localAsyncId, canSampled);
    }

    @Override
    public Trace newTraceObject() {
        logger.debug("newTraceObject()");

        return baseTraceFactory.newTraceObject();
    }

    @Override
    public Trace newTraceObject(String urlPath) {
        logger.debug("newTraceObject(String urlPath)");
        return baseTraceFactory.newTraceObject(urlPath);
    }

    @Override
    @InterfaceAudience.LimitedPrivate("vert.x")
    public Trace newAsyncTraceObject() {
        logger.debug("newAsyncTraceObject()");

        return baseTraceFactory.newAsyncTraceObject();
    }

    public Trace newAsyncTraceObject(String urlPath) {
        logger.debug("newAsyncTraceObject(String urlPath)");
        return baseTraceFactory.newAsyncTraceObject(urlPath);
    }
}
