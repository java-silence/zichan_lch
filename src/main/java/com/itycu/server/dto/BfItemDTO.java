package com.itycu.server.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @author lch
 * @create 2020-03-20 17:32
 */
@Data
public class BfItemDTO implements Serializable {

     /** 处置附件名称 */
     private String name;

     /** 附件地址 */
     private String url;

     /** 处置子项ID */
     private Long id;
}
