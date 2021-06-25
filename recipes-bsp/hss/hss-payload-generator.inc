SUMMARY = "Microchip Polarfire SoC Hart Software Services (HSS) - only Payload Generator"
DESCRIPTION = "(HSS) - only Payload Generator"

#@@@ todo: check license

LICENSE = "MIT & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=25fe68f52711758dbce41c3a07f578fb"

DEPENDS += "elfutils-native libyaml-native"

PV = "1.0+git${SRCPV}"
BRANCH = "master"
SRC_URI = "git://github.com/polarfire-soc/hart-software-services.git;branch=${BRANCH};subpath=tools/hss-payload-generator;name=hsspayloadgenerator"
SRC_URI += "git://github.com/polarfire-soc/hart-software-services.git;branch=${BRANCH};subpath=include;name=hssinclude"

SRCREV_FORMAT = "hsspayloadgenerator_hssinclude"

SRCREV_hsspayloadgenerator = "58b03943834fe34991dc5fa924436b3620e07aa5"
SRCREV_hssinclude = "58b03943834fe34991dc5fa924436b3620e07aa5"

S = "${WORKDIR}/hss-payload-generator"

EXTRA_OEMAKE = "CC='${BUILD_CC}' CFLAGS='-I ../include ${BUILD_CFLAGS}' LDFLAGS='${BUILD_LDFLAGS}'" 

do_install() {
        # create the dir if it does not already exists
        install -d ${D}${bindir}
        # install exe in there
        install -m 0755 hss-payload-generator ${D}${bindir}
}
