package com.juanpoveda.cocktails.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MapTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMap(value: String): HashMap<String?, String?> {
        return Gson().fromJson(value,  object : TypeToken<HashMap<String, String>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun mapToString(value: HashMap<String?, String?>): String {
        return Gson().toJson(value)
    }

}
