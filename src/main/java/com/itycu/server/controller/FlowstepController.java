package com.itycu.server.controller;

import java.util.List;

import com.itycu.server.dao.FlowmemberDao;
import com.itycu.server.dao.FlowstepDao;
import com.itycu.server.dto.FlowstepDto;
import com.itycu.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.model.Flowstep;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flowsteps")
public class FlowstepController {

    @Autowired
    private FlowstepDao flowstepDao;

    @Autowired
    private FlowmemberDao flowmemberDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Flowstep save(@RequestBody Flowstep flowstep) {
        flowstepDao.save(flowstep);

        return flowstep;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public FlowstepDto get(@PathVariable Long id) {
        Flowstep flowstep = flowstepDao.getById(id);
        List<String> bynickname = flowstepDao.findByuserId(flowstep.getFlowid(), flowstep.getStepid());
        FlowstepDto flowstepDto = new FlowstepDto();
        flowstepDto.setFlowid(flowstep.getFlowid());
        flowstepDto.setStepid(flowstep.getStepid());
        flowstepDto.setStepname(flowstep.getStepname());
        flowstepDto.setDescription(flowstep.getDescription());
        flowstepDto.setCreateby(flowstep.getCreateby());
        flowstepDto.setNickname(bynickname);
        return flowstepDto;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Flowstep update(@RequestBody FlowstepDto flowstepDto) {
        flowmemberDao.delFlowidAndStepid(flowstepDto.getFlowid(),flowstepDto.getStepid());
        Flowstep flowstep = new Flowstep();
        flowstep.setFlowid(flowstepDto.getFlowid());
        flowstep.setStepid(flowstepDto.getStepid());
        flowstep.setStepname(flowstepDto.getStepname());
        flowstep.setDescription(flowstepDto.getDescription());
        flowstep.setCreateby(flowstepDto.getCreateby());
        flowstepDao.update(flowstep);

        flowstepDto.getNickname().remove(0);
        flowstepDto.getNickname().remove(0);
        for(String s : flowstepDto.getNickname()){
            flowmemberDao.insertFlowmember(flowstepDto.getFlowid(),flowstepDto.getStepid(),s);
        }
        return null;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowstepDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Flowstep> list(PageTableRequest request) {
                List<Flowstep> list = flowstepDao.list(request.getParams(), request.getOffset(), request.getLimit());
                for(Flowstep flowstep : list){
                    List<String> bynickname = flowstepDao.findBynickname(flowstep.getFlowid(), flowstep.getStepid());
                    String sb = "";
                    for(String s : bynickname){
                        sb += s+" ";
                    }
                    flowstep.setC01(sb);
                }
                return list;
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowstepDao.delete(id);
    }

    @GetMapping(value="/liststeps",params = "flowid")
    @ApiOperation(value = "根据流程ID列出该流程所有步骤")
    public List<Flowstep> listSteps(long flowid) {
        List<Flowstep> steps = flowstepDao.listSteps(flowid);
        return steps;
    }

    @GetMapping(value="/listStepsAndMember",params = "flowid")
    @ApiOperation(value = "根据流程ID列出该流程所有步骤和成员")
    public List<FlowstepDto> listStepsAndMember(long flowid) {
        List<FlowstepDto> steps = flowstepDao.listStepsAndMember(flowid);
        return steps;
    }

}
