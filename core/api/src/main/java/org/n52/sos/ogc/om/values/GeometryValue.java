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
package org.n52.sos.ogc.om.values;

import org.n52.sos.ogc.UoM;
import org.n52.sos.ogc.gml.AbstractGeometry;
import org.n52.sos.ogc.om.values.visitor.ValueVisitor;
import org.n52.sos.ogc.om.values.visitor.VoidValueVisitor;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.util.JavaHelper;
import org.n52.sos.util.StringHelper;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Geometry measurement representation for observation
 *
 * @since 4.0.0
 *
 */
public class GeometryValue extends AbstractGeometry implements Value<Geometry> {
    private static final long serialVersionUID = 4634315072352929082L;
    /**
     * Unit of measure
     */
    private UoM unit;

    public GeometryValue(AbstractGeometry abstractGeometry) {
        setDescription(abstractGeometry.getDescription());
        setGeometry(abstractGeometry.getGeometry());
        setIdentifier(abstractGeometry.getIdentifierCodeWithAuthority());
        setName(abstractGeometry.getName());
        setGmlId("sp_" + JavaHelper.generateID(toString()));
    }

    /**
     * construcor
     *
     * @param value Geometry value
     */
    public GeometryValue(Geometry value) {
        setValue(value);
        setGmlId("sp_" + JavaHelper.generateID(toString()));
    }

    @Override
    public GeometryValue setValue(Geometry value) {
        setGeometry(value);
        return this;
    }

    @Override
    public Geometry getValue() {
        return getGeometry();
    }

    @Override
    public void setUnit(String unit) {
        this.unit = new UoM(unit);
    }

    @Override
    public String getUnit() {
        if (isSetUnit()) {
            return unit.getUom();
        }
        return null;
    }

    @Override
    public UoM getUnitObject() {
        return this.unit;
    }

    @Override
    public void setUnit(UoM unit) {
        this.unit = unit;
    }

    @Override
    public boolean isSetUnit() {
        return getUnitObject() != null && !getUnitObject().isEmpty();
    }

    @Override
    public String toString() {
        return String
                .format("GeometryValue [value=%s, unit=%s]", getValue(), getUnit());
    }

    @Override
    public boolean isSetValue() {
        return isSetGeometry();
    }

    @Override
    public <X> X accept(ValueVisitor<X> visitor)
            throws OwsExceptionReport {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidValueVisitor visitor)
            throws OwsExceptionReport {
        visitor.visit(this);
    }
}
