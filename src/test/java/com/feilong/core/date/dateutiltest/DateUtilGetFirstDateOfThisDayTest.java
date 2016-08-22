/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.date.dateutiltest;

import static java.util.Calendar.DAY_OF_MONTH;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import com.feilong.core.date.BaseDateUtilTest;
import com.feilong.core.date.DateUtil;

import static com.feilong.core.date.DateUtil.toDate;

import static com.feilong.core.DatePattern.COMMON_DATE_AND_TIME;

/**
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class DateUtilGetFirstDateOfThisDayTest extends BaseDateUtilTest{

    @Test
    public void testGetFirstDateOfThisDay(){
        assertEquals(
                        toDate("2016-08-22 00:00:00", COMMON_DATE_AND_TIME),
                        DateUtil.getFirstDateOfThisDay(toDate("2016-08-22 01:00:00", COMMON_DATE_AND_TIME)));
    }

    @Test
    public void testGetFirstDateOfThisDay1(){
        logDate(DateUtil.getFirstDateOfThisDay(new Date()));
        logDate(DateUtils.truncate(new Date(), DAY_OF_MONTH));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFirstDateOfThisDayNull(){
        DateUtil.getFirstDateOfThisDay(null);
    }
}