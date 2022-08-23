package tlvIsoField

import IsoFieldConverter.AsciiFieldConverter

class MyTlvConfiguration: TlvConfiguration() {
    override fun tagLength(): Int = 2

    override fun setConfigurationFieldsList(): List<TlvConfigurationField> = listOf(
        TlvValueField("0012",AsciiFieldConverter()),
    )
}