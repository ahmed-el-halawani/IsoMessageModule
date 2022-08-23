package tlvIsoField

import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import com.google.gson.JsonObject
import standerIsoFields.BaseIsoField

class TlvIsoField(
    maxLength: Int,
    conversion: BaseIsoFieldConverter<JsonObject>,
    defaultValue: JsonObject? = null,
    private val lengthOfLengthInHex: Int = 2,
    private val lengthConversion: BaseIsoFieldConverter<String> = BcdFieldConverter(),
) : BaseIsoField<JsonObject>(maxLength, conversion,defaultValue) {
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

    override fun setFieldValue(value: JsonObject) {
        checkLength(value.size().toString(), defaultMaxLength)
        this.value = value
        fieldLength = value.size()
        valueLength = fieldLength
        hex = null
    }

    override fun setDefaultValue() {
        checkLength(defaultValue.toString(), defaultMaxLength)
        this.value = defaultValue
        fieldLength = defaultValue.toString().length
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
