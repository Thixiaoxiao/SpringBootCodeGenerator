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
                LayUIJpaZipEnum.ENTITY.getFileName(className),
                LayUIJpaZipEnum.ENTITY.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIJpaZipEnum.CONTROLLER.getZipPath(LayUIJpaZipEnum.ENTITY.getPackPath(packageName)),
                LayUIJpaZipEnum.CONTROLLER.getFileName(className),
                LayUIJpaZipEnum.CONTROLLER.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIJpaZipEnum.REPOSITORY.getZipPath(LayUIJpaZipEnum.ENTITY.getPackPath(packageName)),
                LayUIJpaZipEnum.REPOSITORY.getFileName(className),
                LayUIJpaZipEnum.REPOSITORY.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIJpaZipEnum.LAYUI_EDIT.getZipPath(className),
                LayUIJpaZipEnum.LAYUI_EDIT.getFileName(className),
                LayUIJpaZipEnum.LAYUI_EDIT.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIJpaZipEnum.LAYUI_LIST.getZipPath(className),
                LayUIJpaZipEnum.LAYUI_LIST.getFileName(className),
                LayUIJpaZipEnum.LAYUI_LIST.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIJpaZipEnum.RESPONSE.getZipPath(LayUIJpaZipEnum.ENTITY.getPackPath(packageName)),
                LayUIJpaZipEnum.RESPONSE.getFileName(className),
                LayUIJpaZipEnum.RESPONSE.getData(paramInfo)
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
