package com.addontemplate.common.util

import com.addontemplate.common.config.ExampleEnum
import net.minecraft.nbt.CompoundTag
import java.util.UUID

object AddonTemplateUtil
{
    private val exampleEnumMap = createEnumMap( ExampleEnum::class.java )

    private fun <E: Enum<E>> createEnumMap(
        enumClass : Class<E>
    ): Map<String,E>
    {
        val map = mutableMapOf <String,E>()
        for (constant in enumClass.enumConstants) {
            map[formatEnum(constant.name)] = constant
        }
        return map
    }

    /** [value] .[filterNot] `{ ' ' || '_' || '-' }` .[lowercase] */
    private fun formatEnum(
        value: String
    ): String {
        return value.filterNot { it == ' ' || it == '_' || it == '-' }.lowercase()
    }

    fun CompoundTag.getNullableUUID(
        key: String,
    ): UUID? {
        return if (this.contains(key)) {
            this.getUUID(key)
        } else null
    }

    /** Compares [value] .[formatEnum] to all [ExampleEnum] .entries .[formatEnum]
     *
     * if `match found` -> returns `ExampleEnum`
     *
     * if `no match found` -> returns `null`
     */
    fun getExampleEnumOrNull( value: String ) = exampleEnumMap[ formatEnum( value = value ) ]

    // https://www.baeldung.com/kotlin/convert-camel-case-snake-case
    fun String.snakeToCamelCase(): String {
        val pattern = "_[a-z]".toRegex()
        return replace(pattern) { it.value.last().uppercase() }
    }

    fun String.snakeToKebabCase(): String {
        return this.replace('_','-')
    }

}
