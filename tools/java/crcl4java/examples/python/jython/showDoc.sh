#!/bin/sh

set -x;

if ! test -f crcl4java-utils-javadoc/index.html ; then
    jarfile="crcl4java-utils-1.4-javadoc.jar"
    if ! test -f "${jarfile}" ; then
        remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-javadoc.jar";
        echo "Downloading ${remotejarurl}";
        wget "${remotejarurl}"
    fi
    dir="crcl4java-utils-javadoc"
    mkdir -p "${dir}";
    ( set -x; cd "${dir}"; jar -xf "../${jarfile}" ; )
fi

if ! test -f crcl4java-base-javadoc/index.html ; then
    jarfile="crcl4java-base-1.4-javadoc.jar"
    if ! test -f "${jarfile}" ; then
        remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.4/crcl4java-base-1.4-javadoc.jar";
        echo "Downloading ${remotejarurl}";
        wget "${remotejarurl}"
    fi
    dir="crcl4java-base-javadoc"
    mkdir -p "${dir}";
    ( set -x; cd "${dir}"; jar -xf "../${jarfile}" ; )
fi

BASEURL="file://"`pwd`"/crcl4java-base-javadoc/index.html";
UTILSURL="file://"`pwd`"/crcl4java-utils-javadoc/index.html";

if test "x${BROWSER}" != "x"  && which "${BROWSER}" >/dev/null 2>/dev/null ; then
    echo "Nothing to do BROWSER already set.";
elif which xdg-open >/dev/null 2>/dev/null; then
    export BROWSER=xdg-open;
elif which gnome-open >/dev/null 2>/dev/null; then
    export BROWSER=gnome-open;
elif which sensible-browser >/dev/null 2>/dev/null; then
    export BROWSER=sensible-browser;
elif which firefox >/dev/null 2>/dev/null; then
    export BROWSER=firefox;
elif which google-chrome >/dev/null 2>/dev/null; then
    export BROWSER=google-chrome;
elif which chromium-browser >/dev/null 2>/dev/null; then
    export BROWSER=chromium-browser;
elif which open >/dev/null 2>/dev/null; then
    export BROWSER=open;
else
    echo "No Web-Browser found."
fi

echo "BROWSER=${BROWSER}"

"${BROWSER}" "${BASEURL}" &
"${BROWSER}" "${UTILSURL}" &
