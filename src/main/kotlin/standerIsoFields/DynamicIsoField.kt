package standerIsoFields

import BaseIsoField
import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

class DynamicIsoField(
    maxLength: Int,
    conversion: BaseIsoFieldConverter = AsciiFieldConverter(),
    defaultValue: String = "",
    private val lengthOfLengthInHex: Int = 2,
    private val lengthConversion: BaseIsoFieldConverter = BcdFieldConverter(),
) : BaseIsoField(maxLength, conversion, defaultValue) {

    override var hex: String? = null
        get() {
            if (value != null && field == null) {
                val lengthInHex =
                    lengthConversion.toHex(fieldLength.toString(), lengthConversion.getLength(lengthOfLengthInHex))
                val valueInHex = conversion.toHex(value!!, fieldLength)

                field = lengthInHex + valueInHex
            }

            return field
        }

    override fun setFieldValue(value: String) {
        checkLength(value, defaultMaxLength)
        this.value = value
        fieldLength = value.length
        valueLength = fieldLength
        hex = null
    }

    override fun setDefaultValue() {
        checkLength(defaultValue, defaultMaxLength)
        this.value = defaultValue
        fieldLength = defaultValue.length
        valueLength = fieldLength
        hex = null
    }


    override fun setHexValue(hexValue: String) {
        valueLength = getLengthFromHex(hexValue)
        fieldLength = lengthConversion.getLength(lengthOfLengthInHex) + valueLength

        hex = hexValue.substring(0, fieldLength)

        val hexValueWithoutLength =
            hex!!.substring(lengthConversion.getLength(lengthOfLengthInHex))

        value = conversion.fromHex(hexValueWithoutLength)
    }

    private fun getLengthFromHex(hexValue: String): Int {
        return conversion.inHexLength(
            lengthConversion.fromHex(
                hexValue.substring(0, lengthConversion.getLength(lengthOfLengthInHex))
            ).toInt()
        )
    }

}
