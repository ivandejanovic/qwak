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
package com.quine.qwak.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.quine.qwak.util.jqgrid.QwakJqGridFilterColumnRule;

/**
 * Operation comparator container contains static functions that check if passed parameters match requested operation.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class OperationComparatorContainer {
    public static boolean isStringInOperation(String data, String condition, QwakJqGridFilterColumnRule.Op operation) {
        switch (operation) {
            case EQ:
                if (data.compareTo(condition) == 0)
                    return true;
                else
                    return false;
            case NE:
                if (data.compareTo(condition) != 0)
                    return true;
                else
                    return false;
            case LT:
                if (data.compareTo(condition) < 0)
                    return true;
                else
                    return false;
            case GT:
                if (data.compareTo(condition) > 0)
                    return true;
                else
                    return false;
            case CN:
                if (data.contains(condition))
                    return true;
                else
                    return false;
            case BW:
                if (data.startsWith(condition))
                    return true;
                else
                    return false;
            case EW:
                if (data.endsWith(condition))
                    return true;
                else
                    return false;
        }

        return true;
    }

    public static boolean isDateOnlyInOperation(Date data, Date condition, QwakJqGridFilterColumnRule.Op operation) {
        GregorianCalendar dataCalendar = new GregorianCalendar();
        dataCalendar.setTime(data);

        int dataYear = dataCalendar.get(Calendar.YEAR);
        int dataMonth = dataCalendar.get(Calendar.MONTH);
        int dataDayOfMonth = dataCalendar.get(Calendar.DAY_OF_MONTH);

        GregorianCalendar conditionCalendar = new GregorianCalendar();
        conditionCalendar.setTime(condition);

        int conditionYear = conditionCalendar.get(Calendar.YEAR);
        int conditionMonth = conditionCalendar.get(Calendar.MONTH);
        int conditionDayOfMonth = conditionCalendar.get(Calendar.DAY_OF_MONTH);

        switch (operation) {
            case EQ:
                return (dataYear == conditionYear && dataMonth == conditionMonth && dataDayOfMonth == conditionDayOfMonth);
            case NE:
                return !(dataYear == conditionYear && dataMonth == conditionMonth && dataDayOfMonth == conditionDayOfMonth);
            case LT:
                if (dataYear < conditionYear) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth < conditionMonth) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth == conditionMonth
                        && dataDayOfMonth < conditionDayOfMonth) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth == conditionMonth
                        && dataDayOfMonth == conditionDayOfMonth) {
                    return false;
                } else {
                    return false;
                }
            case GT:
                if (dataYear > conditionYear) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth > conditionMonth) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth == conditionMonth
                        && dataDayOfMonth > conditionDayOfMonth) {
                    return true;
                } else if (dataYear == conditionYear && dataMonth == conditionMonth
                        && dataDayOfMonth == conditionDayOfMonth) {
                    return false;
                } else {
                    return false;
                }
            default:
                return true;
        }
    }

    public static boolean isDateInOperation(Date data, Date condition, QwakJqGridFilterColumnRule.Op operation) {
        switch (operation) {
            case EQ:
                return data.equals(condition);
            case NE:
                return !data.equals(condition);
            case LT:
                return data.before(condition);
            case GT:
                return data.after(condition);
            default:
                return true;
        }
    }

    public static boolean isDoubleInOperation(double data, double condition, QwakJqGridFilterColumnRule.Op operation) {
        double tolerance = 0.001;

        switch (operation) {
            case EQ:
                return (Math.abs(data - condition) < tolerance);
            case NE:
                return (Math.abs(data - condition) > tolerance);
            case LT:
                return data < condition;
            case GT:
                return data > condition;
            default:
                return true;
        }
    }

    public static boolean isLongInOperation(long data, long condition, QwakJqGridFilterColumnRule.Op operation) {
        switch (operation) {
            case EQ:
                return data == condition;
            case NE:
                return data != condition;
            case LT:
                return data < condition;
            case GT:
                return data > condition;
            default:
                return true;
        }
    }
}