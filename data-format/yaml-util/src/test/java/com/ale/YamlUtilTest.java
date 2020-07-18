package com.ale;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author alewu
 * @since 2019/7/20 10:39
 */
public class YamlUtilTest {

    @Test
    public void testLoad() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Object ret = yaml.load(yamlStr);
        System.out.println(ret);
    }

    @Test
    public void testLoadAs() throws IOException {
        Yaml yaml = new Yaml();
        // Loading YAML
        URL resource = Resources.getResource("java_bean.yml");
        try (InputStream inputStream = resource.openStream()) {
            Person person = yaml.loadAs(inputStream, Person.class);
            System.out.println(person);
        }

    }

    @Test
    public void testDump() {
        Dice dice = new Dice(3, 6);
        Yaml yaml = new Yaml();
        String output = yaml.dump(dice);
        System.out.println(output);
    }

    /**
     * 打上标签将列表的内容转换为Javabean而不是LinkedHashMap
     *
     * @throws IOException
     */
    @Test
    public void testLoadAll() throws IOException {
        Constructor constructor = new Constructor();
        constructor.addTypeDescription(new TypeDescription(Person.class, "!person"));

        Yaml yaml = new Yaml(constructor);

        URL resource = Resources.getResource("complex_object.yml");
        try (InputStream inputStream = resource.openStream()) {
            Map<String, Object> map = yaml.load(inputStream);
            List<Person> persons = (List<Person>) map.get("persons");
            for (Person person : persons) {
                System.out.println(person);
            }
        }

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev
// .com/forum#!/testme