QWAK - Quine Web Application Kit
===============================

What is Qwak

QWAK, Quine Web Application Kit that can be used to produce rich web solutions. QWAK provides extension to Spring, jQuery, jqGrid and jqPlot.
QWAK components

First part is custom tag library that provide set of interactive components. All that the developer using QWAK is required to do is place a tag with proper parameters on to jsp.

Second part consists of set of abstract controllers that implement most of functionality necessary for responding to user input. Developer needs to extend appropriate controller by setting it on a URL that was given to tag as a parameter and implement just a couple of methods. QWAK will take care of rest of the work required to capture the user input, process it and render appropriate response.

Third part of QWAK is JavaScript library. This library defines set of utility functions that aim to provide basic client functionality and relieve developer from writing same code over and over.

QWAK is still work in progress. First version is still not finely polish and documentation is still not completed. 