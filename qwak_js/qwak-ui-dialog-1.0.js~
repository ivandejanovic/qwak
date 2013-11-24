/*
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
 */
//Author: Ivan Dejanovic idejanovic@gmail.com

function dialogDestroy(dialog) {
    "use strict";
    dialog.dialog('destroy');
}

function openOkCancelDialog(id, url, autoOpenFlag, title, modalFlag,
    resizableFlag, width, okCallback, cancelCallback) {
    "use strict";
    var dialog = $('#' + id),
        okFunction,
        cancelFunction;

    // Check if element in which dialog should be placed exist
    if (dialog === null || dialog === undefined) {
        console.log('Element with id: ' + id + ' does not exist. Dialog can not be opened.');
        return;
    }

    // Check if okCallback is proper function
    if (okCallback === null || okCallback === undefined || typeof okCallback !== 'function') {
        okFunction = dialogDestroy;
    } else {
        okFunction = okCallback;
    }

    // Check if cancelCallback is proper function
    if (cancelCallback === null || cancelCallback === undefined || typeof cancelCallback !== 'function') {
        cancelFunction = dialogDestroy;
    } else {
        cancelFunction = okCallback;
    }

    dialog.empty();
    dialog.load(url).dialog({
        autoOpen: autoOpenFlag,
        title: title,
        modal: modalFlag,
        resisable: resizableFlag,
        width: width,
        buttons: {
            'Ok': function () {
                okFunction(dialog);
            },
            'Cancel': function () {
                cancelFunction(dialog);
            }
        }
    });
}

function openOkDialog(id, url, autoOpenFlag, title, modalFlag, resizableFlag,
    width, okCallback) {
    "use strict";
    var dialog = $('#' + id),
        okFunction;

    // Check if element in which dialog should be placed exist
    if (dialog === null || dialog === undefined) {
        console.log('Element with id: ' + id + ' does not exist. Dialog can not be opened.');
        return;
    }

    // Check if okCallback is proper function
    if (okCallback === null || okCallback === undefined || typeof okCallback !== 'function') {
        okFunction = dialogDestroy;
    } else {
        okFunction = okCallback;
    }

    dialog.empty();
    dialog.load(url).dialog({
        autoOpen: autoOpenFlag,
        title: title,
        modal: modalFlag,
        resisable: resizableFlag,
        width: width,
        buttons: {
            'Ok': function () {
                okFunction(dialog);
            }
        }
    });
}
