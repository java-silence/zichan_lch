package com.itycu.server.app.vo.fenye;


import lombok.Data;

import java.io.Serializable;


@Data
public class PageVO implements Serializable {


    private int offset;

    private int limit;


}
