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
package org.n52.sos.ds.hibernate.dao.observation.series;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.n52.series.db.beans.DatasetEntity;
import org.n52.shetland.ogc.sos.request.AbstractObservationRequest;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class ValueQueryContext {

    private DatasetEntity dataset;
    private AbstractObservationRequest request;
    private Criterion temporalFilterCriterion;
    private int chunkSize;
    private int currentRow;
    private Session session;

    public ValueQueryContext(AbstractObservationRequest request, DatasetEntity dataset, Session session) {
        this.request = request;
        this.dataset = dataset;
        this.session = session;
    }

    public DatasetEntity getDataset() {
        return dataset;
    }

    public Long getDatasetId() {
        return getDataset().getId();
    }

    public AbstractObservationRequest getRequest() {
        return request;
    }

    public Session getSession() {
        return session;
    }

    public Criterion getTemporalFilterCriterion() {
        return temporalFilterCriterion;
    }

    public ValueQueryContext setTemporalFilterCriterion(Criterion temporalFilterCriterion) {
        this.temporalFilterCriterion = temporalFilterCriterion;
        return this;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public ValueQueryContext setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
        return this;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public ValueQueryContext setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
        return this;
    }

}
