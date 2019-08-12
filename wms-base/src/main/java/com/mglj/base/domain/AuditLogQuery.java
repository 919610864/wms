package com.mglj.base.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * 的Query
 * 
 * @author zsp
 * @date 2017-10-26
 */
public class AuditLogQuery implements Serializable{
	
	private static final long serialVersionUID = -6214696820066579697L;

	private static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 页索引
	 */
	@Min(message = "page-index-min-0", value = 0)
	private Integer pageIndex = 0;
	/**
	 * 页大小
	 */
	@Range(message = "page-size-1-to-1000", max=1000, min=1)
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * 排序字段
	 */
	private String sortColumns;
	/**
	 * 返回记录的起始位置。
	 */
	private transient Long offset;
	/**
	 * 返回记录的个数。
	 */
	private transient Integer rows;
	
	private Long warehouseId;
	private Date createTimeStart;
	private Date createTimeEnd;
	private String group;
	private String resourceCode;
	private String actionCode;
	private String billCode;
	private String userName;
	
	/**
	 * 构建offset和rows
	 * @return
	 */
	public AuditLogQuery build() {
		this.offset = (long)this.pageIndex * this.pageSize;
		this.rows = this.pageSize;
		return this;
	}
	
	/**
	 * 构建pageIndex
	 * @param pageIndex
	 * @return
	 */
	public AuditLogQuery withPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		if(this.pageIndex == null) {
			this.pageIndex = 0;
		}
		return this;
	}
	
	/**
	 * 构建pageSize
	 * @param pageSize
	 * @return
	 */
	public AuditLogQuery withPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(this.pageSize == null) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
		return this;
	}
	
	/**
	 * 获取页索引
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * 设置页索引
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	/**
	 * 获取页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置页大小
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}	
	
	/**
	 * 获取排序字段
	 */
	public String getSortColumns() {
		return sortColumns;
	}
	
	/**
	 * 设置排序字段
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}
	
	/**
	 * 获取offset
	 * @return
	 */
	public Long getOffset() {
		return offset;
	}

	/**
	 * 获取rows
	 * @return
	 */
	public Integer getRows() {
		return rows;
	}
	
	/**
	 * 设置仓库ID
	 */
	public void setWarehouseId(Long value) {
		this.warehouseId = value;
	}
	/**
	 * 获取仓库ID
	 */
	public Long getWarehouseId() {
		return this.warehouseId;
	}
	
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
