package com.softdev.system.generator.enums;

import com.softdev.system.generator.entity.ParamInfo;
import com.softdev.system.generator.util.FreemarkerUtil;
import com.softdev.system.generator.util.StringUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Locale;

public enum LayUIJpaZipEnum {

    ENTITY("entity", "entity.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sentity/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%s.java", className);
        }
    },
    CONTROLLER("controller", "jpacontroller.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%scontroller/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sController.java", className);
        }
    },
    REPOSITORY("repository", "repository.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%srepository/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sRepository.java", className);
        }
    },
    LAYUI_EDIT("templates", "layui-edit.ftl") {
        @Override
        public String getZipPath(String className) {
            return String.format(Locale.ROOT, "src/main/resources/templates/%s/", className.toLowerCase());
        }

        @Override
        public String getFileName(String className) {
            return "edit.html";
        }
    },
    LAYUI_LIST("templates", "layui-list.ftl") {
        @Override
        public String getZipPath(String className) {
            return String.format(Locale.ROOT, "src/main/resources/templates/%s/", className.toLowerCase());
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "list.html");
        }
    };
    final String zipPath;
    final String templatePath;
    private static final String FOLDER_PATH = "layui-jpa/";

    LayUIJpaZipEnum(String zipPath, String templatePath) {
        this.zipPath = zipPath;
        this.templatePath = templatePath;
    }

    public abstract String getZipPath(String packPath);

    public abstract String getFileName(String className);

    public String getTemplatePath() {
        return FOLDER_PATH + this.templatePath;
    }

    public String getData(ParamInfo paramInfo) throws IOException, TemplateException {
        return FreemarkerUtil.processString(getTemplatePath(), paramInfo.getOptions());
    }

    public String getPackPath(String packagePath) {
        String replace = packagePath.replace(".", "/");
        if (StringUtils.isNotNull(replace)) {
            if (!replace.startsWith("/")) {
                replace = "/" + replace;
            }
            if (!replace.endsWith("/")) {
                replace = replace + "/";
            }
        }
        return replace;
    }
}
