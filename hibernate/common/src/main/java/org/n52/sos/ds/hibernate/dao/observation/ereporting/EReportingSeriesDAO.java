/**
 * Copyright (C) 2012-2018 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.ds.hibernate.dao.observation.ereporting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.n52.sos.aqd.AqdConstants;
import org.n52.sos.aqd.AqdHelper;
import org.n52.sos.aqd.ReportObligationType;
import org.n52.sos.ds.hibernate.dao.observation.ObservationContext;
import org.n52.sos.ds.hibernate.dao.observation.ObservationFactory;
import org.n52.sos.ds.hibernate.dao.observation.series.AbstractSeriesDAO;
import org.n52.sos.ds.hibernate.entities.ereporting.EReportingAssessmentType;
import org.n52.sos.ds.hibernate.entities.ereporting.EReportingSamplingPoint;
import org.n52.sos.ds.hibernate.entities.observation.ereporting.EReportingSeries;
import org.n52.sos.ds.hibernate.entities.observation.series.Series;
import org.n52.sos.ds.hibernate.util.QueryHelper;
import org.n52.sos.exception.CodedException;
import org.n52.sos.exception.ows.OptionNotSupportedException;
import org.n52.sos.gda.GetDataAvailabilityRequest;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.request.GetObservationByIdRequest;
import org.n52.sos.request.GetObservationRequest;
import org.n52.sos.util.CollectionHelper;

public class EReportingSeriesDAO extends AbstractSeriesDAO {

    @Override
    public Class<?> getSeriesClass() {
        return EReportingSeries.class;
    }

    @Override
    public List<Series> getSeries(GetObservationRequest request, Collection<String> features, Session session) throws OwsExceptionReport {
        List<Series> series = new ArrayList<>();
        if (CollectionHelper.isNotEmpty(features)) {
            for (List<String> ids : QueryHelper.getListsForIdentifiers(features)) {
                series.addAll(getSeriesSet(request, ids, session));
            }
        } else {
            series.addAll(getSeriesSet(request, features, session));
        }
        return series;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Series> getSeries(GetObservationByIdRequest request, Session session) throws OwsExceptionReport {
        return getSeriesCriteria(request.getObservationIdentifier(), session).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Series> getSeries(Collection<String> identifiers, Session session) throws OwsExceptionReport {
        return getSeriesCriteria(identifiers, session).list();
    }

    @Override
    public List<Series> getSeries(GetDataAvailabilityRequest request, Session session)
            throws OwsExceptionReport {
        return new ArrayList<>(getSeriesCriteria(request, session));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Series> getSeries(String observedProperty, Collection<String> features, Session session) {
        if (CollectionHelper.isNotEmpty(features)) {
            List<Series> series = new ArrayList<>();
            for (List<String> ids : QueryHelper.getListsForIdentifiers(features)) {
                series.addAll(getSeriesCriteria(observedProperty, ids, session).list());
            }
            return series;
        } else {
            return getSeriesCriteria(observedProperty, features, session).list();
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Series> getSeries(String procedure, String observedProperty, String offering, Collection<String> features, Session session) {
        if (CollectionHelper.isNotEmpty(features)) {
            List<Series> series = new ArrayList<>();
            for (List<String> ids : QueryHelper.getListsForIdentifiers(features)) {
                series.addAll(getSeriesCriteria(procedure, observedProperty, offering, ids, session).list());
            }
            return series;
        } else {
            return getSeriesCriteria(procedure, observedProperty, offering, features, session).list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Series> getSeries(Collection<String> procedures, Collection<String> observedProperties,
            Collection<String> features, Session session) {
        if (CollectionHelper.isNotEmpty(features)) {
            List<Series> series = new ArrayList<>();
            for (List<String> ids : QueryHelper.getListsForIdentifiers(features)) {
                series.addAll(getSeriesCriteria(procedures, observedProperties, ids, session).list());
            }
            return series;
        } else {
            return getSeriesCriteria(procedures, observedProperties, features, session).list();
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Series> getSeries(Collection<String> procedures, Collection<String> observedProperties,
            Collection<String> features, Collection<String> offerings, Session session) {
        if (CollectionHelper.isNotEmpty(features)) {
            List<Series> series = new ArrayList<>();
            for (List<String> ids : QueryHelper.getListsForIdentifiers(features)) {
                series.addAll(getSeriesCriteria(procedures, observedProperties, ids, offerings, session).list());
            }
            return series;
        } else {
            return getSeriesCriteria(procedures, observedProperties, features, offerings, session).list();
        }
    }

    @Override
    public EReportingSeries getSeriesFor(String procedure, String observableProperty, String featureOfInterest,
            Session session) {
        return (EReportingSeries) getSeriesCriteriaFor(procedure, observableProperty, featureOfInterest, session).uniqueResult();
    }

    @Override
    public EReportingSeries getOrInsertSeries(ObservationContext identifiers, Session session) throws CodedException {
        return (EReportingSeries) super.getOrInsert(identifiers, session);
    }

    /**
     * Add EReportingSamplingPoint restriction to Hibernate Criteria
     *
     * @param c
     *            Hibernate Criteria to add restriction
     * @param samplingPoint
     *            EReportingSamplingPoint identifier to add
     */
    public void addEReportingSamplingPointToCriteria(Criteria c, String samplingPoint) {
        c.createCriteria(EReportingSeries.SAMPLING_POINT).add(Restrictions.eq(EReportingSamplingPoint.IDENTIFIER, samplingPoint));

    }

    /**
     * Add EReportingSamplingPoint restriction to Hibernate Criteria
     *
     * @param c
     *            Hibernate Criteria to add restriction
     * @param samplingPoint
     *            EReportingSamplingPoint to add
     */
    public void addEReportingSamplingPointToCriteria(Criteria c, EReportingSamplingPoint samplingPoint) {
        c.add(Restrictions.eq(EReportingSeries.SAMPLING_POINT, samplingPoint));
    }

    /**
     * Add EReportingSamplingPoint restriction to Hibernate Criteria
     *
     * @param c
     *            Hibernate Criteria to add restriction
     * @param samplingPoints
     *            EReportingSamplingPoint identifiers to add
     */
    public void addEReportingSamplingPointToCriteria(Criteria c, Collection<String> samplingPoints) {
        c.createCriteria(EReportingSeries.SAMPLING_POINT).add(Restrictions.in(EReportingSamplingPoint.IDENTIFIER, samplingPoints));
    }

    @Override
    protected void addSpecificRestrictions(Criteria c, GetObservationRequest request) throws CodedException {
        if (request.isSetResponseFormat() && AqdConstants.NS_AQD.equals(request.getResponseFormat())) {
            ReportObligationType flow = AqdHelper.getInstance().getFlow(request.getExtensions());
            if (ReportObligationType.E1A.equals(flow) || ReportObligationType.E2A.equals(flow)) {
                addAssessmentType(c, AqdConstants.AssessmentType.Fixed.name());
            } else if (ReportObligationType.E1B.equals(flow)) {
                addAssessmentType(c, AqdConstants.AssessmentType.Model.name());
            } else {
                throw new OptionNotSupportedException().withMessage("The requested e-Reporting flow %s is not supported!", flow.name());
            }
        }
    }
    
    @Override
    public ObservationFactory getObservationFactory() {
        return EReportingObservationFactory.getInstance();
    }
    
    private void addAssessmentType(Criteria c, String assessmentType) {
        c.createCriteria(EReportingSeries.SAMPLING_POINT).createCriteria(EReportingSamplingPoint.ASSESSMENTTYPE).
        add(Restrictions.ilike(EReportingAssessmentType.ASSESSMENT_TYPE, assessmentType));
    }
    
}
