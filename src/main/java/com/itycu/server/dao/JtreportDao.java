package com.itycu.server.dao;

import com.itycu.server.model.JtReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JtreportDao {

    List<JtReport> list(Long id);
}
