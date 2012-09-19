# TSSG 3MT Documentation System
This repository contains the following major items:

- Asciidoc2html-pdf toolchain 
- Apache Ant build wrapper system for the toolchain
- Ruby Rake build wrapper system for the toolchain


## asciidoc2html-pdf/
This directory contains the codebase for the asciidoc2html-pdf toolchain wrapper for the Asciidoc tool, while it is possible to run
the script directly, it is recommended you interact with the system through the doc-ant system instead, as it adds
a more user friendly interface for the system.


### Document Template Configurations
The following is a sample Header Template configuration for a document. This must be placed at the top of the document
before any further Asciidoc content. It is used by both the HTML and PDF template generation java helper jar.
This system is rather fragile at present, and will most likely break if you attempt to alter the structure or swap
the order they appear in etc.


    :reporttype:    HOWTO
    :reporttitle:   Configuring a .netrc file for automatic user authentication with bitbucket.org
    :author:        David Kirwan
    :email:         dkirwan@tssg.org
    :group:         Telecommunications Software and Systems Group (TSSG)
    :address:       Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland 
    :revdate:       September 10, 2012
    :revnumber:     0.1
    :docdate:       September 10, 2012
    :description:   HOWTO configure the .netrc file to allow automatic user credentials authentication with https://bitbucket.org
    :legal:         (C) Waterford Institute of Technology
    :encoding:      iso-8859-1
    :toc:

The Asciidoc2html-pdf toolchain expects images which your Asciidoc source file links to are all contained within an images/
directory on the same directory level as the input asciidoc file ie:

    .
    |-- images
    `-- test.asciidoc

The following example should demonstrate how best to do this from within your Asciidoc source file:

    image::images/test.png["Title of the image", scaledwidth="60%", scaledheight="60%"]

You may need to build the pdf/html output several times while adjusting the _scaledwidth_ or _scaledheight_ values to suit
your document, or alternatively edit the size of the input image instead.

## doc-ant/
This directory contains the file structure and build system wrapper for the asciidoc2html-pdf toolchain. This system is an Ant
build file, and requires that Apache Ant be installed on the system PATH.

This ant build system is designed to make the Asciidoc2html-pdf toolchain easier to manage, it allows an easy to use
interface for building both html and pdf documents, and providesa relatively userfriendly structure for the documents
themselves.

### Instructions
First copy the sample doc-build.properties to your HOME directory, and edit the paths to the Asciidoc2html-pdf
tool for both the pdf.builder.path and html.builder.path sections.

it should look something similar to the following:

    pdf.builder.path=/home/david/Files/Git/asciidoc2html-pdf/asciidoc2html-pdf.sh
    pdf.builder.output=pdf
    html.builder.path=/home/david/Files/Git/asciidoc2html-pdf/asciidoc2html-pdf.sh
    html.builder.output=html
    html.builder.publish=
    perforce.workingdir=

_pdf.builder.output_ is the name of the folder created to hold to PDF output.

_html.builder.output_ is the name of the folder created to hold the HTML output.

_perforce.workingdir_ is the output folder in the perforce repository PDF output will be copied to, can be ignored for basic use.

_html.builder.publish_ is the output folder such as a web-dav mounted folder which the HTML output would be copied to, can be ignored for basic use

To setup a new document for use we need to carry out the following steps:

This currently this task needs to be carried out manually, future updates to the system will allow
a much more user friendly interface for the system.

We need to copy the following structure each time we wish to create a new document:

    .
    |-- build.properties
    |-- build.xml
    `-- src
        |-- images
        `-- test.asciidoc

The build.properties must be manually updated to match the name of the asciidoc main file in the src/ directory like the following:

    name.project=test

This case the document we wish to build is named test.asciidoc and is stored in the .src/ directory relative to the build.xml and
build.properties files, name this to what ever you wish, and be sure to rename the ./src/test.asciidoc to match.


### Building the Document
The document can be built with the following commands using the Ant buildfile system:

- _ant -P_ to list all tasks
- ant html
- ant pdf

or alternatively using the Rakefile system:

- _rake_ or _rake --tasks_ or _rake -T_ to list all tasks
- rake build:html
- rake build:pdf
