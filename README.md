# TSSG 3MT Documentation System
This repository contains both the Asciidoc2html-pdf toolchain, and also the build wrapper system for the toolchain.


## asciidoc2html-pdf 
Contains the codebase for the asciidoc2html-pdf toolchain wrapper for the Asciidoc tool, while it is possible to run
the script directly, it is recommended you interact with the system through the doc-ant system instead, as it adds
a more user friendly interface for the system.


## doc-ant
Contains the file structure and build system wrapper for the asciidoc2html-pdf toolchain.

This ant build system is designed to make the Asciidoc2html-pdf toolchain easier to manage, 
it allows an easy to use interface for building both html and pdf documents, and provides
a relatively userfriendly structure for the documents themselves.

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

pdf.builder.output is the name of the folder created to hold to PDF output.

html.builder.output is the name of the folder created to hold the HTML output.

perforce.workingdir is the output folder in the perforce repository PDF output will be copied to, can be ignored for basic use.

html.builder.publish is the output folder such as a web-dav mounted folder which the HTML output would be copied to, can be ignored for basic use

To setup a new document for use, currently this task needs to be carried out manually, future updates to the system will allow
a much more user friendly interface for the system.

We need the following to create a document:

    .
    |-- build.properties
    |-- build.xml
    `-- src
        |-- images
        `-- test.asciidoc

The build.properties must be manually updated to match the name of the asciidoc main file in the src/ directory, and once done 
the document can be built with the following commands:

- ant html
- ant pdf

