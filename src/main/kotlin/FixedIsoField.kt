import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

class FixedIsoField(
    maxLength: Int,
    conversion: BaseIsoFieldConverter = BcdFieldConverter(),
    defaultValue: String? = null
) : BaseIsoField(maxLength, conversion, true, defaultValue) {

    override var hex: String? = null
        get() {
            return if (value != null && field == null)
                conversion.toHex(value!!, length)
            else
                field;
        }

    override fun setFieldValue(value: String) {
        this.value = value
        hex = null
        length = maxLength
    }

    public override fun getLengthFromHex(hexValue: String): Int {
        return length
    }

    public override fun setHexValue(hexValue: String) {
        hex = hexValue.substring(0, conversion.inHexLength(length))
        value = conversion.fromHex(hex!!)
    }


}
