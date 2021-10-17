package com.softdev.system.generator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.entity.ParamInfo;
import com.softdev.system.generator.util.MapUtil;
import com.softdev.system.generator.util.TableParseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softdev.system.generator.util.ZipUtil.toZip;

@Controller
public class ZipController {
    @RequestMapping("/download/layui.jpa.zip")
    public void downloadLocal(
            @RequestParam(required = false) String paramInfoStr,
            HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ParamInfo paramInfo = mapper.readValue(paramInfoStr, ParamInfo.class);

        //1.Parse Table Structure 表结构解析
        ClassInfo classInfo = null;
        String dataType = MapUtil.getString(paramInfo.getOptions(),"dataType");
        if ("sql".equals(dataType)||dataType==null) {
            classInfo = TableParseUtil.processTableIntoClassInfo(paramInfo);
        }else if ("json".equals(dataType)) {
            //JSON模式：parse field from json string
            classInfo = TableParseUtil.processJsonToClassInfo(paramInfo);
            //INSERT SQL模式：parse field from insert sql
        } else if ("insert-sql".equals(dataType)) {
            classInfo = TableParseUtil.processInsertSqlToClassInfo(paramInfo);
            //正则表达式模式（非完善版本）：parse sql by regex
        } else if ("sql-regex".equals(dataType)) {
            classInfo = TableParseUtil.processTableToClassInfoByRegex(paramInfo);
            //默认模式：default parse sql by java
        }
        //2.Set the params 设置表格参数

        paramInfo.getOptions().put("classInfo", classInfo);
        paramInfo.getOptions().put("tableName", classInfo == null ? System.currentTimeMillis() : classInfo.getTableName());



        System.out.println(paramInfo);

        response.reset();
        response.setContentType("application/octet-stream");
        String filename = "test.zip";
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();

        List<Map<String, String>> list = new ArrayList<>();


        Map m1 = new HashMap() {{
            put("path", "/f1/f2/f3/");
            put("name", "1.txt");
            put("data", "abcdefg");
        }};

        Map m2 = new HashMap() {{
            put("path", "/f1/f2/f3/f4/");
            put("name", "2.txt");
            put("data", "abcdefg");
        }};

        Map m3 = new HashMap() {{
            put("path", "");
            put("name", "3.txt");
            put("data", "abcdefg");
        }};

        list.add(m1);

        list.add(m2);

        list.add(m3);

        toZip(list, outputStream);

        if (outputStream != null) {
            outputStream.close();
        }
    }
}
