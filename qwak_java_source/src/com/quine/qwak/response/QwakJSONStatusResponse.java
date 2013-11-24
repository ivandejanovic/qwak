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

/**
 * Interface that declared all the functionality object needs to implement in
 * order to be send as a JSONstatus status response.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 */
public interface QwakJSONStatusResponse {
	/**
	 * Get the success status.
	 * 
	 * @return success
	 */
	public Boolean getSuccess();

	/**
	 * Set the success status.
	 * 
	 * @param success
	 */
	public void setSuccess(Boolean success);

	/**
	 * Get messages.
	 * 
	 * @return messages
	 */
	public List<String> getMessage();

	/**
	 * Add message to messages list.
	 * 
	 * @param message
	 */
	public void setMessage(String message);
}
