package com.softdev.system.generator.enums;

import java.util.Locale;

public enum LayUIJpaZipEnum {

    ENTITY("entity", "entity.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%sentity/", packPath);
        }
    },
    CONTROLLER("controller", "jpacontroller.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%scontroller/", packPath);
        }
    },
    REPOSITORY("repository", "repository.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/java%srepository/", packPath);
        }
    },
    LAYUI_EDIT("templates", "layui-edit.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/resources/templates%s", packPath);
        }
    },
    LAYUI_LIST("templates", "layui-list.ftl") {
        @Override
        public String getZipPath(String packPath) {
            return String.format(Locale.ROOT, "src/main/resources/templates%s", packPath);
        }
    };
    final String zipPath;
    final String templatePath;
    private static final String FOLDER_PATH = "layui-jpa";

    LayUIJpaZipEnum(String zipPath, String templatePath) {
        this.zipPath = zipPath;
        this.templatePath = templatePath;
    }

    public abstract String getZipPath(String packPath);

    public String getTemplatePath() {
        return FOLDER_PATH + this.templatePath;
    }
}
