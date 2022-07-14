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
package org.n52.sos.aquarius.pojo.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.Interval;
import org.n52.sos.aquarius.ds.AquariusTimeHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GradeCode", "StartTime", "EndTime" })
public class Grade implements Serializable, AquariusTimeHelper {

    private static final long serialVersionUID = -1327261619367370030L;

    @JsonProperty("GradeCode")
    private String gradeCode;

    @JsonProperty("StartTime")
    private String startTime;

    @JsonProperty("EndTime")
    private String endTime;
    
    @JsonIgnore
    private Interval interval;
    
    @JsonIgnore
    private String displayName;
    
    @JsonIgnore
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public Grade() {
    }

    public Grade(String gradeCode, String startTime, String endTime) {
        super();
        this.gradeCode = gradeCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @JsonProperty("GradeCode")
    public String getGradeCode() {
        return gradeCode;
    }

    @JsonProperty("GradeCode")
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    @JsonProperty("StartTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("StartTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("EndTime")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("EndTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    @JsonIgnore
    public String getDisplayName() {
        return Strings.isNullOrEmpty(displayName) ? getGradeCode() : displayName;
    }

    @JsonIgnore
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonIgnore
    public String getDescription() {
        return Strings.isNullOrEmpty(description) ? getGradeCode() : description;
    }

    @JsonIgnore
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Point applyGrade(Point point) {
        if (getInterval().contains(checkDateTimeStringFor24(point.getTimestamp()))) {
            point.addGrade(this);
        }
        return point;
    }

    @JsonIgnore
    private Interval getInterval() {
        if (interval == null) {
            this.interval = new Interval(checkDateTimeStringFor24(getStartTime()),
                    checkDateTimeStringFor24(getEndTime()).plusMillis(1));
        }
        return interval;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("gradeCode", gradeCode)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(startTime)
                .append(gradeCode)
                .append(endTime)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Grade)) {
            return false;
        }
        Grade rhs = (Grade) other;
        return new EqualsBuilder().append(startTime, rhs.startTime)
                .append(gradeCode, rhs.gradeCode)
                .append(endTime, rhs.endTime)
                .isEquals();
    }

}