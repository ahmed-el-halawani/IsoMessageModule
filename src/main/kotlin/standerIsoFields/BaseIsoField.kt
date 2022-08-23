package standerIsoFields

import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import standerIsoFields.FixedIsoField
import java.lang.Exception

abstract class BaseIsoField<T>(
    var defaultMaxLength: Int,
    protected val conversion: BaseIsoFieldConverter<T>,
    protected val defaultValue: T? = null
) {
    var valueLength: Int = defaultMaxLength
        protected set

    open var value: T? = null
        protected set

    open var fieldLength: Int = conversion.inHexLength(defaultMaxLength)
        protected set

    open var hex: String? = null
        protected set

    abstract fun setFieldValue(value: T)

    abstract fun setDefaultValue()

    abstract fun setHexValue(hexValue: String)

    fun checkLength(value: String, length: Int) {
        if (value.length > length) throw Exception("max length exceeded max length is: ${this.valueLength}")
    }

    override fun toString(): String {
        return "standerIsoFields.BaseIsoField(maxLength=$valueLength, conversion=$conversion,  defaultValue='$defaultValue', value='$value', length=$fieldLength)"
    }
}

