package tlvIsoField

import IsoFieldConverter.BaseIsoFieldConverter
import IsoFieldConverter.BcdFieldConverter

abstract class TlvConfiguration {
    abstract fun tagLength():Int

    open val tagLengthConverter :BaseIsoFieldConverter<String> = BcdFieldConverter()
    open val initFieldType:TlvConfigurationField.ValueTypes = TlvConfigurationField.ValueTypes.Map

    protected abstract  fun setConfigurationFieldsList():List<TlvConfigurationField>

    fun getConfigurationFields():List<TlvConfigurationField> = setConfigurationFieldsList()

    fun getConfigurationFieldWithTag(tag:String):TlvConfigurationField?{
        return setConfigurationFieldsList().firstOrNull { it.tag == tag }
    }
}

class TlvListField(
    tag: String,
    lengthOfLengthInHex: Int = 2,
    lengthConversion: BaseIsoFieldConverter<String> = BcdFieldConverter()
) : TlvConfigurationField(tag,ValueTypes.List, null, lengthOfLengthInHex, lengthConversion)

class TlvObjectField(
    tag: String,
    lengthOfLengthInHex: Int = 2,
    lengthConversion: BaseIsoFieldConverter<String> = BcdFieldConverter()
) : TlvConfigurationField(tag,ValueTypes.Map, null, lengthOfLengthInHex, lengthConversion)

class TlvValueField(
     tag: String,
    valueConversion: BaseIsoFieldConverter<String>? = null,
    lengthOfLengthInHex: Int = 2,
    lengthConversion: BaseIsoFieldConverter<String> = BcdFieldConverter()
) : TlvConfigurationField(tag,ValueTypes.List, valueConversion, lengthOfLengthInHex, lengthConversion)

abstract class TlvConfigurationField(
    val tag: String,
    val valueType: ValueTypes = ValueTypes.Value,
    val valueConversion: BaseIsoFieldConverter<String>? = null,
    val lengthOfLengthInHex: Int = 2,
    val lengthConversion: BaseIsoFieldConverter<String> = BcdFieldConverter()
) {


    enum class ValueTypes {
        Value, List, Map
    }
}

