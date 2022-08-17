data class BaseIsoField(
    val maxLength: Int,
    val conversion: IsoFieldConversionType = IsoFieldConversionType.Bcd,
    val minLength: Int = 0,
) {
    val hexData: MutableList<Byte> = mutableListOf()
}

enum class IsoFieldConversionType {
    Bcd, Hex, Ascii, Binary
}