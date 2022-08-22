import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import standerIsoFields.FixedIsoField
import java.lang.Exception

abstract class BaseIsoField(
    var defaultMaxLength: Int,
    protected val conversion: BaseIsoFieldConverter = BcdFieldConverter(),
    protected val defaultValue: String = ""
) {

    var valueLength: Int = defaultMaxLength
        protected set

    open var value: String? = null
        protected set

    open var fieldLength: Int = conversion.inHexLength(defaultMaxLength)
        protected set

    open var hex: String? = null
        protected set

    abstract fun setFieldValue(value: String)

    abstract fun setDefaultValue()

    abstract fun setHexValue(hexValue: String)

    fun checkLength(value: String, length: Int) {
        if (value.length > length) throw Exception("max length exceeded max length is: ${this.valueLength}")
    }

    override fun toString(): String {
        return "BaseIsoField(maxLength=$valueLength, conversion=$conversion, isFixed=${this is FixedIsoField}, defaultValue='$defaultValue', value='$value', length=$fieldLength)"
    }
}

