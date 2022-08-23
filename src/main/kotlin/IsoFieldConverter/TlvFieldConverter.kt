package IsoFieldConverter

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.sun.jdi.event.ExceptionEvent
import standerIsoFields.DynamicIsoField
import tlvIsoField.Tlv
import tlvIsoField.TlvConfiguration
import tlvIsoField.TlvConfigurationField
import java.text.FieldPosition


class TlvFieldConverter(
    private val configuration: TlvConfiguration,
    paddingWith: Char? = ' '
) : BaseIsoFieldConverter<JsonObject>(paddingWith) {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun fromHex(hexValue: String): JsonObject {
        val jsonObject = JsonObject()

        jsonObject.add("tlv", when(configuration.initFieldType){
            TlvConfigurationField.ValueTypes.List -> getJsonArray(hexValue,hexValue.length)
            TlvConfigurationField.ValueTypes.Map -> getJsonObject(hexValue,hexValue.length)
            else -> {
                throw Exception(" not tlv field ")
            }
        })

        return jsonObject
    }



    override fun toHex(value: JsonObject, fieldLength: Int): String {
        val json = JsonObject()
        json.add("ahmed", JsonArray())
        json.add("ahmed", JsonObject())
        return Gson().toJson(value)
    }

    fun getJsonArray(arrayHexValue: String,length:Int):JsonArray {
        val array = JsonArray()
        var position=0
        while (position<length){
            val tlv = getTlvFromHex(arrayHexValue,position)
            position+= tlv.endPosition
            when(tlv.field.valueType){
                TlvConfigurationField.ValueTypes.Value -> array.add(JsonObject().also {
                    it.addProperty(tlv.field.tag,getJsonValue(tlv.value,tlv.field))
                })
                TlvConfigurationField.ValueTypes.List -> array.add(getJsonArray(tlv.value,tlv.length) )
                TlvConfigurationField.ValueTypes.Map -> array.add(getJsonObject(tlv.value,tlv.length))
            }
        }

        return array
    }

    fun getJsonValue(objectHexValue: String,field: TlvConfigurationField): String? {
        return field.valueConversion?.fromHex(objectHexValue)
    }

    fun getJsonObject(objectHexValue: String,length:Int):JsonObject {
        val objectJson = JsonObject()
        var position=0
        while (position<length){
            val tlv = getTlvFromHex(objectHexValue,position)
            position+= tlv.endPosition
            when(tlv.field.valueType){
                TlvConfigurationField.ValueTypes.Value -> objectJson.addProperty(tlv.field.tag,getJsonValue(tlv.value,tlv.field))
                TlvConfigurationField.ValueTypes.List -> objectJson.add(tlv.field.tag,getJsonArray(tlv.value,tlv.length) )
                TlvConfigurationField.ValueTypes.Map -> objectJson.add(tlv.field.tag,getJsonObject(tlv.value,tlv.length))
            }
        }

        return objectJson
    }

    fun getTlvFromHex(hexString:String,positionInString: Int):Tlv{
        var position = positionInString
        val tagLength = configuration.tagLengthConverter.getLength(configuration.tagLength())
        val tag = configuration.tagLengthConverter.fromHex(hexString.substring(position,tagLength))
        val field = configuration.getConfigurationFieldWithTag(tag) ?: throw Exception("field not init in configuration")
        val lengthOfLength = field.lengthConversion.getLength(field.lengthOfLengthInHex)
        position += tagLength
        val length = field.lengthConversion.fromHex(hexString.substring(position,tagLength+lengthOfLength))
        position+= lengthOfLength
        val valueLength = length.toInt();
        val fieldLength = position+valueLength;
        val value = hexString.substring(position,fieldLength)

        return Tlv(field,valueLength,value,position,fieldLength)
    }


}