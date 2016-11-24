package com.ushine.common.vo;

/**
 * 分页信息对象,记录分页信息
 * 
 * @author Franklin
 *
 */
public class Paging {
	
	/**
	 * 默认每页20条记录
	 */
	public static final int DEFAULT_SIZE_PAGE = 20;
	
	/**
	 * 每页记录数
	 */
	private int sizePage;
	
	/**
	 * 总记录数
	 */
	private long totalRecord;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 当前页码
	 */
	private int currentPage;
	
	/**
	 * 起始记录
	 */
	private int startRecord;
	
	/**
	 * 是否有上一页
	 */
	private boolean isPrevious;
	
	/**
	 * 是否有下一页
	 */
	private boolean isNext;
	
	/**
	 * 构造分页信息（默认每页20条记录）
	 * @param currentPage int 当前页
	 * @param totalRecord int 总记录数
	 */
	public Paging(int currentPage, int totalRecord) {
		this.sizePage = DEFAULT_SIZE_PAGE;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		count();
	}
	
	/**
	 * 构造分页信息
	 * @param sizePage int 每页记录数
	 * @param currentPage int 当前页
	 * @param totalRecord int 总记录数
	 */
	public Paging(int sizePage, int currentPage, long totalRecord) {
		this.sizePage = sizePage;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		count();
	}
	
	/**
	 * 计算页信息 
	 */
	private void count() {
		// 计算总页数
		if((totalRecord % sizePage) == 0) {
			totalPage = (int)(totalRecord / (long)sizePage);
		} else {
			totalPage = (int)(totalRecord / (long)sizePage + 1);
		}
		
		// 判断是否有下一页
		if(currentPage >= totalPage) {
			isNext = false;
			currentPage = totalPage;
		} else {
			isNext = true;
		}
		
		// 判断是否有上一页
		if(currentPage <= 1) {
			isPrevious = false;
			currentPage = 1;
		} else {
			isPrevious = true;
		}
		
		// 开始记录
		startRecord = (currentPage - 1) * sizePage;
	}

	/*
	 * Set And Get Method
	 */
	
	public int getSizePage() {
		return sizePage;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public boolean isPrevious() {
		return isPrevious;
	}

	public boolean isNext() {
		return isNext;
	}
	
}