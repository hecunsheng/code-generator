package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * class: ModuleEnums <br>
 * description: 模块枚举 <br>
 * time: 2019/5/22
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public enum ModuleEnums {

    MAPPER(FileTypeEnums.JAVA,"generate.java.module.mapper.","name-format","package","out-put-path","数据库接口"),

    POJO(FileTypeEnums.JAVA,"generate.java.module.model.","name-format","package","out-put-path","数据对象"),

    XML(FileTypeEnums.XML,"generate.resources.xml.","name-format","package","out-put-path","mybatis xml 文件"),
    ;
    /** 文件类型 */
    private FileTypeEnums typeEnums;
    /** 配置前缀 */
    private String keyPrefix;
    /** 生成文件的文件名格式化配置 */
    private String fileNameFormatKey;
    /** 生成文件的包路径配置 */
    private String filePackageKey;
    private String outputPathKey;
    /** 模块描述 */
    private String moduleDescription;

    ModuleEnums(FileTypeEnums typeEnums, String keyPrefix, String fileNameFormatKey, String filePackageKey, String outputPathKey, String moduleDescription) {
        this.typeEnums = typeEnums;
        this.keyPrefix = keyPrefix;
        this.fileNameFormatKey = fileNameFormatKey;
        this.filePackageKey = filePackageKey;
        this.outputPathKey = outputPathKey;
        this.moduleDescription = moduleDescription;
    }

    public FileTypeEnums getTypeEnums() {
        return typeEnums;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public String getFileNameFormatKey() {
        return keyPrefix + fileNameFormatKey;
    }

    public String getFilePackageKey() {
        return keyPrefix + filePackageKey;
    }

    public String getOutputPathKey() {
        return keyPrefix + outputPathKey;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}
