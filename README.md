# TSSG 3MT Documentation System
This repository contains both the Asciidoc2html-pdf toolchain, and also the build wrapper system for the toolchain.


## asciidoc2html-pdf 
Contains the codebase for the asciidoc2html-pdf toolchain wrapper for the Asciidoc tool, while it is possible to run
the script directly, it is recommended you interact with the system through the doc-ant system instead, as it adds
a more user friendly interface for the system.


## doc-ant
Contains the file structure and build system wrapper for the asciidoc2html-pdf toolchain. This system is an Ant
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
The document can be built with the following commands:

- ant html
- ant pdf

