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

package org.apache.shenyu.admin.service.manager.impl;

import org.apache.shenyu.admin.mapper.PluginMapper;
import org.apache.shenyu.admin.model.bean.UpstreamInstance;
import org.apache.shenyu.admin.model.entity.PluginDO;
import org.apache.shenyu.admin.model.page.CommonPager;
import org.apache.shenyu.admin.model.page.PageParameter;
import org.apache.shenyu.admin.model.vo.SelectorVO;
import org.apache.shenyu.admin.model.vo.ShenyuDictVO;
import org.apache.shenyu.admin.service.SelectorService;
import org.apache.shenyu.admin.service.ShenyuDictService;
import org.apache.shenyu.admin.service.converter.SelectorHandleConverter;
import org.apache.shenyu.admin.service.converter.SelectorHandleConverterFactor;
import org.apache.shenyu.admin.service.manager.ServiceDocManager;
import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.dto.convert.selector.CommonUpstream;
import org.apache.shenyu.common.enums.DataEventTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoadServiceDocEntryImplTest {

    @InjectMocks
    private LoadServiceDocEntryImpl loadServiceDocEntry;

    @Mock
    private SelectorService selectorService;

    @Mock
    private SelectorHandleConverterFactor converterFactor;

    @Mock
    private PluginMapper pluginMapper;

    @Mock
    private ServiceDocManager serviceDocManager;

    @Mock
    private ShenyuDictService shenyuDictService;

    @Test
    public void testLoadApiDocument() {
        ShenyuDictVO shenyuInitData = new ShenyuDictVO();
        shenyuInitData.setDictValue("true");
        when(shenyuDictService.findByDictCodeName(any(), any())).thenReturn(shenyuInitData);
        List<PluginDO> pluginDOList = new ArrayList<>();
        PluginDO pluginDO = new PluginDO();
        pluginDO.setId("1");
        pluginDO.setName("test");
        pluginDOList.add(pluginDO);
        CommonPager<SelectorVO> commonPager = new CommonPager<>();
        List<SelectorVO> list = new ArrayList<>();
        String dateString = "2023-05-06 03:48:48";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String formattedDateString = outputFormat.format(date);
        SelectorVO selectorVO = new SelectorVO(
                "1",
                "1",
                "test",
                1,
                "testMatchMode",
                1,
                "testType",
                1,
                true,
                true,
                true,
                true,
                "[{\"weight\":1}]",
                new ArrayList<>(),
                formattedDateString,
                formattedDateString
        );
        list.add(selectorVO);
        commonPager.setDataList(list);
        commonPager.setPage(new PageParameter(1, 1));
        SelectorHandleConverter selectorHandleConverter = mock(SelectorHandleConverter.class);
        List<CommonUpstream> upstreamList = new ArrayList<>();
        upstreamList.add(new CommonUpstream("testProtocol", "testUpstreamHost", "testUrl", true, 1000L));

        when(selectorHandleConverter.convertUpstream(any())).thenReturn(upstreamList);
        when(converterFactor.newInstance(any())).thenReturn(selectorHandleConverter);
        when(selectorService.listByPage(any())).thenReturn(commonPager);
        when(pluginMapper.selectByNames(any())).thenReturn(pluginDOList);
        loadServiceDocEntry.loadApiDocument();
        verify(serviceDocManager).pullApiDocument((Set<UpstreamInstance>) any());
    }

    @Test
    public void testLoadDocOnSelectorChanged() {
        SelectorData selectorData = new SelectorData();
        selectorData.setId("1");
        selectorData.setName("test");
        selectorData.setEnabled(true);
        selectorData.setHandle("testHandle");
        selectorData.setPluginId("1");
        selectorData.setMatchMode(1);
        List<SelectorData> changedList = new ArrayList<>();
        changedList.add(selectorData);
        DataEventTypeEnum eventType = DataEventTypeEnum.acquireByName("CREATE");
        this.testLoadApiDocument();
        when(shenyuDictService.findByDictCodeName(any(), any())).thenReturn(null);
        loadServiceDocEntry.loadDocOnSelectorChanged(changedList, eventType);
        verify(serviceDocManager).pullApiDocument((Set<UpstreamInstance>) any());
    }
}
