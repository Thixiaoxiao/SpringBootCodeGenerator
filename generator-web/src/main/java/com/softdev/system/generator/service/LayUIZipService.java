package com.softdev.system.generator.service;

import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.entity.ParamInfo;
import com.softdev.system.generator.enums.LayUIZipEnum;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class LayUIZipService {

    public List<Map<String, String>> generateLayUIJpaList(ParamInfo paramInfo) throws IOException, TemplateException {
        Map<String, Object> options = paramInfo.getOptions();
        String className = ((ClassInfo) options.get("classInfo")).getClassName();
        String packageName = options.get("packageName").toString();
        ArrayList<Map<String, String>> result = new ArrayList<>();
        result.add(getMap(
                LayUIZipEnum.ENTITY.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.ENTITY.getFileName(className),
                LayUIZipEnum.ENTITY.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.CONTROLLER.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.CONTROLLER.getFileName(className),
                LayUIZipEnum.CONTROLLER.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.REPOSITORY.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.REPOSITORY.getFileName(className),
                LayUIZipEnum.REPOSITORY.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.LAYUI_EDIT.getZipPath(className),
                LayUIZipEnum.LAYUI_EDIT.getFileName(className),
                LayUIZipEnum.LAYUI_EDIT.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.LAYUI_LIST.getZipPath(className),
                LayUIZipEnum.LAYUI_LIST.getFileName(className),
                LayUIZipEnum.LAYUI_LIST.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.RESPONSE.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.RESPONSE.getFileName(className),
                LayUIZipEnum.RESPONSE.getData(paramInfo)
        ));
        return result;
    }

    public List<Map<String, String>> generateLayUIMyBatisList(ParamInfo paramInfo) throws IOException, TemplateException {
        Map<String, Object> options = paramInfo.getOptions();
        String className = ((ClassInfo) options.get("classInfo")).getClassName();
        String packageName = options.get("packageName").toString();
        ArrayList<Map<String, String>> result = new ArrayList<>();
        result.add(getMap(
                LayUIZipEnum.LAYUI_EDIT.getZipPath(className),
                LayUIZipEnum.LAYUI_EDIT.getFileName(className),
                LayUIZipEnum.LAYUI_EDIT.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.LAYUI_LIST.getZipPath(className),
                LayUIZipEnum.LAYUI_LIST.getFileName(className),
                LayUIZipEnum.LAYUI_LIST.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.RESPONSE.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.RESPONSE.getFileName(className),
                LayUIZipEnum.RESPONSE.getData(paramInfo)
        ));
        // mybatis 相关
        result.add(getMap(
                LayUIZipEnum.MYBATIS_CONTROLLER.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.MYBATIS_CONTROLLER.getFileName(className),
                LayUIZipEnum.MYBATIS_CONTROLLER.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.MYBATIS_MAPPER.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.MYBATIS_MAPPER.getFileName(className),
                LayUIZipEnum.MYBATIS_MAPPER.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.MYBATIS_MODEL.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.MYBATIS_MODEL.getFileName(className),
                LayUIZipEnum.MYBATIS_MODEL.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.MYBATIS_XML.getZipPath(packageName),
                LayUIZipEnum.MYBATIS_XML.getFileName(className),
                LayUIZipEnum.MYBATIS_XML.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.MYBATIS_INTERFACE.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.MYBATIS_INTERFACE.getFileName(className),
                LayUIZipEnum.MYBATIS_INTERFACE.getData(paramInfo)
        ));
        result.add(getMap(
                LayUIZipEnum.MYBATIS_IMPL.getZipPath(LayUIZipEnum.ENTITY.getPackPath(packageName)),
                LayUIZipEnum.MYBATIS_IMPL.getFileName(className),
                LayUIZipEnum.MYBATIS_IMPL.getData(paramInfo)
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
