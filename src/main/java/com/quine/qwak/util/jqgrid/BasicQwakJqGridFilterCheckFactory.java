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

import java.util.ArrayList;
import java.util.List;

import com.quine.qwak.util.jqgrid.QwakJqGridFilterColumnRule.Op;

/**
 * Template factory class that implements all the functionality necessary for creating appropriate QwakJqGridFilterCheck
 * object.
 * 
 * @author Ivan Dejanovic
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 */
public class BasicQwakJqGridFilterCheckFactory {
    private static String AND_GROUP_STRING   = "{\"groupOp\":\"AND\",\"rules\":";
    private static String OR_GROUP_STRING    = "{\"groupOp\":\"OR\",\"rules\":";
    private static String START_RULE_STRING  = "{";
    private static String END_RULE_STRING    = "}";
    private static String FIELD_START_STRING = "\"field\":\"";
    private static String OP_START_STRING    = "\"op\":\"";
    private static String DATA_START_STRING  = "\"data\":\"";

    public static <T extends QwakJqGridUniqueId>
            QwakJqGridFilterCheck<T>
            createQwakJqGridFilterCheck(QwakJqGridFilterColumnCheck<T> filterColumnCheck, String filters) {
        boolean andFlag = false;
        int rulesIndex = 0;

        if (filterColumnCheck == null)
            return null;

        if (filters.startsWith(AND_GROUP_STRING) && filters.endsWith(END_RULE_STRING)) {
            andFlag = true;
            rulesIndex = AND_GROUP_STRING.length();
        } else if (filters.startsWith(OR_GROUP_STRING) && filters.endsWith(END_RULE_STRING)) {
            andFlag = false;
            rulesIndex = OR_GROUP_STRING.length();
        } else
            return null;

        String rules = filters.substring(rulesIndex);
        rules = rules.substring(0, rules.length() - 1);
        List<String> rulesList = parseRules(rules);
        List<QwakJqGridFilterColumnRule> columnRules = createColumnRulesList(rulesList);

        if (columnRules.isEmpty())
            return null;

        if (andFlag) {
            return new BasicAndQwakJqGridFilterCheck<T>(filterColumnCheck, columnRules);
        } else {
            return new BasicOrQwakJqGridFilterCheck<T>(filterColumnCheck, columnRules);
        }
    }

    private static List<String> parseRules(String rules) {
        List<String> rulesList = new ArrayList<String>();
        boolean found = true;
        String leftoverRules = rules;
        String rule;
        int start = -1;
        int end = -1;

        while (found) {
            start = leftoverRules.indexOf(START_RULE_STRING);
            end = leftoverRules.indexOf(END_RULE_STRING);

            if (start > 0 && end > 0 && start < end)
                found = true;
            else
                found = false;

            if (found) {
                rule = leftoverRules.substring(start + 1, end);
                leftoverRules = leftoverRules.substring(end + 1);
                rulesList.add(rule);
            }
        }

        return rulesList;
    }

    private static List<QwakJqGridFilterColumnRule> createColumnRulesList(List<String> rulesList) {
        List<QwakJqGridFilterColumnRule> columnRules = new ArrayList<QwakJqGridFilterColumnRule>();

        for (String rule : rulesList) {
            String field = "";
            String op = "";
            String data = "";
            String[] ruleArray = rule.split(",");
            Op operation = null;

            if (ruleArray.length == 3) {
                if (ruleArray[0].startsWith(FIELD_START_STRING) && ruleArray[0].endsWith("\"")) {
                    field = ruleArray[0].substring(FIELD_START_STRING.length(), ruleArray[0].length() - 1);
                }
                if (ruleArray[1].startsWith(OP_START_STRING) && ruleArray[1].endsWith("\"")) {
                    op = ruleArray[1].substring(OP_START_STRING.length(), ruleArray[1].length() - 1);
                }
                if (ruleArray[2].startsWith(DATA_START_STRING) && ruleArray[2].endsWith("\"")) {
                    data = ruleArray[2].substring(DATA_START_STRING.length(), ruleArray[2].length() - 1);
                }

                switch (op) {
                    case "eq":
                        operation = QwakJqGridFilterColumnRule.Op.EQ;
                        break;
                    case "ne":
                        operation = QwakJqGridFilterColumnRule.Op.NE;
                        break;
                    case "lt":
                        operation = QwakJqGridFilterColumnRule.Op.LT;
                        break;
                    case "gt":
                        operation = QwakJqGridFilterColumnRule.Op.GT;
                        break;
                    case "cn":
                        operation = QwakJqGridFilterColumnRule.Op.CN;
                        break;
                    case "bw":
                        operation = QwakJqGridFilterColumnRule.Op.BW;
                        break;
                    case "ew":
                        operation = QwakJqGridFilterColumnRule.Op.EW;
                        break;
                }

                if (!field.isEmpty() && operation != null && !data.isEmpty()) {
                    columnRules.add(new BasicQwakJqGridFilterColumnRule(field, operation, data));
                }
            }
        }

        return columnRules;
    }
}