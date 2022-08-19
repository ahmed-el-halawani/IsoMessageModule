import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

abstract class BaseIsoField(
    protected var maxLength: Int,
    val conversion: BaseIsoFieldConverter = BcdFieldConverter(),
    protected val isFixed: Boolean = true,
    protected val defaultValue: String? = null
) {

    var value: String? = defaultValue
        protected set

    var length: Int = maxLength
        protected set

    open var hex: String? = null
        protected set

    abstract fun setFieldValue(value: String)

    abstract fun setHexValue(hexValue: String)

    abstract fun getLengthFromHex(hexValue: String): Int

    override fun toString(): String {
        return "BaseIsoField(maxLength=$maxLength, conversion=$conversion, isFixed=$isFixed, defaultValue='$defaultValue', value='$value', length=$length)"
    }
}

