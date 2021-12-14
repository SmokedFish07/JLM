package com.jlm.common.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 代码生成工具
 *
 * @author: YCL
 * @date: 2021-06-04 23:42:38
 */
public class CodeGeneratorUtil {

	public static void main(String[] args) {

		//数据源
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		//String databaseName = scanner("数据库名");
		dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/jlm?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
		dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("");

		//全局配置region_area,region_city
		GlobalConfig globalConfig = new GlobalConfig();
		//String modelName = scanner("分模块名", false);
		//String projectPath = System.getProperty("user.dir") + "\\" + modelName;
		String projectPath = System.getProperty("user.dir") + "\\";
		globalConfig.setOutputDir(projectPath + "/src/main/java");
		globalConfig.setAuthor(scanner("作者"));
		globalConfig.setOpen(false);
		globalConfig.setSwagger2(true);
		globalConfig.setServiceName("%sService");
		globalConfig.setServiceImplName("%sServiceImpl");

		//包配置
		PackageConfig packageConfig = new PackageConfig();
		String domainName = scanner("域名");
		String packageName = scanner("包名");
		packageConfig.setParent(domainName + "." + packageName);

		//模板引擎
		String templatePath = "/templates/mapper.xml.vm";

		//自定义输出配置
		ArrayList<FileOutConfig> fileOutConfigs = new ArrayList<>();
		fileOutConfigs.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
				//自定义输出文件名
				return projectPath + "/src/main/resources/mapper/"
						+ (packageConfig.getModuleName() == null ? "" : packageConfig.getModuleName() + "/")
						+ tableInfo.getEntityName() + "Mapper" +
						StringPool.DOT_XML;
			}
		});

		//自定义配置
		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {
			}
		};
		injectionConfig.setFileOutConfigList(fileOutConfigs);

		//配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);

		//配置策略
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);
		strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
		strategyConfig.setEntityLombokModel(true);
		strategyConfig.setRestControllerStyle(true);
		strategyConfig.setInclude(scanner("表名, 以','分开").split(","));
		strategyConfig.setControllerMappingHyphenStyle(true);
		strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");
		strategyConfig.setEntityLombokModel(true);
		strategyConfig.setRestControllerStyle(true);

		//设置自定义继承类
//		strategyConfig.setSuperEntityClass(BaseEntity.class);
//		strategyConfig.setSuperMapperClass(" ycl.important.common.base.mapper.BaseDao");
////		strategyConfig.setSuperServiceClass(BaseService.class);
////		strategyConfig.setSuperServiceImplClass(BaseServiceImpl.class);
//		strategyConfig.setSuperControllerClass(BaseController.class);


		//代码生成器
		AutoGenerator generator = new AutoGenerator();
		generator.setGlobalConfig(globalConfig);
		generator.setDataSource(dataSourceConfig);
		generator.setPackageInfo(packageConfig);
		generator.setCfg(injectionConfig);
		generator.setTemplate(templateConfig);
		generator.setStrategy(strategyConfig);
		generator.execute();
	}

	private static String scanner(String name) {
		return scanner(name, true);
	}

	private static String scanner(String name, boolean b) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("请输入" + name + ":");
			if (sc.hasNext()) {
				String packageName = sc.nextLine();
				if (b) {
					if (StrUtil.isNotBlank(packageName)) {
						return packageName;
					}
				} else {
					return packageName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new MybatisPlusException("请输入正确的" + name);
	}
}
