package converters

interface BaseValueConverter {
    public fun toHex(): String

    public fun toBcd(): String

    public fun toBase64(): String

    public fun toAscii(): String

    public fun toBinary(): String
}