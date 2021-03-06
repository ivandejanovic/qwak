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
package com.quine.qwak.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Abstract base class of Qwak tag set.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public abstract class QwakBasicTag extends TagSupport {
    private static final long serialVersionUID = -2052091567459364528L;

    @Override
    public int doStartTag() throws JspException {
        try {
            // Get the writer object for output.
            JspWriter out = pageContext.getOut();

            // Generate tag
            StringBuilder sb = new StringBuilder();

            appendTagCode(sb);

            out.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    protected abstract void appendTagCode(StringBuilder sb);

    protected List<String> propertyToList(String property) {
        List<String> list = new ArrayList<String>();

        String[] propertySplit = property.split(",");

        for (String temp : propertySplit) {
            list.add(temp.trim());
        }

        return list;
    }
}