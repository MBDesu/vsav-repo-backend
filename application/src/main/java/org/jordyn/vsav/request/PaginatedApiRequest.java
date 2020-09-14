package org.jordyn.vsav.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to retrieve a range of items from the API
 * Items are dependent on endpoint
 * @author jordyn
 *
 */
public class PaginatedApiRequest {

	@ApiModelProperty(value = "The page to retrieve (starts at zero)")
	private int page;

	@ApiModelProperty(value = "The size of the page; minimum 1, maximum 50")
	@Min(1)
	@Max(50)
	private int pageSize;

	public int getPage() {
		return page;
	}

	public void setPage(final int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}



}
