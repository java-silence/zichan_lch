package com.itycu.server.utils;

import org.springframework.util.ObjectUtils;
import java.util.ArrayList;

/**
 * @author lch
 * @create 2019-12-23 11:21
 */
public class EcpIdUtil {

    /**
     * 生成EcpId集合
     * @param lastEcpId 数据库最后的ecpId数
     * @param num 生成数量
     * @param suffix 部门的前缀
     * @return
     */
    public synchronized static ArrayList<String> getEcpIdLlist(Integer lastEcpId,Integer num,String suffix){
        ArrayList<String> ecpIds = new ArrayList<>();
        if (ObjectUtils.isEmpty(suffix)){
            suffix = "";
        }
        for (Integer i = 0; i < num; i++) {
            Integer id = lastEcpId+(i+1);
            String ecpId = getEcpId(id);
            ecpId = suffix+ecpId;
            ecpIds.add(ecpId);
        }
        return ecpIds;
    }

    /**
     * 生成EcpId 8位
     * @return
     */
    public synchronized static String getEcpId(Integer id){
        String staticNum = getStaticNum(id, 8);
        return staticNum;
    }

    /**
     * 生成指定位数的数字
     * @param count
     * @param num
     */
    public synchronized static String getStaticNum(int count,int num) {
        int length = String.valueOf(count).length();
        int n = num -length;
        if (n == 0) {
            return String.valueOf(count);
        }
        // 生成指定长度0
        String a = "";
        for (int i = 0; i < n; i++) {
            a = a+"0";
        }
        return a+String.valueOf(count);
    }

}
