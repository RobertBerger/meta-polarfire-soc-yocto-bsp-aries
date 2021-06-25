# Note:
# This seems to be included by the machine config multiple times?
# Hence we can not build an extensible SDK?
#
# Even if I exclude it from the machine config and add it with IMAGE_INSTALL_append
# there is the same problem
#
# Something like:
#
# ERROR: Task hss.do_fetch attempted to execute unexpectedly
# Task /workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/poky/meta/recipes-core/images/core-image-minimal.bb:do_image_qa, unihash 54353e4bb02af2cb3996f538748bacca0f23513e82257e23a53549d415b62b3a, taskhash 54353e4bb02af2cb3996f538748bacca0f23513e82257e23a53549d415b62b3a
# Task /workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/poky/meta/recipes-core/images/core-image-minimal.bb:do_image_complete, unihash a2f366b5b599652db6a807d5bddf16004bfcb83c881e97a945a7adcd22772686, taskhash a2f366b5b599652db6a807d5bddf16004bfcb83c881e97a945a7adcd22772686
# This is usually due to missing setscene tasks. Those missing in this build were: {'/workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/poky/meta/recipes-core/images/core-image-minimal.bb:do_image_complete',
#  '/workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/poky/meta/recipes-core/images/core-image-minimal.bb:do_image_qa'}
# ERROR: Task (/workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/meta-polarfire-soc-yocto-bsp/recipes-bsp/hss/hss.bb:do_fetch) failed with exit code 'setscene whitelist'
# NOTE: Tasks Summary: Attempted 310 tasks of which 309 didn't need to be rerun and 1 failed.
# 
# Summary: 1 task failed:
#  /workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/sdk-ext/image/tmp-renamed-sdk/layers/meta-polarfire-soc-yocto-bsp/recipes-bsp/hss/hss.bb:do_fetch
# Summary: There was 1 ERROR message shown, returning a non-zero exit code.
# ERROR: Logfile of failure stored in: /workdir/3rd-party/aries/build/m100pfsevp/tmp/work/m100pfsevp-poky-linux/core-image-minimal/1.0-r0/temp/log.do_populate_sdk_ext.32278
# ERROR: Task (/workdir/3rd-party/aries/sources/poky/meta/recipes-core/images/core-image-minimal.bb:do_populate_sdk_ext) failed with exit code '1'
#
# what allows me to build an ext-SDK?
# removing this recipe from the machine config ;)
# 
# At the moment it looks like it boils down to the do_deploy task
# Whenever this is in, the ext-SDK can not build
# 
# If I rename do_deploy to some other name it seems its fine
#
# At least for the experiments where it's a "normal" recipe 
# which also contains a do_install so we a proper package
#
# ... this all boils down to do_deploy being called multiple times
# when we try to build the eSDK
#
# right ;)
#
# on the other hand I need this thing to build the SD card image with wic
#
# let's see what happens if we copy payload.bin into DEPLOY_DIR_IMAGE from do_compile
#
# 1) bitbake core-image-minimal - OK
#
# 2) bitbake core-image-minimal -c populate_sdk_ext - OK
#
# ... but still issues after removed all build artifacts

# @todo: check description
SUMMARY = "Microchip Polarfire SoC Hart Software Services (HSS) Payload Generator"
DESCRIPTION = "HSS requires U-Boot to be packaged with header details applied with hss payload generator"

# @todo: check the license

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#LIC_FILES_CHKSUM = "file://LICENSE.md;md5=2dc9e752dd76827e3a4eebfd5b3c6226"

#LICENSE = "MIT"

# we split the payload generator out into it's own recipe:
# append with space (it is independent of u-boot)
do_compile[depends] += "hss-payload-generator-native:do_populate_sysroot"

# Strict dependency (append with space)
do_compile[depends] += "u-boot:do_deploy"
#do_configure[depends] += "u-boot:do_deploy"

## we split the payload generator out into it's own recipe:
#do_compile[depends] += "hss-payload-generator-native:do_populate_sysroot"

#DEPENDS += "elfutils-native libyaml-native"

#PV = "1.0+git${SRCPV}"
#BRANCH = "master"
#SRCREV="2eba46d5e3ddf1a90b3066423f5c1b7acd6a89c5"
#SRC_URI = "git:///workdir/yocto-repos/payload.git;protocol=file;branch=master"
#PV = "1.0+git${SRCPV}"
#SRC_URI = "git://github.com/polarfire-soc/hart-software-services.git;branch=${BRANCH} \
#           file://uboot.yaml \
#          "
#SRC_URI = "file://uboot.yaml \
#file://LICENSE.md"
SRC_URI = "file://uboot.yaml"
#S = "${WORKDIR}/git"

# NOTE: Only using the Payload generator from the HSS
#do_configure () {
#	## taking U-Boot binary and package for HSS
#	cp -f ${DEPLOY_DIR_IMAGE}/u-boot.bin ${S}
#	#cp -f ${WORKDIR}/uboot.yaml ${WORKDIR}/git/tools/hss-payload-generator/
#}

#do_configure () {
#   :
#}

#EXTRA_OEMAKE = "CC='${BUILD_CC}' CFLAGS='${BUILD_CFLAGS}' LDFLAGS='${BUILD_LDFLAGS}'"
#do_compile[nostamp] = "1"
do_compile () {
    ## Adding u-boot as a payload
    ## Using hss-payload-generator application
#    oe_runmake -C ${S}/tools/hss-payload-generator
#    ${S}/tools/hss-payload-generator/hss-payload-generator -c ${S}/tools/hss-payload-generator/uboot.yaml -v payload.bin
     cp -f ${DEPLOY_DIR_IMAGE}/u-boot.bin ${S}
     rm -f ${DEPLOY_DIR_IMAGE}/payload.bin
     hss-payload-generator -c ${WORKDIR}/uboot.yaml -v payload.bin
     #cp payload.bin ${WORKDIR}/payload.bin
   if [ ! -f ${DEPLOY_DIR_IMAGE}/payload.bin ]; then
      install -d ${DEPLOY_DIR_IMAGE}
      install -m 755 ${S}/payload.bin ${DEPLOY_DIR_IMAGE}/payload.bin
   fi
}

# for the time being just for fun something in a package
#do_install() {
#        # create the dir if it does not already exists
#        install -d ${D}${bindir}
#        # install exe in there
#        install -m 755 ${S}/payload.bin ${D}${bindir}
#}


#do_install() {
#   :
#}

#do_deploy() {
#   install -d ${DEPLOY_DIR_IMAGE}
#   install -m 755 ${S}/payload.bin ${DEPLOY_DIR_IMAGE}/
#}
#
#addtask deploy after do_install

## Copy script to the deploy area with u-boot, uImage, and rootfs
## If we call is do_install the extensible SDK build is unhappy
#do_dont_call_me_deploy () {
#   #bbwarn ${DEPLOY_DIR_IMAGE}
#   # shield from multiple calls to it
#   if [ ! -f ${DEPLOY_DIR_IMAGE}/payload.bin ]; then
#      install -d ${DEPLOY_DIR_IMAGE}
#      install -m 755 ${S}/payload.bin ${DEPLOY_DIR_IMAGE}/payload.bin
#   fi
#}
#addtask do_dont_call_me_deploy after do_compile
