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
package org.n52.sos.aquarius.pojo.location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ReferenceStandard", "ReferenceStandardOffsets" })
public class ReferenceStandard implements Serializable {

    private static final long serialVersionUID = 5085241288254172016L;

    @JsonProperty("ReferenceStandard")
    private String referenceStandard;

    @JsonProperty("ReferenceStandardOffsets")
    private List<ReferenceStandardOffset> referenceStandardOffsets = new ArrayList<ReferenceStandardOffset>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ReferenceStandard() {
    }

    public ReferenceStandard(String referenceStandard, Collection<ReferenceStandardOffset> referenceStandardOffsets) {
        super();
        this.referenceStandard = referenceStandard;
        setReferenceStandardOffsets(referenceStandardOffsets);
    }

    @JsonProperty("ReferenceStandard")
    public String getReferenceStandard() {
        return referenceStandard;
    }

    @JsonProperty("ReferenceStandard")
    public void setReferenceStandard(String referenceStandard) {
        this.referenceStandard = referenceStandard;
    }

    @JsonProperty("ReferenceStandardOffsets")
    public List<ReferenceStandardOffset> getReferenceStandardOffsets() {
        return Collections.unmodifiableList(referenceStandardOffsets);
    }

    @JsonProperty("ReferenceStandardOffsets")
    public void setReferenceStandardOffsets(Collection<ReferenceStandardOffset> referenceStandardOffsets) {
        this.referenceStandardOffsets.clear();
        if (referenceStandardOffsets != null) {
            this.referenceStandardOffsets.addAll(referenceStandardOffsets);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("referenceStandard", referenceStandard)
                .append("referenceStandardOffsets", referenceStandardOffsets).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(referenceStandard).append(referenceStandardOffsets).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ReferenceStandard)) {
            return false;
        }
        ReferenceStandard rhs = (ReferenceStandard) other;
        return new EqualsBuilder().append(referenceStandard, rhs.referenceStandard)
                .append(referenceStandardOffsets, rhs.referenceStandardOffsets).isEquals();
    }

}