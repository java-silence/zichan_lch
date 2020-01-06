package com.itycu.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.itycu.server.dao.CustomerDao;
import com.itycu.server.dao.CustomerclassDao;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.model.Customer;
import com.itycu.server.model.Customerclass;
import com.itycu.server.model.Dept;
import com.itycu.server.model.FileInfo;
import com.itycu.server.service.CustomerService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceimpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerclassDao customerclassDao;

    @Override
    public Map cusImport(MultipartFile file) {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<Customer> list = new ArrayList<>();
        FileInfo fileInfo = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), Customer.class, params);
            if(! CollectionUtils.isEmpty(list)){
                List<Customerclass> customerclasses =  customerclassDao.listAll();  //获取所有客户分类
                LoginUser loginUser = UserUtil.getLoginUser();
                Dept dept = deptDao.getById(loginUser.getDeptid());  //部门

                for (int i=0;i<list.size();i++){
                    Customer customer = list.get(i);
                    //判断必需的数据是否为空
                    if (customer.getClassname() == null || "".equals(customer.getClassname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行客户分类不能为空");
                        return map;
                    }

                    if(! CollectionUtils.isEmpty(customerclasses)){
                        List<Customerclass> customerClass = findCustomerClass(customerclasses, customer.getClassname());
                        if (customerClass.size() == 0){
                            throw new NullPointerException("客户分类中没有："+customer.getClassname()+"，请完善数据");
                        }else if (customerClass.size() == 1){
                            Customerclass customerclass = customerClass.get(0);
                            customer.setCid(customerclass.getId());

                            //生成分类位置
                            StringBuffer ppath = new StringBuffer(customerclass.getId().toString());
                            generatePpath(customerclasses, customerclass,ppath);
                            customer.setPpath(ppath.toString());
                        }else{
                            throw new NullPointerException("客户分类中有多个："+customer.getClassname()+"，请核对数据");
                        }
                    }else{
                        throw new NullPointerException("客户分类没有数据，请完善数据");
                    }

                    if (customer.getCname() == null || "".equals(customer.getCname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行客户名称不能为空");
                        return map;
                    }

                    if (customer.getCtype() == null || "".equals(customer.getCtype())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行客户类型不能为空");
                        return map;
                    }

                    customer.setDeptid(loginUser.getDeptid());
                    customer.setCreateby(loginUser.getId());
                }

                customerDao.saves(list);
                map.put("code","0");
//                fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            map.put("code","-1");
            map.put("msg",e.getMessage());
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    List<Customerclass> findCustomerClass(List<Customerclass> customerclassList,String ksname){
        List<Customerclass> collect = new ArrayList<>();
        if (collect != null){
            collect = customerclassList.stream().filter(c -> ksname.equals(c.getCname())).collect(Collectors.toList());
        }
        return collect;
    }

    public void generatePpath(List<Customerclass> customerclassList,Customerclass customerclass,StringBuffer ppath){
        if (customerclass.getPid() != null && !new Long(0).equals(customerclass.getPid())){
            List<Customerclass> collect = customerclassList.stream().filter(c -> c.getId().equals(customerclass.getPid())).collect(Collectors.toList());
            if (collect.size() == 1){
                Customerclass cusclassPid = collect.get(0);
                ppath.insert(0,cusclassPid.getId()+"-");
                generatePpath(customerclassList,cusclassPid,ppath);
            }else{
                throw new NullPointerException("客户分类："+customerclass.getCname()+"有多个上级分类，请核对数据");
            }
        }
    }
}
