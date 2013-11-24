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
package com.quine.qwak.tag;

import java.util.List;

/**
 * QwakComboBox tag implementation.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 */
public class QwakComboBoxTag extends QwakBasicTag {
	private String name;
	private String comboBoxOptions;
	private String comboBoxValues;
	private String selectedValue;
	private List<String> comboBoxOptionsList;
	private List<String> comboBoxValuesList;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the comboBoxOptions
	 */
	public String getComboBoxOptions() {
		return comboBoxOptions;
	}

	/**
	 * @param comboBoxOptions
	 *            the comboBoxOptions to set
	 */
	public void setComboBoxOptions(String comboBoxOptions) {
		this.comboBoxOptions = comboBoxOptions;
	}

	/**
	 * @return the comboBoxValues
	 */
	public String getComboBoxValues() {
		return comboBoxValues;
	}

	/**
	 * @param comboBoxValues
	 *            the comboBoxValues to set
	 */
	public void setComboBoxValues(String comboBoxValues) {
		this.comboBoxValues = comboBoxValues;
	}

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue
	 *            the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	@Override
	protected void appendTagCode(StringBuilder sb) {
		setPropertiesToLists();

		appendJavaScriptHTMLCode(sb);
	}

	protected void setPropertiesToLists() {
		comboBoxOptionsList = propertyToList(comboBoxOptions);
		comboBoxValuesList = propertyToList(comboBoxValues);
	}

	protected void appendJavaScriptHTMLCode(StringBuilder sb) {
		sb.append("<script>\n");
		sb.append("$(function() {\n");
		sb.append("$( \"#" + name + "Id\" ).combobox();\n");
		sb.append("});\n");
		sb.append("</script>\n");
		sb.append("<select name=\"" + name + "\" id=\"" + name + "Id\">\n");
		sb.append("<option value=\"\"></>\n");
		int index = 0;
		for (String option : comboBoxOptionsList) {
			if (selectedValue != null
					&& selectedValue.equals(comboBoxValuesList.get(index))) {
				sb.append("<option selected value="
						+ comboBoxValuesList.get(index++) + ">" + option
						+ "</>\n");
			} else {
				sb.append("<option value=" + comboBoxValuesList.get(index++)
						+ ">" + option + "</>\n");
			}
		}
		sb.append("</select>\n");
	}
}
