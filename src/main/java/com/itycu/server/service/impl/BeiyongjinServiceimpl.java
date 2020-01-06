package com.itycu.server.service.impl;

import com.itycu.server.dao.BeiyongjinDao;
import com.itycu.server.model.Beiyongjin;
import com.itycu.server.service.BeiyongjinService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BeiyongjinServiceimpl implements BeiyongjinService {

    @Autowired
    private BeiyongjinDao beiyongjinDao;

    @Override
    public Beiyongjin save(Beiyongjin beiyongjin) {
        beiyongjin.setCreateby(UserUtil.getLoginUser().getId());
        beiyongjin.setDdate(new Date());
        if(beiyongjin.getShouru() != null){
            BigDecimal yue = beiyongjinDao.getYue();
            BigDecimal shouru = beiyongjin.getShouru();
            if(yue==null){
                beiyongjin.setYue(shouru);
            }else{
                BigDecimal jine = yue.add(shouru);
                beiyongjin.setYue(jine);
            }

        }
        if(beiyongjin.getZhichu() != null){
            BigDecimal yue = beiyongjinDao.getYue();
            BigDecimal zhichu = beiyongjin.getZhichu();
            BigDecimal jine = yue.subtract(zhichu);
            beiyongjin.setYue(jine);
        }
//        if("yz".equals(beiyongjin.getCtype())){
//            beiyongjin.setDeptid(10l);
//        }
        beiyongjinDao.save(beiyongjin);
        return beiyongjin;
    }

    @Override
    public Beiyongjin update(Beiyongjin beiyongjin) {
        Beiyongjin byj = beiyongjinDao.getById(beiyongjin.getId());
        if(!"js".equals(beiyongjin.getCtype())) {

            if (beiyongjin.getShouru() != null) {
                BigDecimal gxyue = beiyongjin.getShouru().subtract(byj.getShouru());
                beiyongjin.setYue(byj.getYue().add(gxyue));
                beiyongjinDao.updateYue(gxyue, beiyongjin.getId());
            }
            if (beiyongjin.getZhichu() != null) {
                BigDecimal gxyue = byj.getZhichu().subtract(beiyongjin.getZhichu());
                beiyongjin.setYue(byj.getYue().add(gxyue));
                beiyongjinDao.updateYue(gxyue, beiyongjin.getId());
            }
        }else {     //结算
            if(beiyongjin.getShouru() != null){
                if (beiyongjin.getShouru() == null) beiyongjin.setShouru(new BigDecimal(0));
                if (byj.getShouru() == null) byj.setShouru(new BigDecimal(0));
                BigDecimal gxyue = beiyongjin.getShouru().subtract(byj.getShouru());
//                BigDecimal shouru = beiyongjin.getShouru().add(gxyue);

//                beiyongjin.setShouru(shouru);
                beiyongjin.setYue(byj.getYue().add(gxyue));
                beiyongjinDao.updateYue(gxyue, beiyongjin.getId());

                //结算更新结算人，结算时间
//                Beiyongjin beiyongjin1 = new Beiyongjin();
                beiyongjin.setJsby(UserUtil.getLoginUser().getId());
                beiyongjin.setJsTime(new Date());
                beiyongjin.setStatus("2");
//                beiyongjin.setId(beiyongjin.getId());
//                beiyongjinDao.update(beiyongjin1);
            }

        }
//
        beiyongjin.setUpdateby(UserUtil.getLoginUser().getId());

        beiyongjinDao.update(beiyongjin);
        return  beiyongjin;
    }
}
