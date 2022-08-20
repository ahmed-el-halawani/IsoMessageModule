import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import java.lang.Exception

abstract class BaseIsoField(
    maxLength: Int,
    protected val conversion: BaseIsoFieldConverter = BcdFieldConverter(),
    private val defaultValue: String? = null
) {

    var maxLength: Int = maxLength
        protected set

    open var value: String? = defaultValue
        protected set

    var fieldLength: Int = conversion.inHexLength(maxLength)
        protected set


    open var hex: String? = null
        protected set

    abstract fun setFieldValue(value: String)

    abstract fun setHexValue(hexValue: String)

    fun checkLength(value: String, length: Int) {
        if (value.length > length) throw Exception("max length exceeded max length is: $maxLength")
    }

    override fun toString(): String {
        return "BaseIsoField(maxLength=$maxLength, conversion=$conversion, isFixed=${this is FixedIsoField}, defaultValue='$defaultValue', value='$value', length=$fieldLength)"
    }
}

