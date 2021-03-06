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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class FeiLongDateUtilSuiteTests.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
@RunWith(Suite.class)
@SuiteClasses({
                DateUtilGetFirstDateOfThisYearTest.class,
                DateUtilGetFirstDateOfThisMonthTest.class,
                DateUtilGetFirstDateOfThisWeekTest.class,
                DateUtilGetFirstDateOfThisDayTest.class,

                DateUtilGetLastDateOfThisYearTest.class,
                DateUtilGetLastDateOfThisMonthTest.class,
                DateUtilGetLastDateOfThisWeekTest.class,
                DateUtilGetLastDateOfThisDayTest.class,

                //*********************************************
                DateUtilGetYearTest.class,
                DateUtilGetMonthTest.class,
                DateUtilGetWeekOfYearTest.class,

                DateUtilGetDayOfYearTest.class,
                DateUtilGetDayOfMonthTest.class,
                DateUtilGetDayOfWeekTest.class,

                DateUtilGetHourOfYearTest.class,
                DateUtilGetHourOfDayTest.class,
                DateUtilGetMinuteTest.class,
                DateUtilGetSecondOfDayTest.class,
                DateUtilGetSecondOfHourTest.class,
                DateUtilGetSecondTest.class,
                DateUtilGetTimeTest.class,

                //*********************************************
                DateUtilGetYearParameterizedTest.class,
                DateUtilGetMonthParameterizedTest.class,
                DateUtilGetWeekOfYearParameterizedTest.class,
                DateUtilGetDayOfYearParameterizedTest.class,
                DateUtilGetDayOfMonthParameterizedTest.class,
                DateUtilGetDayOfWeekParameterizedTest.class,

                DateUtilAddTest.class,

                DateUtilAddYearParameterizedTest.class,
                DateUtilAddMonthParameterizedTest.class,
                DateUtilAddWeekParameterizedTest.class,
                DateUtilAddDayParameterizedTest.class,

                DateUtilAddHourParameterizedTest.class,
                DateUtilAddMinuteParameterizedTest.class,
                DateUtilAddSecondParameterizedTest.class,
                DateUtilAddMillisecondParameterizedTest.class,

                DateUtilIsLeapYearParameterizedTest.class,
                DateUtilIsInTimeTest.class,

                DateUtilIsBeforeTest.class,
                DateUtilIsAfterTest.class,
                DateUtilIsEqualsTest.class,

                DateUtilToDateTest.class,
                DateUtilToStringTest.class,

                DateUtilTest.class,
        //                
})
public class FeiLongDateUtilSuiteTests{

}
