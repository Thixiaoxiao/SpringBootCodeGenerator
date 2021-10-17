package com.softdev.system.generator.service;

import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.entity.ParamInfo;
import com.softdev.system.generator.enums.LayUIJpaZipEnum;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class LayUIZipService {

    public List<Map<String, String>> generateList(ParamInfo paramInfo) throws IOException, TemplateException {
        Map<String, Object> options = paramInfo.getOptions();
        String className = ((ClassInfo) options.get("classInfo")).getClassName();
        String packageName = options.get("packageName").toString();
        ArrayList<Map<String, String>> result = new ArrayList<>();
        result.add(getMap(
                LayUIJpaZipEnum.ENTITY.getZipPath(LayUIJpaZipEnum.ENTITY.getPackPath(packageName)),
                String.format(Locale.ROOT, "%s.java", className),
                LayUIJpaZipEnum.ENTITY.getData(paramInfo)
        ));
        return result;
    }

    private Map<String, String> getMap(String path, String name, String data) {
        return new HashMap<String, String>() {{
            put("path", path);
            put("name", name);
            put("data", data);
        }};
    }
}
