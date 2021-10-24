package com.softdev.system.generator.enums;

import com.softdev.system.generator.entity.ParamInfo;
import com.softdev.system.generator.util.FreemarkerUtil;
import com.softdev.system.generator.util.StringUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Locale;

public enum LayUIZipEnum {
    // 针对 layui 的响应实体类
    RESPONSE("responseEntity", "response-entity.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sentity/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return "ResponseForLayUIEntity.java";
        }
    },
    // JPA 后端 组件
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
    // 前端
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
    },
    // MyBatis 后端组件
    MYBATIS_CONTROLLER("controller", "controller.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%scontroller/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sController.java", className);
        }
    },
    MYBATIS_MAPPER("mapper", "mapper.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sdao/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sMapper.java", className);
        }
    },
    MYBATIS_MODEL("model", "model.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%smodel/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%s.java", className);
        }
    },
    MYBATIS_XML("xml", "mybatis1.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return "src/main/resources/mappers/";
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sMapper.xml", className);
        }
    },
    MYBATIS_INTERFACE("interface", "service.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sservices/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sService.java", className);
        }
    },
    MYBATIS_IMPL("impl", "service_impl.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sservices/impl/", packPath);
        }

        @Override
        public String getFileName(String className) {
            return String.format(Locale.ROOT, "%sServiceImpl.java", className);
        }
    };

    final String zipPath;
    final String templatePath;
    private static final String FOLDER_PATH = "layui-zip/";

    LayUIZipEnum(String zipPath, String templatePath) {
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
