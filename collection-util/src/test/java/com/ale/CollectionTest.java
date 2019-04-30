package com.ale;

import com.ale.data.DataGenerator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;

/**
 * @author alewu
 * @since 2019/4/30 22:50
 */
public class CollectionTest {
    @Test
    public void testListToMap() {
        List<String> alphabet = Lists.newArrayList(DataGenerator.generateAlphabet().toString());
        ImmutableMap<String, String> immutableMap = Maps.uniqueIndex(alphabet, original -> original);
        System.out.println(immutableMap);
    }
}
