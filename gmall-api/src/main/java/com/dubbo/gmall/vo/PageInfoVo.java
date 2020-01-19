package com.dubbo.gmall.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * mode class
 *
 * @Author ZengHao
 * @Date 2020/1/13 10:10
 */
@NoArgsConstructor //无参构造器
@AllArgsConstructor //全参构造器
@ApiModel
@Data
@Accessors(chain = true)
public class PageInfoVo implements Serializable {
    @ApiModelProperty("总记录数")
    private Long total;
    @ApiModelProperty("总页码")
    private Long totalPage;
    @ApiModelProperty("每页显示的记录数")
    private Long pageSize;
    @ApiModelProperty("分页查出的数据")
    private List<? extends Object> list;
    @ApiModelProperty
    private Long pageNum;

    public static PageInfoVo getVo(IPage iPage,Long size){
        PageInfoVo pageInfoVo = new PageInfoVo(iPage.getTotal(), iPage.getPages(), size, iPage.getRecords(), iPage.getCurrent());
        return pageInfoVo;
    }
}
