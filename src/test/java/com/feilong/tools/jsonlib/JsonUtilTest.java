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
package com.feilong.tools.jsonlib;

import static org.junit.Assert.assertEquals;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.bean.PropertyUtil;
import com.feilong.core.entity.HttpMethodTestType;
import com.feilong.store.system.Menu;
import com.feilong.test.User;
import com.feilong.tools.AbstractJsonTest;

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.core.bean.ConvertUtil.toBigDecimal;
import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.core.bean.ConvertUtil.toMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * The Class JsonlibTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class JsonUtilTest extends AbstractJsonTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtilTest.class);

    /**
     * Test json menu.
     */
    @Test
    public void testJsonMenu(){
        Menu menu = new Menu(4L);
        menu.setChildren(toList(new Menu(5L)));

        LOGGER.debug(JsonUtil.format(menu));
    }

    /**
     * Test json string.
     */
    @Test
    public void testJsonString(){
        LOGGER.debug("DEFAULT_USER_FOR_JSON_TEST_JSON:{}--->{}", USER_JSON_STRING, JsonUtil.format(USER_JSON_STRING));
    }

    /**
     * Test json string 11.
     */
    @Test
    public void testJsonString11(){
        Map<String, Object> map = toMap("ID", (Object) 4616189619433466044L);
        LOGGER.debug("{}", JsonUtil.format(map));
    }

    /**
     * Test json string2.
     */
    @Test
    public void testJsonMap(){
        Map<String, String> nullMap = null;
        LOGGER.debug(JsonUtil.format(nullMap));
    }

    /**
     * Test json string2.
     */
    @Test
    public void testJsonString2(){
        LOGGER.debug(JsonUtil.format(1L));
        LOGGER.debug(JsonUtil.format(1));
    }

    /**
     * Test json string1.
     */
    @Test
    public void testJsonString1(){
        LOGGER.debug(JsonUtil.format(toArray(toBigDecimal("99999999.00"))));
    }

    /**
     * Name.
     */
    @Test
    public void name(){
        String json_test = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";

        JSONObject jsonObject = JSONObject.fromObject(json_test);
        Object bean = JSONObject.toBean(jsonObject);

        assertEquals(jsonObject.get("name"), PropertyUtil.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtil.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtil.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtil.getProperty(bean, "double"));
        assertEquals(jsonObject.get("func"), PropertyUtil.getProperty(bean, "func"));
        List<?> expected = JSONArray.toList(jsonObject.getJSONArray("array"));
        assertEquals(expected, PropertyUtil.getProperty(bean, "array"));
    }

    /**
     * Format array 2.
     */
    @Test
    public void formatArray2(){
        String json = "[{'name':'get'},{'name':'set'}]";
        LOGGER.debug(JsonUtil.format(json));
    }

    /**
     * Format enum.
     */
    @Test
    public void formatEnum(){
        LOGGER.debug(JsonUtil.format(HttpMethodTestType.GET));
    }

    /**
     * Name1.
     */
    @Test
    public void testExcludes(){
        LOGGER.debug(JsonUtil.format(USER, toArray("name", "loves", "attrMap", "userInfo", "userAddresses")));
    }

    /**
     * Test excludes 1.
     */
    @Test
    public void testExcludes1(){
        LOGGER.debug(JsonUtil.format(USER, toArray("name", "loves", "attrMap", "userInfo", "userAddresses"), 4, 4));
    }

    /**
     * TestJsonUtilTest.
     */
    @Test
    public void testFormatWithIncludes1(){
        User user1 = new User("feilong1", 24);
        user1.setNickNames(toArray("xin.jin", "shuai.ge"));
        User user2 = new User("feilong2", 240);
        user2.setNickNames(toArray("xin.jin", "shuai.ge"));

        List<User> list = toList(user1, user2);

        LOGGER.debug(JsonUtil.formatWithIncludes(list, "name", "age"));

        String[] array = { "id", "name" };
        LOGGER.debug(JsonUtil.formatWithIncludes(toArray(user1, user2), array));

        LOGGER.debug(JsonUtil.formatWithIncludes(toList("2,5,8", "2,5,9")));
        LOGGER.debug(JsonUtil.formatWithIncludes(user1));
    }

    /**
     * TestJsonUtilTest.
     */
    @Test
    public void testFormatWithIncludes(){
        Object[][] objects = { { "nike shoe", "500", 1 }, { "nike shoe2", "5000", 1 } };
        LOGGER.debug(JsonUtil.formatWithIncludes(objects));
    }

    /**
     * Test vector.
     */
    @Test
    public void testVector(){
        Vector<Integer> vector = new Vector<Integer>();
        vector.add(1);
        vector.add(2222);
        vector.add(3333);
        vector.add(55555);
        LOGGER.debug("vector:{}", JsonUtil.format(vector));
        LOGGER.debug("" + vector.get(0));
    }

    /**
     * Test hashtable.
     */
    @Test
    public void testHashtable(){
        Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        hashtable.put("a", "a");
        // hashtable.put("a", null);
        LOGGER.debug("hashtable:{}", JsonUtil.format(hashtable));
    }
}
