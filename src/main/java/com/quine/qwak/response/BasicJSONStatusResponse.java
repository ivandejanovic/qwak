/**
 * The MIT License (MIT)
 *
 * Copyright 2008-2015 Ivan Dejanovic and Quine Interactive
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

import java.util.ArrayList;
import java.util.List;

/**
 * Convenience class that implements all the functionality object needs to implement in order to be send as a JSON
 * status response.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class BasicJSONStatusResponse implements QwakJSONStatusResponse {
    private boolean      success;

    private List<String> messages;

    public BasicJSONStatusResponse() {
        messages = new ArrayList<String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridStatusResponse#getSuccess()
     */
    @Override
    public Boolean getSuccess() {
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridStatusResponse#setSuccess(java.lang.Boolean )
     */
    @Override
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridStatusResponse#getMessage()
     */
    @Override
    public List<String> getMessage() {
        return messages;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridStatusResponse#setMessage(java.lang.String)
     */
    @Override
    public void setMessage(String message) {
        this.messages.add(message);
    }
}
