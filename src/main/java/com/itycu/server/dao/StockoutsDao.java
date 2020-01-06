package com.itycu.server.dao;

import com.itycu.server.dto.StockoutsVO;
import com.itycu.server.model.Stockouts;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StockoutsDao {

    @Select("select * from v_kc_stockouts t where t.id = #{id}")
    StockoutsVO getById(Long id);

    @Select("select * from v_kc_stockouts t where t.pid = #{stockid} order by mxwhid desc,invname,cpgg")
    List<StockoutsVO> getByStockId(Long stockid);

    @Select("select invname,'小计' cpgg,count(1) discount, sum(jian) jian,sum(inum) inum,sum(imoney) imoney from v_kc_stockouts  where pid=#{stockid} and status != '3' and (mxwhid=5 or (invname='PE盘管' or invname='PE盘管(2)' or invname='PE水龙带' or invname='PE水龙带(2)'))  GROUP BY invname  HAVING count(1) >1")
    List<StockoutsVO> getpanguanByStockId(Long stockid);

    @Select("select * from kc_stockouts t where t.pid = #{stockid}")
    List<Stockouts> getByPid(Long stockid);

    List<StockoutsVO> getByStockIds(@Param("stockids") List<Long> stockids);

    @Delete("delete from kc_stockouts where pid = #{id}")
    int delete(Long id);

    @Delete("delete from kc_stockouts where id = #{id}")
    int deleteId(Long id);

//    @Delete("delete from kc_stockouts where pid = #{id}")
    @Update("update kc_stockouts set del ='1' where pid = #{id}")
    int deletelist(Long stockid);

    @Update("update kc_stockouts set del ='0' where pid = #{id}")
    int undeletelist(Long stockid);

    int update(Stockouts stockouts);

    int updates(@Param("stockouts") Stockouts stockouts, @Param("stockoutsIds") List<Long> stockoutsIds);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kc_stockouts(pid, invid,cpgg, ilen,perlen,inum, iprice, discount, taxrate, itax, imoney, cbatch, status, del, memo, ctype, c01, c02, c03,costprice,costmoney,profit,danwei,jian,jianzhong,zhuangcheid,mxwhid) values(#{pid}, #{invid},#{cpgg}, #{ilen},#{perlen},#{inum}, #{iprice}, #{discount}, #{taxrate}, #{itax}, #{imoney}, #{cbatch}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03},#{costprice},#{costmoney},#{profit},#{danwei},#{jian},#{jianzhong},#{zhuangcheid},#{mxwhid})")
    int save(Stockouts stockouts);

    @Insert("update kc_stockouts set pid=#{pid}, invid=#{invid},cpgg=#{cpgg}, ilen=#{ilen},perlen=#{perlen},inum=#{inum}, iprice=#{iprice}, discount=#{discount}, taxrate=#{taxrate}, itax=#{itax}, imoney=#{imoney}, cbatch=#{cbatch}, status=#{status}, del=#{del}, memo=#{memo}, ctype=#{ctype}, c01=#{c01}, c02=#{c02}, c03=#{c03},costprice=#{costprice},costmoney=#{costmoney},profit=#{profit},danwei=#{danwei},jian=#{jian},jianzhong=#{jianzhong},zhuangcheid=#{zhuangcheid},mxwhid=#{mxwhid} where id = #{id}")
    int updateNew(Stockouts stockouts);

    int saves(@Param("stockoutsList") List<Stockouts> stockoutsList, @Param("stockoutid") Long stockoutid);
    
    int count(@Param("params") Map<String, Object> params);

    List<StockoutsVO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int counthz(@Param("params") Map<String, Object> params);

    List<StockoutsVO> listhz(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from kc_stockouts t order by id")
    List<Stockouts> listAll();

    List<StockoutsVO> listByCondition(@Param("params") Map<String, Object> params);

    List<StockoutsVO> listByConditionhz(@Param("params") Map<String, Object> params);

    @Update("update kc_stockouts set status = #{status} where pid = #{pid}")
    int updateStatusByPid(@Param("status") String status, @Param("pid") Long pid);

    @Update("update kc_stockouts set status = '0',zhuangcheid = null where pid = #{pid}")
    int delzc(@Param("pid") Long pid);

    @Update("update kc_stockouts set zhuangcheid = null where pid = #{pid}")
    int delzc_baozhuang(@Param("pid") Long pid);

    @Update("update kc_stockouts set status = '0',zhuangcheid = null where zhuangcheid = #{zcid}")
    int delByZcid(@Param("zcid") Long zcid);
    @Update("update kc_stockouts set zhuangcheid = null where zhuangcheid = #{zcid}")
    int delByZcid_baozhaung(@Param("zcid") Long zcid);

    @Update("update kc_stockouts set status=#{status} where pid=#{pid} ")
    int updatestatusbypid(@Param("pid") Long pid, @Param("status") String status);

    @Update("update kc_stockouts set status=#{status} where id=#{id} ")
    int updatestatusbyid(@Param("id") Long id, @Param("status") String status);

    @Select("select * from v_kc_stockouts t where t.pid = #{stockid} and t.status != '3' order by mxwhid desc,invname,cpgg")
    List<StockoutsVO> getByUnReject(Long stockid);  //获取状态不是已驳回的

    @Select("select * from kc_stockouts t where t.pid = #{stockid} and t.status != '3'")
    List<Stockouts> getByUnReject_noview(Long stockid);  //获取状态不是已驳回的

    @Select("SELECT whid mxwhid,'包装膜成品库' mxwhname,t.cusid,t_customer.cname as cusname,sum(jian) jian ,sum(inum) inum from kc_stockout t left outer join t_customer on t_customer.id = t.cusid where t.zhuangcheid=#{zhuangcheid} and t.deptid=5 GROUP BY t.whid,t.cusid")
    List<StockoutsVO> getbaozhuanghuizong(Long zhuangcheid);  //获取包装膜车间汇总数据

}
