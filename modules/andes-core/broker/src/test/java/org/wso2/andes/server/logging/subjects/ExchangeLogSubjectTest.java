/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.wso2.andes.server.logging.subjects;

import org.wso2.andes.server.exchange.Exchange;
import org.wso2.andes.server.registry.ApplicationRegistry;
import org.wso2.andes.server.virtualhost.VirtualHost;


/**
 * Validate ExchangeLogSubjects are logged as expected
 */
public class ExchangeLogSubjectTest extends AbstractTestLogSubject
{
    Exchange _exchange;
    VirtualHost _testVhost;

    public void setUp() throws Exception
    {
        super.setUp();

        _testVhost = ApplicationRegistry.getInstance().getVirtualHostRegistry().
                getVirtualHost("test");

        _exchange = _testVhost.getExchangeRegistry().getDefaultExchange();
        _subject = new ExchangeLogSubject(_exchange,_testVhost);
    }

    /**
     * Validate that the logged Subject  message is as expected:
     * MESSAGE [Blank][vh(/test)/ex(direct/<<default>>)] <Log Message>
     * @param message the message whos format needs validation
     */
    @Override
    protected void validateLogStatement(String message)
    {
        verifyVirtualHost(message, _testVhost);
        verifyExchange(message, _exchange);
    }
}
