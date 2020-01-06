package com.itycu.server.utils;

import com.itycu.server.page.table.PageTableRequest;

import javax.servlet.http.HttpServletRequest;

public class DynamicConditionUtil {
    public static void dynamicCondition(PageTableRequest request, HttpServletRequest httpServletRequest){
        String rowLength = httpServletRequest.getParameter("rowLength");   //条件个数
        if (rowLength != null && !"".equals(rowLength)){
              int rowl = Integer.valueOf(rowLength);
              if (rowl > 0){
                  String condition = "";
                  for (int i=0;i<rowl;i++){
                      String field = httpServletRequest.getParameter("QueryCondition["+i+"].conditionField");     //字段
                      String option = httpServletRequest.getParameter("QueryCondition["+i+"].conditionOption");   //条件
                      String value = httpServletRequest.getParameter("QueryCondition["+i+"].conditionValue");     //值

                      if ("".equals(value) || value == null)
                          value = "''";

                      switch (option) {
                                   case "equal":    //等于
                                           condition += " and "+field+" = '"+value+"'";
                                           break;
                                   case "like":     //包含
                                           condition += " and "+field+" like '%"+value+"%'";
                                           break;
                                   case "between":  //范围
                                           String left = httpServletRequest.getParameter("QueryCondition["+i+"].conditionValueLeft");
                                           String right = httpServletRequest.getParameter("QueryCondition["+i+"].conditionValueRight");
                                           condition += " and "+field+" between '"+left+"' and '"+right+"'";
                                           break;
                                   case "start":    //开头字符
                                           condition += " and "+field+" like '"+value+"%'";
                                           break;
                                   case "end":      //结尾字符
                                           condition += " and "+field+" like '%"+value+"'";
                                           break;
                                   case "unequal":  //不等于
                                           condition += " and "+field+" != '"+value+"'";
                                           break;
                                   case "empty":    //为空
                                           condition += " and "+field+" is null";
                                           break;
                      }
                  }
                  request.getParams().put("dynamicCondition", condition);
              }
        }
    }
}
