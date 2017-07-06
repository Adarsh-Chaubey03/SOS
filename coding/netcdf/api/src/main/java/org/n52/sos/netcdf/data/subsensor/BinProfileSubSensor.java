/*
 * Copyright (C) 2012-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
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
package org.n52.sos.netcdf.data.subsensor;

/**
 * Implementation of {@link ProfileSubSensor} for bin profile sub sensors. From
 * a line string geometry.
 *
 * @author <a href="mailto:shane@axiomdatascience.com">Shane StClair</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.4.0
 *
 */
public class BinProfileSubSensor extends ProfileSubSensor {
    private double topHeight;
    private double bottomHeight;

    public BinProfileSubSensor(double topHeight, double bottomHeight) {
        this.topHeight = topHeight;
        this.bottomHeight = bottomHeight;
    }

    public double getTopHeight() {
        return topHeight;
    }

    public double getBottomHeight() {
        return bottomHeight;
    }

    @Override
    public double getHeight() {
        return (topHeight + bottomHeight) / 2.0;
    }

    public double getBinHeight() {
        return topHeight - bottomHeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(bottomHeight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(topHeight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj) {
        } else {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BinProfileSubSensor other = (BinProfileSubSensor) obj;
        return Double.doubleToLongBits(bottomHeight) == Double.doubleToLongBits(other.bottomHeight) &&
               Double.doubleToLongBits(topHeight) == Double.doubleToLongBits(other.topHeight);
    }

    @Override
    public String toString() {
        return "BinProfileSubSensor [topHeight=" + topHeight
                + ", bottomHeight=" + bottomHeight + "]";
    }
}