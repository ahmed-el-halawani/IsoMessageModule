package tlvIsoField

import java.text.FieldPosition

class Tlv(
    val field:TlvConfigurationField,
    val length:Int,
    val value:String,
    val endPosition:Int,
    val fieldLength:Int
){

}