package com.addontemplate.common.util

import com.cobblemon.mod.common.util.fromJson
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.*
import java.nio.file.Path

object StoreUtil
{
    fun getFile(
        savePath: Path,
        dirName: String,
        subDirName: String?
    ): File
    {
        val file: File = if (subDirName != null) {
            savePath.resolve("$dirName/$subDirName/").toFile()
        } else {
            savePath.resolve("$dirName/").toFile()
        }
        return file
    }

    /**
     * @throws [IOException] (1) from [File.createNewFile]
     * @throws [FileNotFoundException] (2) from [FileReader] constructor
     * @return - target [JsonObject] if found
     * - null if [Exception] thrown from [Gson.fromJson]
     */
    fun getJsonOrCreateFile(file: File,gson: Gson): JsonObject?
    {
        try {
            file.parentFile.mkdirs()
            file.createNewFile()
        } catch (e: IOException) {
            throw IOException(e)
        }

        val br: BufferedReader
        try {
            br = BufferedReader(FileReader(file))
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }

        try {
            val json = gson.fromJson<JsonObject>(br)
            br.close()
            return json
        } catch (e: Exception){
            return null
        }
    }

}
