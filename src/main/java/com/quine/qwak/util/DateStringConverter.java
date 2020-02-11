/**
 * The MIT License (MIT)
 *
 * Copyright 2020-2016 Ivan Dejanovic and Quine Interactive
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
package com.quine.qwak.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Utility class that implements Date to String and String to Date conversion.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 */

public class DateStringConverter {
    private static final GregorianCalendar NULL_DATE        = new GregorianCalendar(1900, 0, 1);
    private static final String            NULL_DATE_STRING = "01/01/1900";

    public static GregorianCalendar String2Date(String value) {
        String[] stringArray = value.split("/");
        if (stringArray.length == 3) {
            int month = Integer.parseInt(stringArray[0]);
            int day = Integer.parseInt(stringArray[1]);
            int year = Integer.parseInt(stringArray[2]);

            return new GregorianCalendar(year, month, day);
        } else {
            return null;
        }
    }

    public static String Date2String(GregorianCalendar value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringValue = dateFormat.format(value.getTime());

        if (stringValue.equals(NULL_DATE_STRING)) {
            return "";
        } else {
            return stringValue;
        }
    }

    public static GregorianCalendar getNullDate() {
        return (GregorianCalendar) NULL_DATE.clone();
    }
}
