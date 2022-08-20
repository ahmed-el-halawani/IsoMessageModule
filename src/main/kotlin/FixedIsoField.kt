import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import java.lang.Exception
import kotlin.math.max

class FixedIsoField(
    maxLength: Int, conversion: BaseIsoFieldConverter = BcdFieldConverter(), defaultValue: String? = null
) : BaseIsoField(maxLength, conversion, defaultValue) {


    override var hex: String? = null
        get() {
            return if (value != null && field == null)
                conversion.toHex(value!!, maxLength)
            else field;
        }

    override fun setFieldValue(value: String) {
        this.value = value
        checkLength(value, maxLength)
        hex = null
    }


    override fun setHexValue(hexValue: String) {
        hex = hexValue.substring(0, fieldLength)
        value = conversion.fromHex(hex!!)
    }


}
