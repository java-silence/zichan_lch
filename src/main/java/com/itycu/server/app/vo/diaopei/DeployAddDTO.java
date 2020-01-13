package com.itycu.server.app.vo.diaopei;


import lombok.Data;

import java.util.List;

/**
 * APP 端资产添加数据
 */
@Data
public class DeployAddDTO {

    private int type;

    private int backDeptId;

    private List<DeployZcListVO> zcDeployItems;


}
