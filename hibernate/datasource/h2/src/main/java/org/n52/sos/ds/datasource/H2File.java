/*
 * Copyright (C) 2012-2022 52°North Spatial Information Research GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.ds.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Pattern;

import org.n52.faroe.ConfigurationError;

public interface H2File extends HibernateDatasource {

    Pattern JDBC_URL_PATTERN = Pattern
            .compile("^jdbc:h2:file:(.+)$");

    String JDBC_URL_FORMAT = "jdbc:h2:file:%s";

    String USER_HOME = "user.home";

    default boolean checkTableSize(Map<String, Object> settings, Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute("show tables");
            ResultSet resultSet = stmt.getResultSet();
            resultSet.last();
            return resultSet.getRow() <= 1;
        } catch (SQLException ex) {
            throw new ConfigurationError(ex);
        } finally {
            close(stmt);
        }
    }
}
