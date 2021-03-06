/*
 * Copyright 2010 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.metrics.collector.binder;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.ning.metrics.collector.endpoint.extractors.QueryParameterEventExtractor;
import com.ning.metrics.collector.events.parsing.ThriftEnvelopeEventParser;

import java.lang.annotation.Annotation;

public class ExternalEventExtractorProvider implements Provider<QueryParameterEventExtractor>
{
    private Injector injector;
    private Key<ThriftEnvelopeEventParser> thriftEnvelopeEventParserKey;

    public ExternalEventExtractorProvider(Annotation thriftEnvelopeEventParserAnnotation)
    {
        this.thriftEnvelopeEventParserKey = Key.get(ThriftEnvelopeEventParser.class, thriftEnvelopeEventParserAnnotation);
    }

    public ExternalEventExtractorProvider(Class<? extends Annotation> thriftEnvelopeEventParserAnnotation)
    {
        this.thriftEnvelopeEventParserKey = Key.get(ThriftEnvelopeEventParser.class, thriftEnvelopeEventParserAnnotation);
    }

    @Inject
    public void configure(Injector injector)
    {
        this.injector = injector;
    }

    @Override
    public QueryParameterEventExtractor get()
    {
        return new QueryParameterEventExtractor(injector.getInstance(thriftEnvelopeEventParserKey));
    }
}
