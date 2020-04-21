package com.company.project.controller;

import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.company.project.model.OrderItem;
import com.company.project.service.OrderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: YUEXIN
 * @Date: 2020-04-21 11:04:08
 */
@Api("订单相关接口")
@RestController
@RequestMapping("/order/item")
public class OrderItemController {
    @Resource
    private OrderItemService orderItemService;

    @ApiOperation(value = "新增订单", notes = "添加订单信息")
    @ApiImplicitParam(name = "orderItem", value = "订单", required = true, dataType = "OrderItem")
    @PostMapping("/add")
    public Result add(@RequestBody OrderItem orderItem) {
        orderItemService.insert(orderItem);
        return ResultGenerator.success();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orderItemService.deleteById(id);
        return ResultGenerator.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody OrderItem orderItem) {
        orderItemService.update(orderItem);
        return ResultGenerator.success();
    }

    @ApiOperation(value = "订单详情", notes = "根据id查询订单详情")
    @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "Integer")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OrderItem orderItem = orderItemService.findById(id);
        return ResultGenerator.success(orderItem);
    }

    @ApiOperation(value = "订单", notes = "查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "Integer")
    })
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OrderItem> list = orderItemService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.success(pageInfo);
    }
}
