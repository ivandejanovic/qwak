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
package com.quine.qwak.tag;

import java.util.List;

/**
 * QwakJqPlotPieChart tag implementation.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

public class QwakJqPlotPieChartTag extends QwakBasicTag {
    private static final long serialVersionUID = -5655868847744498848L;

    private String       name;
    private String       pieChartLabels;
    private String       pieChartValues;
    private String       onClickCallback;
    private List<String> pieChartLabelsList;
    private List<String> pieChartValuesList;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pieChartLabels
     */
    public String getPieChartLabels() {
        return pieChartLabels;
    }

    /**
     * @param pieChartLabels the pieChartLabels to set
     */
    public void setPieChartLabels(String pieChartLabels) {
        this.pieChartLabels = pieChartLabels;
    }

    /**
     * @return the pieChartValues
     */
    public String getPieChartValues() {
        return pieChartValues;
    }

    /**
     * @param pieChartValues the pieChartValues to set
     */
    public void setPieChartValues(String pieChartValues) {
        this.pieChartValues = pieChartValues;
    }

    /**
     * @return the onClickCallback
     */
    public String getOnClickCallback() {
        return onClickCallback;
    }

    /**
     * @param onClickCallback the onClickCallback to set
     */
    public void setOnClickCallback(String onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    /**
     * @return the pieChartLabelsList
     */
    public List<String> getPieChartLabelsList() {
        return pieChartLabelsList;
    }

    /**
     * @param pieChartLabelsList the pieChartLabelsList to set
     */
    public void setPieChartLabelsList(List<String> pieChartLabelsList) {
        this.pieChartLabelsList = pieChartLabelsList;
    }

    /**
     * @return the pieChartValuesList
     */
    public List<String> getPieChartValuesList() {
        return pieChartValuesList;
    }

    /**
     * @param pieChartValuesList the pieChartValuesList to set
     */
    public void setPieChartValuesList(List<String> pieChartValuesList) {
        this.pieChartValuesList = pieChartValuesList;
    }

    protected void appendTagCode(StringBuilder sb) {
        setPropertiesToLists();

        appendJavaScriptHTMLCode(sb);
    }

    protected void setPropertiesToLists() {
        pieChartLabelsList = propertyToList(pieChartLabels);
        pieChartValuesList = propertyToList(pieChartValues);
    }

    protected void appendJavaScriptHTMLCode(StringBuilder sb) {
        sb.append("<script>\n");
        sb.append("$(function() {\n");
        sb.append("var data = [\n");

        for (int index = 0; index < pieChartLabelsList.size(); ++index) {
            if (index == 0) {
                sb.append("['" + pieChartLabelsList.get(index) + "', " + pieChartValuesList.get(index) + "]\n");
            } else {
                sb.append(",['" + pieChartLabelsList.get(index) + "', " + pieChartValuesList.get(index) + "]\n");
            }
        }

        sb.append("];\n");
        sb.append("jQuery.jqplot ('" + name + "Id', [data],\n");
        sb.append("{\n");
        sb.append("seriesDefaults: {\n");
        sb.append("renderer: jQuery.jqplot.PieRenderer,\n");
        sb.append("rendererOptions: {\n");
        sb.append("startAngle: -180,\n");
        sb.append("showDataLabels: true,\n");
        sb.append("dataLabels: 'value'\n");
        sb.append("}\n");
        sb.append("},\n");
        sb.append("legend: { show:true, location: 'e' }\n");
        sb.append("}\n");
        sb.append(");\n");
        if (onClickCallback != null) {
            sb.append("$('#" + name + "Id').bind('jqplotDataClick',\n");
            sb.append("function (ev, seriesIndex, pointIndex, data) {\n");
            sb.append(onClickCallback + "(data);\n");
            sb.append("}\n");
            sb.append(");\n");
        }
        sb.append("});\n");
        sb.append("</script>\n");
        sb.append("<div id=\"" + name + "Id\" ></div>\n");
    }
}