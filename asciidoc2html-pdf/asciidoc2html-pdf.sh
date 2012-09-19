#!/bin/bash
####################################################
# @author 	David Kirwan <dkirwan@tssg.org>
# @description 	Script for building the  AsciiDoc
#  		document in HTML and PDF format.
#
# @date 	16-01-2012
####################################################

if [ "$1" = "" -o "$2" = "" -o "$3" = "" ]; then
echo ""
echo "To output the AsciiDoc index.txt as HTML use the following command:"
echo "    Useage: ./asciidoc2html-pdf.sh html /path/to/report/asciidoc.txt /path/to/output/folder"
echo ""
echo "To output the index.txt as a PDF use the following command:"
echo "    Useage: ./asciidoc2html-pdf.sh pdf /path/to/report/asciidoc.txt /path/to/output/folder"
echo ""
echo ""
exit 0
fi

DIR="$( cd "$( dirname "$0" )" && pwd )"
INPATH="$( cd "$( dirname "$2" )" && pwd )"

input=$1
infile=$(basename $2)
output=$3

echo "This is the name of the file: " $infile
echo "This is the path to the file: " $INPATH/$infile
extension=${infile##*.}
echo "This is the file extension: "$extension
outputfilename=${infile%.*}
echo "This is the file name itself: " $outputfilename


# Check to see if the final character in the output paramter is a / and if so delete it.
if [ "${output#${output%?}}" = "/" ]; then
output=${output%?}
fi


########################################################## HTML Generation Script #################################################

if [ "$input" = "html" ]; then
echo ""
echo "Running the toolchain to produce the output as HTML"
echo ""
#####################################################
# Copies the contents of coverpage_xml.xml into a maincontent.txt, then appends the contents of the input report onto that
# and then passes this generated maincontent.txt into the asciidoc tool.
outputfilename=$infile
outputfilename=$(basename $outputfilename)
outputfilename=${outputfilename%.*}

# Copies the contents of the images sub-directory and the resources sub-directory in which the input asciidoc resides to the output directory. 
# This allows html links and images etc to work for the generated HTML page.

if [ -d "images" ]; then
    cp -R $INPATH/images $output/
fi

if [ -d "resources" ]; then
    cp -R $INPATH/resources $output/
fi

#mkdir $output/inputReport
cp -R $INPATH/* $output/

java -jar $DIR/bin/coverpagehelper.jar -c $INPATH/$infile $output/$outputfilename.output.xml

touch $output/$outputfilename.txt

java -jar $DIR/bin/coverpagehelper.jar -h $output/$outputfilename.output.xml $output/$outputfilename.txt

cat $INPATH/$infile >> $output/$outputfilename.txt

cp $DIR/conf/template_stylesheet.css $output/

#Calls the asciidoc tool through the a2x toolchain to generate an output document in XML format
$DIR/bin/asciidoc-8.6.6/a2x.py -v --stylesheet=template_stylesheet.css -f xhtml -d article -L -D $output $output/$outputfilename.txt
echo "Report successfully generated and stored as $output/inputReport/$outputfilename.html"

echo "Copying coverpage/stylesheet resources into the output directory."
cp $DIR/conf/whitepaper/template/tssglogo.png $output/
cp $DIR/conf/whitepaper/template/tssg_bar_full.png $output/
cp $DIR/conf/whitepaper/template/tssg_logo.png $output/
cp $DIR/conf/template_stylesheet.css $output/

echo "Deleting unneeded files"
#rm -rf $output/inputReport
rm $output/$outputfilename.xml
rm $output/$outputfilename.output.xml

#####################################################
exit 0
fi

####################################################### PDF Generation Script ##########################################################

if [ "$input" = "pdf" ]; then
echo ""
echo "Running the toolchain to produce the output as PDF"
echo ""
#####################################################


mkdir $output/inputReport
cp -R $INPATH/* $output/inputReport

java -jar $DIR/bin/coverpagehelper.jar -c $INPATH/$infile $output/inputReport/$outputfilename.xml

#Calls the asciidoc tool to generate an output document in XML format
$DIR/bin/asciidoc-8.6.6/asciidoc.py --verbose --backend docbook  --doctype article  --out-file $output/inputReport/$outputfilename.output.xml $INPATH/$infile

# Calls the coverpagehelper.jar to copy the coverpage.xml contents into the index.xml output from asciidoc.
java -jar $DIR/bin/coverpagehelper.jar -p $output/inputReport/$outputfilename.xml $output/inputReport/$outputfilename.output.xml

# Converts the XML using XSL stylesheets to a .fo file suitible for passing to tools such as Apache-FOP
xsltproc -nonet \
-param header.image.filename "'$DIR/conf/whitepaper/template/tssglogo.png'" \
-param footer.image.filename "'$DIR/conf/whitepaper/template/tssg_bar_full.png'" \
-param cover.image.filename "'$DIR/conf/whitepaper/template/tssg_logo.png'" \
-param navig.graphics.path "'$DIR/bin/asciidoc-8.6.6/images/icons/'" \
-param admon.graphics.path "'$DIR/bin/asciidoc-8.6.6/images/icons/'" \
-param callout.graphics.path "'$DIR/bin/asciidoc-8.6.6/images/icons/callouts/'" \
-o $output/inputReport/index.fo $DIR/conf/template_stylesheet.xsl $output/inputReport/$outputfilename.output.xml

# Converts the .fo file to a PDF using Apache-FOP
$DIR/bin/fop-1.0/fop -fo $output/inputReport/index.fo -pdf $output/$outputfilename.pdf

echo "Deleting unneeded files index.fo, $outputfilename.xml and $outputfilename.output.xml"
rm -rf $output/inputReport

echo "Document successfully generated and stored as $output/inputReport/$outputfilename.pdf."
# Launch the index.pdf file using the xpdf tool.
#xpdf $output/inputReport/$outputfilename.pdf &
#####################################################
exit 0
fi
