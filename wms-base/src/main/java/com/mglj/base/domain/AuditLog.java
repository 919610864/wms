package com.mglj.base.domain;

import java.util.Date;


public class AuditLog {
	
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 仓库ID
	 */
	private Long warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 资源编码
	 */
	private String resourceCode;
	/**
	 * 资源中文名
	 */
	private String resourceName;
	/**
	 * 资源分组
	 */
	private String group;
	/**
	 * 操作行为编码
	 */
	private String actionCode;
	/**
	 * 操作中文名
	 */
	private String actionName;
	/**
	 * 单据ID
	 */
	private Long billId;
	/**
	 * 单据号
	 */
	private String billCode;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 操作人ID
	 */
	private Long userId;
	/**
	 * 操作人姓名
	 */
	private String userName;
	/**
	 * 操作时间
	 */
	private Date createTime;
	/**
	 * 花费时间
	 */
	private Integer cost;

	/**
	 * 设置ID
	 */
	public void setId(Long value) {
		this.id = value;
	}
	/**
	 * 获取ID
	 */
	public Long getId() {
		return this.id;
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
	/**
	 * 设置仓库名称
	 */
	public void setWarehouseName(String value) {
		this.warehouseName = value;
	}
	/**
	 * 获取仓库名称
	 */
	public String getWarehouseName() {
		return this.warehouseName;
	}
	/**
	 * 设置资源编码
	 */
	public void setResourceCode(String value) {
		this.resourceCode = value;
	}
	/**
	 * 获取资源编码
	 */
	public String getResourceCode() {
		return this.resourceCode;
	}
	/**
	 * 设置资源中文名
	 */
	public void setResourceName(String value) {
		this.resourceName = value;
	}
	/**
	 * 获取资源中文名
	 */
	public String getResourceName() {
		return this.resourceName;
	}
	/**
	 * 设置资源分组
	 */
	public void setGroup(String value) {
		this.group = value;
	}
	/**
	 * 获取资源分组
	 */
	public String getGroup() {
		return this.group;
	}
	/**
	 * 设置操作行为编码
	 */
	public void setActionCode(String value) {
		this.actionCode = value;
	}
	/**
	 * 获取操作行为编码
	 */
	public String getActionCode() {
		return this.actionCode;
	}
	/**
	 * 设置操作中文名
	 */
	public void setActionName(String value) {
		this.actionName = value;
	}
	/**
	 * 获取操作中文名
	 */
	public String getActionName() {
		return this.actionName;
	}
	/**
	 * 设置单据ID
	 */
	public void setBillId(Long value) {
		this.billId = value;
	}
	/**
	 * 获取单据ID
	 */
	public Long getBillId() {
		return this.billId;
	}
	/**
	 * 设置单据号
	 */
	public void setBillCode(String value) {
		this.billCode = value;
	}
	/**
	 * 获取单据号
	 */
	public String getBillCode() {
		return this.billCode;
	}
	/**
	 * 设置描述
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	/**
	 * 获取描述
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * 设置操作人ID
	 */
	public void setUserId(Long value) {
		this.userId = value;
	}
	/**
	 * 获取操作人ID
	 */
	public Long getUserId() {
		return this.userId;
	}
	/**
	 * 设置操作人姓名
	 */
	public void setUserName(String value) {
		this.userName = value;
	}
	/**
	 * 获取操作人姓名
	 */
	public String getUserName() {
		return this.userName;
	}
	/**
	 * 设置操作时间
	 */
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	/**
	 * 获取操作时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 设置花费时间
	 */
	public void setCost(Integer value) {
		this.cost = value;
	}
	/**
	 * 获取花费时间
	 */
	public Integer getCost() {
		return this.cost;
	}

}

