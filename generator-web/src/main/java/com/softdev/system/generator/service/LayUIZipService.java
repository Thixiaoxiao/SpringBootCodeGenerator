package com.softdev.system.generator.service;

import com.softdev.system.generator.entity.ParamInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LayUIZipService {

    public List<Map<String, String>> generateList(ParamInfo paramInfo) {
        ArrayList<Map<String, String>> result = new ArrayList<>();

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
