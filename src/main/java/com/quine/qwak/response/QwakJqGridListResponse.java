/**
 * The MIT License (MIT)
 *
 * Copyright 2008-2014 Ivan Dejanovic and Quine Interactive
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
 * Template interface that declared all the functionality object needs to implement in order to be send as a list
 * response to qwakJqGrid tag.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface QwakJqGridListResponse<T extends QwakJqGridUniqueStringId> {
    /**
     * Get page that should be displayed on the grid.
     * 
     * @return page
     */
    public String getPage();

    /**
     * Set page that should be displayed on the grid.
     * 
     * @param page
     */
    public void setPage(String page);

    /**
     * Get total number of pages for the grid.
     * 
     * @return total
     */
    public String getTotal();

    /**
     * Set total number of pages for the grid.
     * 
     * @param total
     */
    public void setTotal(String total);

    /**
     * Get total number of records for the grid.
     * 
     * @return records
     */
    public String getRecords();

    /**
     * Get row records that should be displayed in the grid.
     * 
     * @return rows
     */
    public List<T> getRows();

    /**
     * Set row records that should be displayed in the grid.
     * 
     * @param rows
     */
    public void setRows(List<T> rows);
}
