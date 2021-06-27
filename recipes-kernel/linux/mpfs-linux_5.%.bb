require recipes-kernel/linux/mpfs-linux-common.inc

#pokyuser@e450-10:/workdir/build/m100pfsevp-polarfire-resy-master/tmp/deploy/ipk$ bitbake virtual/kernel -e | grep ^OVERRIDES=
#OVERRIDES="linux:riscv64:pn-mpfs-linux:m100pfsevp:resy-systemd:class-target:libc-glibc:forcevariable"

LINUX_VERSION ?= "5.6.x"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "linux-5.6.y"
SRCREV = "v5.6.16"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"
#SRC_URI_append_icicle-kit-es = " \
#    file://icicle-kit-es-microchip.dts \
#    file://0001-PFSoC-Icicle-kit-Adding-DTS-makefile.patch \
#    file://0002-PFSoC-MAC-Interface-auto-negotiation.patch \
#    file://0001-V2-GPIO-Driver-updates.patch \
#    file://0003-Microchip-Adding-I2C-Support-for-the-Polarfire-SoC.patch \
#    file://0008-pac139x.patch \
#    file://v11-0004-PCI-microchip-Add-host-driver-for-Microchip-PCIe.patch \
#    file://v1-0002-Add-definition-for-Microchip-PolarFire-SoC.patch \
#    file://v3-0001-dt-bindings-clk-microchip-Add-Microchip-PolarFire.patch \
#    file://v3-0002-clk-microchip-Add-driver-for-Microchip-PolarFire-.patch \
#    file://0001-V5-Adding-Microchip-MUSB-Driver.patch \
#    file://0002-uio-can-Microchip-PolarFire-add-CAN-support-via-uio.patch \
#    file://0001-Microsemi-UIO-Fabric-DMA-support.patch \
#    file://0003-mbox-add-polarfire-soc-system-controller-mailbox.patch \
#    file://0002-atish-soc-support.patch \
#    file://0001-rng-add-support-for-rng-on-the-polarfire-soc.patch \
#    file://0005-soc-add-polarfire-soc-system-controller.patch \
#    file://0007-sys-serv-fpga-digest-and-serial-num.patch \
#    file://v1-0001-dt-bindings-rtc-microchip-Add-Microchip-PolarFire.patch \
#    file://v1-0002-rtc-microchip-Add-driver-for-Microchip-PolarFire-.patch \
#    file://v1-0001-dt-bindings-dma-microchip-Add-Microchip-PolarFire.patch \
#    file://v1-0002-uio-pdma-microchip-Add-uio-driver-for-Microchip-P.patch \
#    file://v1-0001-riscv-add-cpu-frequency-reporting-to-cpuinfo.patch \
#    file://v1-0001-dmabuf-u-dma-buf-add-u-dma-buf-to-v5.6.x.patch \
# "

#SRC_URI_append_mpfs = " \
#    file://mpfs.dts \
#    file://0004-SiFive-Unleashed-CPUFreq.patch \
#    file://0007-Add-PWM-LEDs-D1-D2-D3-D4.patch \
#    file://riscv-add-support-to-determine-no-of-L2-cache-way-enabled.patch \
#    file://0001-Polarfire-SoC-DTS-support.patch \
#    file://v11-0003-PCI-microchip-Add-host-driver-for-Microchip-PCIe.patch \
#    file://v11-0004-PCI-microchip-Add-host-driver-for-Microchip-PCIe.patch \
#    file://0001-Microchip-GPIO-Support-for-the-Polarfire-SoC.patch \
#    file://0002-Microchip-SPI-Support-for-the-Polarfire-SoC.patch \
#    file://0003-Microchip-Adding-I2C-Support-for-the-Polarfire-SoC.patch \
#    file://0004-Microchip-Adding-QSPI-driver-for-Polarfire-SoC.patch \
#    file://0002-Microsemi-UIO-CAN-support.patch \
#"

#SRC_URI_append_lc-mpfs = " \
#    file://mpfs.dts \
#    file://0004-SiFive-Unleashed-CPUFreq.patch \
#    file://0007-Add-PWM-LEDs-D1-D2-D3-D4.patch \
#    file://riscv-add-support-to-determine-no-of-L2-cache-way-enabled.patch \
#    file://0001-Polarfire-SoC-DTS-support.patch \
#    file://0001-Microchip-GPIO-Support-for-the-Polarfire-SoC.patch \
#    file://0002-Microchip-SPI-Support-for-the-Polarfire-SoC.patch \
#    file://0003-Microchip-Adding-I2C-Support-for-the-Polarfire-SoC.patch \
#    file://0004-Microchip-Adding-QSPI-driver-for-Polarfire-SoC.patch \
#    file://0002-Microsemi-UIO-CAN-support.patch \
#"

#SRC_URI_append_m100pfsevp
SRC_URI_append= " \
    file://m100pfsevp.dtsi \
    file://m100pfsevp-emmc.dts \
    file://m100pfsevp-sdcard.dts \
    file://0001-PFSoC-Icicle-kit-Adding-DTS-makefile.patch \
    file://0002-PFSoC-MAC-Interface-auto-negotiation.patch \
    file://0001-V2-GPIO-Driver-updates.patch \
    file://0003-Microchip-Adding-I2C-Support-for-the-Polarfire-SoC.patch \
    file://0008-pac139x.patch \
    file://v11-0004-PCI-microchip-Add-host-driver-for-Microchip-PCIe.patch \
    file://v1-0002-Add-definition-for-Microchip-PolarFire-SoC.patch \
    file://v3-0001-dt-bindings-clk-microchip-Add-Microchip-PolarFire.patch \
    file://v3-0002-clk-microchip-Add-driver-for-Microchip-PolarFire-.patch \
    file://0001-V5-Adding-Microchip-MUSB-Driver.patch \
    file://0002-uio-can-Microchip-PolarFire-add-CAN-support-via-uio.patch \
    file://0001-Microsemi-UIO-Fabric-DMA-support.patch \
    file://0003-mbox-add-polarfire-soc-system-controller-mailbox.patch \
    file://0002-atish-soc-support.patch \
    file://0001-rng-add-support-for-rng-on-the-polarfire-soc.patch \
    file://0005-soc-add-polarfire-soc-system-controller.patch \
    file://0007-sys-serv-fpga-digest-and-serial-num.patch \
    file://v1-0001-dt-bindings-rtc-microchip-Add-Microchip-PolarFire.patch \
    file://v1-0002-rtc-microchip-Add-driver-for-Microchip-PolarFire-.patch \
    file://v1-0001-dt-bindings-dma-microchip-Add-Microchip-PolarFire.patch \
    file://v1-0002-uio-pdma-microchip-Add-uio-driver-for-Microchip-P.patch \
    file://v1-0001-riscv-add-cpu-frequency-reporting-to-cpuinfo.patch \
    file://v1-0001-dmabuf-u-dma-buf-add-u-dma-buf-to-v5.6.x.patch \
    file://0001-riscv-Add-Aries-M100PFSEVP-PolarFire-SoC-FPGA-Platfo.patch \
 "

#do_configure_prepend_icicle-kit-es() {
#    cp -f ${WORKDIR}/icicle-kit-es-microchip.dts ${S}/arch/riscv/boot/dts/microchip
#}
#do_configure_prepend_mpfs() {
#    cp -f ${WORKDIR}/mpfs.dts ${S}/arch/riscv/boot/dts/sifive
#}
#do_configure_prepend_lc-mpfs() {
#    cp -f ${WORKDIR}/mpfs.dts ${S}/arch/riscv/boot/dts/sifive
#}
#do_configure_prepend_m100pfsevp() {
#do_configure_prepend() {
#   cp -f ${WORKDIR}/m100pfsevp.dtsi ${S}/arch/riscv/boot/dts/aries
#    cp -f ${WORKDIR}/m100pfsevp-emmc.dts ${S}/arch/riscv/boot/dts/aries
#   cp -f ${WORKDIR}/m100pfsevp-sdcard.dts ${S}/arch/riscv/boot/dts/aries
#

#SRC_URI_append_icicle-kit-es = " file://defconfig"
#SRC_URI_append_mpfs = " file://defconfig"
#SRC_URI_append_lc-mpfs = " file://defconfig"
#SRC_URI_append_m100pfsevp = " file://defconfig"
SRC_URI_append = " file://defconfig"
