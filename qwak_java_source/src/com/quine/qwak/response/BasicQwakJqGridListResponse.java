/**
 * The MIT License (MIT)
 * 
 * Copyright 2008-2013 Quine Interactive and other contributors
 * www.quineinteractive.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.quine.qwak.response;

import java.util.List;

import com.quine.qwak.util.jqgrid.QwakJqGridUniqueStringId;

/**
 * Convenience template class that defines all the functionality object needs to
 * implement in order to be send as a list response to qwakJqGrid tag. This
 * class can be used as base class for list response classes to qwakJqGrid tag.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 */
public class BasicQwakJqGridListResponse<T extends QwakJqGridUniqueStringId>
		implements QwakJqGridListResponse<T> {
	/**
	 * Current page of the grid
	 */
	private String page;

	/**
	 * Total pages for the grid
	 */
	private String total;

	/**
	 * An array that contains the actual objects
	 */
	private List<T> rows;

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String getTotal() {
		return total;
	}

	@Override
	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String getRecords() {
		return "" + rows.size();
	}

	@Override
	public List<T> getRows() {
		return rows;
	}

	@Override
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
