import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

class DynamicIsoField(
    maxLength: Int,
    conversion: BaseIsoFieldConverter = AsciiFieldConverter(),
    defaultValue: String? = null,
    private val lengthOfLengthInHex: Int = 2,
    private val lengthConversion: BaseIsoFieldConverter = BcdFieldConverter(),
) : BaseIsoField(maxLength, conversion, defaultValue) {

    override var hex: String? = null
        get() {
            return if (value != null && field == null)
                BcdFieldConverter().toHex(
                    fieldLength.toString(), BcdFieldConverter().getLength(lengthOfLengthInHex)
                ) + conversion.toHex(value!!, fieldLength)
            else field;
        }

    override fun setFieldValue(value: String) {
        this.value = value
        checkLength(value, maxLength)
        fieldLength = value.length
        maxLength = fieldLength
        hex = null
    }


    override fun setHexValue(hexValue: String) {
        maxLength = getLengthFromHex(hexValue)
        fieldLength = lengthConversion.getLength(lengthOfLengthInHex) + maxLength

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
