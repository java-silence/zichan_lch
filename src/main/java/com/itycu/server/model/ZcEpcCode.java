package com.itycu.server.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 资产追溯码
 */
@Data
public class ZcEpcCode implements Serializable {

    /** 主键ID. */
    private Long id;

	private String epcid;

	private Long deptId;

	private Integer enable;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
