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
package com.quine.qwak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.icepush.PushContext;

import com.quine.qwak.response.QwakJSONStatusResponse;
import com.quine.qwak.util.jqgrid.QwakJqGridUniqueId;
import com.quine.qwak.util.jqgrid.QwakJqGridUniqueStringId;

/**
 * AbstractQwakJqGridControllerWithICEPush class extends
 * AbstractQwakJqGridController with ICEpush support.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 */

abstract public class AbstractQwakJqGridControllerWithICEPush<T extends QwakJqGridUniqueId, V extends QwakJqGridUniqueStringId, U>
		extends AbstractQwakJqGridController<T, V, U> {
	@RequestMapping(params = "add")
	public @ResponseBody
	QwakJSONStatusResponse add(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("add") String add,
			@RequestParam("gridname") String gridName) throws Exception {
		QwakJSONStatusResponse status = super.add(request, response, add,
				gridName);

		if (status.getSuccess()) {
			PushContext pushContext = PushContext.getInstance(request
					.getServletContext());
			pushContext.push(getPushTopic());
		}

		return status;
	}

	@RequestMapping(params = "edit")
	public @ResponseBody
	QwakJSONStatusResponse edit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("edit") String edit,
			@RequestParam("gridname") String gridName,
			@RequestParam("id") String id) throws Exception {
		QwakJSONStatusResponse status = super.edit(request, response, edit,
				gridName, id);

		if (status.getSuccess()) {
			PushContext pushContext = PushContext.getInstance(request
					.getServletContext());
			pushContext.push(getPushTopic());
		}

		return status;
	}

	@RequestMapping(params = "delete")
	public @ResponseBody
	QwakJSONStatusResponse delete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("delete") String delete,
			@RequestParam("gridname") String gridName,
			@RequestParam("id") String id) throws Exception {
		QwakJSONStatusResponse status = super.delete(request, response, delete,
				gridName, id);

		if (status.getSuccess()) {
			PushContext pushContext = PushContext.getInstance(request
					.getServletContext());
			pushContext.push(getPushTopic());
		}

		return status;
	}

	/**
	 * Creates an instance of the data object used in the controller.
	 * 
	 * @return pushTopic
	 */
	abstract protected String getPushTopic();
}
