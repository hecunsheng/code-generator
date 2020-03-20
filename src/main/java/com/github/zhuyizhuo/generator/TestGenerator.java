package com.github.zhuyizhuo.generator;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

/**
 * @author hecs
 * @date 2020-03-20 10:58
 */
public class TestGenerator {
    public static void main(String[] args) {
        /** 此处使用 配置文件的绝对路径或者在项目中的相对路径
         * 本例配置文件路径在 maven 项目的 src/main/resources 文件夹下
         */
        Generator generator = new GeneratorBuilder().build("generate-config.properties");
        generator.generate();
    }
}
