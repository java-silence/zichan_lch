package com.itycu.server.scheduled;

import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资产定期计算
 * @author lch
 * @create 2020-03-05 15:46
 */
@Component
public class CalculateZcinfo {

    @Autowired
    private ZcInfoDao zcInfoDao;

    /**
     * 每月1号增加计提数
     */
    //
    @Scheduled(cron = "0 0 1 1 * ?")
    //@Scheduled(cron = "*/15 * * * * ?")
    public void calculateZcinfo() {

        // 1, 查询全部的固定资产(1,固定 2,剩余期限大于0)
        List<ZcInfo> list = zcInfoDao.listByCatType(0);
        ArrayList<ZcInfo> zcList = new ArrayList<>();
        // 2,计算值
        /**
         * 计提数加1
         * 累计折旧
         * 本年折旧
         * 净值=净额
         * 净残值
         * 剩余期限
         */
        BigDecimal divide100 = new BigDecimal("100");
        for (ZcInfo zcInfo : list) {
            ZcInfo zc = new ZcInfo();
            zc.setId(zcInfo.getId());
            // 开始计算
            BigDecimal originalValue = zcInfo.getOriginalValue();
            BigDecimal netResidualRate = zcInfo.getNetResidualRate();
            Date startUseTime = zcInfo.getStartUseTime();   //2020-1
            Integer useMonths = zcInfo.getUseMonths();      //36
            if (originalValue != null && netResidualRate != null
                    && startUseTime != null && useMonths != null ) {
                // 获取当前时间
                Date nowDate = new Date();
                int betweenMonths = DateUtil.getBetweenMonths(startUseTime, nowDate);
                // 1-残值率
                BigDecimal subtract = new BigDecimal(1).subtract(netResidualRate.divide(divide100, 2, BigDecimal.ROUND_HALF_UP));
                // 月折旧额
                BigDecimal perZj = originalValue.multiply(subtract.divide(new BigDecimal(useMonths),4, BigDecimal.ROUND_HALF_UP))
                        .setScale(4,BigDecimal.ROUND_HALF_UP);
                if ((useMonths-betweenMonths) > 0) {
                    // 已计提数
                    int lastMonth = useMonths - (betweenMonths+1);
                    BigDecimal ljZj = perZj.multiply(new BigDecimal(betweenMonths+1));
                    // 本年折旧
                    int thisYearBetweenMonths = DateUtil.getThisYearBetweenMonths(nowDate);
                    BigDecimal bnZj = perZj.multiply(new BigDecimal(thisYearBetweenMonths+1));
                    zc.setLjZhejiu(ljZj);
                    zc.setBnZhejiu(bnZj);
                    zc.setHaveCount(betweenMonths+1);
                    zc.setRemainingperiod(lastMonth);
                }else {
                    // 已经使用完成的,考虑本年的折旧
                    // 最后截止日期
                    Date endDate = DateUtil.addMonthDate(startUseTime, String.valueOf(useMonths));
                    if (DateUtil.getBetweenMonths(endDate,nowDate)-12 > 0) {
                        // 今年有
                        int thisYearBetweenMonths = DateUtil.getThisYearBetweenMonths(endDate);
                        BigDecimal bnZj = perZj.multiply(new BigDecimal(thisYearBetweenMonths));
                        zc.setBnZhejiu(bnZj);
                    }
                    // 净残值 净值 净额 累计折旧
                    BigDecimal jcz = originalValue.multiply(netResidualRate).divide(divide100, 2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal ljzhejiu = originalValue.subtract(jcz);
                    zc.setNetResidualValue(jcz);
                    zc.setNetvalue(jcz);
                    zc.setNet(jcz);
                    zc.setLjZhejiu(ljzhejiu);
                    zc.setHaveCount(useMonths);
                    zc.setRemainingperiod(0);
                }
            }
            zcList.add(zc);
        }
        if (zcList.size()>0) {
            // 3,批量更新(只更新特定的几个值)
            zcInfoDao.updateList(zcList);
        }
    }
}
