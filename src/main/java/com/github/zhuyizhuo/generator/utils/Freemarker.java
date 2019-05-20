package com.github.zhuyizhuo.generator.utils;

import java.io.*;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 创建时间：2015年2月8日
 * @version 1.0
 */
public class Freemarker {

	/**
	 * 打印到控制台(测试用)
	 */
	public static void print(String ftlPath, String ftlName, Object root) throws Exception{
		try {
			//通过Template可以将模板文件输出到相应的流
			Template temp = getTemplate(ftlPath, ftlName);
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将root对象输出到文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 * @param outFilePath 输出前的文件上部路径
	 */
	public static void printFile(String ftlPath, String ftlName, Map<String,Object> root,String outFilePath, String outFile) throws Exception{
		try {
			File file = new File(outFilePath + outFile);
			//判断有没有父路径，就是判断文件整个路径是否存在
			if(!file.getParentFile().exists()){
				//不存在就全部创建
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlPath, ftlName);
			//模版输出
			template.process(root, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出对象到文件
	 * @param ftlFullPath ftl模板路径
	 * @param outPutPath 输出文件全路径
	 * @param outPutObject 输出对象
	 * @throws Exception
	 */
	public static void printFile(String ftlFullPath, String outPutPath, Object outPutObject) throws Exception{
		LogUtils.printInfo("文件输出路径:"+outPutPath);
		printFile(GeneratorStringUtils.getFrontPath(ftlFullPath),GeneratorStringUtils.getFileName(ftlFullPath),outPutPath,outPutObject);
	}

	/**
	 * 将root对象输出到文件
	 * @param ftlPath   ftl文件路径
	 * @param ftlName	 ftl文件名
	 * @param outPutPath	输出后的文件全部路径
	 * @param outPutObject 输出对象
	 */
	public static void printFile(String ftlPath, String ftlName, String outPutPath, Object outPutObject) throws Exception{
		try {
			File file = new File(outPutPath);
			//判断有没有父路径，就是判断文件整个路径是否存在
			if(file != null && file.getParentFile() != null && !file.getParentFile().exists()){
				//不存在就全部创建
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlPath, ftlName);
			//模版输出
			template.process(outPutObject, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过文件名加载模版
	 * @param ftlName ftl文件名
	 */
	public static Template getTemplate(String ftlPath, String ftlName) throws Exception{
		try {
			//通过Freemaker的Configuration读取相应的ftl
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
			cfg.setEncoding(Locale.CHINA, "utf-8");
			//设定去哪里读取相应的ftl模板文件
			cfg.setClassLoaderForTemplateLoading(Freemarker.class.getClassLoader(), ftlPath);
			//在模板文件目录中找到名称为name的文件
			Template temp = cfg.getTemplate(ftlName);
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
