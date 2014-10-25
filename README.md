QWAK - Quine Web Application Kit
===============================

What is QWAK:

QWAK was first open source project that I stared way back when I was starting with web development. Idea was to produce a library that would provide several custom tags that will ease my development of a project I was working on at the time. Since my JavaScript experience at the time was pretty bad I though that by implementing enough custom tags through QWAK I could just focus on the backend and I would still be able to produce rich web applications. Turned out that by working on QWAK I learned how powerful JavaScript is. I started using more and more JavaScript in my project and eventually moved to JavaScript MVCs like Backbone and Angular for my frontend development. QWAK also losed its appeal with appearance of modern component based frameworks for Java like PrimeFaces, Vaadin etc. QWAK is not something that I can recomend for some serious development.

Why I keep its github repo then you could ask. Well two reasons. Sentimental value because this is my first open source project. Second reason is that I belive that it can help people coming to Java web development. Focus today is on using powerful frameworks with a lot of features available out of the box. Learning courve for somebody new can be quite steep. Maybe for people that are just starting the simplicity of QWAK can provide some useful guidance.

What QWAK consist of:

First part is custom tag library that provide set of interactive components. All that the developer using QWAK is required to do is place a tag with proper parameters on to jsp.

Second part consists of set of abstract controllers that implement most of functionality necessary for responding to user input. Developer needs to extend appropriate controller by setting it on a URL that was given to tag as a parameter and implement just a couple of methods. QWAK will take care of rest of the work required to capture the user input, process it and render appropriate response.

Third part of QWAK is JavaScript library. This library defines set of utility functions that aim to provide basic client functionality and relieve developer from writing same code over and over.

QWAK is not under active development but I still come to it from time to time in order to complete features that I envisioned when I started the project. 