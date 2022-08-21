import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import java.lang.Exception
import kotlin.math.max

class FixedIsoField(
    maxLength: Int, conversion: BaseIsoFieldConverter = BcdFieldConverter(), defaultValue: String = ""
) : BaseIsoField(maxLength, conversion, defaultValue) {


    override var hex: String? = null
        get() {
            return if (value != null && field == null)
                conversion.toHex(value!!, defaultMaxLength)
            else field;
        }

    override var fieldLength: Int = conversion.inHexLength(defaultMaxLength).let {
        var newLength = it
        if (it % 2 != 0) newLength++
        newLength
    }


    override fun setFieldValue(value: String) {
        checkLength(value, defaultMaxLength)
        this.value = value
        hex = null
    }

    override fun setDefaultValue() {
        checkLength(defaultValue, defaultMaxLength)
        this.value = defaultValue
        hex = null
    }

    override fun setHexValue(hexValue: String) {
        hex = hexValue.substring(0, fieldLength)
        value = conversion.fromHex(hex!!)
    }


}
