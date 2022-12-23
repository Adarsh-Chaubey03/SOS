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
package org.n52.sos.ds.observation;

import org.n52.series.db.beans.UnitEntity;
import org.n52.series.db.beans.quality.QualityEntity;
import org.n52.series.db.beans.quality.QuantityQualityEntity;
import org.n52.series.db.beans.quality.TextQualityEntity;
import org.n52.shetland.ogc.UoM;
import org.n52.shetland.ogc.swe.simpleType.SweAbstractSimpleType;
import org.n52.shetland.ogc.swe.simpleType.SweQuality;
import org.n52.shetland.ogc.swe.simpleType.SweQuantity;
import org.n52.shetland.ogc.swe.simpleType.SweText;

public class ObservationQualityVisitorCreator {

    public SweQuality visit(QualityEntity q) {
        if (q instanceof QuantityQualityEntity) {
            return visit((QuantityQualityEntity) q);
//        } else if (q instanceof BlobDataEntity) {
//            return visit((BlobDataEntity) q);
//        } else if (q instanceof BooleanDataEntity) {
//            return visit((BooleanDataEntity) q);
//        } else if (q instanceof CategoryDataEntity) {
//            return visit((CategoryDataEntity) q);
//        } else if (q instanceof ComplexDataEntity) {
//            return visit((ComplexDataEntity) q);
//        } else if (q instanceof CountDataEntity) {
//            return visit((CountDataEntity) q);
//        } else if (q instanceof GeometryDataEntity) {
//            return visit((GeometryDataEntity) q);
        } else if (q instanceof TextQualityEntity) {
            return visit((TextQualityEntity) q);
//        } else if (q instanceof DataArrayDataEntity) {
//            return visit((DataArrayDataEntity) q);
//        } else if (q instanceof ProfileDataEntity) {
//            return visit((ProfileDataEntity) q);
//        } else if (q instanceof TrajectoryDataEntity) {
//            return visit((TrajectoryDataEntity) q);
//        } else if (q instanceof ReferencedDataEntity) {
//            return visit((ReferencedDataEntity) q);
        }
        return null;
    }

    public SweQuantity visit(QuantityQualityEntity q) {
        return visit(q, new SweQuantity());
    }

    public SweText visit(TextQualityEntity q) {
        return visit(q, new SweText());
    }

    protected SweQuantity visit(QuantityQualityEntity q, SweQuantity v) {
        addAdditonalQualityEntity(q, v);
        v.setValue(q.getValue());
        if (q.isSetUnit()) {
            v.setUom(getUnit(q.getUnit()));
        }
        return v;
    }

    protected SweText visit(TextQualityEntity q, SweText v) {
        addAdditonalQualityEntity(q, v);
        v.setValue(q.getValue());
        return v;
    }

    @SuppressWarnings("rawtypes")
    protected void addAdditonalQualityEntity(QualityEntity<?> q, SweAbstractSimpleType v) {
        if (q.isSetIdentifier()) {
            v.setIdentifier(q.getIdentifier());
        }
        if (q.isSetName()) {
            v.setName(q.getName());
        }
        if (q.isSetDescription()) {
            v.setDescription(q.getDescription());
        }
    }

    protected UoM getUnit(UnitEntity unit) {
        UoM uom = new UoM(unit.getUnit());
        return uom;
    }
}
