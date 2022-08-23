package tlvIsoField
abstract class TlvBaseIsoField(
    var defaultMaxLength: Int,
    protected val defaultValue: String = ""
) {

    var valueLength: Int = defaultMaxLength
        protected set

    open var value: TlvConfiguration? = null
        protected set

    open var fieldLength: Int = defaultMaxLength
        protected set

    open var hex: String? = null
        protected set

    abstract fun setFieldValue(value: TlvConfiguration)

    abstract fun setDefaultValue()

    abstract fun setHexValue(hexValue: String)

    fun checkLength(value: String, length: Int) {
        if (value.length > length) throw Exception("max length exceeded max length is: ${this.valueLength}")
    }

    override fun toString(): String {
        return "standerIsoFields.BaseIsoField(maxLength=$valueLength, defaultValue='$defaultValue', value='$value', length=$fieldLength)"
    }
}
