/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.e2e.client.admin.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

/**
 * RuleDTO.
 */
public class RuleDTO implements ResourceDTO {
    
    private String id;
    
    private String name;
    
    private String selectorId;
    
    private int matchMode;
    
    private String matchModeName;
    
    private int sort;
    
    @JsonAlias("loged")
    private boolean logged;
    
    private boolean enabled;
    
    private String handle;
    
    private JsonNode ruleConditions;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

    /**
     * get id.
     *
     * @return id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * set id.
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get selectorId.
     *
     * @return selectorId
     */
    public String getSelectorId() {
        return selectorId;
    }

    /**
     * set selectorId.
     *
     * @param selectorId selectorId
     */
    public void setSelectorId(String selectorId) {
        this.selectorId = selectorId;
    }

    /**
     * get matchMode.
     *
     * @return matchMode
     */
    public int getMatchMode() {
        return matchMode;
    }

    /**
     * set matchMode.
     *
     * @param matchMode matchMode
     */
    public void setMatchMode(int matchMode) {
        this.matchMode = matchMode;
    }

    /**
     * get matchModeName.
     *
     * @return matchModeName
     */
    public String getMatchModeName() {
        return matchModeName;
    }

    /**
     * set matchModeName.
     *
     * @param matchModeName matchModeName
     */
    public void setMatchModeName(String matchModeName) {
        this.matchModeName = matchModeName;
    }

    /**
     * get sort.
     *
     * @return sort
     */
    public int getSort() {
        return sort;
    }

    /**
     * set sort.
     *
     * @param sort sort
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * is logged.
     *
     * @return
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * set logged.
     *
     * @param logged logged
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * is enabled.
     *
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * set enabled.
     *
     * @param enabled enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * get handle.
     *
     * @return handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * set handle.
     *
     * @param handle handle
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * get ruleConditions.
     *
     * @return ruleConditions
     */
    public JsonNode getRuleConditions() {
        return ruleConditions;
    }

    /**
     * set ruleConditions.
     *
     * @param ruleConditions ruleConditions
     */
    public void setRuleConditions(JsonNode ruleConditions) {
        this.ruleConditions = ruleConditions;
    }

    /**
     * get dateCreated.
     *
     * @return dateCreated
     */
    @Override
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * set dateCreated.
     *
     * @param dateCreated dateCreated
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * get dateUpdated.
     *
     * @return dateUpdated
     */
    @Override
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * set dateUpdated.
     *
     * @param dateUpdated dateUpdated
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
