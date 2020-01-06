package com.itycu.server.dao;

import com.itycu.server.dto.StockoutVO;
import com.itycu.server.model.Stockout;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StockoutDao {

    @Select("select * from v_kc_stockout t where t.id = #{id}")
    StockoutVO getById(Long id);

//    @Delete("delete from kc_stockout where id = #{id}")
    @Update("update kc_stockout set del='1' where id = #{id}")
    int delete(Long id);

    @Update("update kc_stockout set del='0' where id = #{id}")
    int undelete(Long id);

    int update(Stockout stockout);

//    @Insert("update kc_stockout set ccode=#{ccode}, ddate=#{ddate},busstype=#{busstype}, csource=#{csource},bussid=#{bussid},whid=#{whid}, deptid=#{deptid}, whuserid=#{whuserid}, whid2=#{whid2}, deptid2=#{deptid2}, whuserid2=#{whuserid2}, userid=#{userid}, userid2=#{userid2}, cusid=#{cusid}, venid=#{venid}, orderid=#{orderid}, invoice=#{invoice}, taxrate=#{taxrate}, imoney=#{imoney},createby=#{createby},createTime=#{createTime},updateby=#{updateby},updateTime=#{updateTime},auditby=#{auditby},auditTime=#{auditTime},status=#{status},del=#{del},memo=#{memo},ctype=#{ctype},c01=#{c01},c02=#{c02},c03=#{c03},invid=#{invid},inum=#{inum},jian=#{jian},flowid=#{flowid},stepid=#{stepid},zhuangcheid=#{zhuangcheid},xsddtype=#{xsddtype},tax=#{tax},thje=#{thje},itotal=#{itotal},fkfs=#{fkfs},clbm=#{clbm},yunfei=#{yunfei},yfdj=#{yfdj},discount=#{discount},ssje=#{ssje},n01=#{n01},n02=#{n02},n03=#{n03},lat=#{lat},lng=#{lng},sksj=#{sksj},printstatus=#{printstatus},xianjin=#{xianjin},weixin=#{weixin},zhifubao=#{zhifubao},shouzhang=#{shouzhang},qiankuan=#{qiankuan},shaofu=#{shaofu},duoxiao=#{duoxiao},kouyufu=#{kouyufu} where id = #{id}")
    @Insert("update kc_stockout set thje=#{thje},itotal=#{itotal},ssje=#{ssje},xianjin=#{xianjin},weixin=#{weixin},zhifubao=#{zhifubao},shouzhang=#{shouzhang},qiankuan=#{qiankuan},shaofu=#{shaofu},duoxiao=#{duoxiao},kouyufu=#{kouyufu},memo=#{memo} where id = #{id}")
    int updateNew(Stockout stockout);

    @Update("update kc_stockouts set status=#{status} where pid=#{pid}")
    int updatestatusbypid(@Param("pid") Long pid, @Param("status") String status);

    int updates(@Param("stockout") Stockout stockout, @Param("stockoutIds") List<Long> stockoutIds);

    @Update("update xs_paiche set status=#{status} where bussid=#{bussid}")
    int updatepaiche(@Param("bussid") Long bussid, @Param("status") String status);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kc_stockout(ccode, ddate, busstype, csource, bussid, whid, deptid, whuserid, whid2, deptid2, whuserid2, userid, userid2, cusid, venid, orderid, invoice, taxrate, imoney, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03, invid, inum,jian,flowid,stepid,zhuangcheid,xsddtype,tax,itotal,fkfs,clbm,yunfei,yfdj,discount,ssje,n01,n02,n03,thje,duoxiao,kouyufu,lat,lng,sksj,printstatus,xianjin,weixin,zhifubao,shouzhang,qiankuan,shaofu) values(#{ccode}, #{ddate}, #{busstype}, #{csource}, #{bussid}, #{whid}, #{deptid}, #{whuserid}, #{whid2}, #{deptid2}, #{whuserid2}, #{userid}, #{userid2}, #{cusid}, #{venid}, #{orderid}, #{invoice}, #{taxrate}, #{imoney}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{invid}, #{inum},#{jian}, #{flowid}, #{stepid}, #{zhuangcheid}, #{xsddtype}, #{tax}, #{itotal}, #{fkfs}, #{clbm}, #{yunfei}, #{yfdj}, #{discount}, #{ssje}, #{n01}, #{n02}, #{n03},#{thje},#{duoxiao},#{kouyufu},#{lat},#{lng},#{sksj},#{printstatus},#{xianjin},#{weixin},#{zhifubao},#{shouzhang},#{qiankuan},#{shaofu})")
    int save(Stockout stockout);
    
    int count(@Param("params") Map<String, Object> params);

    List<StockoutVO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from kc_stockout t order by id")
    List<Stockout> listAll();

    @Select("select t.* from v_kc_stockout t where DATE_FORMAT(ddate,'%Y-%m-%d') = #{ddate} and ctype = #{ctype} and status = #{status} order by cusid")
    List<StockoutVO> getByddate(@Param("ddate") String ddate, @Param("ctype") String ctype, @Param("status") String status);

    @Select("select t.* from v_kc_stockout t where DATE_FORMAT(ddate,'%Y-%m-%d') = #{ddate} and ctype = #{ctype} and status = #{status} and deptid=#{deptid} and stepid=#{stepid} order by cusid")
    List<StockoutVO> getByddatebaozhuang(@Param("ddate") String ddate, @Param("ctype") String ctype, @Param("status") String status, @Param("deptid") String deptid, @Param("stepid") String stepid);


    @Select("select t.* from v_kc_stockout t where zhuangcheid = #{zhuangcheid} order by cusid")
    List<StockoutVO> listByZhuangche(@Param("zhuangcheid") Long zhuangcheid);

    @Select("select t.* from v_kc_stockout t where deptid <> 5 and zhuangcheid = #{zhuangcheid} order by cusid")
    List<StockoutVO> listByZhuangche_sm(@Param("zhuangcheid") Long zhuangcheid);

    //商贸多装发货单
    @Select("select t.* from v_kc_stockout t where deptid <> 5 and zhuangcheid = #{zhuangcheid} and cusname like '%多装%' order by cusid")
    List<StockoutVO> listByZhuangche_sm_duozhuang(@Param("zhuangcheid") Long zhuangcheid);

    //商贸多装发货单
    @Select("select t.* from kc_stockout t where zhuangcheid = #{zhuangcheid} and ctype='che' limit 1 ")
    StockoutVO getChekuByZhuangcheid(@Param("zhuangcheid") Long zhuangcheid);

    @Update("update kc_stockout set status = #{status} where id = #{stockoutId}")
    int updateStatus(@Param("stockoutId") Long stockoutId, @Param("status") String status);

    @Update("update kc_stockout set status = #{status},zhuangcheid = null where id = #{stockoutId}")
    int delzc(@Param("stockoutId") Long stockoutId, @Param("status") String status);

    @Update("update kc_stockout set stepid = 3,zhuangcheid = null where id = #{stockoutId}")
    int delzc_baozhuang(@Param("stockoutId") Long stockoutId);

    @Update("update kc_stockout set status = #{status},zhuangcheid = null where zhuangcheid = #{zcid}")
    int delByZcid(@Param("zcid") Long zcid, @Param("status") String status);

    //删除包装车间装车ID
    @Update("update kc_stockout set stepid = 3,zhuangcheid = null where deptid=5 and zhuangcheid = #{zcid}")
    int delByZcid_baozhuang(@Param("zcid") Long zcid);
    @Select("select max(ccode) from kc_stockout t where ccode like #{ccode} ")
    String getMaxCcode(String ccode);
    @Update(" update kc_stockout o,(select pid,sum(jian) jian,sum(inum) inum,sum(imoney) imoney from kc_stockouts where `status`<> 3 group by pid ) m \n" +
            " set o.jian = m.jian,o.inum = m.inum ,o.imoney =m.imoney,o.itotal=m.imoney where o.id = m.pid and o.id = #{id}")
    int updatemoney(Long id);

}
