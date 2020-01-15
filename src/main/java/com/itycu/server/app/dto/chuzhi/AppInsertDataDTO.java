package com.itycu.server.app.dto.chuzhi;


import com.itycu.server.app.vo.chuzhi.DealZcInfoVO;
import lombok.Data;

import java.util.List;

@Data
public class AppInsertDataDTO {


    /**
     * 处置方式
     */
    private int bfCategory;

    /**
     * 处置描述
     */
    private String bfDes;

    private int type;

    private List<DealZcInfoVO> zcBfItemList;


}
