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
package com.feilong.core.lang;

import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.NumberPattern;

import static com.feilong.core.bean.ConvertUtil.toBigDecimal;

/**
 * The Class NumberUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class NumberUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtilTest.class);

    /**
     * TestMathTest.
     */
    @Test
    public void testMathTest(){
        LOGGER.debug("" + (0.4 + 0.8)); // = 1.2 ?
        LOGGER.debug("" + (2 - 1.1)); // = 0.9 ?
        LOGGER.debug("" + (0.2 * 3)); // = 0.6 ?
        LOGGER.debug("" + (1.2 / 3)); // = 0.4 ?

        //1.2000000000000002
        //0.8999999999999999
        //0.6000000000000001
        //0.39999999999999997

        //new BigDecimal(0.1) ====>   0.1000000000000000055511151231257827021181583404541015625
        //BigDecimal.valueOf(0.1) ====>  0.1
        LOGGER.debug("" + (new BigDecimal(0.1)));
        LOGGER.debug("" + (BigDecimal.valueOf(0.1)));

        LOGGER.debug("" + (new BigDecimal(0.4).add(new BigDecimal(0.8)))); // = 1.2 ?
        LOGGER.debug("" + (new BigDecimal(2).subtract(new BigDecimal(1.1)))); // = 0.9 ?
        LOGGER.debug("" + (new BigDecimal(0.2).multiply(new BigDecimal(3)))); // = 0.6 ?

    }

    //**************************************************************************

    @Test
    public void compareTo(){
        BigDecimal totalFee = BigDecimal.valueOf(-0.01);
        boolean isLEZero = (totalFee.compareTo(BigDecimal.ZERO) < 0) || (totalFee.compareTo(BigDecimal.ZERO) == 0);
        assertEquals(true, isLEZero);
    }

    /**
     * Testadd.
     */
    @Test
    public void testAdd(){
        BigDecimal a = new BigDecimal(19);
        BigDecimal b = new BigDecimal(20);

        BigDecimal temp = b;

        int j = 5;
        int scale = 8;
        for (int i = 0; i < j; ++i){
            temp = NumberUtil.getDivideValue(a.add(temp), 2, scale);
            LOGGER.debug("{}次:{}", i, temp);
        }

    }

    /**
     * Test convert number to string.
     */
    @Test
    public void testToString1(){
        String pattern = "#######.########";
        assertEquals("88.02", NumberUtil.toString(88.02, pattern));
        assertEquals("88.02", NumberUtil.toString(88.020, pattern));
        assertEquals("88.02002", NumberUtil.toString(88.02002, pattern));
        assertEquals("88", NumberUtil.toString(88, pattern));
        assertEquals("88.02000005", NumberUtil.toString(88.02000005, pattern));
        assertEquals("88.025", NumberUtil.toString(88.02500000, pattern));
        assertEquals("88.0200005", NumberUtil.toString(88.0200005, pattern));
        assertEquals("88.002", NumberUtil.toString(88.002, pattern));
        // //######0
        LOGGER.debug(NumberUtil.toString((double) 5 / 8 * 100, "#######.###"));
        DecimalFormat df = new DecimalFormat("######0");
        LOGGER.debug(df.format(((double) 5 / Integer.valueOf(8)) * 100));

        assertEquals("C00000008", NumberUtil.toString(8, "C00000000"));

        assertEquals("24%", NumberUtil.toString(0.24f, NumberPattern.PERCENT_WITH_NOPOINT));
        assertEquals("24.00%", NumberUtil.toString(0.24f, NumberPattern.PERCENT_WITH_2POINT));

        Integer a = 1;
        Long b = 400L;
        assertEquals("0.25%", NumberUtil.toString((double) a / b, NumberPattern.PERCENT_WITH_2POINT));

        assertEquals("1", NumberUtil.toString(0.8, NumberPattern.NO_SCALE));
        assertEquals("-1", NumberUtil.toString(-0.8, NumberPattern.NO_SCALE));
        assertEquals("-2", NumberUtil.toString(-1.8, NumberPattern.NO_SCALE));
        assertEquals("2", NumberUtil.toString(1.8, NumberPattern.NO_SCALE));
        assertEquals("111112", NumberUtil.toString(111111.5, NumberPattern.NO_SCALE));
        assertEquals("111113", NumberUtil.toString(111112.5, NumberPattern.NO_SCALE));
        assertEquals("88888888", NumberUtil.toString(88888888, NumberPattern.NO_SCALE));
    }

    @Test
    public void testToPERCENT_WITH_NOPOINT(){
        assertEquals("0%", NumberUtil.toString(0, NumberPattern.PERCENT_WITH_NOPOINT));
        assertEquals("100%", NumberUtil.toString(1, NumberPattern.PERCENT_WITH_NOPOINT));
        assertEquals("10000%", NumberUtil.toString(100, NumberPattern.PERCENT_WITH_NOPOINT));
    }

    /**
     * To percen t_ wit h_ nopoin t1.
     */
    @Test
    public void toPERCENT_WITH_NOPOINT1(){
        assertEquals("0.0%", NumberUtil.toString(0, NumberPattern.PERCENT_WITH_1POINT));
        assertEquals("100.0%", NumberUtil.toString(1, NumberPattern.PERCENT_WITH_1POINT));
        assertEquals("10000.0%", NumberUtil.toString(100, NumberPattern.PERCENT_WITH_1POINT));
    }

    /**
     * To percen t_ wit h_ nopoin t2.
     */
    @Test
    public void toPERCENT_WITH_NOPOINT2(){
        assertEquals("0.00%", NumberUtil.toString(0, NumberPattern.PERCENT_WITH_2POINT));
        assertEquals("100.00%", NumberUtil.toString(1, NumberPattern.PERCENT_WITH_2POINT));
        assertEquals("10000.00%", NumberUtil.toString(100, NumberPattern.PERCENT_WITH_2POINT));
    }

    /**
     * To no scale.
     */
    @Test
    public void toNoScale(){
        assertEquals(new BigDecimal(123), NumberUtil.toNoScale(new BigDecimal(123.02)));
        assertEquals(new BigDecimal(123), NumberUtil.toNoScale(new BigDecimal(123.49)));
        assertEquals(new BigDecimal(124), NumberUtil.toNoScale(new BigDecimal(123.51)));
        assertEquals(new BigDecimal(-124), NumberUtil.toNoScale(new BigDecimal(-123.51)));
    }

    /**
     * To no scale2.
     */
    @Test
    public void toNoScale2(){
        assertEquals(new BigDecimal(88), NumberUtil.toNoScale(88.02));
        assertEquals(new BigDecimal(89), NumberUtil.toNoScale(88.520));
        assertEquals(new BigDecimal(89), NumberUtil.toNoScale(88.820f));
        assertEquals(new BigDecimal(88), NumberUtil.toNoScale(88.4999f));
        assertEquals(new BigDecimal(88), NumberUtil.toNoScale(88.4999d));
        assertEquals(new BigDecimal(-89), NumberUtil.toNoScale(-88.5999d));
        // ***********************************************************************
        assertEquals(ZERO, NumberUtil.toNoScale(0.1));
        assertEquals(BigDecimal.ONE, NumberUtil.toNoScale(0.5));
        //
        assertEquals(new BigDecimal(-1), NumberUtil.toNoScale(-0.5));
        assertEquals(ZERO, NumberUtil.toNoScale(-0.11111111));

    }

    /**
     * To no scale3.
     */
    @Test(expected = NullPointerException.class)
    public void toNoScale3(){
        NumberUtil.toNoScale(null);
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString(){
        assertEquals("88.02", NumberUtil.toString(88.02, NumberPattern.TWO_DECIMAL_POINTS));
        assertEquals("88.03", NumberUtil.toString(88.028, NumberPattern.TWO_DECIMAL_POINTS));
    }

    /**
     * Test to string2.
     */
    @Test
    public void testToString2(){
        double value = -88.6;
        LOGGER.debug("" + toBigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP));
        LOGGER.debug(NumberUtil.toString(value, NumberPattern.TWO_DECIMAL_POINTS));

        value = -88.067;
        LOGGER.debug(NumberUtil.toString(value, NumberPattern.TWO_DECIMAL_POINTS));

        LOGGER.debug("******************************");

        value = 88.6;
        LOGGER.debug("" + toBigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP));
        LOGGER.debug(NumberUtil.toString(value, NumberPattern.NO_SCALE));

        value = -88.6;
        LOGGER.debug("" + toBigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP));
        LOGGER.debug(NumberUtil.toString(value, NumberPattern.NO_SCALE));
    }

    /**
     * Gets the divide no scale value.
     *
     * @return the divide no scale value
     */
    @Test
    public void getDivideNoScaleValue(){
        assertEquals(0, NumberUtil.getDivideValue(0, 2, 0).intValue());
        assertEquals(2, NumberUtil.getDivideValue(6, 4, 0).intValue());

        assertEquals("3.33", NumberUtil.getDivideValue(10, 3, 2).toString());
        assertEquals("1.67", NumberUtil.getDivideValue(5, 3, 2).toString());
    }

    /**
     * 获得 divide value1.
     *
     * @return the divide value 1
     */
    @Test(expected = NullPointerException.class)
    public void getDivideValue1(){
        NumberUtil.getDivideValue(null, 0, 0);
    }

    /**
     * 获得 divide value2.
     *
     * @return the divide value 2
     */
    @Test(expected = NullPointerException.class)
    public void getDivideValue2(){
        NumberUtil.getDivideValue(0, null, 0);
    }

    /**
     * Gets the divide value.
     *
     * @return the divide value
     */
    @Test(expected = IllegalArgumentException.class)
    public void getDivideValue(){
        NumberUtil.getDivideValue(new BigDecimal(6), 0, 0);
    }

    /**
     * Test get multiply value.
     */
    @Test
    public void testGetMultiplyValue(){
        assertEquals(toBigDecimal("7.31250"), NumberUtil.getMultiplyValue(new BigDecimal(6.25), 1.17, 5));
        assertEquals(toBigDecimal("10.00000"), NumberUtil.getMultiplyValue(5, 2, 5));
        assertEquals(toBigDecimal("10"), NumberUtil.getMultiplyValue(5, 2, 0));
    }

    /**
     * Gets the progress5.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void testGetMultiplyValue1(){
        NumberUtil.getMultiplyValue(null, 1, 2);
    }

    /**
     * Test get multiply value2.
     */
    @Test(expected = NullPointerException.class)
    public void testGetMultiplyValue2(){
        NumberUtil.getMultiplyValue(1, null, 2);
    }

    /**
     * Test get multiply value3.
     */
    @Test(expected = NullPointerException.class)
    public void testGetMultiplyValue3(){
        NumberUtil.getMultiplyValue(null, null, 2);
    }

    //***********************testGetAddValue**********************************************************

    /**
     * Test get add value.
     */
    @Test
    public void testGetAddValue(){
        assertEquals(new BigDecimal(11), NumberUtil.getAddValue(new BigDecimal(6), 5));
    }

    /**
     * Test get add value 2.
     */
    @Test
    public void testGetAddValue2(){
        assertEquals(new BigDecimal(11), NumberUtil.getAddValue(2, 4, 5));
    }

    /**
     * Test get add value null pointer exception.
     */
    @Test(expected = NullPointerException.class)
    public void testGetAddValueNullPointerException(){
        NumberUtil.getAddValue(null);
    }

    /**
     * Test get add value illegal argument exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAddValueIllegalArgumentException(){
        NumberUtil.getAddValue(null, null);
    }

    /**
     * Test get add value illegal argument exception 1.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAddValueIllegalArgumentException1(){
        NumberUtil.getAddValue(null, 5);
    }

    /**
     * Test get add value 4.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAddValue4(){
        NumberUtil.getAddValue(new BigDecimal(6), null);
    }

    /**
     * Test get add value 33.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAddValue33(){
        NumberUtil.getAddValue(2, 4, null);
    }
}
