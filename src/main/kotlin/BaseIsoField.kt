import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter
import kotlin.math.max

data class BaseIsoField(
    private var maxLength: Int,
    val conversion: BaseIsoFieldConverter = BcdFieldConverter(),
    val paddingType: IsoFieldPaddingType = IsoFieldPaddingType.Default,
    private val isFixed: Boolean = true,
    private val defaultValue: String = ""
) {

    private var value = defaultValue

    var length: Int = conversion.getLength(maxLength)
        private set

    public fun setValue(value: String) {
        this.value = value

        if (!isFixed) setLength(this.value.length)
    }

    public fun setValue(value: Int) {
        this.value = value.toString()

        if (!isFixed) setLength(this.value.length)
    }

    public fun getValue(): String {
        return value;
    }

    public fun setHexValue(hexValue: String) {
        value = conversion.fromHex(hexValue)
    }

    public fun getHexValue(): String {
        return conversion.toHex(value, paddingType)
    }

    public fun setLength(length: Int) {
        if (isFixed) {
            this.length = conversion.getLength(length)
            this.maxLength = conversion.getLength(length)
        } else {
            this.length = conversion.getLength(length)
            this.length += getLengthLength()
        }
    }

    public fun getLengthLength(): Int {
        return if (maxLength <= 100) 2
        else 3
    }

    override fun toString(): String {
        return "BaseIsoField(maxLength=$maxLength, conversion=$conversion, paddingType=$paddingType, isFixed=$isFixed, defaultValue='$defaultValue', value='$value', length=$length)"
    }


}

enum class IsoFieldConversionType {
    Bcd, Hex, Ascii, Binary, Base64
}

enum class IsoFieldPaddingType {
    Default, Zeros, Nulls, Spaces;

    companion object {
        fun getDefaultType(conversionType: IsoFieldConversionType): IsoFieldPaddingType = when (conversionType) {
            IsoFieldConversionType.Bcd -> Zeros
            IsoFieldConversionType.Hex -> Zeros
            IsoFieldConversionType.Ascii -> Spaces
            IsoFieldConversionType.Binary -> Zeros
            IsoFieldConversionType.Base64 -> Spaces
        }
    }
}