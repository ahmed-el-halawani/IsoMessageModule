import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

class DynamicIsoField(
    maxLength: Int,
    conversion: BaseIsoFieldConverter = AsciiFieldConverter(),
    defaultValue: String? = null,
    private val lengthOfLengthInHex: Int = 2
) : BaseIsoField(maxLength, conversion, false, defaultValue) {

    override fun setFieldValue(value: String) {
        this.value = value
        hex = null
        length = lengthOfLengthInHex + value.length
    }

    override fun getLengthFromHex(hexValue: String): Int {
        return BcdFieldConverter().fromHex(
            hexValue.substring(0, BcdFieldConverter().getLength(lengthOfLengthInHex))
        ).toInt()
    }

    override fun setHexValue(hexValue: String) {
        length = BcdFieldConverter().fromHex(
            hexValue.substring(0, BcdFieldConverter().getLength(lengthOfLengthInHex))
        ).toInt()
        hex = hexValue.substring(BcdFieldConverter().getLength(lengthOfLengthInHex), length)
        value = conversion.fromHex(hex!!)
    }

    override var hex: String? = null
        get() {
            return if (value != null && field == null)
                BcdFieldConverter().toHex(
                    length.toString(), BcdFieldConverter().getLength(lengthOfLengthInHex)
                ) + conversion.toHex(value!!, length)
            else field;
        }
}
