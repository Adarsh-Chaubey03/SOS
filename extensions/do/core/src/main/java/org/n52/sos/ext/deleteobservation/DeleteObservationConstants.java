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
package org.n52.sos.ext.deleteobservation;

import static java.util.Collections.unmodifiableSet;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 *         J&uuml;rrens</a>
 * 
 * @since 1.0.0
 */
public interface DeleteObservationConstants {

    String NS_SOSDO_1_0 = "http://www.opengis.net/sosdo/1.0";

    String NS_SOSDO_PREFIX = "sosdo";
    
    String NS_SOSDO_2_0 = "http://www.opengis.net/sosdo/2.0";

    String PARAM_OBSERVATION = "observation";
    String PARAM_PROCEDURE = "procedure";
    String PARAM_OBSERVED_PROPERTY= "observedProperty";
    String PARAM_FEATURE_OF_INTEREST = "featureOfInterest";
    String PARAM_OFFERING = "offering";
    String PARAM_TEMPORAL_FILTER = "temporalFilter";

    enum Operations {
        DeleteObservation;

        public static boolean contains(final String s) {
            for (final Enum<?> p : values()) {
                if (p.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    String CONFORMANCE_CLASS_10 = "http://www.opengis.net/extension/SOSDO/1.0/observationDeletion";
    
    String CONFORMANCE_CLASS_20 = "http://www.opengis.net/extension/SOSDO/2.0/observationDeletion";

    Set<String> CONFORMANCE_CLASSES = unmodifiableSet(Sets.newHashSet(CONFORMANCE_CLASS_10, CONFORMANCE_CLASS_20));

    String NS_SOSDO_1_0_SCHEMA_LOCATION = "http://52north.org/schema/sosdo/1.0/sosdo.xsd";
    
    String NS_SOSDO_2_0_SCHEMA_LOCATION = "http://52north.org/schema/sosdo/2.0/sosdo.xsd";

}