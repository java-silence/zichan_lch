package com.itycu.server.utils;

import com.itycu.server.dto.GenerateInput;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class TemplateUtil {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	public static String getTemplete(String fileName) {
		return FileUtil.getText(TemplateUtil.class.getClassLoader().getResourceAsStream("generate/" + fileName));
	}

	public static void saveJava(GenerateInput input) {
		String path = input.getPath();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		List<String> beanFieldName = input.getBeanFieldName();
		List<String> beanFieldType = input.getBeanFieldType();
		List<String> beanFieldValue = input.getBeanFieldValue();

		String text = getTemplete("java.txt");
		text = text.replace("{beanPackageName}", beanPackageName).replace("{beanName}", beanName);

		String imports = "";
		if (beanFieldType.contains(BigDecimal.class.getSimpleName())) {
			imports += "import " + BigDecimal.class.getName() + ";\n";
		}
		if (beanFieldType.contains(Date.class.getSimpleName())) {
			imports += "import " + Date.class.getName() + ";\n";
			imports += "import com.fasterxml.jackson.annotation.JsonFormat;";
		}

		text = text.replace("{import}", imports);
		String filelds = getFields(beanFieldName, beanFieldType, beanFieldValue);
		text = text.replace("{filelds}", filelds);
		text = text.replace("{getset}", getset(beanFieldName, beanFieldType));

		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(beanPackageName) + beanName + ".java");
		log.debug("生成java model：{}模板", beanName);
	}

	private static String getFields(List<String> beanFieldName, List<String> beanFieldType,
			List<String> beanFieldValue) {
		StringBuffer buffer = new StringBuffer();
		int size = beanFieldName.size();
		for (int i = 0; i < size; i++) {
			String name = beanFieldName.get(i);
			if ("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name)) {
				continue;
			}
			String type = beanFieldType.get(i);
			if (type.equals("Date")){
				buffer.append("\t@JsonFormat(pattern = \"yyyy-MM-dd\")\n");
			}
			buffer.append("\tprivate ").append(type).append(" ").append(name);
			// 默认值
//			String value = beanFieldValue.get(i);
//			if (!StringUtils.isEmpty(value)) {
//				buffer.append(" = ");
//				if (type.equals(String.class.getSimpleName())) {
//					value = "\"" + value + "\"";
//				} else if (type.equals(Double.class.getSimpleName())) {
//					value = value + "D";
//				} else if (type.equals(Float.class.getSimpleName())) {
//					value = value + "F";
//				} else if (type.equals(BigDecimal.class.getSimpleName())) {
//					value = "new BigDecimal(" + value + ")";
//				}
//
//				buffer.append(value);
//			}
			buffer.append(";\n");
		}

		return buffer.toString();
	}

	private static String getset(List<String> beanFieldName, List<String> beanFieldType) {
		StringBuffer buffer = new StringBuffer();
		int size = beanFieldName.size();
		for (int i = 0; i < size; i++) {
			String name = beanFieldName.get(i);
			if ("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name)) {
				continue;
			}

			String type = beanFieldType.get(i);
			buffer.append("\tpublic ").append(type).append(" get")
					.append(StringUtils.substring(name, 0, 1).toUpperCase() + name.substring(1, name.length()))
					.append("() {\n");
			buffer.append("\t\treturn ").append(name).append(";\n");
			buffer.append("\t}\n");
			buffer.append("\tpublic void ").append(" set")
					.append(StringUtils.substring(name, 0, 1).toUpperCase() + name.substring(1, name.length()))
					.append("("+ type + " " + name +") {\n");
			buffer.append("\t\tthis.").append(name).append(" = " + name + ";\n");
			buffer.append("\t}\n");
		}
		return buffer.toString();
	}

	public static void saveJavaDao(GenerateInput input) {
		String path = input.getPath();
		String tableName = input.getTableName();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		String daoPackageName = input.getDaoPackageName();
		String daoName = input.getDaoName();

		String text = getTemplete("dao.txt");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{beanPackageName}", beanPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{table_name}", tableName);
		text = text.replace("{beanName}", beanName);
		text = text.replace("{beanParamName}", lowerFirstChar(beanName));

		String insertColumns = getInsertColumns(input.getColumnNames());
		text = text.replace("{insert_columns}", insertColumns);
		String insertValues = getInsertValues(input.getColumnNames(), input.getBeanFieldName());
		text = text.replace("{insert_values}", insertValues);
		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(daoPackageName) + daoName + ".java");
		log.debug("生成java dao：{}模板", beanName);

		text = getTemplete("mapper.xml");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{table_name}", tableName);
		text = text.replace("{beanName}", beanName);
		String sets = getUpdateSets(input.getColumnNames(), input.getBeanFieldName());
		text = text.replace("{update_sets}", sets);
		String where = getWhere(input.getColumnNames(), input.getBeanFieldName());
		text = text.replace("{where}", where);
		FileUtil.saveTextFile(text, path + File.separator + beanName + "Mapper.xml");
	}

	private static String getInsertValues(List<String> columnNames, List<String> beanFieldName) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column)) {
				buffer.append("#{").append(beanFieldName.get(i)).append("}, ");
			}
		}

		String sets = StringUtils.substringBeforeLast(buffer.toString(), ",");
		return sets;
	}

	private static String getInsertColumns(List<String> columnNames) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column)) {
				buffer.append(column).append(", ");
			}
		}

		String insertColumns = StringUtils.substringBeforeLast(buffer.toString(), ",");
		return insertColumns;
	}

	private static String getUpdateSets(List<String> columnNames, List<String> beanFieldName) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column) && !"createTime".equals(column)) {
				buffer.append("\t\t\t<if test=\"" + column + " != null\">\n");
				buffer.append("\t\t\t\t" + column).append(" = ").append("#{").append(beanFieldName.get(i))
						.append("}, \n");
				buffer.append("\t\t\t</if>\n");
			}
		}

		return buffer.toString();
	}

	private static String getWhere(List<String> columnNames, List<String> beanFieldName) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			buffer.append("\t\t\t<if test=\"params." + column + " != null and params." + column + " != ''\">\n");
			buffer.append("\t\t\t\tand t." + column).append(" = ").append("#{params.").append(beanFieldName.get(i))
					.append("} \n");
			buffer.append("\t\t\t</if>\n");
		}

		return buffer.toString();
	}

	/**
	 * 变量名
	 * 
	 * @param beanName
	 * @return
	 */
	public static String lowerFirstChar(String beanName) {
		String name = StrUtil.str2hump(beanName);
		String firstChar = name.substring(0, 1);
		name = name.replaceFirst(firstChar, firstChar.toLowerCase());

		return name;
	}

	private static String getPackagePath(String packageName) {
		String packagePath = packageName.replace(".", "/");
		if (!packagePath.endsWith("/")) {
			packagePath = packagePath + "/";
		}

		return packagePath;
	}

	public static void saveController(GenerateInput input) {
		String path = input.getPath();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		String daoPackageName = input.getDaoPackageName();
		String daoName = input.getDaoName();

		String text = getTemplete("controller.txt");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{beanPackageName}", beanPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{daoParamName}", lowerFirstChar(daoName));
		text = text.replace("{beanName}", beanName);
		text = text.replace("{beanParamName}", lowerFirstChar(beanName));
		text = text.replace("{controllerPkgName}", input.getControllerPkgName());
		text = text.replace("{controllerName}", input.getControllerName());

		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(input.getControllerPkgName())
				+ input.getControllerName() + ".java");
		log.debug("生成controller：{}模板", beanName);
	}

	public static void saveHtmlList(GenerateInput input) {
		String path = input.getPath();
		String beanName = input.getBeanName();
		String beanParamName = lowerFirstChar(beanName);
		String tableName = input.getTableName();

		String text = getTemplete("htmlList.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{beanName}", beanName);
		List<String> beanFieldNames = input.getBeanFieldName();
		List<String> beanFiledComment = input.getBeanFieldComment();
		List<String> isNullable = input.getIsNullable();
		//text = text.replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));
		//text = text.replace("{ths}", getHtmlThs(beanFieldNames,beanFiledComment));
		text = text.replace("{columnsDatas}",getLayuiCols(beanFieldNames,beanFiledComment));
		FileUtil.saveTextFile(text, path + File.separator + beanParamName + "List.html");
		log.debug("生成查询页面：{}模板", beanName);

		text = getTemplete("htmlAdd.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames,beanFiledComment,isNullable));
		FileUtil.saveTextFile(text, path + File.separator + "add" + beanName + ".html");
		log.debug("生成添加页面：{}模板", beanName);

		text = getTemplete("htmlUpdate.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames,beanFiledComment,isNullable));
		text = text.replace("{initData}", getInitData(beanFieldNames));
		FileUtil.saveTextFile(text, path + File.separator + "update" + beanName + ".html");
		log.debug("生成修改页面：{}模板", beanName);

		text = getTemplete("sql.txt");

		text = text.replace("{selectcols}", getSelectCols(beanFieldNames,beanFiledComment));
		text = text.replace("{table_name}", tableName);
		FileUtil.saveTextFile(text, path + File.separator + "sql" + beanName + ".txt");
		log.debug("生成sql：{}模板", beanName);


		text = getTemplete("apphtmlList.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{beanName}", beanName);
		text = text.replace("{columnsDatas}",getLayuiCols(beanFieldNames,beanFiledComment));
		FileUtil.saveTextFile(text, path + File.separator + "app/" + beanParamName + "List.html");
		log.debug("生成APP查询页面：{}模板", beanName);

		text = getTemplete("apphtmlAdd.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames,beanFiledComment,isNullable));
		FileUtil.saveTextFile(text, path + File.separator + "app/add" + beanName + ".html");
		log.debug("生成APP添加页面：{}模板", beanName);

		text = getTemplete("apphtmlUpdate.txt");
		text = text.replace("{beanParamName}", beanParamName);
		text = text.replace("{addDivs}", getAddDivs(beanFieldNames,beanFiledComment,isNullable));
		text = text.replace("{initData}", getInitData(beanFieldNames));
		FileUtil.saveTextFile(text, path + File.separator + "app/update" + beanName + ".html");
		log.debug("生成APP修改页面：{}模板", beanName);

	}

	private static CharSequence getInitData(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			if ( !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
				builder.append("\t\t\t\t$('#" + b + "').val(data." + b + ");\n");
			}

		});

		return builder.toString();
	}

	private static String getAddDivs(List<String> beanFieldNames,List<String> beanFiledComment ,List<String> isNullable) {
		StringBuilder builder = new StringBuilder();

		IntStream.range(0,beanFieldNames.size()).forEach(i -> {
			String b = beanFieldNames.get(i);
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {

				builder.append("\t\t\t<div class='layui-form-item'>\n");
				builder.append("\t\t\t\t<div class='layui-inline'>\n");
				builder.append("\t\t\t\t\t<label class='layui-form-label'>" + beanFiledComment.get(i) + "</label>\n");
				builder.append("\t\t\t\t\t<div class='layui-input-inline'>\n");
				builder.append("\t\t\t\t\t\t<input class='layui-input' placeholder='" + beanFiledComment.get(i) + "' type='text' name='" + b
						+ "' id='" + b + "' ");
				if ( "NO".equals(isNullable.get(i))){
					builder.append("lay-verify='required'>\n");
				}else{
					builder.append(">\n");
				}
				builder.append("\t\t\t\t\t</div>\n");
				builder.append("\t\t\t\t</div>\n");
				builder.append("\t\t\t</div>\n");

				/*
				builder.append("\t\t\t<div class='form-group'>\n");
				builder.append("\t\t\t\t<label class='col-md-2 control-label'>" + beanFiledComment.get(i) + "</label>\n");
				builder.append("\t\t\t\t<div class='col-md-10'>\n");
				builder.append("\t\t\t\t\t<input class='form-control' placeholder='" + beanFiledComment.get(i) + "' type='text' name='" + b
						+ "' id='" + b + "' ");
				if ( "NO".equals(isNullable.get(i))){
					builder.append("data-bv-notempty='true' data-bv-notempty-message='" + beanFiledComment.get(i) + "不能为空'>\n");
				}else{
					builder.append(">\n");
				}
				builder.append("\t\t\t\t</div>\n");
				builder.append("\t\t\t</div>\n");
				 */
			}
		});


		return builder.toString();
	}

	//生成SQL语句
	private static String getSelectCols(List<String> beanFieldNames,List<String> beanFiledComment ) {
		StringBuilder builder = new StringBuilder();

		IntStream.range(0,beanFieldNames.size()).forEach(i -> {
			String b = beanFieldNames.get(i);
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
				builder.append(b + " as " +  beanFiledComment.get(i) +",\n");
			}
		});


		return builder.toString();
	}

	private static String getHtmlThs(List<String> beanFieldNames,List<String> beanFiledComment) {
		StringBuilder builder = new StringBuilder();

		IntStream.range(0,beanFieldNames.size()).forEach(i -> {
			String b = beanFieldNames.get(i);
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
				builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", beanFiledComment.get(i) ));
			}
		});
//		beanFieldNames.forEach(b -> {
//			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
//					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
//					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
//				builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", b));
//			}
//
//		});
		return builder.toString();
	}

	private static String getHtmlColumnsDatas(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
				builder.append("\t\t\t\t{\"data\" : \"{beanFieldName}\", \"defaultContent\" : \"\"},\n"
						.replace("{beanFieldName}", b));
			}
		});
		builder.append("");
		return builder.toString();
	}

	//Layui表格列自动生成
	private static String getLayuiCols(List<String> beanFieldNames,List<String> beanFiledComment) {
		StringBuilder builder = new StringBuilder();

		IntStream.range(0,beanFieldNames.size()).forEach(i -> {
			String b = beanFieldNames.get(i);
			if (!"id".equals(b) && !"createTime".equals(b) && !"updateTime".equals(b) && !"auditTime".equals(b)
					&& !"status".equals(b) && !"del".equals(b) && !"c01".equals(b) && !"c02".equals(b) && !"c03".equals(b)
					&& !"createby".equals(b) && !"updateby".equals(b) && !"auditby".equals(b) && !"biztype".equals(b) ) {
				builder.append("\t\t\t\t\t,{field:'{beanFieldName}',title:'{beanFieldComment}', width:100, sort: true}\n".replace("{beanFieldName}",b) .replace("{beanFieldComment}", beanFiledComment.get(i) ));
			}
		});

		return builder.toString();
	}

}
