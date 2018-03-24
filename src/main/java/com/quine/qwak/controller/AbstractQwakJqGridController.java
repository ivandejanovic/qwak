/**
 * The MIT License (MIT)
 *
 * Copyright 2008-2016 Ivan Dejanovic and Quine Interactive
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
package com.quine.qwak.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quine.qwak.response.BasicJSONStatusResponse;
import com.quine.qwak.response.BasicQwakJqGridListResponse;
import com.quine.qwak.response.QwakJqGridListResponse;
import com.quine.qwak.response.QwakJSONStatusResponse;
import com.quine.qwak.util.jqgrid.BasicQwakJqGridFilterCheckFactory;
import com.quine.qwak.util.jqgrid.BasicQwakJqGridSort;
import com.quine.qwak.util.jqgrid.QwakJqGridFilterCheck;
import com.quine.qwak.util.jqgrid.QwakJqGridFilterColumnCheck;
import com.quine.qwak.util.jqgrid.QwakJqGridSort;
import com.quine.qwak.util.jqgrid.QwakJqGridUniqueId;
import com.quine.qwak.util.jqgrid.QwakJqGridUniqueStringId;

/**
 * AbstractQwakJqGridController class implements most functionality necessary for responding to requests from qwakJqGrid
 * tag. T object represent data object that should be processed in services. V object represent UI object that represent
 * one row in qwakJqGrid tag.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

@Controller
abstract public class AbstractQwakJqGridController<T extends QwakJqGridUniqueId, V extends QwakJqGridUniqueStringId, U> {
    @RequestMapping(params = "list")
    public @ResponseBody QwakJqGridListResponse<V> list(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @RequestParam("list") String list,
                                                        @RequestParam("gridname") String gridName) throws Exception {
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int rowsOnPage = Integer.parseInt(request.getParameter("rows"));

        String search = request.getParameter("_search");

        QwakJqGridFilterCheck<T> filterCheck = null;

        if (search.equalsIgnoreCase("true")) {
            String filters = request.getParameter("filters");
            QwakJqGridFilterColumnCheck<T> filterColumnCheck = getFilterColumnCheck();
            filterCheck = BasicQwakJqGridFilterCheckFactory.createQwakJqGridFilterCheck(filterColumnCheck, filters);
        }

        U user = getUser(request);
        Object additionalRequestDataObject = getAdditionalRequestData(request);

        List<T> dataList;

        if (user == null) {
            dataList = new Vector<T>();
        } else {
            if (filterCheck == null) {
                dataList = getRequestedDataObjects(user, additionalRequestDataObject);
            } else {
                dataList = getRequestedDataObjects(user, filterCheck, additionalRequestDataObject);
            }
        }

        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        QwakJqGridSort sort = new BasicQwakJqGridSort(sidx, sord);
        Comparator<T> comparator = getComparator(sort);

        Collections.sort(dataList, comparator);

        int records = dataList.size();

        int totalPages = records / rowsOnPage;
        int remainder = records % rowsOnPage;

        if (remainder > 0) {
            ++totalPages;
        }

        if (currentPage > totalPages) {
            currentPage = 1; // set current page to one in case requested page is out of bound
        }

        int startIndex = (currentPage - 1) * rowsOnPage;
        int endIndex = (currentPage * rowsOnPage) - 1;

        if (endIndex > records) {
            endIndex = records;
        }

        List<V> uiList = new ArrayList<V>();

        for (int index = startIndex; index < endIndex; ++index) {
            T t = dataList.get(index);
            V v = getNewUIInstance();
            v.setId(gridName + t.getId());
            uiList.add(fillUIFromData(t, v));
        }

        QwakJqGridListResponse<V> listResponse = new BasicQwakJqGridListResponse<V>();
        listResponse.setRows(uiList);
        listResponse.setPage(String.valueOf(currentPage));
        listResponse.setTotal(String.valueOf(totalPages));

        return listResponse;
    }

    @RequestMapping(params = "add")
    public @ResponseBody QwakJSONStatusResponse add(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @RequestParam("add") String add,
                                                    @RequestParam("gridname") String gridName) throws Exception {
        T t = getNewDataInstance();
        t.setId(QwakJqGridUniqueId.INVALID_ID);

        U user = getUser(request);

        if (user == null) {
            return userNotFound();
        }

        QwakJSONStatusResponse status = parseRequest(request, t, user);
        if (status.getSuccess()) {
            status = insertDataObject(user, t);
        }

        return status;
    }

    @RequestMapping(params = "edit")
    public @ResponseBody QwakJSONStatusResponse edit(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestParam("edit") String edit,
                                                     @RequestParam("gridname") String gridName,
                                                     @RequestParam("id") String id) throws Exception {
        T t = getNewDataInstance();

        U user = getUser(request);

        if (user == null) {
            return userNotFound();
        }

        t.setId(Integer.parseInt(id.replace(gridName, "")));

        QwakJSONStatusResponse status = parseRequest(request, t, user);
        if (status.getSuccess()) {
            status = updateDataObject(user, t);
        }

        return status;
    }

    @RequestMapping(params = "delete")
    public @ResponseBody QwakJSONStatusResponse delete(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       @RequestParam("delete") String delete,
                                                       @RequestParam("gridname") String gridName,
                                                       @RequestParam("id") String id) throws Exception {
        U user = getUser(request);

        if (user == null) {
            return userNotFound();
        }

        T t = loadDataObject(user, Integer.parseInt(id.replace(gridName, "")));

        return deleteDataObject(user, t);
    }

    protected Comparator<T> getComparator(QwakJqGridSort sort) {
        if (sort.getSortColumn().compareTo("id") == 0) {
            if (sort.isAscending()) {
                return (Comparator<T>) INDEX_ASCENDING_COMPARATOR;
            } else {
                return (Comparator<T>) INDEX_DESCENDING_COMPARATOR;
            }
        }

        Comparator<T> comparator = getNonIndexComparator(sort);

        if (comparator == null) {
            comparator = INDEX_ASCENDING_COMPARATOR;
        }

        return comparator;
    }

    /**
     * Gets user logged in to the session that initiated the request. If session authentication is not performed it is
     * necessary to return a dummy object since qwak will check user object and block operations if user is null.
     * 
     * @param request
     * @return user
     */
    abstract protected U getUser(HttpServletRequest request);

    /**
     * Creates an instance of the data object used in the controller.
     * 
     * @return data
     */
    abstract protected T getNewDataInstance();

    /**
     * Creates an instance of the UI object used in the controller.
     * 
     * @return ui
     */
    abstract protected V getNewUIInstance();

    /**
     * Returns appropriate comparator based on requested sort order. If appropriate comparator does not exit it should
     * return null.
     * 
     * @param sort
     * @return comparator
     */
    abstract protected Comparator<T> getNonIndexComparator(QwakJqGridSort sort);

    /**
     * Returns filter column check object. If appropriate check object does not exist is should return null.
     * 
     * @return filterColumnCheck
     */
    abstract protected QwakJqGridFilterColumnCheck<T> getFilterColumnCheck();

    /**
     * Sets up field in the UI object based on the data object. Unique id of object t will be set prior to calling this
     * method so no need to set id of object t.
     * 
     * @param t
     * @param v
     * @return ui object
     */
    abstract protected V fillUIFromData(T t, V v);

    /**
     * Gets all additional data from the request and packs it in a returned object.
     * 
     * @param request
     * @return additionalRequestDataObject
     */
    abstract protected Object getAdditionalRequestData(HttpServletRequest request);

    /**
     * Parse parameters from request, validate them and set them in the data object. Data objects id will be set prior
     * to calling this method so setting id from request is not necessary. Returns full QwakJqGridStatusResponse so we
     * can report any problems to the user.
     * 
     * @param request
     * @param t
     * @param user
     * @return statusResponse
     */
    abstract protected QwakJSONStatusResponse parseRequest(HttpServletRequest request, T t, U user);

    /**
     * Insert data object. Returns full QwakJqGridStatusResponse so we can report any problems to the user.
     * 
     * @param user
     * @param t
     * 
     * @return qwakJqGridStatusResponse
     */
    abstract protected QwakJSONStatusResponse insertDataObject(U user, T t);

    /**
     * Load data object with given id.
     * 
     * @param user
     * @param id
     * 
     * @return object
     */
    abstract protected T loadDataObject(U user, long id);

    /**
     * Update data object. Returns full QwakJqGridStatusResponse so we can report any problems to the user.
     * 
     * @param user
     * @param t
     * 
     * @return qwakJqGridStatusResponse
     */
    abstract protected QwakJSONStatusResponse updateDataObject(U user, T t);

    /**
     * Delete data object. Returns full QwakJqGridStatusResponse so we can report any problems to the user.
     * 
     * @param user
     * @param t
     * 
     * @return qwakJqGridStatusResponse
     */
    abstract protected QwakJSONStatusResponse deleteDataObject(U user, T t);

    /**
     * Gets the list of requested data objects associated with the grid.
     * 
     * @param additionalRequestDataObject
     * @param user
     *
     * @return list
     */
    abstract protected List<T> getRequestedDataObjects(U user, Object additionalRequestDataObject);

    /**
     * Gets the list of requested data objects associated with the grid that match the filter.
     * 
     * @param user
     * @param filterCheck
     * @param additionalRequestDataObject
     * 
     * @return list
     */
    abstract protected List<T> getRequestedDataObjects(U user,
                                                       QwakJqGridFilterCheck<T> filterCheck,
                                                       Object additionalRequestDataObject);

    private Comparator<T> INDEX_ASCENDING_COMPARATOR  = new Comparator<T>() {
                                                          @Override
                                                          public int compare(T o1, T o2) {
                                                              return (int) (o1.getId() - o2.getId());
                                                          }
                                                      };

    private Comparator<T> INDEX_DESCENDING_COMPARATOR = new Comparator<T>() {
                                                          @Override
                                                          public int compare(T o1, T o2) {
                                                              return (int) ((-1) * (o1.getId() - o2.getId()));
                                                          }
                                                      };

    private QwakJSONStatusResponse userNotFound() {
        QwakJSONStatusResponse response = new BasicJSONStatusResponse();
        response.setSuccess(false);
        response.setMessage("Failed to identify logged in user. Operation aborted.");
        return response;
    }
}
