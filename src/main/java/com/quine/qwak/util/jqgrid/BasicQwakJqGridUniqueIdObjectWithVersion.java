/**
 * The MIT License (MIT)
 *
 * Copyright 2008-2020 Ivan Dejanovic and Quine Interactive
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
package com.quine.qwak.util.jqgrid;

/**
 * Convenience class that implements all the functionality of the QwakJqGridUniqueIdInterfaceWithVersion interface.
 * Classes that will be used as Data objects in a AbstractQwakJqGridController can extend this class.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 */
public class BasicQwakJqGridUniqueIdObjectWithVersion extends BasicQwakJqGridUniqueIdObject implements
        QwakJqGridUniqueIdWithVersion {
    protected long version;

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridUIObject#getVersion()
     */
    @Override
    public long getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quine.qwak.response.JqGridUIObject#setVersion(java.lang.Long)
     */
    @Override
    public void setVersion(long version) {
        this.version = version;
    }
}
