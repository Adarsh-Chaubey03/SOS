/*
 * Copyright (C) 2012-2023 52°North Spatial Information Research GmbH
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
package org.n52.sos.ds.hibernate.util.restriction;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import org.n52.sos.ds.hibernate.util.TemporalRestriction;

/**
 * Creates filters according to the following table.
 * <table>
 * <tr>
 * <td><i>Self/Other</i></td>
 * <td><b>Period</b></td>
 * <td><b>Instant</b></td>
 * </tr>
 * <tr>
 * <td><b>Period</b></td>
 * <td>{@code self.begin = other.begin AND self.end = other.end}</td>
 * <td><i>if period is from a reduced precision instant, self.begin &ge; other.begin and self.end &le; other.end,
 * otherwise not defined</i></td>
 * </tr>
 * <tr>
 * <td><b>Instant</b></td>
 * <td><i>not defined</i></td>
 * <td>{@code self.position = other.position}</td>
 * </tr>
 * </table>
 */
public class EqualsRestriction implements TemporalRestriction {
    @Override
    public Criterion filterPeriodWithPeriod(String selfBegin, String selfEnd, Date otherBegin, Date otherEnd) {
        return Restrictions.and(Restrictions.eq(selfBegin, otherBegin),
                                Restrictions.eq(selfEnd, otherEnd));
    }

    @Override
    public Criterion filterPeriodWithPeriod(String selfBegin, String selfEnd, Integer count) {
        return Restrictions.and(Restrictions.eq(selfBegin, getStartPlaceHolder(count)),
                                Restrictions.eq(selfEnd, getEndPlaceHolder(count)));
    }

    @Override
    public Criterion filterPeriodWithInstant(String selfBegin, String selfEnd, Date otherPosition) {
        return filterPeriodWithPeriod(selfBegin, selfEnd, otherPosition, otherPosition);
    }

    @Override
    public Criterion filterPeriodWithInstant(String selfBegin, String selfEnd, Integer count) {
        return Restrictions.and(Restrictions.eq(selfBegin, getInstantPlaceHolder(count)),
                Restrictions.eq(selfEnd, getInstantPlaceHolder(count)));
    }

    @Override
    public Criterion filterInstantWithInstant(String selfPosition, Date otherPosition) {
        return Restrictions.eq(selfPosition, otherPosition);
    }

    @Override
    public Criterion filterInstantWithInstant(String selfPosition, String otherPosition, Integer count) {
        return Restrictions.eq(selfPosition, getInstantPlaceHolder(count));
    }
}
