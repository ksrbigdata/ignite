/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.rest.handlers.query;

import org.apache.ignite.internal.*;
import org.apache.ignite.internal.processors.rest.*;
import org.apache.ignite.internal.processors.rest.handlers.*;
import org.apache.ignite.internal.processors.rest.request.*;
import org.apache.ignite.internal.util.future.*;
import org.apache.ignite.internal.util.typedef.internal.*;
import org.apache.ignite.lang.*;

import java.util.*;

import static org.apache.ignite.internal.processors.rest.GridRestCommand.*;

/**
 * Query command handler.
 */
public class QueryCommandHandler extends GridRestCommandHandlerAdapter {
    /** Supported commands. */
    private static final Collection<GridRestCommand> SUPPORTED_COMMANDS = U.sealList(EXECUTE_SQL_QUERY);

    /**
     * @param ctx Context.
     */
    public QueryCommandHandler(GridKernalContext ctx) {
        super(ctx);
    }

    /** {@inheritDoc} */
    @Override public Collection<GridRestCommand> supportedCommands() {
        return SUPPORTED_COMMANDS;
    }

    /** {@inheritDoc} */
    @Override public IgniteInternalFuture<GridRestResponse> handleAsync(GridRestRequest req) {
        assert req != null;

        assert SUPPORTED_COMMANDS.contains(req.command());

        switch (req.command()) {
            case EXECUTE_SQL_QUERY: {
                assert req instanceof RestSqlQueryRequest : "Invalid type of query request.";

                return ctx.closure().callAsync(new IgniteClosure<RestSqlQueryRequest, GridRestResponse>() {
                    @Override public GridRestResponse apply(RestSqlQueryRequest req) {
                        try {
                            return new GridRestResponse(); //TODO
                        }
                        catch (Exception e) {
                            return new GridRestResponse(GridRestResponse.STATUS_FAILED, e.getMessage());
                        }
                    }
                }, (RestSqlQueryRequest)req, Collections.singleton(ctx.grid().localNode()));
            }
        }

        return new GridFinishedFuture<>();
    }
}
