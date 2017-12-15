/**
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
package org.n52.sos.ogc.sos;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * SOS internal representation of SOS insertion metadata
 * 
 * @since 4.0.0
 */
public class SosInsertionMetadata {

    /**
     * list of valid feature types
     */
    private Set<String> featureOfInterestTypes;

    /**
     * list of valid observation types
     */
    private Set<String> observationTypes;

    /**
     * constructor
     */
    public SosInsertionMetadata() {
        super();
    }

    /**
     * @return the featureOfInterestTypes
     */
    public Set<String> getFeatureOfInterestTypes() {
        return featureOfInterestTypes;
    }

    /**
     * @param featureOfInterestTypes
     *            the featureOfInterestTypes to set
     */
    public SosInsertionMetadata setFeatureOfInterestTypes(Collection<String> featureOfInterestTypes) {
        this.featureOfInterestTypes = Sets.newHashSet(featureOfInterestTypes);
        return this;
    }

    /**
     * @return the observationTypes
     */
    public Set<String> getObservationTypes() {
        return observationTypes;
    }

    /**
     * @param observationTypes
     *            the observationTypes to set
     */
    public SosInsertionMetadata setObservationTypes(Collection<String> observationTypes) {
        this.observationTypes = Sets.newHashSet(observationTypes);
        return this;
    }
}