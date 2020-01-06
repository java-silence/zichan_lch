package com.itycu.server.dao;

import com.itycu.server.model.Equipment;
import com.itycu.server.model.ZxBorrows;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZxBorrowsDao {
    @Delete("delete from zx_borrows where pid = #{pid}")
    int deleteByPid(Long pid);

    int saves(@Param("zxBorrowsList") List<ZxBorrows> zxBorrowsList, @Param("zxBorrowid") Long zxBorrowid);

    @Select("select * from v_zx_borrows t where t.pid = #{zxBorrowid}")
    List<ZxBorrows> getByzxBorrowid(Long zxBorrowid);

    @Select("SELECT e.id,CONCAT(ifnull(e.serialno,''),'-', e.cname,ifnull(e.etype,''),'-',e.status) as cname from zx_borrows b " +
            "left join zx_equipment e on e.id = b.eqpid where b.pid=#{pid} ")
    List<Equipment> listbypid(Long pid);
}
