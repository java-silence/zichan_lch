package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.ZcBfItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcBuyItem;

@Mapper
public interface ZcBuyItemDao {

    //@Select("select * from zc_buy_item t where t.id = #{id}")
    ZcBuyItem getById(Long id);

    @Delete("delete from zc_buy_item where id = #{id}")
    int delete(Long id);

    int update(ZcBuyItem zcBuyItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_buy_item(zc_buy_id, gl_dept_id, name, model, unit, brand, price, totalPrice,supplier_name, use_des, arrive_time, zc_bz, shb_status, cwb_status, status, bz, del, create_time, update_time) values(#{zcBuyId}, #{glDeptId}, #{name}, #{model}, #{unit}, #{brand}, #{price}, #{totalPrice},#{supplierName}, #{useDes}, #{arriveTime}, #{zcBz}, #{shbStatus}, #{cwbStatus}, #{status}, #{bz}, #{del}, #{createTime}, #{updateTime})")
    int save(ZcBuyItem zcBuyItem);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcBuyItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_buy_item t order by id")
    List<ZcBuyItem> listAll();

    @Select("select * from zc_buy_item t where t.Ccode = #{Ccode}")
    ZcBuyItem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_buy_item t")
    String getMaxCcode();

    /** 资产购买批量添加 */
    void saves(@Param("zcBuyItemList")List<ZcBuyItem> zcBuyItemList, @Param("zcBuyId")Long zcBuyId, @Param("applyUserId")Long applyUserId);

    int countCheck(@Param("type") String type,@Param("zcBuyId") Long zcBuyId);

    void updateStatus(@Param("buyid") Long buyid, @Param("status") Integer status);

    @Select("select * from zc_buy_item t where t.zc_buy_id = #{zcBuyId} and t.del = 0")
    List<ZcBuyItem> listByZcBuyId(@Param("zcBuyId") Long zcBuyId);

    List<Map<String,Object>> listDetailByZcBfId(@Param("zcBuyId") Long zcBuyId,@Param("cw")String cw);

    /**
     * 根据待办查询资产报废子项
     * @param todoId
     * @return
     */
    List<Map<String,Object>> listDetailByFlowTodoIdNew(@Param("todoId") Long todoId);

    // 批量更新状态
    void updateListStatus(@Param("type") String type, @Param("status")Integer status, @Param("buyItemIds")List<Long> buyItemIds);

    void updateShStatusNull(@Param("buyItemId") Long buyItemId);

    @Delete("delete from zc_buy_item where zc_buy_id = #{zcBuyId}")
    void deleteByZcBuyId(@Param("zcBuyId") Long zcBuyId);

    List<ZcBuyItem> listPassByZcBuyId(@Param("zcBuyId") Long zcBuyId);

    /** 根据TodoItem更新子项 */
    void updateByflowTodoItem(FlowTodoItem flowTodoItem);

    int countFinish(@Param("params") Map<String,Object> params);

    List<Map<String,Object>> listZcBuyFinish(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
